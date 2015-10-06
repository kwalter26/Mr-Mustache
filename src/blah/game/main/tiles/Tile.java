package blah.game.main.tiles;

import java.awt.Graphics;
import java.awt.Rectangle;

import blah.game.main.Game;
import blah.game.main.gfx.ImageManager;

public abstract class Tile 
{
	protected ImageManager iM;
	public int x, y;

	public static Tile LRazor = new RazorLTile(Game.getImageManager());
	public static Tile MRazor = new RazorMTile(Game.getImageManager());
	public static Tile RRazor = new RazorRTile(Game.getImageManager());
	public static Tile HRazor = new RazorHandle(Game.getImageManager());
	public static Tile Invis = new invisTile(Game.getImageManager());
	public static Tile End = new EndTile(Game.getImageManager());
	public Tile(ImageManager iM)
	{
		this.iM = iM;
	}

	public void render(Graphics g, int x, int y)
	{
		this.x = x;
		this.y = y;
	}
}
