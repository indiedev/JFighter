package indiedev.jfighter.Helpers;

import java.io.File;

/*
 * Contains the audio files for each world
 *		may also contains ambient sfx 
 * 
 */
public class World_AudioConstants implements ResourceConstants{
	
	private static final String audioFileName="bg_audio.wav";
	
	//for stage-1
	public static final File WORLDIMG_STAGE_1=new File(BASEDIR_AUDIO_WORLD.getPath()+"\\stage_1"+"\\"+audioFileName);

}
