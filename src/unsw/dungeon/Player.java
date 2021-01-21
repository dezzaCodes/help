package unsw.dungeon;

import java.util.ArrayList;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity implements PlayerSubject {

	private ArrayList<Entity> inventory = new ArrayList<Entity>();
    private Dungeon dungeon;
    private PlayerState state;
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private boolean gameStarted = false;
    private BooleanProperty isAlive = new SimpleBooleanProperty(true);
    private BooleanProperty invin = new SimpleBooleanProperty(false);
    private IntegerProperty invenSize = new SimpleIntegerProperty(0);

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     * Player starts off the game, alive, not invincible and inventory size of 0.
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.state = new PlayerNormal();
    }
    
    /**
     * Returns booleanProperty stating if the player is alive or not
     * @return
     */
    public BooleanProperty isAliveProperty() {
    	return isAlive;
    }
    
    /**
     * Returns integer property stating the player's inventory size
     * @return
     */
    public IntegerProperty getInvenSize() {
    	return invenSize;
    }

    /**
     * Moves the player up if the move is valid
     * and updates the dungeon of all changes done during that move
     */
    public void moveUp() {
    	if (!isAlive()) return;
    	if (!validMove("up", getX(), getY() - 1))
        	return;
        
		if (getY() > 0)
            y().set(getY() - 1);
		
		pickUpItem();
		
		enemyInteraction();
		
		updateGoals();
		checkGoals();
    }

    /**
     * Moves the player down if the move is valid
     * and updates the dungeon of all changes done during that move
     */
    public void moveDown() {
    	if (!isAlive()) return;
    	if (!validMove("down", getX(), getY() + 1))
        	return;
        
        if (this.getY() < dungeon.getHeight() - 1)
            y().set(getY() + 1);
        
        pickUpItem();
        
		enemyInteraction();
		
		updateGoals();
		checkGoals();
    }

    /**
     * Moves the player left if the move is valid
     * and updates the dungeon of all changes done during that move
     */
    public void moveLeft() {
    	if (!isAlive()) return;
    	if (!validMove("left", getX() - 1, getY()))
        	return;
    	
        if (getX() > 0)
            x().set(getX() - 1);
        
        pickUpItem();
        
		enemyInteraction();
		
		updateGoals();
		checkGoals();
    }

    /**
     * Moves the player right if the move is valid
     * and updates the dungeon of all changes done during that move
     */
    public void moveRight() {
    	if (!isAlive()) return;
        if (!validMove("right", getX() + 1, getY()))
        	return;
    	
        if (getX() < dungeon.getWidth() - 1)
            x().set(getX() + 1);
        
        
        pickUpItem();
        
		enemyInteraction();
		
		updateGoals();
		checkGoals();
    }
    
    public ArrayList<Entity> getInventory() {
		return inventory;
	}

	public PlayerState getState() {
		return state;
	}
	
	/**
	 * Changes the state of the player to be invincible
	 */
	public void Invincible() {
    	state = state.Invincible();
    	invin.set(state.isInvincible().get());
    }
    
	/**
	 * Changes the state of the player to be normal
	 */
    public void Normal() {
    	state = state.Normal();
    	invin.set(state.isInvincible().get());
    }

	@Override
	public boolean isItem() {
		return false;
	}

	@Override
	public boolean isMovable() {
		return true;
	}

	/**
	 * Function in charge of picking up an item and adding it into the player's inventory
	 * and removing it from the board
	 * @return - true is the item could be picked up, false otherwise
	 */
	public boolean pickUpItem() {
        Entity item = dungeon.RemoveItem(this);
        
    	if (item != null) {
    		invenSize.set(invenSize.get()+1);
    		item.removeFromBoard();
    		if (item instanceof Potion) {
    			Invincible();
    			notifyEnemy();
    			dungeon.startInvincibilityCounter(this);
    			return true;
    		}
    		if (item instanceof Key) {
    			Entity invenKey = checkKeyInventory();
    			if (invenKey != null) {
    				invenSize.set(invenSize.get()-1);
    				inventory.add(item);
    				inventory.remove(invenKey);
    				dropItem(invenKey);
    	    		return true;
    			}
    		}
    		
    		inventory.add(item);
    		return true;
    	}
    	return false;
    }
	
	/**
	 * Drops an item from the the player's inventory onto the board
	 * @param e - the entity being dropped
	 */
	public void dropItem(Entity e) {
		if(!isAlive()) return;
		e.x().set(getX());
		e.y().set(getY());
		e.putOnBoard();
		invenSize.set(invenSize.get()-1);
		dungeon.addEntity(e);
	}
	
	/**
	 * Checks if that is a key in the player's inventory
	 * @return - the key if a key was found, null otherwise
	 */
	public Entity checkKeyInventory() {
		for (Entity item: inventory) {
			if (item instanceof Key) {
				return item;
			}
		}
		return null;
	}
	
	/**
	 * Function that checks whether or not a move that the player is trying to make is valid
	 * @param direction - the direction that the player is trying to move
	 * @param x - the new x coordinate that player is trying to move to
	 * @param y - the new y coordinate that player is trying to move to
	 * @return - true if the move is valid, false otherwise
	 */
	public boolean validMove(String direction, int x, int y) {
        if (!dungeon.isMovable(x, y)) {
        	if (tryOpenDoor(x, y)) {
        		return true;
        	}
        	if (moveBoulder(direction, x, y)) {
        		return true;
        	}
        	return false;
        }
		return true;
	}
	
	/**
	 * Updates the invincibility status of the player
	 */
	public void updateInvincibility() {
		if (playerIsInvincible()) {
        	state.setTurnsLeft();
        	if (!playerIsInvincible()) {
				Normal();
				notifyEnemy();
			}
		}
	}
	
	/**
	 * Returns whether or not the player is invincible
	 * @return
	 */
	public boolean playerIsInvincible() {
		return state.isInvincible().get();
	}
	
	/**
	 * Returns the invincibility status of the player as a BolleanProperty
	 * @return
	 */
	public BooleanProperty playerInvincibleProperty() {
		return invin;
	}
	
	/**
	 * Function in charge of what happens when the enemy and player entities collide
	 * One of them have to die
	 */
	public boolean fightEnemy() {
    	for (Entity e : dungeon.getEntities()) {
    		if (e instanceof Enemy && dungeon.sameCoordinate(e, this)) {
				if (getState() instanceof PlayerInvincible) {
					dungeon.removeEntity(e);
					enemies.remove(e);
					((Enemy) e).killEnemy();
					return true;
				}
				
				for (Entity item : getInventory()) {
					if (item instanceof Sword) {
						((Sword) item).useSword();
						if (((Sword) item).getSwings().get() <= 0) {
							invenSize.set(invenSize.get()-1);
							getInventory().remove(item);
						}
						dungeon.removeEntity(e);
						enemies.remove(e);
						((Enemy) e).killEnemy();
						return true;
					}
				}
				killPlayer();
				dungeon.getEntities().remove(this);
				return true;
    		}
    	}
    	return false;
    }
	
	/**
	 * Function for when the player tries to push a boulder
	 * @param position - the position of the the boulder is being pushed to
	 * @param x - x coordinate of the boulder
	 * @param y - y coordinate of the boulder
	 * @return - true if the boulder could be pushed in that direction, false otherwise
	 */
	public boolean moveBoulder(String position, int x, int y) {
    	for (Entity e : dungeon.getEntities()) {
    		if(e instanceof Boulder && dungeon.sameCoordinate(e, x, y)) {
    			if (position.equals("up")) {
    				return ((Boulder) e).moveUp();
    			} else if (position.equals("right")) {
    				return ((Boulder) e).moveRight();
    			} else if (position.equals("left")) {
    				return ((Boulder) e).moveLeft();
    			} else if (position.equals("down")) {
    				return ((Boulder) e).moveDown();
    			}
    		}
    	}
    	return false;
    }

	/**
	 * Function in charge of what happens when a player is trying to move through a door
	 * Opens the door only if the player has the right key
	 * @param x - x coordinate of the door
	 * @param y - y coordinate of the door
	 * @return - true if the door could be opened, false otherwise
	 */
    public boolean tryOpenDoor(int x, int y) {
    	for (Entity e : dungeon.getEntities()) {
    		if (e instanceof Door && dungeon.sameCoordinate(e, x, y)) {
				if (!((Door) e).isOpen().get()) {
    				for (Entity i : inventory) {
    					if (i instanceof Key) {
    						if (((Door) e).getDoorNo() == ((Key) i).getKeyNo()) {
    							((Key) i).useKey();
    							((Door) e).open();
    							inventory.remove(i);
    							i.removeFromBoard();
    							invenSize.set(invenSize.get()-1);
    							return true;
    						}
    					}
    				}
    			}
    		}
		}
    	return false;
    }

    /**
     * Function that notifies the enemies of the player's change of state
     */
	@Override
	public void notifyEnemy() {
		for (Enemy e : enemies) {
			e.update(this);
		}
	}

	/**
	 * Function that add a list of enemies to the player
	 */
	@Override
	public void addEnemy(Enemy e) {
		enemies.add(e);
	}

	/**
	 * Removes an enemy from the player's list of enemies
	 */
	@Override
	public void removeEnemy(Enemy e) {
		enemies.remove(e);
	}
	
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	/**
	 * Function in charge of all of the enemy's interactions with the player
	 */
	public void enemyInteraction() {
		dungeon.getEnemies(this);
		enemyMovement();
        notifyEnemy();
		fightEnemy();
	}
	
	/**
	 * Function that udpates the goals of the dungeon when the player has completed any
	 */
	public void updateGoals() {
		dungeon.getGoals().updateComplete();
		dungeon.updateGoals();
	}
	
	/**
	 * Function that checks the goals of the dungeon that the player has to complete
	 * @return - true if the goals are completed
	 */
	public boolean checkGoals() {
		boolean b = dungeon.getGoals().isGoalComplete();
		return b;
	}
	
	/**
	 * Function that drops bombs onto the board if the player has a bomb in
	 * their inventory
	 * @param x - the x coordinate the bomb is being dropped to
	 * @param y - the y coordinate the bomb is being dropped to
	 */
	public void dropBomb(int x, int y) {
		Entity invenBomb = checkBombInventory();
		if (invenBomb != null) {
			((Bomb) invenBomb).blowUp();
			inventory.remove(invenBomb);
			dropItem(invenBomb);
			dungeon.startBombCounter((Bomb) invenBomb);
		} else {
			return;
		}
	}
	
	/**
	 * Function that checks if the player has an instance of a bomb in their
	 * inventory
	 * @return - the bomb if a bomb is found, null otherwise
	 */
	public Entity checkBombInventory() {
		for (Entity item: inventory) {
			if (item instanceof Bomb) {
				return item;
			}
		}
		return null;
	}
	
	/**
	 * Function that starts the enemy movement once the player has started moving
	 */
	public void enemyMovement() {
		if (!gameStarted) {
			for (Enemy e: enemies) {
				dungeon.startEnemyCounter(this, e);
			}
			gameStarted = true;
		}
	}


	@Override
	public boolean canBeBombed() {
		if (playerIsInvincible()) {
			return false;
		}
		killPlayer();
		return true;
	}
	
	/**
	 * Function that kills the player
	 */
	public void killPlayer() {
		isAlive.set(false);
	}
	
	/**
	 * Getter for the player's alive status
	 * @return
	 */
	public boolean isAlive() {
		return isAlive.get();
	}
}
