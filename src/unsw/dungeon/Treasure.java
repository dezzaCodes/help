package unsw.dungeon;

/**
 * Treasure class for the game
 * @author jamespo
 * Extends entity class
 */
public class Treasure extends Entity {

	/**
	 * Constructor for the treasure
	 * @param x - x coordinate of the treasure
	 * @param y - y coordinate of the treasure
	 */
	public Treasure(int x, int y) {
		super(x, y);
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
