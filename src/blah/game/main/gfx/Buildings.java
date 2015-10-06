package blah.game.main.gfx;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import blah.game.main.Game;

public class Buildings 
{
	private int x, y;
	private final int width = 200;
	private final int height = 200;
	ImageLoader loader = new ImageLoader();
	private BufferedImage bldgImage;
	
	Graphics g;
	
	public Buildings(int newx, int newy)
	{
		BufferedImage[] bldgArray = {loader.load("/shop.png"), loader.load("/bldg.png"), loader.load("/bldg1.png")};
		Random ran = new Random();
		bldgImage = bldgArray[ran.nextInt(3)];
		x = newx;
		y = newy;
	}
	
	public void tick(){}
	
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
		return bldgImage;
	}	
}
