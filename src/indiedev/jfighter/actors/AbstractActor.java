package indiedev.jfighter.actors;

import indiedev.jfighter.Helpers.ActorConstants;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;

import javax.swing.ImageIcon;

/*
 * Default Implementation of Actors.. 
 */
public abstract class AbstractActor
{
	
	//Actor:Misc vars
	final String Actor_Name;
	
	//Actor:Co-ords vars
	private static int X_LIMIT;//World limit :X-dir
	private static int Y_LIMIT;//World limit :Y-dir
	private int xpos=100;
	private int ypos=100;
	private int dx=1;//speed factor
	private int dy=1;//speed factor
	private int currentMovementDirection=ActorConstants.actorSTOP;//USED IN move()..(currently 4 directions only)
	private int currentFacingDirection=ActorConstants.actorSTOP;//[CHANGE THIS TO BE DYNAMIC!]

	//Actor:Images vars
	private Image currentImage; 
	

	public Image getCurrentImage()
	{	
		try
		{
			return currentImage;
		}
		catch(Exception e)
		{
			System.out.println("No CurrentImage for Actor!!! :"+getActorName());
			return null;
		}
	}

	public void setCurrentImage(Image currentImage) {
		this.currentImage = currentImage;
	}

	AbstractActor(String ActorName,int actorInitDir,int xPos)
	{
		//set the ActorName
		this.Actor_Name=ActorName;
		
		//CALLED BY SUB-CLASS
		//loads all the actor's images and stores in "Img_actor" Array
		populateActorImages();
		
		//setting actor direction for 1st time
		updateCurrentActorImageDirection(actorInitDir);

		initActorPosition(xPos);
	}
	
	/*
	 * ABSTRACT METHODS
	 */
	abstract void updateCurrentActorImageDirection(int imageDirection);
	abstract void populateActorImages();	//load all the actor's images
	abstract void moveActor();//move actor(Must check bound conditions within)
	
	public void setSpeedFactors(int xspeed,int yspeed)
	{
		this.dx=xspeed;
		this.dy=yspeed;
	}
	/*
	 * STATIC METHODS
	 */
	public static void setX_LIMIT(int temp)
	{
		X_LIMIT=temp;
	}
	
	public static void setY_LIMIT(int temp)
	{
		Y_LIMIT=temp;
	}	
	
	//loads a single Image file
	public Image loadActorImage(File f)
	{
		try
		{
			if(!f.exists())
				throw new NullPointerException("Path caused: "+f.getPath());
				
			return new ImageIcon(f.getPath()).getImage();
		}catch(NullPointerException e)
		{
			System.out.println("Unable to load image for Actor{"+Actor_Name+"}!!: "+e.getMessage());
			System.exit(0);
			return null;
		}
	}
	
	public void drawActor(Graphics g,boolean showBounds) {
	
		//draws the image
		g.drawImage(getCurrentImage(),xpos,ypos,null);
		if(showBounds)
			g.drawRect(xpos,ypos,getCurrentImage().getWidth(null),getCurrentImage().getHeight(null));
	
	}

	public int getX_Pos() {
		return xpos;
	}

	public int getY_Pos() {
		return ypos;
	}
	
	//moves actor towards RIGHT
	public void incrementX_POS()
	{
		xpos+=dx;
	}
	
	//moves actor towards BOTTOM
	public void incrementY_POS()
	{
		ypos+=dx;
	}
	
	//moves actor towards LEFT
	public void decrementX_POS()
	{
		xpos-=dy;
	}
	
	//moves actor towards TOP
	public void decrementY_POS()
	{
		ypos-=dy;
	}
	

	public int getCurrentMovementDirection()
	{
		return this.currentMovementDirection;
	}
	
	public void setCurrentMovementDirection(int temp)
	{
		this.currentMovementDirection=temp;
	}
	
	public void setCurrentFacingDirection(int temp) {
		this.currentFacingDirection = temp;
	}

	public int getCurrentFacingDirection() {
		return currentFacingDirection;
	}
	
	public boolean isMinXReached()
	{
		if(xpos<1)
		{
			//System.out.println("Min xlength reached:"+xpos);
			return true;
		}
		return false;
	}
	
	public boolean isMinYReached()
	{
		if(ypos<1)
		{
			//System.out.println("Min ylength reached:"+ypos);
			return true;
		}
		return false;
	}
	
	public boolean isMaxXReached()
	{
		if((xpos+getCurrentImage().getWidth(null))>X_LIMIT)
		{
			//System.out.println("Max xlength reached:"+(xpos+imgWidth));
			return true;
		}
		return false;
	}
	
	public boolean isMaxYReached()
	{
		if((ypos+getCurrentImage().getHeight(null))>Y_LIMIT)
		{
			//System.out.println("Max ylength reached:"+(ypos+imgHeight));
			return true;
		}
		return false;
	}	
	
	public void updateActorView()
	{
		moveActor();
	}
	
	public void initActorPosition(int x_position)
	{
		xpos=x_position;
		ypos=Y_LIMIT-getCurrentImage().getHeight(null);
	}
	
	public String getActorName()
	{
		return this.Actor_Name;
	}
	
	//returns currentActor image-bounds as Rectangle (also used for collision detection)
	public Rectangle getCurrentImageCoords()
	{
/*		if(getCurrentFacingDirection()==ActorConstants.worldLeft || getCurrentFacingDirection()==ActorConstants.worldRight)//during left normal stance
		{
			//return new Rectangle(xpos,ypos,getCurrentImage().getWidth(null)-300,getCurrentImage().getHeight(null));
			return new Rectangle(0,0,1,1);
		}*/
		return new Rectangle(xpos,ypos,getCurrentImage().getWidth(null),getCurrentImage().getHeight(null));
	}
}
