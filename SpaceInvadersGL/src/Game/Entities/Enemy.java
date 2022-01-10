package Game.Entities;
import com.jogamp.opengl.GL2;

import Game.GLHandler;
import Game.Shapes.Color3D;
import Game.Shapes.Cube3D;

public class Enemy extends Cube3D {
	private GLHandler events;
	private float currentMaxX;
	private float currentMaxY;
	private boolean isMovingHorizontally;
	private boolean isMovingRight;
	private final static float SPEED = 0.02f;
	
	public Enemy(float x, float y, float z, float size, boolean isMovingRight, GLHandler events)
	{
		super(x, y, z, size);
		this.events = events;
		this.color = new Color3D(1, 0, 0);
		
		this.currentMaxY = y;
		
		if (isMovingRight) {
			this.currentMaxX = 2;
		} else {
			this.currentMaxX = -2;
		}
		
		this.isMovingHorizontally = true;
		this.isMovingRight = isMovingRight;
	}
	
	public void display(GL2 gl)
	{
		super.display(gl);
		
		if ( this.y <= this.currentMaxY ) {
			if ( this.x <= this.currentMaxX ) {
				// If it's moving horizontally and it's moving left, it needs to move vertically since it went over his currentMaxX
				if ( this.isMovingHorizontally && !this.isMovingRight ) {
					this.currentMaxY -= 0.5f;
					this.isMovingHorizontally = false;
				} else {
					// Else it's here to move right
					this.x += Enemy.SPEED;
				}
			} else if ( this.x >= this.currentMaxX ) {
				// If it's moving horizontally and it's moving right, it needs to move vertically since it went under his currentMaxX
				if ( this.isMovingHorizontally && this.isMovingRight ) {
					this.currentMaxY -= 0.5f;
					this.isMovingHorizontally = false;
				} else {
					// Else it's here to move left
					this.x -= Enemy.SPEED;
				}
			}
		} else if ( this.y >= this.currentMaxY ) {
			// Move vertically to the bottom
			this.y -= Enemy.SPEED;
			
			// If y went over his currentMaxY, it needs to move horizontally now
			if (this.y <= currentMaxY && !this.isMovingHorizontally ) {
				this.currentMaxX = -this.currentMaxX;
				this.isMovingHorizontally = true;
				this.isMovingRight = !this.isMovingRight;
			}
		}
		
		if (this.y <= -1.7f) {
			System.out.println("Game over !");
			this.events.endGame();
		}
	}
}
