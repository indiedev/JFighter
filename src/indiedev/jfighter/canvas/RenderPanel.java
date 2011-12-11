package indiedev.jfighter.canvas;

import indiedev.jfighter.Helpers.ImageHelper;
import indiedev.jfighter.Helpers.World_ImageConstants;
import indiedev.jfighter.actors.AbstractActor;
import indiedev.jfighter.actors.AbstractNPlayableActor;
import indiedev.jfighter.actors.AbstractPlayableActor;
import indiedev.jfighter.actors.TestFan;
import indiedev.jfighter.controller.PlayableActorMovementListener;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;



public class RenderPanel extends JPanel implements ActionListener{
	
	//BG-IMG var
	private static BufferedImage BI_bgimg=null;
		
	//Timer
	private Timer Timer_repaint;
	
	//keylistener for actors
	PlayableActorMovementListener keyL_PActorHandler;
	
	//TESTING
	TestFan fan=new TestFan();
	
	//arrlist containing all actors
	private final ArrayList<AbstractActor> actorsList=new ArrayList<AbstractActor>();
	
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

		keyL_PActorHandler=new PlayableActorMovementListener();
		addKeyListener(keyL_PActorHandler);
		setFocusable(true);
		
		//initializing Timer(Repaints every 5ms)
		Timer_repaint=new Timer(5,this);
		System.out.println("RenderPanel initialized:SIZE[w,h]:"+BI_bgimg.getWidth(null)+" , "+BI_bgimg.getHeight(null));
		System.out.println("RenderPanel:ActorArray Size:"+actorsList.size());
		Timer_repaint.start();
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2d=(Graphics2D)g;
		
		//drawing the BG-Image
		g2d.drawImage(BI_bgimg,0,0,null);
		
		//drawing all actors in the array
		for(int i=0;i<actorsList.size();i++)
		{
			actorsList.get(i).drawActor(g2d,false);
		}
		fan.draw(g2d,actorsList.get(1).getCurrentImageCoords());
	}
	
	//called automatically by "Timer" class
	public void actionPerformed(ActionEvent ae)
	{
		repaint();

		//update all actors position
		for(int i=0;i<actorsList.size();i++)
		{
			actorsList.get(i).updateActorView();
		}
	}
	
	public void addNonPlayableActorsToScene(AbstractNPlayableActor t_nActor)
	{
		actorsList.add(t_nActor);
		System.out.println("Added a new NActor:"+t_nActor.getActorName());
	}
	public void addActorsToScene(AbstractPlayableActor t_actor)
	{
		actorsList.add(t_actor);
		//add to keylistener
			keyL_PActorHandler.setActorToHandle(t_actor);
		System.out.println("Added a new Actor:"+t_actor.getActorName());
	}
	
	public void destroy()
	{
		Timer_repaint.stop();
		System.out.println("Released RenderPanel resources");
	}
}
