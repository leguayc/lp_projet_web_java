package Game.Entities;
import com.jogamp.opengl.GL2;

import Game.Shapes.Cube3D;

public class Bullet extends Cube3D {
	private float speed;
	
	public Bullet(float x, float y, float z, float size)
	{
		super(x, y, z, size);
		this.speed = 0.05f;
	}
	
	public void display(GL2 gl)
	{
		super.display(gl);
		
		this.y += this.speed;
	}
}
