package Game.Entities;
import com.jogamp.opengl.GL2;

import Game.Shapes.Color3D;
import Game.Shapes.Cube3D;

public class Player extends Cube3D {
	private final static float SPEED = 0.03f;
	
	public Player(float x, float y, float z, float size)
	{
		super(x, y, z, size);
		this.color = new Color3D(0, 0, 1);
	}
	
	public void display(GL2 gl)
	{
		super.display(gl);
	}
	
	public void moveRight() {
		this.x += Player.SPEED;
	}
	
	public void moveLeft() {
		this.x -= Player.SPEED;
	}
}
