package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public abstract class Entity {

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private IntegerProperty x, y;
    private BooleanProperty onBoard;

    /**
     * Create an entity positioned in square (x,y)
     * @param x
     * @param y
     */
    public Entity(int x, int y) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.onBoard = new SimpleBooleanProperty(true);
    }

    /**
     * Getter for the IntegerProperty x of entity
     * @return
     */
    public IntegerProperty x() {
        return x;
    }

    /**
     * Getter for the IntegerProperty y of entity
     * @return
     */
    public IntegerProperty y() {
        return y;
    }
    
    /**
     * Returns whether or not the entity can be seen on the board
     * @return
     */
    public BooleanProperty onBoard() {
        return onBoard;
    }

    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }

	public void setX(IntegerProperty x) {
		this.x = x;
	}

	public void setY(IntegerProperty y) {
		this.y = y;
	}
	/**
	 * Function that implements polymorphism to check if this entity is an item
	 */
	public abstract boolean isItem();
	/**
	 * Function that implements polymorphism to check if this entity can be moved through
	 */
	public abstract boolean isMovable();
	/**
	 * Function that implements polymorphism to check if this entity can be bombed
	 */
	public abstract boolean canBeBombed();
	
	/**
	 * function that sets the entity so that it can't be seen on the board
	 */
	public void removeFromBoard() {
		onBoard.set(false);
	};
	/**
	 * function that sets the entity so that can be seen on the board
	 */
	public void putOnBoard() {
		onBoard.set(true);
	}
	
}
