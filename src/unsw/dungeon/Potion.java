package unsw.dungeon;

/**
 * Potion class for the game
 * @author jamespo
 * Extends the entity class
 */
public class Potion extends Entity {
	
	/**
	 * Constructor for the potion
	 * @param x - x coordinate of potion
	 * @param y - y coordinate of potion
	 */
	public Potion(int x, int y) {
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
