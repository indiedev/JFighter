package indiedev.jfighter.controller;

import indiedev.jfighter.Helpers.ActorConstants;
import indiedev.jfighter.Helpers.ActorConstants.ActorActions;
import indiedev.jfighter.actors.IActorAction;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

/**
 * This class handles the KeyEvents and calls the appropriate ActorAction.
 * Also maps each Action to the specified keys
 * @author IndieDev
 *
 */
public class KeyManager implements KeyListener{
	
	//other classes
	/** An interface which references AbstractPlayableActor class **/
	IActorAction actorAction=null;
	
	/** Contains the state of entire keys  */
	private final boolean[] keyMap=new boolean[300];
	
	/** Contains the mapped keys as HashMap{ <b>String:keyName, Enum:Actions</b>}  */
	private final HashMap<String,Enum<ActorConstants.ActorActions>> MAPPED_KEYS=new HashMap<String,Enum<ActorConstants.ActorActions>>();
	
	public KeyManager(IActorAction t_actorAction)
	{
		this.actorAction=t_actorAction;
		System.out.println("[KeyManager]: Mapped a actor!");
		
		this.mapKeytoAction("X",ActorActions.KICK.toString());
		this.mapKeytoAction("Up",ActorActions.JUMP.toString());
		this.mapKeytoAction("Down",ActorActions.DOWN.toString());
		this.mapKeytoAction("Left",ActorActions.LEFT.toString());
		this.mapKeytoAction("Right",ActorActions.RIGHT.toString());
		this.mapKeytoAction("Shift",ActorActions.TURN.toString());
	}
	
	/**
	 * Checks the key[] for the state of entire keys.
	 */
	public void poll()
	{
		for(int i=0;i<keyMap.length;i++)
		{
			//if TRUE:pass the keyName to hashMap
			if(keyMap[i]==true)
				performAction(KeyEvent.getKeyText(i));//the index value(keyCode) is converted into its String
		}
	}
	/**
	 * Calls the action for the given keyName.<br />The keyName is checked whether mapped or not before doing so.
	 * 
	 * @param 
	 */
	private void performAction(String keyName)
	{
		//check in hashmap
		if(isMapped(keyName))
		{
			//Call the appr.action on the actor
			actionCaller(keyName);
			//System.out.println("key is mapped!!");

		}
		else
		{
			//do nothing
			System.out.println("[ERROR] Key: "+keyName+" not mapped!");
		}
	}
	/**
	 * Calls the appropriate interface method based on the keyName.<br />
	 * <b>NOTE:</b>keyName should contain value within the Enum:Actions defined in ActorConstants
	 * @param keyName
	 */
	private void actionCaller(String keyName)
	{

		//retrieving the corresponding action for the given keyName.And calling the action
		if(MAPPED_KEYS.get(keyName).toString()==ActorConstants.ActorActions.JUMP.toString())
		{
			actorAction.invokeJump();
		}
		else
		if(MAPPED_KEYS.get(keyName).toString()==ActorConstants.ActorActions.UP.toString())
		{
			actorAction.moveUp();
		}		
		else
		if(MAPPED_KEYS.get(keyName).toString()==ActorConstants.ActorActions.DOWN.toString())
		{
			actorAction.moveDown();
		}
		else
		if(MAPPED_KEYS.get(keyName).toString()==ActorConstants.ActorActions.LEFT.toString())
		{
			actorAction.moveLeft();
		}		
		else
		if(MAPPED_KEYS.get(keyName).toString()==ActorConstants.ActorActions.RIGHT.toString())
		{
			actorAction.moveRight();
		}
		else
		if(MAPPED_KEYS.get(keyName).toString()==ActorConstants.ActorActions.PUNCH.toString())
		{
			actorAction.punch();
		}		
		else
		if(MAPPED_KEYS.get(keyName).toString()==ActorConstants.ActorActions.KICK.toString())
		{
			actorAction.invokeKick();
		}		
		else
		if(MAPPED_KEYS.get(keyName).toString()==ActorConstants.ActorActions.TURN.toString())
		{
			actorAction.invokeTurn();
		}
		else
		{
			System.out.println("[ERROR]KeyManager: Unable to dispatch an Action! for the key="+MAPPED_KEYS.get(keyName));
//			System.out.println("HashMap list:");
//			for(String temp:MAPPED_KEYS.)
		}

	}
	
	/**
	 * Maps the given keyName to the specified Action.<br />
	 * Stored in HashMap.
	 * @param ActionName
	 * @param keyName
	 */
	private void mapKeytoAction(String keyName, String ActionName)
	{
		try
		{
			//converting ActionName to Enum type.
			ActorActions temp=ActorActions.valueOf(ActionName);
			
			//check whether the given ActionName is valid or not
			if(temp!=null)
				MAPPED_KEYS.put(keyName, temp);
			
			System.out.println("[KeyManager]: Modified the key mapping for "+temp+" to "+keyName);
		}
		catch(IllegalArgumentException e)
		{
			System.out.println("[Error]: Key is being mapped to an Unknown Action: "+ActionName);
		}
		catch(NullPointerException e)
		{
			System.out.println("[Error]: KeyManager "+e);
		}
	}
	/**
	 * Checks whether the given KeyName is mapped or not.
	 * Each KeyName is mapped to a particular Action.
	 * @param 
	 * @return boolean
	 */
	private boolean isMapped(String keyName)
	{
		return MAPPED_KEYS.containsKey(keyName);
	}
	
	@Override
	public synchronized void keyPressed(KeyEvent e) {
		keyMap[e.getKeyCode()]=true;
		e.consume();
	}

	@Override
	public synchronized void keyReleased(KeyEvent e) {
		keyMap[e.getKeyCode()]=false;
		System.out.println("FALSE:"+KeyEvent.getKeyText(e.getKeyCode()));
		e.consume();
	}
	
	@Override
	public synchronized void keyTyped(KeyEvent e) {
		
	}
	
	
}
