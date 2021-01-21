package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
/**
 * Swords class for the game
 * @author jamespo
 * Extends the Entity class
 */
public class Sword extends Entity {

	private IntegerProperty swings;
	
	/**
	 * Constructor for the sword
	 * @param x - x coordindate of sword
	 * @param y - y coordinate of sword
	 * The sword intially starts off with 5 uses before breaking
	 */
	public Sword(int x, int y) {
		super(x, y);
		this.swings = new SimpleIntegerProperty(5);
	}
	
	public IntegerProperty getSwings() {
		return swings;
	}

	/**
	 * Decrements the number of uses for the sword
	 */
	public void useSword() {
		if (getSwings().get() != 0)
			swings.set(swings.get() - 1);
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
