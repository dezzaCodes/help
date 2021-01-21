package unsw.dungeon;

/**
 * Class for the Enemy's movement when it is attacking
 * @author jamespo
 * Implements the enemy strategy interface
 */
public class EnemyAttack implements EnemyStrategy {

	/**
	 * Returns an instance of an EnemyAttack state, in this case, it already is one
	 * so it returns itself
	 */
	@Override
	public EnemyStrategy Attack() {
		return this;
	}

	/**
	 * Returns an instance of an Enemy Defend state
	 */
	@Override
	public EnemyStrategy Defend() {
		return new EnemyDefend();
	}

	/**
	 * Function in charge of movement pattern for the enemy while it is 
	 * trying to attack the player
	 */
	@Override
	public void move(Entity e, Player p, Dungeon d) {
		int eUp = e.getY() - 1;
		int eDown = e.getY() + 1;
		int eLeft = e.getX() - 1;
		int eRight = e.getX() + 1;
		
		if (p.getY() > e.getY()) {
			if (d.isMovableEnemy(e.getX(), eDown)) {
				e.y().set(eDown);
				return;
			}
		} else if (p.getY() < e.getY()) {
			if (d.isMovableEnemy(e.getX(), eUp)) {
				e.y().set(eUp);
				return;
			}
		}
		
		if (p.getX() > e.getX()) {
			if (d.isMovableEnemy(eRight, e.getY()))
				e.x().set(eRight);
		} else if (p.getX() < e.getX()) {
			if (d.isMovableEnemy(eLeft, e.getY()))
				e.x().set(eLeft);
		}
	}
}
