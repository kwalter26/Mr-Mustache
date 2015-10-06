package blah.game.main.tiles;

import java.awt.Graphics;

import blah.game.main.Game;
import blah.game.main.gfx.ImageManager;

public class invisTile extends Tile
{
	public invisTile(ImageManager iM) 
	{
		super(iM);
	}

	
	public void render(Graphics g, int x, int y) 
	{
		g.drawImage(iM.invis, x, y, Game.TILESIZE*Game.SCALE, Game.TILESIZE*Game.SCALE, null);
		
	}
}
