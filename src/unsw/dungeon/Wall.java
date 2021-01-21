package unsw.dungeon;

/**
 * The Wall class for the game
 * @author jamespo
 * Extends entity class
 */
public class Wall extends Entity {

	/**
	 * Constructor for the wall
	 * @param x - x coordinate of the wall
	 * @param y - y coordinate of the wall
	 */
    public Wall(int x, int y) {
        super(x, y);
    }

	@Override
	public boolean isItem() {
		return false;
	}

	@Override
	public boolean isMovable() {
		return false;
	}

	@Override
	public boolean canBeBombed() {
		return false;
	}
}
