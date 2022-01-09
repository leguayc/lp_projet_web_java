package Game.Shapes;
import com.jogamp.opengl.GL2;

public abstract class Shape3D 
{
	protected float x, y, z;
	protected float r, g, b;
	protected float size;
	
	public Shape3D()
	{ 
		x = 0; y = 0; z = 0;
		r = 0; g = 0; b = 0;
		size = 1;
	}
	
	public abstract void display(GL2 gl);
	
	public float getX() {
		return this.x;
	}
	
	public float getY() {
		return this.y;
	}
	
	public float getSize() {
		return this.size;
	}
}
