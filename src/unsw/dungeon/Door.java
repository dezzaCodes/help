package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
/**
 * Main Door class for the game
 * @author jamespo
 * Extends Entity class
 */
public class Door extends Entity {
	
	private int doorNo;
	private DoorInterface state;
	private BooleanProperty isOpen;

	/**
	 * Constructs the door class
	 * @param x - The x coordinate of the door
	 * @param y - the y coordinate of the door
	 * @param id - the id number of the door to match with a key with the same id
	 * Door is initially closed when created
	 */
	public Door(int x, int y, int id) {
		super(x, y);
		this.doorNo = id;
		this.state = new DoorClosed();
		this.isOpen = new SimpleBooleanProperty(false);
	}
	
	/**
	 * Function that gets the door id number
	 * @return - returns the id number of this current instance of door as an int
	 */
	public int getDoorNo() {
		return doorNo;
	}
	
	/**
	 * Gets the door's state (Closed or Open)
	 * @return - returns a DoorInterface instance that is a state of open or closed
	 */
	public DoorInterface getState() {
		return state;
	}
	
	/**
	 * Method to open a door, delegates the method to it's state and then
	 * Sets its status to have the same as the state
	 */
	public void open() {
		state = state.Open();
		isOpen();
	}
	
	/**
	 * Function to return whether or not a door is open
	 * @return - Returns a a boolean whether or not the state is open or closed
	 * Also updates this instance's isOpen status
	 */
	public BooleanProperty isOpen() {
		isOpen.set(state.isOpen().get());
		return isOpen;
	}

	/**
	 * Function that implements polymorphism to check if this entity is an item
	 */
	@Override
	public boolean isItem() {
		return false;
	}

	/**
	 * Function that implements polymorphism to check if this entity can be moved through
	 */
	@Override
	public boolean isMovable() {
		return getState().isOpen().get();
	}

	/**
	 * Function that implements polymorphism to check if this entity can be bombed
	 */
	@Override
	public boolean canBeBombed() {
		return false;
	}
	
}
