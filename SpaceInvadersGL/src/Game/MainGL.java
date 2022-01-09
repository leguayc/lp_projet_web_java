package Game;
import java.awt.Dimension;

import javax.swing.JFrame;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

public class MainGL
{
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		GLCanvas canvas = new GLCanvas();
		GLHandler events = new GLHandler();
		canvas.addKeyListener(new UserKeyboard(events));
		canvas.setPreferredSize(new Dimension(800, 600));
		canvas.addGLEventListener(events);
		frame.getContentPane().add(canvas);
		frame.setTitle("Space Invaders OpenGL");
		frame.pack();
		frame.setVisible(true);
		FPSAnimator anim = new FPSAnimator(canvas, 60);
		anim.start();
		
	}

}

