package indiedev.jfighter.actors;

import indiedev.jfighter.Helpers.ActorConstants;

import java.awt.Image;
import java.io.File;


/*
 * This class defines Non-Playable actors such as,
 * 1.Projectiles.[a sub-class exists:Short-lived type] 
 * 2.Screen objects.
 */
public abstract class AbstractNPlayableActor extends AbstractActor
{
	//Actor:Images[2 directions]
	Image Img_Left,Img_Right;
//	Image Img_Up,Img_Down;
	
	AbstractNPlayableActor(String ActorName, int actorInitDir) {
		super(ActorName, actorInitDir);
		
		System.out.println("Creating NP_Actor :"+ActorName);
	}

	@Override
	public void updateCurrentActorImageDirection(int imageDirection) {
		//check if it is already in specified direction
		if(getCurrentFacingDirection()!=imageDirection)
		{
			//select the direction image
			if(imageDirection==ActorConstants.worldLeft)
			{
				//change the actor image to LEFT
				setCurrentImage(Img_Left);
			}
			else
			if(imageDirection==ActorConstants.worldRight)
			{
				//change the actor image to RIGHT
				setCurrentImage(Img_Right);
			}
		}
		
		//update the currentFacingDirection
		setCurrentFacingDirection(imageDirection);

	}

	//load all the actor's images
	/*ImageArray struture
	 * -------------------
	 * imageArray[0]->Right Facing
	 * imageArray[1]->Left Facing
	 * UNIMPLEMENTED
	 * imageArray[3]->Up stance
	 * imageArray[4]->Down stance
	 */
	@Override
	public void populateActorImages()
	{
		//initialize the Img_actor Array
		
		try
		{
			Img_Left=loadActorImage( new File(ActorConstants.BASEDIR_IMG_NP_ACTORS.getPath()+"\\"+Actor_Name+"\\"+ActorConstants.actorImageNames[0]));
			Img_Right=loadActorImage(new File(ActorConstants.BASEDIR_IMG_NP_ACTORS.getPath()+"\\"+Actor_Name+"\\"+ActorConstants.actorImageNames[1]));
			//Img_Up=loadActorImage(new File("Images\\"+Actor_Name+"\\"+ActorConstants.actorImageNames[2]));
			//Img_Down=loadActorImage(new File("Images\\"+Actor_Name+"\\"+ActorConstants.actorImageNames[3]));

			setCurrentImage(Img_Right);
			
			System.out.println(Actor_Name+":Loaded all images.Size[w,h]:"+getCurrentImage().getWidth(null)+" , "+getCurrentImage().getHeight(null));
		}
		catch(Exception e)
		{
			System.out.println(Actor_Name+":Unable to load image!!!"+e.getLocalizedMessage());
		}
	}

	@Override
	public void moveActor() {
		/*
		 * Continuously changes the positional values.
		 * CURRENTLY X-AXIS ONLY
		 */
		if(getCurrentMovementDirection()==ActorConstants.worldLeft && !isMinXReached() )//left
		{
			//decrement xpos
			decrementX_POS();
		}
		else
		if(getCurrentMovementDirection()==ActorConstants.worldRight && !isMaxXReached())//right
		{
			//increment xpos
			incrementX_POS();
		}
		
	}

}
