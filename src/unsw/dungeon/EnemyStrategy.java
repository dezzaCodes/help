package unsw.dungeon;

/**
 * Enemy movement strategy interface for the enemies
 * @author jamespo
 *
 */
public interface EnemyStrategy {
	public EnemyStrategy Attack();
	public EnemyStrategy Defend();
	public void move(Entity e, Player p, Dungeon d);
}
