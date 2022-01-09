package Game;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class UserKeyboard implements KeyListener
{
	
	private GLHandler events;
	
	public UserKeyboard(GLHandler events)
	{
		this.events = events;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyChar() == 'z')
			this.events.shoot();
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		if (e.getKeyChar() == 'q')
			this.events.setPlayerMovingLeft(true);
		if (e.getKeyChar() == 'd')
			this.events.setPlayerMovingRight(true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyChar() == 'q')
			this.events.setPlayerMovingLeft(false);
		if (e.getKeyChar() == 'd')
			this.events.setPlayerMovingRight(false);
	}
}