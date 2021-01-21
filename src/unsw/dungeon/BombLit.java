package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Lit State for the Bomb
 * @author jamespo
 * Implements the bomb state
 */
public class BombLit implements BombState {
	//Timer for the bomb to know when to blow up
	private IntegerProperty turnsLeft = new SimpleIntegerProperty(4);
	
	
	/**
	 * Function that returns a BombLit state, in this case, this 
	 * already is a BombLit state so it returns itself
	 */
	@Override
	public BombState Lit() {
		return this;
	}

	/**
	 * Function that returns whether or not this state is a lit state
	 */
	@Override
	public boolean isLit() {
		return true;
	}
	
	/**
	 * Function that returns the time left before the bomb blows up as an IntegerProperty
	 * @return returns the time left as an integer property
	 */
	@Override
	public IntegerProperty turnsLeft() {
        return turnsLeft;
    }
	
	/**
	 * Function that updates the timer of the bomb, simply subtracts the timer by 1.
	 */
	@Override
	public void updateLitBomb() {
		turnsLeft().set(turnsLeft().get()-1);
	}

	/**
	 * Returns the time left before the bomb blows up as an integer
	 */
	@Override
	public int LitBombTimer() {
		return turnsLeft().get();
	}

}
