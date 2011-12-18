package indiedev.jfighter.actors;

/**
 * This interface defines all possible actions that an AbstractPlayableActor can perform.
 * Must be implemented by the concrete classes.
 * @author IndieDev
 *
 */
public interface IActorAction {
	public void moveLeft();
	public void moveRight();
	public void moveUp();
	public void moveDown();
	public void punch();
	public void invokeJump();
	public void invokeKick();
	public void invokeTurn();
}
