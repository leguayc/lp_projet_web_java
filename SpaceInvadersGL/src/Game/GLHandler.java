package Game;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;

import Game.Entities.Bullet;
import Game.Entities.Enemy;
import Game.Entities.Player;

public class GLHandler implements GLEventListener
{
	// Used to end the game properly
	private JFrame frame;
	private FPSAnimator animator;
	// Needed for OpenGL
	private GLU glu;
	// Entities
	private ArrayList<Enemy> enemies;
	private ArrayList<Bullet> bullets;
	private Player player;
	// To know if we have to move player and if yes, in which direction
	private boolean isPlayerMovingLeft;
	private boolean isPlayerMovingRight;
	
	public GLHandler(JFrame frame, FPSAnimator animator)
	{
		this.frame = frame;
		this.animator = animator;
		this.glu = new GLU();
		this.enemies = new ArrayList<Enemy>();
		
		// Values are due to tests with the screen size and cube size
		float baseX = -2;
		float baseY = 1.8f;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 9; j++) {
				if ( i%2 == 0 ) {
					this.enemies.add(new Enemy(baseX + (0.5f * j), baseY - (0.5f * i), -5, 0.2f, true, this));
				} else {
					this.enemies.add(new Enemy(-baseX - (0.5f * j), baseY - (0.5f * i), -5, 0.2f, false, this));
				}
			}
		}
		
		this.bullets = new ArrayList<Bullet>();
		
		this.player = new Player(0, -1.8f, -5, 0.18f);
	}

	@Override
	public void init(GLAutoDrawable draw) {
		GL2 gl = draw.getGL().getGL2();
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		gl.glEnable(GL2.GL_DEPTH_TEST);
	}
	
	@Override
	public void reshape(GLAutoDrawable draw, int x, int y, int width, int height) {
		GL2 gl = draw.getGL().getGL2();
		
		// -- SCREEN
		float aspect = (float)width / height;
		gl.glViewport(0, 0, width, height);
		
		// -- CAMERA
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrtho(0.0, 500.0, 0.0, 0.0, -1.0, 1.0);
		
		// FOCAL, ASPECT, Zmin, Zmax
		this.glu.gluPerspective(45.0, aspect, 0.1, 100);
		
		// -- OBJECTS ?
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
	}

	@Override
	public void display(GLAutoDrawable draw) 
	{
		GL2 gl = draw.getGL().getGL2();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		
		// Move player
		if (this.isPlayerMovingLeft) {
			this.goLeft();
		}
		
		if (this.isPlayerMovingRight) {
			this.goRight();
		}
		
		// Display entities
		this.player.display(gl);
		
		for (Enemy e : this.enemies)
			e.display(gl);
		
		for (int i = 0; i < this.bullets.size(); i++) {
			Bullet b = this.bullets.get(i);
			if (b.getY() < 2) {
				b.display(gl);
			} else {
				this.bullets.remove(i);
				continue;
			}
			
			// Remove enemy that hits bullet
			for (int j = 0; j < this.enemies.size(); j++) {
				Enemy e = this.enemies.get(j);
				if ((b.getX() >= e.getX() - e.getSize()/2 && b.getX() <= e.getX() + e.getSize()/2) && (b.getY() >= e.getY() - e.getSize()/2 && b.getY() <= e.getY() + e.getSize()/2)) {
					this.enemies.remove(j);
				}
			}
		}
		
		if (this.enemies.size() == 0) {
			System.out.println("You win !");
			this.endGame();
		}
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
	}
	
	/**
	 * Move player to the right
	 */
	public void goRight() {
		this.player.moveRight();
	}
	
	
	/**
	 * Move player to the left
	 */
	public void goLeft() {
		this.player.moveLeft();
	}
	
	/**
	 * Shoot bullet if there is less than 2 bullets spawned
	 */
	public void shoot() {
		if (this.bullets.size() < 2)
			this.bullets.add(new Bullet(this.player.getX(), this.player.getY(), -5, 0.1f));
	}

	/**
	 * Set if player is moving left or not
	 * @param isPlayerMovingLeft boolean
	 */
	public void setPlayerMovingLeft(boolean isPlayerMovingLeft) {
		this.isPlayerMovingLeft = isPlayerMovingLeft;
	}

	/**
	 * Set if player is moving right or not
	 * @param isPlayerMovingRight boolean
	 */
	public void setPlayerMovingRight(boolean isPlayerMovingRight) {
		this.isPlayerMovingRight = isPlayerMovingRight;
	}
	
	/**
	 * End game properly
	 */
	public void endGame() {
		this.frame.dispose();
		this.animator.stop();
		System.exit(0);
	}
}
