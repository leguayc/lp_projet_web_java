package Game.Entities;
import com.jogamp.opengl.GL2;

import Game.Shapes.Color3D;
import Game.Shapes.Cube3D;

public class Enemy extends Cube3D {
	private float currentMaxX;
	private float currentMaxY;
	private boolean isMovingHorizontally;
	private boolean isMovingRight;
	private float speed;
	
	public Enemy(float x, float y, float z, float size, boolean isMovingRight)
	{
		super(x, y, z, size);
		this.speed = 0.02f;
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
					this.x += this.speed;
				}
			} else if ( this.x >= this.currentMaxX ) {
				// If it's moving horizontally and it's moving right, it needs to move vertically since it went under his currentMaxX
				if ( this.isMovingHorizontally && this.isMovingRight ) {
					this.currentMaxY -= 0.5f;
					this.isMovingHorizontally = false;
				} else {
					// Else it's here to move left
					this.x -= this.speed;
				}
			}
		} else if ( this.y >= this.currentMaxY ) {
			// Move vertically to the bottom
			this.y -= this.speed;
			
			// If y went over his currentMaxY, it needs to move horizontally now
			if (this.y <= currentMaxY && !this.isMovingHorizontally ) {
				this.currentMaxX = -this.currentMaxX;
				this.isMovingHorizontally = true;
				this.isMovingRight = !this.isMovingRight;
			}
		}
		
		// Cube is at the bottom, exit (DIRTY it needs to call animator.stop() and frame.dispose() to clean the memory usage)
		if (this.y <= -1.8f) {
			System.out.println("Game over !");
			System.exit(0);
		}
	}
}
