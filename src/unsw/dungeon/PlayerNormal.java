package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * Normal state for the player
 * @author jamespo
 * Implements the playerstate interface
 */
public class PlayerNormal implements PlayerState {

	private int turnsLeft;
	private BooleanProperty stillInvincible = new SimpleBooleanProperty(false);
	
	/**
	 * Constructor for the normal state
	 * initially the time left of invincibility is 0
	 */
	public PlayerNormal() {
		this.turnsLeft = 0;
	}
	
	/**
	 * Sets the player's state to be a new instance of invincible
	 */
	@Override
	public PlayerState Invincible() {
		return new PlayerInvincible();
	}

	/**
	 * Sets the player's state to be a new instance of normal
	 */
	@Override
	public PlayerState Normal() {
		return this;
	}

	/**
	 * Gets the time left of invincibility
	 */
	public int getTurnsLeft() {
		return turnsLeft;
	}

	/**
	 * Does nothing since the player is not invincible
	 */
	public void setTurnsLeft() {
		return;
	}
	
	/**
	 * Returns the player's invincibility status as a BooleanProperty
	 */ 
	public BooleanProperty isInvincible() {
		return stillInvincible;
	}
	
}
