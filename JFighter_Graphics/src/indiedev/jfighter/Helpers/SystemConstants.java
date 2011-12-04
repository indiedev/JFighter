package indiedev.jfighter.Helpers;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

/*
 * 
 * 
 * Holds all the necessary system variables for various computations
 * Only one-time initializations
 */
public class SystemConstants 
{
    public final static GraphicsDevice GFX_SCREENDEVICE = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

    //get the native system's "Default screen config"
	public final static GraphicsConfiguration GFX_DEFCONFIG=SystemConstants.GFX_SCREENDEVICE.getDefaultConfiguration();

 }
