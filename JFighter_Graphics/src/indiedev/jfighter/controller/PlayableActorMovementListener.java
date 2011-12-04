package indiedev.jfighter.controller;

import indiedev.jfighter.Helpers.ActorConstants;
import indiedev.jfighter.actors.AbstractPlayableActor;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;



public class PlayableActorMovementListener  implements KeyListener
{
	AbstractPlayableActor p_actor;
	public PlayableActorMovementListener()
	{		//this.p_actor=p_actor;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_RIGHT)
		{
			p_actor.setMovementDirection(ActorConstants.worldRight);			
		}
		
		if(e.getKeyCode()==KeyEvent.VK_LEFT)
		{
			p_actor.setMovementDirection(ActorConstants.worldLeft);			
		}
		
		if(e.getKeyCode()==KeyEvent.VK_UP)
		{
			if(!p_actor.isActorThreadRunning())
			{
				//p_actor.setMovementDirection(ActorConstants.worldUp);	
				p_actor.invokeJump();
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN)
		{
			p_actor.setMovementDirection(ActorConstants.worldDown);			
		}
		
		if(e.getKeyCode()==KeyEvent.VK_SHIFT)
		{
			//no movement changes here.Only direction changes
			p_actor.toggleFacingDirection();
			System.out.println("changed dir!!!");
		}
		

	}

	@Override
	public void keyReleased(KeyEvent e) {
	/*
	 *stops the movement
	 * 
	 */
		p_actor.setMovementDirection(ActorConstants.actorSTOP);
		if(e.getKeyCode()==KeyEvent.VK_X)
		{
			//invoke kick
			p_actor.invokeKick();
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
	
	public void setActorToHandle(AbstractPlayableActor actor)
	{
		this.p_actor= actor;
	}


}
