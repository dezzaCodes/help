/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private ArrayList<Entity> entities;
    private Player player;
    private GoalComponent goals;
	public boolean multiplayer;
	public Player p1;
	public ArrayList<String> goalsList;
	public ArrayList<BooleanProperty> goalsBoolean;

	/**
	 * Constructor for the dungeon class
	 * @param width - the width of the dungeon
	 * @param height - the height of the dungeon
	 */
    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
    }
    
    public GoalComponent getGoals() {
		return goals;
	}

	public void setGoals(GoalComponent goals) {
		this.goals = goals;
	}

	public int getWidth() {
        return width;
    }

    public ArrayList<Entity> getEntities() {
		return entities;
	}

	public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    
    public Player getP1() {
        return p1;
    }

    public void setP1(Player p1) {
        this.p1 = p1;
    }

    /**
     * Adds an additional entity into the dungeon
     * @param entity - the entity being added into the dungeon
     */
    public void addEntity(Entity entity) {
        entities.add(entity);
    }
    
    /**
     * Removes an entity from the dungeon
     * @param entity - the entity being removed from the dungeon
     */
    public void removeEntity(Entity entity) {
    	entities.remove(entity);
    }

    /**
     * Function that returns true or false depending on whether or not an entity can move
     * @param x - the x coordinate of the entity
     * @param y - the y coordinate of the entity
     * @return - boolean whether or not an entity can be moved
     */
    public boolean isMovable(int x, int y) {
    	for (Entity e : entities) {
    		if (!e.isMovable() && sameCoordinate(e, x, y))
				return false;
    	}
    	return true;
    }

    /**
     * Returns whether or not the entity at coordinates x and y is an enemy
     * @param x - x coordinate of the entity
     * @param y - y coordinate of the entity
     * @return - boolean whether entity is an enemy
     */
    public boolean isEnemy(int x, int y) {
    	for (Iterator<Entity> it = getEntities().iterator(); it.hasNext(); ) {
			Entity e = it.next();
			if (sameCoordinate(e, x, y) && e instanceof Enemy) {
				return true;
			}
    	}
    	return false;
    }
    
    /**
     * Returns whether or not the entity at coordinates x and y is a player
     * @param x - x coordinate of the entity
     * @param y - y coordinate of the entity
     * @return - boolean whether entity is an player
     */
    public boolean isPlayer(int x, int y) {
    	for (Iterator<Entity> it = getEntities().iterator(); it.hasNext(); ) {
			Entity e = it.next();
			if (sameCoordinate(e, x, y) && e instanceof Player) {
				return true;
			}
    	}
    	return false;
    }
    
    /**
     * Function that returns true or false depending on whether or not an enemy can move
     * @param x - the x coordinate of the enemy
     * @param y - the y coordinate of the enemy
     * @return - boolean whether or not an enemy can be moved
     */
    public boolean isMovableEnemy(int x, int y) {
    	for (Iterator<Entity> it = getEntities().iterator(); it.hasNext(); ) {
			Entity e = it.next();
    		if (!e.isMovable() && sameCoordinate(e, x, y))
				return false;
    		if (isEnemy(x,y)) {
    			return false;
    		}
    	}
    	return true;
    }

    /**
     * Returns true or false depending if the entities have the same coordinate
     * @param e1 - entity 1
     * @param e2 - entity 2
     * @return - true if e1 and e2 have same coordinate, false otherwise
     */
    public boolean sameCoordinate(Entity e1, Entity e2) {
    	return e1.getX() == e2.getX() && e1.getY() == e2.getY();
    }
    
    /**
     * Returns true or false depending if the entity has the same coordinate
     * @param e - the entity being checked
     * @param x - the x coordinate being checked
     * @param y - the y coordinate being checked
     * @return - true if e has the coordinate x and y, false otherwise
     */
    public boolean sameCoordinate(Entity e, int x, int y) {
    	return e.getX() == x && e.getY() == y;
    }
    
    /**
     * Function that removes an item from the dungeon, when a player picks it up
     * @param p - a reference the player
     * @return - The entity that was to be removed, returns null if the entity can't be removed
     * 
     */
	public Entity RemoveItem(Player p) {
    	for (Entity e : entities) {
    		if (e instanceof Sword && sameCoordinate(e, p)) {
				for (Entity item : p.getInventory()) {
    				if (item instanceof Sword) {
    					return null;
    				}
    			}
    		} 
    		if (e instanceof Treasure && sameCoordinate(e, p)) {
    			getEntities().remove(e);
    			return e;
    		}
    		if (e.isItem() && sameCoordinate(e, p)) {
    			if (e instanceof Bomb) {
    				if (((Bomb) e).isLit()) {
    					return null;
    				}
    			}
    			getEntities().remove(e);
    			return e;
    		}
		}
    	return null;
    }
	
	/**
	 * Function that adds all the enemies into the player's list of enemies
	 * @param p - a reference to the player
	 */
	public void getEnemies(Player p) {
		for (Entity e : entities) {
			if (e instanceof Enemy) {
				if (p.getEnemies().contains(e)) {
					continue;
				} else {
					p.addEnemy((Enemy) e);
				}
			}
		}
	}
	
	/**
	 * Returns whether or not the treasure goal has been completed
	 * @return - whether or not the treasure goal has been completed
	 */
	public boolean treasureGoal() {
		for (Entity e : entities) {
			if (e instanceof Treasure)
				return false;
		}
		return true;
	}
	
	/**
	 * Returns whether or not the enemy goal has been completed
	 * @return - whether or not the enemy goal has been completed
	 */
	public boolean enemyGoal() {
		for (Entity e : entities) {
			if (e instanceof Enemy)
				return false;
		}
		return true;
	}

	/**
	 * Returns whether or not the exit goal has been completed
	 * @return - whether or not the exit goal has been completed
	 */
	public boolean exitGoal() {
		for (Entity e : entities) {
			if (e instanceof Exit) {
				for (Entity en : entities) {
					if (en instanceof Player && !sameCoordinate(en, e)) {
						return false;
					}
				}
			}		
		}
		return true;
	}

	/**
	 * Returns whether or not the plate goal has been completed
	 * @return - whether or not the plate goal has been completed
	 */
	public boolean plateGoal() {
		for (Entity e : entities) {
			if (e instanceof Plate)
				if (((Plate) e).getState() instanceof PlateInactive)
					return false;
		}
		return true;
	}
	
	/**
	 * Function that checks if the goals for this dungeon is complete
	 * @param s - string containing the goal
	 * @return - boolean returns true if the goals are completed
	 */
	public boolean checkGoals(String s) {
		if (s.equals("boulders"))
			return plateGoal();
		if (s.equals("exit"))
			return exitGoal();
		if (s.equals("enemies"))
			return enemyGoal();
		if (s.equals("treasure"))
			return treasureGoal();
		return false;
	}

	/**
	 * Function in charge of destroying the surrounding entities of the bomb blowing up
	 * @param b - the bomb that is blowing up
	 */
	public void bombBlowUp(Bomb b) {
		int x = b.getX();
		int y = b.getY();
		if (b.LitBombTimer() <= 0) {
			for (Iterator<Entity> it = getEntities().iterator(); it.hasNext(); ) {
				Entity e = it.next();
				if (sameCoordinate(e, x - 1, y)) {
					if (e.canBeBombed()) {
						it.remove();
					}
		 		}
				if (sameCoordinate(e, x + 1, y)) {
					if (e.canBeBombed()) {
						it.remove();
					}

				}
				if (sameCoordinate(e, x, y - 1)) {
					if (e.canBeBombed()) {
						it.remove();
					}
				}
				if (sameCoordinate(e, x, y + 1)) {
					if (e.canBeBombed()) {
						it.remove();
					}
				}
				if (sameCoordinate(e, x, y)) {
					if (e.canBeBombed()) {
						it.remove();
					}
				}
			}
		}
		
		entities.remove(b);
	}

	/**
	 * Timer that ticks every half a second when a bomb is lit
	 * Blows up the bomb when the timer reaches 0
	 * @param b - the bomb being lit
	 */
	public void startBombCounter(Bomb b) {
	    Timer timer = new Timer();
	    TimerTask timerTask = new TimerTask() {
	    	public void run() {
	    		b.updateLitBomb();
	    		if (b.LitBombTimer() == 0) {
	    			bombBlowUp(b);
	    		} else if (b.LitBombTimer() < 0 ) {
	    			timer.cancel();
	    		}
	    	}
	    };
	    timer.scheduleAtFixedRate(timerTask,500,500);
	}
	
	/**
	 * Timer that ticks every second when a player picks up an invincibility potion
	 * Ticks for 5 seconds
	 * @param p - the player becoming invincible
	 */
	public void startInvincibilityCounter(Player p) {
	    Timer timer = new Timer();
	    TimerTask timerTask = new TimerTask() {
	    	public void run() {
	    		p.updateInvincibility();
	    		if (p.getState().getTurnsLeft() <= 0) {
	    			timer.cancel();
	    		}
	    	}
	    };
	    timer.scheduleAtFixedRate(timerTask,1000,1000);
	}

	/**
	 * Timer that ticks every second once the player has started moving,
	 * makes the enemy make a move every half second
	 * @param p - the player entity that the enemy is trying to move towards
	 * @param e - the enemy entity that is moving
	 */
	public void startEnemyCounter(Player p, Enemy e) {
		Dungeon d = this;
		Timer timer = new Timer();
	    TimerTask timerTask = new TimerTask() {
	    	public void run() {
	    		if (!e.isAlive() || !p.isAlive()) {
	    			timer.cancel();
	    		} else {
	    			e.move(p, d);
	    			if (sameCoordinate(e, p)) {
		    			p.enemyInteraction();
		    			timer.cancel();
	    			}
	    		}
	    	}
	    };
	    timer.scheduleAtFixedRate(timerTask,500,500);
	}
	
	/**
	 * A function that returns true of false depending on whether or not there are any players
	 * still alive
	 * @return - true if there is still a player alive, false otherwise
	 */
	public boolean noPlayersAlive() {
		for(Entity e : entities) {
			if (e instanceof Player) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Function that updates the goals of the dungeon
	 */
	public void updateGoals() {
		int i = 0;
		for (String s : goalsList) {
			if (checkGoals(s)) {
				goalsBoolean.get(i).set(true);
			} else if (s.equals("exit") && !checkGoals(s)) {
				goalsBoolean.get(i).set(false);
			} else if (s.equals("boulders") && !checkGoals(s)) {
				goalsBoolean.get(i).set(false);
			}
			i++;
		}
	}
}
