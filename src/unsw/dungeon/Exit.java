package unsw.dungeon;
/**
 * Exit class for the exit entity for the exit goal
 * @author jamespo
 *
 */
public class Exit extends Entity {

	/**
	 * Constructor for the exit entity are coordinates (x,y)
	 * @param x
	 * @param y
	 */
	public Exit(int x, int y) {
		super(x, y);
	}

	@Override
	public boolean isItem() {
		return false;
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
