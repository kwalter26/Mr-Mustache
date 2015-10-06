package blah.game.main.gfx;

import java.awt.Graphics2D;
import java.util.ArrayList;

import blah.game.main.Game;
import blah.game.main.entities.ClippersE;
import blah.game.main.entities.Scissors;
import blah.game.main.entities.Wax;

public class Render 
{

	public static void renderBuildings(Graphics2D g, ArrayList bldgList)
	{
		for (int i = 0; i < bldgList.size(); i++) 
		{
			Buildings bg = (Buildings) bldgList.get(i);
			g.drawImage(bg.getImage() , bg.getX(), bg.getY(), bg.getW(),
					bg.getH(), null);
		}
	}
	public static void updateBuildings(ArrayList bldgList)
	{
		for (int i = 0; i < bldgList.size(); i++) 
		{
			Buildings bg = (Buildings) bldgList.get(i);
			bg.tick();
			if(bg.getX() == - bg.getW())
				bg.setX(Game.WIDTH*2);
		}
	}
	public static void renderBackground(Graphics2D g, ArrayList bgList)
	{
		for (int i = 0; i < bgList.size(); i++) 
		{
			Background bg = (Background) bgList.get(i);
			g.drawImage(bg.getImage() , bg.getX(), bg.getY(), bg.getW(),
					bg.getH(), null);
		}
	}
	
	public static void updateBackground(ArrayList bgList)
	{
		for (int i = 0; i < bgList.size(); i++) 
		{
			Background bg = (Background) bgList.get(i);
			bg.tick();
			if(bg.getX() == - bg.getW())
				bg.setX(bg.getW());
		}
	}
	
	public static void renderWax(Graphics2D g, ArrayList waxList)
	{
		for(int i = 0; i < waxList.size(); i++)
		{
			Wax w = (Wax) waxList.get(i);
			g.drawImage(w.getSprite(), w.getX(), w.getY(), w.getScale(),
					w.getScale(), null);
		}
  	}
	public static void updateWax(ArrayList waxList)
	{
		for(int i = 0; i < waxList.size(); i++)
		{
			Wax w = (Wax) waxList.get(i);
			if(w.isVisible())
				w.tick();
		}
	}
	public static void renderScissors(Graphics2D g, ArrayList scisList)
	{
		for (int i = 0; i < scisList.size(); i++) 
		{
			Scissors scis = (Scissors) scisList.get(i);
			g.drawImage(scis.getSprite(), scis.getX(), scis.getY(), scis.getScale(),
					scis.getScale(), null);
		}
	}
	public static void updateScissors(ArrayList scisList)
	{
		for(int i = 0; i < scisList.size(); i++)
		{
			Scissors scis = (Scissors) scisList.get(i);
			if(scis.isVisible())
				scis.tick();
		}
	}
	public static void renderClippers(Graphics2D g, ArrayList clipList)
	{
		for (int i = 0; i < clipList.size(); i++) 
		{
			ClippersE clips = (ClippersE) clipList.get(i);
			g.drawImage(clips.getSprite(), clips.getX(), clips.getY(), clips.getScale(),
					clips.getScale(), null);
		}
	}
	public static void updateClippers(ArrayList clipList)
	{
		for(int i = 0; i < clipList.size(); i++)
		{
			ClippersE clips = (ClippersE) clipList.get(i);
			if(clips.isVisible())
				clips.tick();
		}
	}

}
