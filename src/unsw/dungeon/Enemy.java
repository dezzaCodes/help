package unsw.dungeon;
/**
 * Main Enemy class for the game
 * @author jamespo
 * Extends the Entity class and implements the EnemyObserver interface
 */
public class Enemy extends Entity implements EnemyObserver {
	
	private EnemyStrategy state;
	private Dungeon dungeon;
	private boolean isAlive;
	
	/**
	 * Constructor for the enemy class
	 * @param dungeon - a reference to the dungeon it is constructed in
	 * @param x - x coordinate of the enemy
	 * @param y - y coordinate of the enmy
	 */
	public Enemy(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.state = new EnemyAttack();
		this.dungeon = dungeon;
		this.isAlive = true;
	}
	
	/**
	 * Getter for the enemy's state
	 * @return - an EnemyStrategy state, either enemyAttack or EnemyDefend
	 */
	public EnemyStrategy getState() {
		return state;
	}
	
	/**
	 * Function that sets the enemy's state to attack the player
	 */
	public void attack() {
		state = getState().Attack();
	}
	
	/**
	 * Function that sets the enemy's state to run away from the player
	 */
	public void run() {
		state = getState().Defend();
	}
	
	/**
	 * Function that delegates movement to its state
	 * @param p - reference to the player
	 * @param d - reference to the dungeon
	 */
	public void move(Player p, Dungeon d) {
		state.move(this, p, d);
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
	 * Function that updates the enemy of the player's state so that the enemy knows to
	 * change its movement pattern when it changes
	 */
	@Override
	public void update(Player p) {
		if (p.getState() instanceof PlayerInvincible) {
			run();
		} else {
			attack();
		}
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
	 * Function that kills the enemy
	 */
	public void killEnemy() {
		isAlive = false;
		removeFromBoard();
	}

	/**
	 * Function that returns whether or not the enemy is alive
	 * @return
	 */
	public boolean isAlive() {
		return isAlive;
	}
}
