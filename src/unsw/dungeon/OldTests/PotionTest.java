package unsw.dungeon.OldTests;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Bomb;
import unsw.dungeon.Boulder;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.EnemyDefend;
import unsw.dungeon.Entity;
import unsw.dungeon.GoalSingle;
import unsw.dungeon.Player;
import unsw.dungeon.PlayerInvincible;
import unsw.dungeon.PlayerNormal;
import unsw.dungeon.Potion;
import unsw.dungeon.Sword;

public class PotionTest {
	Dungeon d = new Dungeon(10, 10);
	Dungeon d1 = new Dungeon(0, 4);
	Player p = new Player(d, 1, 1);
	Potion po = new Potion(1, 2);
	Sword s = new Sword(2, 2);
	Sword s2 = new Sword(2, 3);
	Enemy e = new Enemy(d, 1, 3);
	Boulder b = new Boulder (d, 1, 2);
	Bomb bo = new Bomb(1, 3);
	Enemy e2 = new Enemy(d1, 0, 3);
	Potion po1 = new Potion(0, 1);
	Player p1 = new Player(d1, 0, 0);
	
	@Test
    public void dontDieBomb() {
		d.addEntity(po);
		d.addEntity(p);
		d.addEntity(bo);
		d.setGoals(new GoalSingle(d, "boulder"));
		
		p.moveDown();
		p.moveDown();
		
		boolean alive = false;
		System.out.println(p.getInventory().size());
		assert(p.getInventory().size() == 1);
		p.dropBomb(p.getX(), p.getY());
		for(Entity e : d.getEntities()) {
			if (e instanceof Player) {
				alive = true;
			}
		}
		assert(alive == true);
	}
	
	@Test
    public void invincibility5Turns() {
		d.addEntity(po);
		d.addEntity(p);
		d.setGoals(new GoalSingle(d, "boulder"));
		assert(p.getState() instanceof PlayerNormal);
		p.moveDown();
		assert(p.getState() instanceof PlayerInvincible);
		p.moveDown();
		p.moveDown();
		p.moveDown();
		p.moveDown();
		assert(p.getState() instanceof PlayerNormal);
		
	}
	
	@Test
    public void isInvincibleImmediately() {
		d.addEntity(po);
		d.addEntity(p);
		d.addEntity(bo);
		d.setGoals(new GoalSingle(d, "boulder"));
		
		p.moveDown();
		
		assert(p.getState() instanceof PlayerInvincible);
	}
	
	@Test
	public void isNoLongerInvincible() {
		d.addEntity(po);
		d.addEntity(p);
		d.addEntity(bo);
		d.setGoals(new GoalSingle(d, "boulder"));
		
		p.moveDown();
		assert(p.getState().getTurnsLeft() == 4);
		p.moveDown();
		assert(p.getState().getTurnsLeft() == 3);
		p.moveDown();
		assert(p.getState().getTurnsLeft() == 2);
		p.moveDown();
		assert(p.getState().getTurnsLeft() == 1);
		p.moveDown();
		assert(p.getState().getTurnsLeft() == 0);
		assert(p.getState() instanceof PlayerNormal);
		p.moveDown();
		
		assert(p.getState() instanceof PlayerNormal);
	}
	@Test
    public void enemyTouchesPlayerInvincibility() {
		d1.addEntity(po1);
		d1.addEntity(e2);
		d1.addEntity(p1);
		d1.setGoals(new GoalSingle(d1, "boulder"));
		
		p1.moveDown();
		assert(e2.getState() instanceof EnemyDefend);
		
		p1.moveDown();
		p1.moveDown();
		p1.moveDown();
		for(Entity en : d1.getEntities()) {
			assert(!(en instanceof Enemy));
		}
		
    }
	@Test
    public void enemyRunPotion() {
		d.addEntity(po);
		d.addEntity(e);
		d.addEntity(p);
		d.setGoals(new GoalSingle(d, "boulder"));
		
		p.moveDown();
		
		assert(e.getX() == 1 && e.getY() == 4);
		p.moveDown();
		assert(e.getX() == 1 && e.getY() == 5);
		p.moveDown();
		assert(e.getX() == 1 && e.getY() == 6);
		p.moveDown();
		assert(e.getX() == 1 && e.getY() == 7);
    }
}
