package blah.game.main;

import java.awt.AlphaComposite;
import java.awt.Canvas;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import blah.game.main.entities.ClippersE;
import blah.game.main.entities.Player;
import blah.game.main.entities.Scissors;
import blah.game.main.entities.Wax;
import blah.game.main.gfx.Background;
import blah.game.main.gfx.Buildings;
import blah.game.main.gfx.ImageLoader;
import blah.game.main.gfx.ImageManager;
import blah.game.main.gfx.Render;
import blah.game.main.gfx.SpriteSheet;
import blah.game.main.levels.CollisionCheck;
import blah.game.main.levels.Level;

public class Game extends Canvas implements Runnable
{

	private static final long serialVersionUID = 1L;
	public static int WIDTH, HEIGHT, SCALE, TILESIZE;
	public static boolean running = false;
	public static boolean endGame = false;
	public static int multiplier = 4;

	public Thread gameThread;
	protected BufferedImage spritesheet;
	protected static BufferedImage background, foreground, lOc;
	protected static ImageManager iM;

	private Level l1;

	private static Player player;
	private Background backGround;

	public static ArrayList scissors, clippers, stachewax;
	public ArrayList backg, bldg;

	protected static int score, scoreInc;
	public static int waxcount;

	public Game(int WIDTH, int HEIGHT, int SCALE, int TILESIZE)
	{
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		this.SCALE = SCALE;
		this.TILESIZE = TILESIZE;
	}

	public void init()
	{
		ImageLoader loader = new ImageLoader();
		spritesheet = loader.load("/spriteSheet.png");
		background = loader.load("/bground.png");
		foreground = loader.load("/foreground.png");

		bldg = new ArrayList();
		backg = new ArrayList();
		clippers = new ArrayList();
		scissors = new ArrayList();
		stachewax = new ArrayList();

		SpriteSheet ss = new SpriteSheet(spritesheet);
		iM = new ImageManager(ss);

		player = new Player(400, 400, iM);

		this.addKeyListener(new KeyManager());

		backg.add(new Background(0, 0));
		backg.add(new Background(2460, 0));
		bldg.add(new Buildings(500, 215));
		bldg.add(new Buildings(250, 215));
		bldg.add(new Buildings(750, 215));

		BufferedImage lImage = loader.load("/testLevel.png");
		l1 = new Level(lImage, 0, 0);

		iM.clippers.setSpeed(200);
		iM.clippers.start();
		iM.Mwax.setSpeed(300);
		iM.Mwax.start();
		iM.scissor.setSpeed(200);
		iM.scissor.start();
		iM.player.setSpeed(200);
		iM.player.start();
		iM.Beard.setSpeed(500);
		iM.Beard.start();
		iM.Chairs.setSpeed(900);
		iM.Chairs.start();

		waxcount = 0;
		setWaxCount(3);
	}

	public static void waxAdd()
	{
		stachewax.add(new Wax(iM));
	}

	public synchronized void start()
	{
		if (running)
		{
			return;
		}
		running = true;
		gameThread = new Thread(this);
		gameThread.start();
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
			gameThread.join();
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	public void tick()
	{
		if (CollisionCheck.checkTileCollision(l1.getTiles(), l1.getX(), player))
		{
			endGame = true;
		}

		if (CollisionCheck.checkScissorCollision(player, scissors))
		{
			if (!player.getVulnerability())
				endGame = true;
			else
			{
				setScore(10);
				scissors.remove(0);
				player.setInvulnerable(false);
			}
		}
		if (CollisionCheck.checkClipperCollision(player, clippers))
		{
			if (!player.getVulnerability())
				endGame = true;
			else
			{

				setScore(10);
				clippers.remove(0);
				player.setInvulnerable(false);
			}
		}
		if (CollisionCheck.checkBonusCollision(player, stachewax))
			stachewax.remove(0);
		player.tick();

		Render.updateScissors(scissors);
		Render.updateClippers(clippers);
		Render.updateWax(stachewax);
		Render.updateBackground(backg);
		Render.updateBuildings(bldg);

		if (player.getStart())
			l1.tick();
	}

	int blah = 0;

	public void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null)
		{
			createBufferStrategy(2);
			return;
		}
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		
		
		Random rand = new Random();
		int ran = rand.nextInt(3);
		// RENDER HERE

		g.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);

		if (backg != null)
		{
			Render.renderBackground(g, backg);
			Render.renderBuildings(g, bldg);
		}

		g.drawImage(foreground, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		iM.Chairs.update(System.currentTimeMillis());
		g.drawImage(iM.Chairs.sprite, 0, 0, 1200, 600, null);
		iM.Mwax.update(System.currentTimeMillis());
		iM.Beard.update(System.currentTimeMillis());

		if (iM.scissor != null)
		{
			iM.scissor.update(System.currentTimeMillis());
			Render.renderScissors(g, scissors);
		}
		if (iM.clippers != null)
		{
			iM.clippers.update(System.currentTimeMillis());
			Render.renderClippers(g, clippers);
		}
		if (getScore() % 7 + ran == 0 && score != 0 && clippers.size() < 1)
			clippers.add(new ClippersE(iM));
		if (getScore() % 9 + ran == 0 && score != 0 && stachewax.size() < 1)
			waxAdd();
		if (getScore() % 5 + ran == 0 && score != 0 && scissors.size() < 1)
			scissors.add(new Scissors(iM, ran));

		l1.render(g);
		Render.renderWax(g, stachewax);
		if (iM.player != null)
		{
			iM.player.update(System.currentTimeMillis());
			player.render(g);
		}
		g.setFont(new Font("Agent Orange", Font.PLAIN, 30));
		g.drawString("Score: " + getScore(), 300, 30);
		g.drawImage(iM.Mwax.sprite, 150, 5, TILESIZE * SCALE, TILESIZE * SCALE,
				null);
		g.drawString(": " + getWaxCount(), 185, 30);

		if(endGame){
           g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.7f));
           g.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
		}
		
		// END RENDER
		g.dispose();
		bs.show();
	}

	public void run()
	{
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60D;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;

		while (running)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1)
			{
				tick();
				delta--;
		}
		render();

			if (endGame)
			{
				System.out.println("endGame true");
				render();
				GameMenu.timer.start();
				stop();
			}
		}

	}
	public static void setWaxCount(int wC)
	{
		if (waxcount < 3)
			waxcount += wC;
	}

	public static int getWaxCount()
	{
		return waxcount;
	}

	public static ImageManager getImageManager()
	{
		return iM;
	}

	public static ArrayList getScissors()
	{
		return scissors;
	}

	public static ArrayList getClippers()
	{
		return clippers;
	}

	public static Player getPlayer()
	{
		return player;
	}

	public static void updateScore()
	{
		scoreInc++;
	}

	public static int getScore()
	{
		score = (int) Math.floor(scoreInc / 4);
		return score;
	}

	public static void setScore(int s)
	{
		scoreInc += s * 20;
	}
}
