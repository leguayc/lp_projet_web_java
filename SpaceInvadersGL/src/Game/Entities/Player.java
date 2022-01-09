package Game.Entities;
import com.jogamp.opengl.GL2;

import Game.Shapes.Color3D;
import Game.Shapes.Cube3D;

public class Player extends Cube3D {
	private float speed;
	
	public Player(float x, float y, float z, float size)
	{
		super(x, y, z, size);
		this.speed = 0.03f;
		this.color = new Color3D(0, 0, 1);
	}
	
	public void display(GL2 gl)
	{
		super.display(gl);
	}
	
	public void moveRight() {
		this.x += this.speed;
	}
	
	public void moveLeft() {
		this.x -= this.speed;
	}
}
