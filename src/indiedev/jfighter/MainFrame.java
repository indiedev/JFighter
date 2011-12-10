package indiedev.jfighter;

import indiedev.jfighter.Helpers.ActorConstants;
import indiedev.jfighter.actors.AbstractPlayableActor;
import indiedev.jfighter.actors.Scorpion;
import indiedev.jfighter.audio.WorldAudio;
import indiedev.jfighter.canvas.RenderPanel;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;


public class MainFrame extends JFrame implements WindowListener
{
	
	//render panel
	RenderPanel panel_render;
	WorldAudio world_Audio;
	
	MainFrame()
	{
		super("JFighter");
		
		//init render panel
		panel_render=new RenderPanel();

			//init actors
			AbstractPlayableActor scorpion=new Scorpion(ActorConstants.worldRight);
	
			//adding actors to renderpanel
			panel_render.addActorsToScene(scorpion);

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
