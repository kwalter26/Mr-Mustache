package blah.game.main.gfx;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import blah.game.main.Game;

public class ImageManager 
{
	public BufferedImage grass, razorL, razorM, razorR, rHandle, invis, bldg1, bldg2, bldg3;
	ImageLoader loader = new ImageLoader();
	
	
	public ArrayList<BufferedImage> plyr = new ArrayList();
	public Animator player = new Animator(plyr);

	public ArrayList<BufferedImage> sciss = new ArrayList();
	public Animator scissor = new Animator(sciss);
	
	public ArrayList<BufferedImage> clprs = new ArrayList();
	public Animator clippers = new Animator(clprs);
	
	public ArrayList<BufferedImage> wax = new ArrayList();
	public Animator Mwax = new Animator(wax);
	
	public ArrayList<BufferedImage> LoC = new ArrayList();
	public Animator Chairs = new Animator(LoC);
	
	public ArrayList<BufferedImage> iBeard = new ArrayList();
	public Animator Beard = new Animator(iBeard);
	
	public int w = 16, h = 16;
	
	public ImageManager(SpriteSheet ss)
	{
		//Creates buffered image objects based off sprite sheet coordinates
		plyr.add(ss.crop(0, 0, w, h));
		plyr.add(ss.crop(1, 0, w, h));
		clprs.add(ss.crop(2, 0, w, h));
		clprs.add(ss.crop(3, 0, w, h));
		sciss.add(ss.crop(6, 0, w, h));
		sciss.add(ss.crop(7, 0, w, h));
		razorL = ss.crop(2, 1, w, h);
		razorM = ss.crop(3, 1, w, h);
		razorR = ss.crop(4, 1, w, h);
		invis = ss.crop(2, 2, w, h);
		rHandle = ss.crop(5, 1, w, h);
		wax.add(ss.crop(0, 2, w, h));
		wax.add(ss.crop(1, 2, w, h));
		iBeard.add(ss.crop(4, 2, w, h));
		iBeard.add(ss.crop(3, 2, w, h));
		LoC.add(loader.load("/NewlineOchairs.png"));
		LoC.add(loader.load("/NewlineOchairs2.png"));
		
	}
	
}
