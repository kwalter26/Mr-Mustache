package blah.game.main.entities;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import blah.game.main.Game;
import blah.game.main.gfx.ImageManager;

public class Wax 
{
	private int x, y, scale = Game.TILESIZE * (Game.SCALE + 1);
	private ImageManager iM;
	public boolean lt = true, visible = true;

	private final double SPEED = 1.75* Game.multiplier;
	
	public Wax(ImageManager iM)
	{
		randX();
		y = 250;
		this.iM = iM;	
	}
	public void randX()
	{
		Random ran = new Random();
		x = ran.nextInt(100 % 40) + Game.WIDTH * Game.SCALE +  500;
	}
	
	public void tick()
	{

		if(lt)
			x -= (SPEED);
		if(x < -100)
		{
			Game.stachewax.remove(0);
			visible = true;
		}

	}
	
	public Rectangle getBounds()
	{
		return new Rectangle(x,y,scale, scale / 2);
	}
	public boolean isVisible()
	{
		return visible;
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public int getScale()
	{
		return scale;
	}
	public void setX(int otherx)
	{
		x = otherx;
	}
	public BufferedImage getSprite()
	{
		return iM.Mwax.sprite;
	}
}
