package blah.game.main.gfx;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import blah.game.main.Game;

public class Background 
{
	private int x, y;
	private final int width = 2460;
	private final int height = 600;
	ImageLoader loader = new ImageLoader();
	BufferedImage bgImage;
	
	public boolean up = false, dn = false, lt = true, rt = false;
	
	private double SPEED;
	
	Graphics g;
	
	public Background(int newx, int newy)
	{
		SPEED = 1* Game.multiplier;
		bgImage = loader.load("/bground.png");
		x = newx;
		y = newy;
	}
	
	public void tick()
	{
		
		if(up)
		{
			y -= SPEED;
		}
		if(dn)
		{
			y += SPEED;
		}
		if(lt)
		{
			x -= SPEED;
		}
		if(rt)
		{
			x += SPEED;
		}
		
		
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	public int getW()
	{
		return width;
	}
	public int getH()
	{
		return height;
	}
	
	public void setX(int otherx)
	{
		x = otherx;
	}
	public BufferedImage getImage()
	{
		return bgImage;
	}
	
	
}