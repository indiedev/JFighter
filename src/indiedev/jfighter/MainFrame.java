package indiedev.jfighter;

import indiedev.jfighter.Helpers.ActorConstants;
import indiedev.jfighter.actors.Scorpion;
import indiedev.jfighter.actors.npa_crate;
import indiedev.jfighter.audio.WorldAudio;
import indiedev.jfighter.canvas.RenderPanel;
import indiedev.jfighter.controller.KeyManager;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;


public class MainFrame extends JFrame implements WindowListener
{
	//NOTE:First ,create the RenderPanel(bg image).Then create other stuffs
	
	//render panel
	RenderPanel panel_render;
	WorldAudio world_Audio;
	KeyManager keyManager;
	MainFrame()
	{
		super("JFighter MainFrame");
		//init render panel(adding the KeyManager to which the events are dispatched)
		panel_render=new RenderPanel();
		
		//creating actors
		Scorpion scorpion=new Scorpion(ActorConstants.worldRight);

		//init KeyManager(adding the actor to process)
		keyManager=new KeyManager(scorpion);
		
			//adding actors to renderpanel
			panel_render.addNonPlayableActorsToScene(new npa_crate(100));
			panel_render.addNonPlayableActorsToScene(new npa_crate(260));
			panel_render.addNonPlayableActorsToScene(new npa_crate(420));
			panel_render.addActorsToScene(scorpion);

		//adding keymanager to renderpanel
			panel_render.addKeyManager(keyManager);
			
		//init audiosystem
		world_Audio=new WorldAudio();
		if(world_Audio!=null)
			world_Audio.playBGMusic();

		//adding componenets 
		add(panel_render,BorderLayout.CENTER);
		addWindowListener(this);
		
		//setvisual
		setSize(898,490);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	public static void main(String[] args)
	{
		MainFrame frame=new MainFrame();

	}
	@Override
	public void windowActivated(WindowEvent e) {
		System.out.println("MainFrame: Window Activated");
		
		if(world_Audio!=null)
			world_Audio.resumeBGMusic();
	}
	@Override
	public void windowClosed(WindowEvent e) {
		System.out.println("**BYE**");
	}
	@Override
	public void windowClosing(WindowEvent e) {
		System.out.println("***   CLOSING ALL RESOURCES:   ***");
		
		if(world_Audio!=null)
			world_Audio.destroy();
		if(panel_render!=null)
			panel_render.destroy();
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
		System.out.println("MainFrame: Window Deactivated");
		if(world_Audio!=null)
			world_Audio.pauseBGMusic();
		
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
		System.out.println("MainFrame: Window Maximized");
		if(world_Audio!=null)
			world_Audio.resumeBGMusic();
	}
	@Override
	public void windowIconified(WindowEvent e) {
		System.out.println("MainFrame: Window Minimized");
		if(world_Audio!=null)
			world_Audio.pauseBGMusic();
	}
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
