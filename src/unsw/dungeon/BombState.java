package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Main State for the state pattern for the bombs
 * @author jamespo
 *
 */
public interface BombState {
	public BombState Lit();
	public boolean isLit();
	public void updateLitBomb();
	public int LitBombTimer();
	public IntegerProperty turnsLeft();
}
