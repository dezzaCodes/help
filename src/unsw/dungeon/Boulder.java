package unsw.dungeon;
/**
 * Main Boulder class for the game
 * @author jamespo
 * Extens the Entity class
 */
public class Boulder extends Entity {

	private Dungeon dungeon;
	/**
	 * Constructs a Boulder Entity
	 * @param dungeon - A reference to the dungeon that the boulder is in
	 * @param x - The x coordinate of the boulder
	 * @param y - The y coordinate of the boulder
	 */
	public Boulder(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.dungeon = dungeon;
	}

	/**
	 * Function that moves the boulder up if it is a valid move
	 * @return - True or False whether or not the move was valid
	 */
	public boolean moveUp() {
        if (!dungeon.isMovable(getX(), getY() - 1) || dungeon.isPlayer(getX(), getY() - 1)  || dungeon.isEnemy(getX(), getY() - 1))
        	return false;
        
		if (getY() > 0)
			if (onPlate())
				deactivateSwitch();
			y().set(getY() - 1);
		
		if (onPlate())
			activateSwitch();

		return true;
    }
	
	/**
	 * Function that moves the boulder down if it is a valid move
	 * @return - True or False whether or not the move was valid
	 */
    public boolean moveDown() {
        if (!dungeon.isMovable(getX(), getY() + 1) || dungeon.isPlayer(getX(), getY() + 1) || dungeon.isEnemy(getX(), getY() + 1))
        	return false;

        if (getY() < dungeon.getHeight() - 1)
        	if (onPlate())
    			deactivateSwitch();
        	y().set(getY() + 1);
        
        if (onPlate())
			activateSwitch();

        return true;
    }

    /**
	 * Function that moves the boulder left if it is a valid move
	 * @return - True or False whether or not the move was valid
	 */
    public boolean moveLeft() {
    	if (!dungeon.isMovable(getX() - 1, getY()) || dungeon.isPlayer(getX() - 1, getY()) || dungeon.isEnemy(getX() - 1, getY()))
        	return false;
    	
        if (getX() > 0)
        	if (onPlate())
    			deactivateSwitch();
        	x().set(getX() - 1);
         
        if (onPlate())
			activateSwitch();
        
        return true;
    }

    /**
	 * Function that moves the boulder right if it is a valid move
	 * @return - True or False whether or not the move was valid
	 */
    public boolean moveRight() {
    	if (!dungeon.isMovable(getX() + 1, getY()) || dungeon.isPlayer(getX() + 1, getY())|| dungeon.isEnemy(getX() + 1, getY()))
    		return false;

        if (getX() < dungeon.getWidth() - 1)
        	if (onPlate())
    			deactivateSwitch();
        	x().set(getX() + 1);
        
        if (onPlate())
			activateSwitch();
            
        return true;
    }
    
    /**
     * Function that returns whether or not the boulder is currently on a plate
     * @return
     */
    public boolean onPlate() {
    	for (Entity e : dungeon.getEntities()) {
    		if (e instanceof Plate && dungeon.sameCoordinate(e, this))
		    	return true;
    	}
    	return false;
    }
    
    /**
     * Function that notifies the switch that it is pressed down on by a boulder
     */
    public void activateSwitch() {
    	for (Entity e : dungeon.getEntities()) {
    		if (e instanceof Plate && dungeon.sameCoordinate(e, this))
				((Plate) e).activatePlate();
    	}
    }
    
    /**
     * Function that notifies a switch that it is no longer pressed down by a boulder
     */
    public void deactivateSwitch() {
    	for (Entity e : dungeon.getEntities()) {
    		if (e instanceof Plate && dungeon.sameCoordinate(e, this))
				((Plate) e).inactivatePlate();
    	}
    }

    /**
	 * Function that implements polymorphism to check if this entity is an item
	 */
	@Override
	public boolean isItem() {
		return false;
	}

	/**
	 * Function that implements polymorphism to check if this entity can be moved through
	 */
	@Override
	public boolean isMovable() {
		return false;
	}

	/**
	 * Function that implements polymorphism to check if this entity can be bombed
	 */
	@Override
	public boolean canBeBombed() {
		removeFromBoard();
		return true;
	}
}
