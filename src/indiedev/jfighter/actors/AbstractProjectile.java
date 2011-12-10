package indiedev.jfighter.actors;

/*
 * 
 * This Class defines the throwable objects by PActors.
 * Characteristics:
 * ------------------
 * 1.This is short-lived unlike its Baseclass. 
 * 2.Always travels in one direction.
 * 3.Destroyed once it reached the WORLD-LIMITS.
 */
public class AbstractProjectile extends AbstractNPlayableActor{

	AbstractProjectile(String actorName, int actorInitDir) {
		super(actorName, actorInitDir);
	}
	
	public void destroy()
	{
		
	}
	
	//starts emitting the projectile
	
//	public void emitProjectile(String)
//	{
//		set
//	}
	
}
