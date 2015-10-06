package blah.game.main.gfx;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import blah.game.main.Game;

public class Animator
{
	private ArrayList<BufferedImage> frames;

	public BufferedImage sprite;
	private volatile boolean running = false;
	private long beforeTime, prevTime, speed;
	private int frameAtPause, currentFrame;

	public Animator(ArrayList<BufferedImage> frames)
	{
		this.frames = frames;
	}

	public void setSpeed(long speed)
	{
		this.speed = speed;
	}

	public void update(long time)
	{
		if (running)
		{
			if (time - prevTime >= speed)// update animation
			{
				currentFrame++;
				try
				{
					sprite = frames.get(currentFrame);
				} catch (IndexOutOfBoundsException e)
				{
					currentFrame = 0;
					sprite = frames.get(currentFrame);
				}
				prevTime = time;
			}
		}
	}

	public void start()
	{
		running = true;
		prevTime = 0;
		frameAtPause = 0;
		currentFrame = 0;
	}

	public void stop()
	{
		running = false;
		prevTime = 0;
		frameAtPause = 0;
		currentFrame = 0;
	}

	public void pause()
	{
		frameAtPause = currentFrame;
		running = false;
	}

	public void resume()
	{
		currentFrame = frameAtPause;
		running = true;
	}
}
