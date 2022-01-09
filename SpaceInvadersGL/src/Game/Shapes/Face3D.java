package Game.Shapes;
import com.jogamp.opengl.GL2;

public class Face3D extends Shape3D
{

	@Override
	public void display(GL2 gl) 
	{
		gl.glBegin(GL2.GL_QUADS);
			gl.glVertex3d(-1, -1, 0);
			gl.glVertex3d(1, -1, 0);
			gl.glVertex3d(1, 1, 0);
			gl.glVertex3d(-1, 1, 0);
		gl.glEnd();
	}
	

}
