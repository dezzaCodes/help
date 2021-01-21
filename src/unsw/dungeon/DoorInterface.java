package unsw.dungeon;

import javafx.beans.property.BooleanProperty;

/**
 * Door state interface that doorclosed and dooropened implements
 * @author jamespo
 *
 */
public interface DoorInterface {
	public DoorInterface Open();
	public BooleanProperty isOpen();
}
