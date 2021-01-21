package unsw.dungeon;

import javafx.beans.property.BooleanProperty;

/**
 * Interface for the player state
 * @author jamespo
 *
 */
public interface PlayerState {
	public PlayerState Invincible();
	public BooleanProperty isInvincible();
	public PlayerState Normal();
	public int getTurnsLeft();
	public void setTurnsLeft();
}
