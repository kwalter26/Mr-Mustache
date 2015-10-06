package blah.game.main.entities;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import blah.game.main.Game;
import blah.game.main.gfx.ImageManager;

public class Scissors
{
	private int x, y, scale = Game.TILESIZE * (Game.SCALE + 1);
	private ImageManager iM;
	public boolean up = true, dn = false, lt = true, rt = false, visible = true;
	
	private final double SPEED = 1.75* Game.multiplier;
	
	public Scissors(ImageManager iM, int counter)
	{
		randX();
		randY();
		this.iM = iM;	
	}
	public Scissors(ImageManager iM, int x, int y)
	{
		this.x = x;
		this.y = y;
		this.iM = iM;	
	}
	
	public void randX()
	{
		Random ran = new Random();
		x = ran.nextInt(100 % 40) + Game.WIDTH * Game.SCALE +  500;
	}
	public void randY()
	{
		Random ran = new Random();
		y = ran.nextInt(100) + Game.HEIGHT * Game.SCALE + 100;
	}
	public void tick()
	{
		
		if(up)
			y -= (SPEED + 2);
		if(dn)
			y += (SPEED + 2);
		if(lt)
			x -= (SPEED);
		if(rt)
			x += SPEED;
		
		if( y < -50) 
			y = (Game.HEIGHT * Game.SCALE) + 100;
		
		if(x < -100)
		{
			Game.scissors.remove(0);
			visible = true;
		}

	}
	
	public Rectangle getBounds()
	{
		return new Rectangle(x,y,scale, scale);
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
		return iM.scissor.sprite;
	}
}
