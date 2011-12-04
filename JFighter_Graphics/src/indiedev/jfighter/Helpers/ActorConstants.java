package indiedev.jfighter.Helpers;

public class ActorConstants implements ResourceConstants{

	//global vars
	public static final String[] actorImageNames={"L.png","R.png","U.png","D.png","L_KICK.png","R_KICK.png"};
	
	//movement values
	//these vars are used in move() of actor...
	public static final int worldLeft=0;
	public static final int worldRight=1;
	public static final int worldUp=2;
	public static final int worldDown=3;
	
	//direction(Facing) values
	//also,these vals are used in loading approp. directional images

	public static final int actorWorld_LKick=4;
	public static final int actorWorld_RKick=5;
	public static final int actorSTOP=99;	
	
}

