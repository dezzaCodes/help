package unsw.dungeon;

import java.lang.Math;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * Main Class for the new hound enemy
 * @author jamespo
 * Extends enemy class and implements the enemyobserver interface
 */
public class EnemyHound extends Enemy implements EnemyObserver {
	
	private EnemyStrategy state;
	private Dungeon dungeon;
	private boolean isAlive;
	private BooleanProperty isAwake;
	
	/**
	 * Constructor for the hound enemy
	 * @param dungeon - the dungeon that the hound is in 
	 * @param x - the x coordinate of the hound
	 * @param y - the y coordinate of the hound
	 */
	public EnemyHound(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
		this.state = new EnemyAttack();
		this.dungeon = dungeon;
		this.isAlive = true;
		this.isAwake = new SimpleBooleanProperty(false);
	}
	
	public EnemyStrategy getState() {
		return state;
	}
	
	/**
	 * Function that changes the state of the hound to attack the player
	 */
	public void attack() {
		state = getState().Attack();
	}
	
	/**
	 * Function that changes the state of the hound to run away from the player
	 */
	public void run() {
		state = getState().Defend();
	}
	
	/**
	 * Function that delegates movement to the hound's state once it has woken up
	 */
	public void move(Player p, Dungeon d) {
		if (isAwake.get()) {
			state.move(this, p, d);
		}
	}
	/**
	 * Getter for the checking if the hound is awake
	 * @return - booleanProperty saying if the hound has awoken
	 */
	public BooleanProperty isAwake() {
		return isAwake;
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
		return true;
	}

	/**
	 * Function that update's the hound's movement depending or whether or not it has awoken and
	 * whether or not the player is invincible
	 */
	@Override
	public void update(Player p) {
		if (!isAwake.get()) {
			if (playerInRange(p) == true) {
				isAwake.set(true);
			} else {
				return;
			}
		}
		if (p.getState() instanceof PlayerInvincible) {
			run();
		} else {
			attack();
		}
	}
	
	/**
	 * Function that checks whether or not the player is close enough to the hound
	 * to wake it up
	 * @param p - the player
	 * @return - true if the player is close enough to wake up the hound, false otherwise
	 */
	public boolean playerInRange(Player p) {
		if (Math.abs(p.getX() - this.getX()) <= 4 && Math.abs(p.getY() - this.getY()) <= 4) {
			return true;
		}
		return false;
	}

	/**
	 * Function that implements polymorphism to check if this entity can be bombed
	 */
	@Override
	public boolean canBeBombed() {
		killEnemy();
		return true;
	}
	
	/**
	 * Function that kills the enemy and removes it from the screen
	 */
	public void killEnemy() {
		isAlive = false;
		removeFromBoard();
	}

	/**
	 * Getter to check if the hound is still alive
	 */
	public boolean isAlive() {
		return isAlive;
	}
}