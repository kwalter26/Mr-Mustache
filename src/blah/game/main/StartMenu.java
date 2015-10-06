package blah.game.main;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;

import blah.game.main.gfx.Background;
import blah.game.main.gfx.Buildings;
import blah.game.main.gfx.ImageLoader;
import blah.game.main.gfx.ImageManager;
import blah.game.main.gfx.Render;
import blah.game.main.gfx.SpriteSheet;

public class StartMenu extends Game {

	public Thread startThread;
	public StartMenu(int WIDTH, int HEIGHT, int SCALE, int TILESIZE) {
		super(WIDTH, HEIGHT, SCALE, TILESIZE);

	}

	public void init() {
		ImageLoader loader = new ImageLoader();
		spritesheet = loader.load("/spriteSheet.png");
		background = loader.load("/backWall.png");
		foreground = loader.load("/menuForeground.png");

		SpriteSheet ss = new SpriteSheet(spritesheet);
		iM = new ImageManager(ss);

		this.addKeyListener(new KeyManager());

		iM.Chairs.setSpeed(800);
		iM.Chairs.start();

	}

	public synchronized void start()
	{
		if(running)
		{
			return;
		}
		running = true;
		startThread = new Thread(this);
		startThread.start();
	}
	public synchronized void stop()
	{
		if (!running)
		{
			return;
		}
		running = false;
		try
		{
			startThread.join();
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	public void tick() {
		
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(2);
			return;
		}
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		Random rand = new Random();
		int ran = rand.nextInt(3);
		// RENDER HERE

		g.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);
		g.drawImage(background, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		iM.Chairs.update(System.currentTimeMillis());
		g.drawImage(iM.Chairs.sprite, 0, 0, 1200, 600, null);
		g.drawImage(foreground, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);

		// END RENDER
		g.dispose();
		bs.show();
	}

}
