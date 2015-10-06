package blah.game.main.entities;

import java.awt.Graphics;
import java.awt.geom.Ellipse2D;

import blah.game.main.Game;
import blah.game.main.gfx.ImageManager;

public class Player
{
	private int x, y;
	private ImageManager iM;
	// directional key listeners
	public boolean up = false, dn = false, start = false, invulnerable = false;
	// set movement speed
	private final int FLYSPEED = 3 * Game.multiplier;
	private final int FALLSPEED = 4 * Game.multiplier;

	int scale = Game.SCALE;

	public Player(int x, int y, ImageManager iM)
	{
		this.x = x;
		this.y = y;
		this.iM = iM;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public int getY()
	{
		return y;
	}
	public int getX()
	{
		return x;
	}
	public void tick()
	{
		if (up)
		{
			/*
			 * if(y > 0) { y -= SPEED; if(y < 10) { up = !up; } start = true; }
			 */
			start = true;
			if (y > 0)
				y -= FLYSPEED;

		}
		if (dn)
		{
			/*
			 * long lastTime = System.nanoTime(); final double amountOfTicks =
			 * 30D; double ns = 1000000000 / amountOfTicks; double delta = 0;
			 * int oldY = y + 3; if( y < oldY) { long now = System.nanoTime();
			 * delta += (now - lastTime) / ns; lastTime = now; if (delta >= 1) {
			 * y += 1 * Game.multiplier; delta--; } } y+=6 * Game.multiplier;
			 * if(y < 0) { up = !up; dn = true; } if(y >= (Game.HEIGHT * scale)
			 * - 67) dn = !dn;
			 */
			if (y < Game.HEIGHT * Game.SCALE * 0.9)
				y += FALLSPEED;

		}
	}

	public boolean getVulnerability()
	{
		return invulnerable;
	}

	public void setInvulnerable(boolean tf)
	{
		invulnerable = tf;
	}

	public boolean getStart()
	{
		return start;
	}

	public Ellipse2D getBounds()
	{
		return new Ellipse2D.Double(x + 2, y + (6 * scale),
				(Game.TILESIZE * scale) + 8, Game.TILESIZE + 6);
	}

	// draws image based on current x and y coord adjusted by input
	// also determines the size of image / object
	public void render(Graphics g)
	{
		g.drawImage(iM.player.sprite, x, y, Game.TILESIZE * (scale + 1),
				Game.TILESIZE * (scale + 1), null);
		if (invulnerable)
			g.drawImage(iM.Beard.sprite, x, y - 9 + (Game.TILESIZE),
					Game.TILESIZE * (scale + 1), Game.TILESIZE * (scale + 1),
					null);

	}

}
