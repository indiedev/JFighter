package indiedev.jfighter.actors;

public class Scorpion extends AbstractPlayableActor
{
	public Scorpion(int initDir)
	{
		//call the superclass constructor
		super("Scorpion",initDir);
		
		//set character specific props
		setSpeedFactors(4,4);
	}
}
