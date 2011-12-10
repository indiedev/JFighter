package indiedev.jfighter.Helpers;

import java.io.File;

/*
 * Contains path to entire resources
 *  PARENT DIR: \\res
	BASEDIR_AUDIOS:{PARENT DIR}\\AUDIO
	BASEDIR_IMAGES:{PARENT DIR}\\IMAGES
		BASEDIR_IMG_P_ACTORS:{BASEDIR_IMAGES}\\actors\\p_actors
		BASEDIR_IMG_NP_ACTORS:{BASEDIR_IMAGES}\\actors\\np_actors
		BASEDIR_IMG_WORLD:{BASEDIR_IMAGES}\\world
 * 
 * 
 * 
 */
public interface ResourceConstants {
	
	File PARENTDIR=new File("res");
	
	File BASEDIR_AUDIOS=new File(PARENTDIR+"\\audio");
	File BASEDIR_IMAGES=new File(PARENTDIR+"\\images");
	
	//child images
	File BASEDIR_IMG_P_ACTORS=new File(BASEDIR_IMAGES+"\\actors\\p_actors");
	File BASEDIR_IMG_NP_ACTORS=new File(BASEDIR_IMAGES+"\\actors\\np_actors");
	File BASEDIR_IMG_WORLD=new File(BASEDIR_IMAGES+"\\world");
	
	//child audio
	File BASEDIR_AUDIO_WORLD=new File(BASEDIR_AUDIOS+"\\world");

}
