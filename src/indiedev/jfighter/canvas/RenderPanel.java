package indiedev.jfighter.canvas;

import indiedev.jfighter.Helpers.ImageHelper;
import indiedev.jfighter.Helpers.World_ImageConstants;
import indiedev.jfighter.actors.AbstractActor;
import indiedev.jfighter.actors.AbstractNPlayableActor;
import indiedev.jfighter.actors.AbstractPlayableActor;
import indiedev.jfighter.actors.TestFan;
import indiedev.jfighter.controller.KeyManager;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;



public class RenderPanel extends JPanel implements ActionListener{
	
	//BG-IMG var
	private static BufferedImage BI_bgimg=null;
		
	//Timer
	private Timer Timer_repaint;
	
	//TESTING
	TestFan fan=new TestFan();
	
	//arrlist containing all actors
	private final ArrayList<AbstractPlayableActor> actorsList=new ArrayList<AbstractPlayableActor>();
	private final ArrayList<AbstractNPlayableActor> nactorsList=new ArrayList<AbstractNPlayableActor>();
	private KeyManager keyManager=null;
	
	public RenderPanel()
	{
		System.out.println("Initializing BGPanel...");
		Image BG_IMG=null;
		try
		{
			//loading bg image using helper classes
			BG_IMG=ImageHelper.getResourceImage(World_ImageConstants.WORLDIMG_STAGE_1);
		}
		catch(Exception e)
		{
			System.out.println("Error while trying to load Background Image!! "+BG_IMG);
		}
		BI_bgimg= ImageHelper.getCompatibleBufferedImage(BG_IMG);

		//setting bounds for all actors
		AbstractActor.setX_LIMIT(BI_bgimg.getWidth());
		AbstractActor.setY_LIMIT(BI_bgimg.getHeight());
		System.out.println("Actors Bound assigned");

		setFocusable(true);
		
		//initializing Timer(Repaints every 16ms i.e:60fps)
		Timer_repaint=new Timer(16,this);
		System.out.println("RenderPanel initialized:SIZE[w,h]:"+BI_bgimg.getWidth(null)+" , "+BI_bgimg.getHeight(null));
		Timer_repaint.start();
	}
	
	//adding keymanager to this panel in order to receive the key events and process the actor
	public void addKeyManager(KeyManager t_keyManager)
	{
		keyManager=t_keyManager;
		this.addKeyListener(t_keyManager);
		System.out.println("Added the KeyManager to RenderPanel");
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2d=(Graphics2D)g;
		
		//drawing the BG-Image
		g2d.drawImage(BI_bgimg,0,0,null);

		for(int i=0;i<nactorsList.size();i++)
		{
			nactorsList.get(i).drawActor(g2d,true);
		}
		
		//drawing all actors in the array
		for(int i=0;i<actorsList.size();i++)
		{
			actorsList.get(i).drawActor(g2d,true);
		}	
		fan.draw(g2d,actorsList.get(0).getCurrentImageCoords());
	}
	
	//called automatically by "Timer" class
	public void actionPerformed(ActionEvent ae)
	{
		repaint();

		//keyboard polling
		if(keyManager!=null)
			keyManager.poll();
		
		//checks the actor bounds and updates moves it.
		for(int i=0;i<actorsList.size();i++)
		{
			actorsList.get(i).updateActorView();
		}
		
	}
	
	public void addNonPlayableActorsToScene(AbstractNPlayableActor t_nActor)
	{
		nactorsList.add(t_nActor);
		System.out.println("Added a new NActor:"+t_nActor.getActorName());
	}
	public void addActorsToScene(AbstractPlayableActor t_actor)
	{
		actorsList.add(t_actor);
		System.out.println("Added a new Actor:"+t_actor.getActorName());
		System.out.println("ActorList size:"+actorsList.size());
	}
	
	public void destroy()
	{
		Timer_repaint.stop();
		System.out.println("Released RenderPanel resources");
	}
}
