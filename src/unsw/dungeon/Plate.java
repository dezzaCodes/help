package unsw.dungeon;

/**
 * Main Plate class for the game
 * @author jamespo
 * Extends the Entity class
 */
public class Plate extends Entity {
	
	private PlateState state;
	
	/**
	 * Constructor for the plates
	 * @param x - x coordinate for plate
	 * @param y - y coordinate for plate
	 */
	public Plate(int x, int y) {
		super(x, y);
		this.state = new PlateInactive();
	}
	
	/**
	 * Sets the plate's state to change to be activated
	 */
	public void activatePlate() {
		state = state.Active();
	}
	
	/**
	 *  Sets the plate's state to change to be inactivate
	 */
	public void inactivatePlate() {
		state = state.Inactive();
	}

	@Override
	public boolean isItem() {
		return false;
	}

	@Override
	public boolean isMovable() {
		return true;
	}

	public PlateState getState() {
		return state;
	}

	@Override
	public boolean canBeBombed() {
		return false;
	}
}
