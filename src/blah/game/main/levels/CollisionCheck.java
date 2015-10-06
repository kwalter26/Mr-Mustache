package blah.game.main.levels;

import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import blah.game.main.Game;
import blah.game.main.entities.ClippersE;
import blah.game.main.entities.Player;
import blah.game.main.entities.Scissors;
import blah.game.main.entities.Wax;

public class CollisionCheck 
{
	public static boolean clip = false, scis = false, levelEnd = false;
	public static boolean checkTileCollision(int[][] tiles, double dX, Player player)
	{
		boolean result = false, score = false;
		
		Ellipse2D pRec = player.getBounds();
		Rectangle2D lvl = new Rectangle(0, 0, 0, Game.HEIGHT * Game.SCALE);
		int count = 0;
		
		int oldy = player.getY();
		for (int y = 0; y < 20; y++) 
		{
			for (int x = 0; x < 800; x++) 
			{
				Rectangle tilesRec = new Rectangle(((int)dX * Game.SCALE) + (x * 16 * Game.SCALE), y * 16 * Game.SCALE,
						Game.TILESIZE*Game.SCALE, Game.TILESIZE*Game.SCALE);
				switch(tiles[x][y]){
				case 1:
				case 2:
				case 3:
				case 4:
					if(dX < player.getX())
					{
						if(pRec.intersects(tilesRec))
							result = true;
						break;
					}
					break;
				case 5:
					
					tilesRec = new Rectangle(((int)dX * Game.SCALE) + (x * 16 * Game.SCALE), y * 16 * Game.SCALE,
							(int) (dX + 1)-(int)dX, Game.TILESIZE*Game.SCALE);
					if(pRec.intersects(tilesRec) && score == false)
					{
						score = true;
					}
					if(score)
					{
						count++;	
					}
					if(count == 1)
					{
						Game.updateScore();	
						Game.getPlayer().setInvulnerable(false);
					}
					if(!pRec.intersects(tilesRec))
						tilesRec = new Rectangle(((int)dX * Game.SCALE) + (x * 16 * Game.SCALE), y * 16 * Game.SCALE,
								Game.TILESIZE*Game.SCALE, Game.TILESIZE*Game.SCALE);
					break;
				case 6:
					if(pRec.intersects(tilesRec))
						levelEnd = true;
				default:
					break;
				}
			}	
		}
		return result;
	}
	
	public static boolean EndLevel()
	{
		return levelEnd;
	}
	public static boolean checkClipperCollision(Player player, ArrayList clippers)
	{
		Ellipse2D pRec = player.getBounds();
		
		if(clippers.size() > 0)
		{
			for (int i = 0; i < clippers.size(); i++) 
			{
				ClippersE clrs = (ClippersE)clippers.get(i);
				Rectangle cRec = clrs.getBounds();
				if(pRec.intersects(cRec))
					return true;
			}
		}
		return false;
	}
	public static boolean checkScissorCollision(Player player, ArrayList scissors)
	{
		
		
		Ellipse2D pRec = player.getBounds();
		
		if(scissors.size() > 0)
		{
			for (int i = 0; i < scissors.size(); i++) 
			{
				Scissors scrs = (Scissors)scissors.get(i);
				Rectangle sRec = scrs.getBounds();
				if(pRec.intersects(sRec))
					return true;
			}
		}
		return false;	
	}
	
	public static boolean checkBonusCollision(Player player, ArrayList wax)
	{
		Ellipse2D pRec = player.getBounds();
		if(wax.size() > 0)
		{
			for (int i = 0; i < wax.size(); i++) 
			{
				Wax w = (Wax)wax.get(i);
				Rectangle wRec = w.getBounds();
				if(pRec.intersects(wRec))
				{
					Game.setWaxCount(1);
					return true;
				}
			}
		}
		
		return false;
	}
	
	
}
