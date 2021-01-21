package unsw.dungeon;

/**
 * Observer pattern for the enemy's movement
 * @author jamespo
 *
 */
public interface EnemyObserver {
	public void update(Player p);
}
