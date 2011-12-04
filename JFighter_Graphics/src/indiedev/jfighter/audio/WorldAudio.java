package indiedev.jfighter.audio;

import indiedev.jfighter.Helpers.World_AudioConstants;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;



public class WorldAudio 
{
    private AudioInputStream ais ;
    private Clip clip;
    
	public WorldAudio()
	{
		System.out.println("Loading AudioSystem");
		try
		{
			//try to make it dynamic music selection(depending of stage selected)
			ais= AudioSystem.getAudioInputStream(World_AudioConstants.WORLDIMG_STAGE_1);
			clip=AudioSystem.getClip();
		}
		catch(Exception e)
		{
			System.out.println("Unable to load BGMusic!"+e);
		}
	}
	
	public void loadBGMusic(File f)
	{
		try
		{
			ais=AudioSystem.getAudioInputStream(f);
		}
		catch(Exception e)
		{
			System.out.println("Unable to load the given BGMusic! " +e);
		}
	}
	
	public void playBGMusic()
	{
		System.out.println("Starting playing Music");
		try
		{
			clip.open(ais);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.start();
		}
		catch(Exception e)
		{
			System.out.println("Clip Line is already open!!");
		}
	}
	
	public void resumeBGMusic()
	{
		try
		{
			if(!clip.isRunning())
				clip.start();
		}catch(Exception e)
		{
			System.out.println("Unable to resume audio "+e.getLocalizedMessage());
		}
	}
	
	public void pauseBGMusic()
	{
		try
		{	clip.stop();
			System.out.println("Paused the AudioSystem");
		}catch(Exception e)
		{
			System.out.println("Unable to pause audio "+e.getLocalizedMessage());
		}
	}
	
	public void destroy()
	{
		try {
			clip.close();
			ais.close();
			System.out.println("Released the AudioSystem resources");
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch(NullPointerException e)
		{
			System.out.println("Audio was never loaded!:IMMEDIATE SHUTDOWN!");
			System.exit(0);
		}
	}

}
