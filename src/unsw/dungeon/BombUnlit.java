package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Unlit State for the Bomb
 * @author jamespo
 * Implements the bomb state
 */
public class BombUnlit implements BombState {
	//Timer for the bomb to know when to blow up (default timer is always at 4)
	private IntegerProperty turnsLeft = new SimpleIntegerProperty(4);
	
	/**
	 * Function that returns a BombLit state, in this case, it returns a
	 * new BombLit state for the bomb to change its state to
	 */
	@Override
	public BombState Lit() {
		return new BombLit();
	}

	/**
	 *  Function that returns whether or not this state is a lit state
	 */
	@Override
	public boolean isLit() {
		return false;
	}
	
	/**
	 * Function that updates the timer of the bomb, in this case, since the bomb is Unlit,
	 * it does nothing.
	 */
	@Override
	public void updateLitBomb() {
		return;
	}
	
	/**
	 * Function that returns the time left before the bomb blows up as an IntegerProperty
	 * In this case, it will always return the IntegerProperty 4
	 * @return returns the time left as an integer property
	 */
	@Override
	public IntegerProperty turnsLeft() {
		return turnsLeft;
	}

	/**
	 * Returns the time left before the bomb blows up as an integer, in this case
	 * it will always return the integer 4
	 */
	@Override
	public int LitBombTimer() {
		return turnsLeft.get();
	}
}
