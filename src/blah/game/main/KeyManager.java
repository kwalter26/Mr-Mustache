package blah.game.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener 
{

	@Override
	public void keyPressed(KeyEvent e) 
	{
		if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			Game.getPlayer().up = true;
			Game.getPlayer().dn = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
//			Game.getPlayer().dn = true;
//			Game.getPlayer().up = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			if(Game.getWaxCount()> 0)
			{
				Game.getPlayer().setInvulnerable(true);
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			long lastTime = System.nanoTime();
			final double amountOfTicks = 20D;
			double ns = 1000000000 / amountOfTicks;
			double delta = 0;
			int count = 1;
			
			while(Game.getPlayer().up)
			{
				long now = System.nanoTime();
				delta += (now - lastTime) / ns;
				lastTime = now;
				int y = Game.getPlayer().getY();
				if (delta >= 1)
				{	
					Game.getPlayer().setY(y - count--);
					delta--;
				}
				if(count <= 0)
				{
					Game.getPlayer().up = false;
				}
			}
			
			Game.getPlayer().dn = true;
		//	Game.getPlayer().up = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			Game.getPlayer().dn = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			if(Game.getWaxCount()> 0)
			{
				Game.waxcount--;
			}
		//	Game.getPlayer().setInvulnerable(false);
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
		
	

}
