package indiedev.jfighter.actors;

import indiedev.jfighter.Helpers.ActorConstants;

import java.awt.Image;
import java.io.File;



public abstract class AbstractPlayableActor extends AbstractActor implements Runnable,IActorAction
{
	
	
	//Images[4 directions]
	Image Img_Left,Img_Right,Img_Up,Img_Down,Img_L_kick,Img_R_kick;
	
	//Thread for jump mechanism
	Thread actionThread;
	
	//jump booleans
	boolean isRunning;
	
	//secondary action
	boolean isSecondaryAction;
	
	//Current task for thread
	String currentTask=null;
	
	AbstractPlayableActor(String actorName, int actorInitDir,int xPos) {
		super(actorName, actorInitDir,xPos);
		System.out.println("Creating P_Actor  :"+actorName);

	}

	//load all the actor's images
	/*ImageArray struture
	 * -------------------
	 * imageArray[0]->Right Facing
	 * imageArray[1]->Left Facing
	 * imageArray[2]->Up stance
	 * imageArray[3]->Down stance
	 */
	@Override
	public void populateActorImages()
	{
		//initialize the Img_actor Array
		
		try
		{

			Img_Left=loadActorImage( new File(ActorConstants.BASEDIR_IMG_P_ACTORS.getPath()+"\\"+Actor_Name+"\\"+ActorConstants.actorImageNames[0]));
			Img_Right=loadActorImage(new File(ActorConstants.BASEDIR_IMG_P_ACTORS.getPath()+"\\"+Actor_Name+"\\"+ActorConstants.actorImageNames[1]));
			Img_Up=loadActorImage(   new File(ActorConstants.BASEDIR_IMG_P_ACTORS.getPath()+"\\"+Actor_Name+"\\"+ActorConstants.actorImageNames[2]));
			Img_Down=loadActorImage( new File(ActorConstants.BASEDIR_IMG_P_ACTORS.getPath()+"\\"+Actor_Name+"\\"+ActorConstants.actorImageNames[3]));
			Img_L_kick=loadActorImage( new File(ActorConstants.BASEDIR_IMG_P_ACTORS.getPath()+"\\"+Actor_Name+"\\"+ActorConstants.actorImageNames[4]));
			Img_R_kick=loadActorImage( new File(ActorConstants.BASEDIR_IMG_P_ACTORS.getPath()+"\\"+Actor_Name+"\\"+ActorConstants.actorImageNames[5]));

			System.out.println(Actor_Name+":Loaded all images.");
		}
		catch(Exception e)
		{
			System.out.println(Actor_Name+":Unable to load image!!!"+e.getLocalizedMessage());
		}
	}
	
	public void loadActorAudios()
	{
		
	}
	
	/**
	 * Changes the current image of this Actor.<br />Whenever an action is invoked ,this method is called.
	 */
	@Override
	public void updateCurrentImage(int t_newImage)
	{
			//select the requested direction image
			if(t_newImage==ActorConstants.worldLeft)
			{
				//change the actor image to LEFT
				setCurrentImage(Img_Left);
			}
			else
			if(t_newImage==ActorConstants.worldRight)
			{
				//change the actor image to RIGHT
				setCurrentImage(Img_Right);
			}
			else
			if(t_newImage==ActorConstants.worldUp)
			{
				//change the actor image to UP
				setCurrentImage(Img_Up);
			}
			else
			if(t_newImage==ActorConstants.worldDown)
			{
				//change the actor image to DOWN
				setCurrentImage(Img_Down);
			}
			else
			if(t_newImage==ActorConstants.actorWorld_LKick)
			{
				//change the actor image to DOWN
				setCurrentImage(Img_L_kick);
			}
			else
			if(t_newImage==ActorConstants.actorWorld_RKick)
			{
				//change the actor image to DOWN
				setCurrentImage(Img_R_kick);
			}
			else
			{
				throw new NullPointerException("Undefined ActorDirection!");
			}
			
			setCurrentFacingDirection(t_newImage);
	}
	
