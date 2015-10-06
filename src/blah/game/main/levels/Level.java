package blah.game.main.levels;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import blah.game.main.Game;
import blah.game.main.entities.Wax;
import blah.game.main.gfx.ImageManager;
import blah.game.main.tiles.Tile;

public class Level
{
	private int[][] tiles;
	private static int w, h;
	private int x, y;
	private final int SPEED = 1 * Game.multiplier;
	protected boolean endlvl = false;
	ImageManager iM;

	public Level(BufferedImage levelImage, int x, int y)
	{
		this.x = x;
		loadLevel(levelImage);
		w = levelImage.getWidth();
		h = levelImage.getHeight();
	}

	public void loadLevel(BufferedImage levelImage)
	{
		tiles = new int[levelImage.getWidth()][levelImage.getHeight()];

		for (int y = 0; y < levelImage.getHeight(); y++)
		{
			for (int x = 0; x < levelImage.getWidth(); x++)
			{
				Color c = new Color(levelImage.getRGB(x, y));
				String h = String.format("%02x%02x%02x", c.getRed(),
						c.getGreen(), c.getBlue());

				switch (h)
				{
				case "4cff00": // LRazor - 1
					tiles[x][y] = 1;
					break;
				case "404040": // MRazor - 2
					tiles[x][y] = 2;
					break;
				case "ff0000": // RRazor - 3
					tiles[x][y] = 3;
					break;
				case "ff6a00": // HRazor - 4
					tiles[x][y] = 4;
					break;
				case "0026ff": // Score - 5
					tiles[x][y] = 5;
					break;
				case "ff00dc": // ELevel - 6
					tiles[x][y] = 6;
					break;
				default:
					break;
				}

			}
		}
	}

	public void tick()
	{
		x -= SPEED;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public Tile getTile(int x, int y, Graphics2D g)
	{

		switch (tiles[x][y])
		{
		case 1:
			return Tile.LRazor;
		case 2:
			return Tile.MRazor;
		case 3:
			return Tile.RRazor;
		case 4:
			return Tile.HRazor;
		case 5:
			return Tile.Invis;
		case 6:
			return Tile.End;
		default:
			return Tile.Invis;
		}
	}

	public static int getH()
	{
		return h;
	}

	public static int getW()
	{
		return w;
	}

	public int[][] getTiles()
	{
		return tiles;
	}

	public void render(Graphics2D g)
	{
		for (y = 0; y < h; y++)
		{
			for (int x = 0; x < w; x++)
			{
				getTile(x, y, g).render(g,
						((int) getX() * Game.SCALE) + (x * 16 * Game.SCALE),
						y * 16 * Game.SCALE);

			}
		}
	}
}
