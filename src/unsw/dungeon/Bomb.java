package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;

/**
 * Main Bomb class for the game
 * @author jamespo
 * Extends the Entity class
 */
public class Bomb extends Entity {

	private BombState state;
	private IntegerProperty turnsLeft;
	
	/**
	 * Constructs a Bomb entity, at the start, the bomb's state is unlit
	 * @param x
	 * @param y
	 * State refers to the bomb's state either lit or unlit
	 * turnsLeft refers to the number of time left before the bomb blows up
	 */
	public Bomb(int x, int y) {
		super(x, y);
		this.state = new BombUnlit();
		this.turnsLeft  = new SimpleIntegerProperty(4);
	}
	
	/**
	 * Function that delegates the bomb blowing up to it's state and acts different depending
	 * on what the state is.
	 */
	public void blowUp() {
		state = state.Lit();
	}
	
	/**
	 * Function that delegates its return value to its state to see if the bomb is lit
	 * @return A boolean value stating whether or not the bomb is currently lit
	 */
	public boolean isLit() {
		return state.isLit();
	}
	
	/**
	 * Updates the bomb's state's timer depending on whether or not the bomb is lit
	 * (A unlit bomb's timer will not update but a lit bomb's timer will)
	 * Then set the current timer to match the timer of the state's timer
	 */
	public void updateLitBomb() {
		state.updateLitBomb();
		turnsLeft.set(state.turnsLeft().get());
	}
	
	/**
	 * Function that returns the time left before the bomb blows up as an IntegerProperty
	 * @return returns the time left as an integer property
	 */
	public IntegerProperty turnsLeft() {
        return turnsLeft;
    }
	
	/**
	 * Returns the bomb timer of the bomb's state
	 * @return an int representing the bomb timer depending on the bomb's state
	 */
	public int LitBombTimer() {
		return state.LitBombTimer();
	}
	
	/**
	 * Function that implements polymorphism to check if this entity is an item
	 */
	@Override
	public boolean isItem() {
		return true;
	}

	/**
	 * Function that implements polymorphism to check if this entity can be moved through
	 */
	@Override
	public boolean isMovable() {
		return true;
	}

	/**
	 * Function that implements polymorphism to check if this entity can be bombed
	 */
	@Override
	public boolean canBeBombed() {
		return false;
	}
}
