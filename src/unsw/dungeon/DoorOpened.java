package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
/**
 * Open door state for the door
 * @author jamespo
 * Implements doorInterface
 */
public class DoorOpened implements DoorInterface{
	private BooleanProperty isOpen = new SimpleBooleanProperty(true);
	
	/**
	 * Function that returns an instance of an open door state, in this case
	 * this is an instance of an open door stance
	 */
	@Override
	public DoorInterface Open() {
		return this;
	}

	/**
	 * Function that returns a booleanproperty stated whether or not the door
	 * is open in this state
	 */
	@Override
	public BooleanProperty isOpen() {
		return isOpen;
	}
}
