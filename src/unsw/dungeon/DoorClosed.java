package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
/**
 * Closed door state for the door
 * @author jamespo
 * Implements doorInterface
 */
public class DoorClosed implements DoorInterface{
	private BooleanProperty isOpen = new SimpleBooleanProperty(false);

	/**
	 * Function that returns a new instance of a open door state
	 */
	@Override
	public DoorInterface Open() {
		return new DoorOpened();
	}

	/**
	 *  Function that returns a BooleanProperty stating whether or not this door is open
	 *  Will always return a boolean property of false in this case
	 */
	@Override
	public BooleanProperty isOpen() {
		return isOpen;
	}
}