	/**
	 * Used as polling for Actor movement.
	 */
	@Override
	public void calculateMovement()
	{
		/*
		 * Continuously checks whether the actor is within the world limits and changes the positional values.
		 * This method actually moves the actor..If the world limits are reached ,the actor is not moved.
		 *STEPS:
		 *------
		 *NO NEED TO CHECK FOR FACING DIRECTION!
		 *case 1.When facing Right:
		 *    if VK_LEFT pressed:
		 *    	decr X-moves backward
		 *    if VK_RIGHT pressed:
		 *      incr X-moves forward
		 *    
		 *case 2.When facing Left:
		 *    if VK_LEFT pressed:
		 *    	decr X-moves forward
		 *    if VK_RIGHT pressed:
		 *      incr X-moves backward
		 *BOTH WAY,SAME LOGIC
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
			else
		//FOR UP-DOWN MOVEMENTS
		if(getCurrentMovementDirection()==ActorConstants.worldUp && !isMinYReached())//up
			{
				//decrement ypos
				decrementY_POS();
				updateCurrentImage(ActorConstants.worldUp);
			}
			else
		if(getCurrentMovementDirection()==ActorConstants.worldDown && !isMaxYReached())//down
			{
				//increment ypos
				incrementY_POS();
				updateCurrentImage(ActorConstants.worldDown);
			}
		//setting currentMovementDirection back to stop.
		setCurrentMovementDirection(ActorConstants.actorSTOP);

	}	
	
	//toggles the facing direction
	//also changes the direction
	public void toggleFacingDirection()
	{
		if(getCurrentFacingDirection()==ActorConstants.worldLeft)
			updateCurrentImage(ActorConstants.worldRight);
		else
			updateCurrentImage(ActorConstants.worldLeft);

	}
	public void invokeKick()
	{
		if(!isRunning)//false means this thread is free,hence use it!
		{
			actionThread=new Thread(this);
			isRunning=true;
			
			//setting current task name
			currentTask="kick";
			
			//starting the thread
			actionThread.start();
			
		}
		else	//true means this thread is already running another action(like jump)
		{
			if(!isSecondaryAction)//if no secondary action happening,start jump() in that
			{
				isSecondaryAction=true;
				currentTask="kick";
			}
		}
	}
	
	
	public void invokeJump()
	{
		if(isRunning!=true)
		{
			actionThread=new Thread(this);
			isRunning=true;
			
			//setting current task name
			currentTask="jump";
			
			//starting the thread
			actionThread.start();
			
		}
	}
	
	public void run()
	{
		//put the methods that needs controlled,slowned execution
		
		if(currentTask.equals("jump"))
		{   
			while(isRunning)
			{
				//System.out.println("Started jump() in Thread");
				jump();
			}
		}
		else
		if(currentTask.equals("kick"))
		{   
			while(isRunning)
			{
				//System.out.println("Started kick() in Thread");
				kick();
			}
		}
		
		
	}
	
	public synchronized void jump()
	{
		try
		{
			while(!this.isMinYReached())
			{
				Thread.sleep(1000/60);
				//move the actor UP
				this.decrementY_POS();
				//System.out.println("going up");
				
				//doing some additional actions
				//to kick while jumping up
				if(currentTask.equals("kick"))
				{
					System.out.println("Invoking additional action:kick()");
					kick();
				}
			}
			
			while(!this.isMaxYReached())
			{
				Thread.sleep(1000/60);
				//move the actor DOWN
				this.incrementY_POS();
				//System.out.println("going down");
				
				//doing some additional actions
				//to kick while jumping down
				if(currentTask.equals("kick"))
				{
					System.out.println("Invoking additional action:kick()");
					kick();
				}
			}
			
			//setting the tracker value to false
			isRunning=false;
			currentTask="";
		}
		catch(Exception e)
		{
			System.out.println(this.getActorName()+": Error while jumping!");
		}
	}
	
	public synchronized void kick()
	{
		try
		{
			int count=0;//simply making the animation slower
			
			//back-up the current direction
			int beforeDirection=getCurrentFacingDirection();
			//System.out.println("Backed-up direction:"+beforeDirection);
			
			if(beforeDirection==ActorConstants.worldLeft)
			{
				updateCurrentImage(ActorConstants.actorWorld_LKick);
			}
			else
			if(beforeDirection==ActorConstants.worldRight)
			{
				updateCurrentImage(ActorConstants.actorWorld_RKick);
			}

			//correcting backup direction(if error)
			
			if(beforeDirection==ActorConstants.actorWorld_LKick)
			{
				beforeDirection=ActorConstants.worldLeft;
			}
			else
			if(beforeDirection==ActorConstants.actorWorld_RKick)
			{
				beforeDirection=ActorConstants.worldRight;
			}
			else
				//if worldUp,worldDown image has been wrongly recorded..change it
			if(beforeDirection==ActorConstants.worldUp || beforeDirection==ActorConstants.worldDown )
			{
				toggleFacingDirection();
			}
			Thread.sleep(1000/5);
			
			
			//restore the previous stance/Image
			updateCurrentImage(beforeDirection);
			
			//System.out.println("Restored direction:"+getCurrentFacingDirection());

			//setting the tracker value to false
			isRunning=false;
			isSecondaryAction=false;
			currentTask="";
		}
		catch(Exception e)
		{
			System.out.println(this.getActorName()+": Error while kicking!");
		}
	}
	
	
	public void moveLeft()
	{
		setCurrentMovementDirection(ActorConstants.worldLeft);			
	}
	
	public void moveRight()
	{
		setCurrentMovementDirection(ActorConstants.worldRight);		
	}
	
	public void moveUp()
	{
		
	}
	
	public void moveDown()
	{
		setCurrentMovementDirection(ActorConstants.worldDown);		
	}
	
	public boolean isActorThreadRunning()
	{
		return isRunning;
	}
	
	public void punch()
	{
		
	}
	
	public void invokeTurn()
	{
		this.toggleFacingDirection();
	}

}
