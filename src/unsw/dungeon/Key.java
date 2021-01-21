package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * Key class for the game
 * @author jamespo
 *
 */
public class Key extends Entity {

	private int keyNo;
	private BooleanProperty isUsed;
	
	/**
	 * Constructor for the key
	 * @param x - x coordinate of key
	 * @param y - y coordinate of key
	 * @param id - the id number of the key to match with a door with same id
	 */
	public Key(int x, int y, int id) {
		super(x, y);
		this.keyNo = id;
		this.isUsed = new SimpleBooleanProperty(false);
	}
	
	public int getKeyNo() {
		return keyNo;
	}
	
	/**
	 * Returns a boolean property of whether or not the key has been used for a door
	 * @return
	 */
	public BooleanProperty keyUsed() {
		return isUsed;
	}
	
	/**
	 * Function that uses the key and sets it so that the key has been used
	 */
	public void useKey() {
		isUsed.set(true);
	}

	@Override
	public boolean isItem() {
		return true;
	}

	@Override
	public boolean isMovable() {
		return true;
	}

	@Override
	public boolean canBeBombed() {
		return false;
	}
}
