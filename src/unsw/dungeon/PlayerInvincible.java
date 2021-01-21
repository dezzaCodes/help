package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * Invicible state for the player
 * @author jamespo
 * Implements the playerstate interface
 */
public class PlayerInvincible implements PlayerState {

	private int turnsLeft;
	private BooleanProperty stillInvincible = new SimpleBooleanProperty(true);
	
	/**
	 * Constructor for the playerInvincible state, timer starts at 5 seconds
	 */
	public PlayerInvincible() {
		this.turnsLeft = 5;
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
		return new PlayerNormal();
	}

	/**
	 * Gets the time left of invincibility
	 */
	public int getTurnsLeft() {
		return turnsLeft;
	}

	/**
	 * Decrements the time left of invincibility
	 */
	public void setTurnsLeft() {
		turnsLeft--;
	}

	/**
	 * Returns the player's invincibility status as a BooleanProperty
	 */
	public BooleanProperty isInvincible() {
		if (turnsLeft <= 0) {
			stillInvincible.set(false);
		}
		return stillInvincible;
	}

}