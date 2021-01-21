package unsw.dungeon;
/**
 * Class for the Enemy's movement when it is defending
 * @author jamespo
 * Implements the enemy strategy interface
 */
public class EnemyDefend implements EnemyStrategy {

	/**
	 * Returns an instance of an EnemyAttack state
	 */
	@Override
	public EnemyStrategy Attack() {
		return new EnemyAttack();
	}

	/**
	 * Returns an instance of an Enemy Defend state, in this case, it already is one
	 * so it returns itself
	 */
	@Override
	public EnemyStrategy Defend() {
		return this;
	}

	/**
	 * Function in charge of movement pattern for the enemy 
	 * while it is trying to run away from the player
	 */
	@Override
	public void move(Entity e, Player p, Dungeon d) {
		p.getX();
		p.getY();
		int eUp = e.getY() - 1;
		int eDown = e.getY() + 1;
		int eLeft = e.getX() - 1;
		int eRight = e.getX() + 1;
		
		int maxWidth = d.getWidth() - 1;
		int maxHeight = d.getHeight() - 1;
		int minHW = 0;
		
		if (p.getY() > e.getY()) {
			if (eUp >= minHW && d.isMovableEnemy(e.getX(), eUp)) {
				e.y().set(eUp);
				return;
			}
		} else if (p.getY() < e.getY()) {
			if (eDown <= maxHeight && d.isMovableEnemy(e.getX(), eDown)) {
				e.y().set(eDown);
				return;
			}
		} 
		
		if (p.getX() > e.getX()) {
			if (eLeft >= minHW && d.isMovableEnemy(eLeft, e.getY()))
				e.x().set(eLeft);
		} else if (p.getX() < e.getX()) {
			if (eRight <= maxWidth && d.isMovableEnemy(eRight, e.getY()))
				e.x().set(eRight);
		}	
	}
}
