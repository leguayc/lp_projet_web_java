package Game;
import java.util.ArrayList;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;

import Game.Entities.Bullet;
import Game.Entities.Enemy;
import Game.Entities.Player;

public class GLHandler implements GLEventListener
{
	
	private GLU glu;
	private ArrayList<Enemy> enemies;
	private ArrayList<Bullet> bullets;
	private Player player;
	private boolean isPlayerMovingLeft;
	private boolean isPlayerMovingRight;
	
	public GLHandler()
	{
		this.glu = new GLU();
		this.enemies = new ArrayList<Enemy>();
		
		// Values are due to tests with the screen size and cube size
		float baseX = -2;
		float baseY = 1.8f;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 9; j++) {
				if ( i%2 == 0 ) {
					this.enemies.add(new Enemy(baseX + (0.5f * j), baseY - (0.5f * i), -5, 0.2f, true));
				} else {
					this.enemies.add(new Enemy(-baseX - (0.5f * j), baseY - (0.5f * i), -5, 0.2f, false));
				}
			}
		}
		
		this.bullets = new ArrayList<Bullet>();
		//qthis.bullets.add(new Bullet(0, 0.2f, -5, 0.1f));
		
		this.player = new Player(0, -1.8f, -5, 0.18f);
	}

	@Override
	public void init(GLAutoDrawable draw) {
		GL2 gl = draw.getGL().getGL2();
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		// TODO Auto-generated method stub
		//gl.glClearDepth(0.0f);
		gl.glEnable(GL2.GL_DEPTH_TEST);
		
	}
	
	@Override
	public void reshape(GLAutoDrawable draw, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		// DESSIN ???
		GL2 gl = draw.getGL().getGL2();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		
		if (this.isPlayerMovingLeft) {
			this.goLeft();
		}
		
		if (this.isPlayerMovingRight) {
			this.goRight();
		}
		
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
			
			for (int j = 0; j < this.enemies.size(); j++) {
				Enemy e = this.enemies.get(j);
				if ((b.getX() >= e.getX() - e.getSize()/2 && b.getX() <= e.getX() + e.getSize()/2) && (b.getY() >= e.getY() - e.getSize()/2 && b.getY() <= e.getY() + e.getSize()/2)) {
					this.enemies.remove(j);
				}
			}
		}
		
		// No enemy, exit (DIRTY it needs to call animator.stop() and frame.dispose() to clean the memory usage)
		if (this.enemies.size() == 0) {
			System.out.println("You win !");
			System.exit(0);
		}
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
	}
	
	public void goRight() {
		this.player.moveRight();
	}
	
	public void goLeft() {
		this.player.moveLeft();
	}
	
	public void shoot() {
		if (this.bullets.size() < 2)
			this.bullets.add(new Bullet(this.player.getX(), this.player.getY(), -5, 0.1f));
	}

	public void setPlayerMovingLeft(boolean isPlayerMovingLeft) {
		this.isPlayerMovingLeft = isPlayerMovingLeft;
	}

	public void setPlayerMovingRight(boolean isPlayerMovingRight) {
		this.isPlayerMovingRight = isPlayerMovingRight;
	}
}
