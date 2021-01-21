package unsw.dungeon.OldTests;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.EnemyAttack;
import unsw.dungeon.EnemyDefend;
import unsw.dungeon.Entity;
import unsw.dungeon.GoalSingle;
import unsw.dungeon.Player;
import unsw.dungeon.Potion;
import unsw.dungeon.Sword;
import unsw.dungeon.Wall;

public class EnemyTest {
	Dungeon d = new Dungeon(10, 10);
	Dungeon d1 = new Dungeon(0, 4);
	Player p = new Player(d, 1, 1);
	Player pl = new Player(d, 1, 1);
	Player pr = new Player(d, 3, 3);
	Player p1 = new Player(d1, 0, 0);
	Potion po = new Potion(1, 2);
	Potion po1 = new Potion(0, 1);
	Sword s = new Sword(1, 2);
	Sword s2 = new Sword(2, 3);
	Enemy e = new Enemy(d, 1, 3);
	Enemy e1 = new Enemy(d, 1, 10);
	Enemy e2 = new Enemy(d1, 0, 3);
	Wall wl = new Wall(0,3);
	Wall wr = new Wall(2,3);
	Wall wu = new Wall(1,2);
	Wall wd = new Wall(1,4);
	
	@Test
    public void enemyWall() {
		d.addEntity(pr);
		d.addEntity(pl);
		d.addEntity(e);
		d.addEntity(wl);
		d.addEntity(wr);
		d.addEntity(wu);
		d.addEntity(wd);
		d.setGoals(new GoalSingle(d, "boulder"));
		
		pl.moveLeft();
		assert(e.getX() == 1 && e.getY() == 3);
		pl.moveRight();
		pl.moveRight();
		assert(e.getX() == 1 && e.getY() == 3);
		
		pr.moveUp();
		assert(e.getX() == 1 && e.getY() == 3);
		pr.moveDown();
		pr.moveDown();
		assert(e.getX() == 1 && e.getY() == 3);
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
    public void enemyChangeState() {
		d.addEntity(po);
		d.addEntity(e);
		d.setGoals(new GoalSingle(d, "boulder"));
		
		assert(e.getState() instanceof EnemyAttack);
		p.moveDown();
		assert(e.getState() instanceof EnemyDefend);
		
    }
	
	@Test
    public void enemyRevertState() {
		d.addEntity(po);
		d.addEntity(e);
		d.setGoals(new GoalSingle(d, "boulder"));
		
		assert(e.getState() instanceof EnemyAttack);
		p.moveDown();
		assert(e.getState() instanceof EnemyDefend);
		p.moveDown();
		p.moveUp();
		p.moveDown();
		p.moveUp();
		assert(e.getState() instanceof EnemyAttack);
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
	@Test
    public void enemyMoveTowardsPlayer() {
		d.addEntity(e1);
		d.addEntity(p);
		d.setGoals(new GoalSingle(d, "boulder"));
		
		p.moveDown();
		System.out.println("E X " + e1.getX() + "E Y: " + e1.getY());
		assert(e1.getX() == 1 && e1.getY() == 9);
		p.moveDown();
		assert(e1.getX() == 1 && e1.getY() == 8);
		p.moveDown();
		assert(e1.getX() == 1 && e1.getY() == 7);
		p.moveDown();
		assert(e1.getX() == 1 && e1.getY() == 6);
    }
	
	@Test
    public void enemyTouchesPlayerNormal() {
		d.addEntity(e);
		d.addEntity(p);
		d.setGoals(new GoalSingle(d, "boulder"));
		
		p.moveDown();
		p.moveDown();
		for(Entity en : d.getEntities()) {
			assert(!(en instanceof Player));
		}
    }
	@Test
    public void enemyTouchesPlayerSword() {
		d.addEntity(e);
		d.addEntity(p);
		d.addEntity(s);
		d.setGoals(new GoalSingle(d, "boulder"));
		
		p.moveDown();
		p.moveDown();
		for(Entity en : d.getEntities()) {
			assert(!(en instanceof Enemy));
		}
    }
	
	@Test
	public void testEnemyMovement() {
		d.addEntity(p);
		d.addEntity(e);
		d.addEntity(e1);
		d.addEntity(e2);
		d.setGoals(new GoalSingle(d, "boulder"));
		assert(p.getX() == 1 && p.getY() == 1);
		assert(e.getX() == 1 && e.getY() == 3);
		assert(e1.getX() == 1 && e1.getY() == 10);
		assert(e2.getX() == 0 && e2.getY() == 3);
		p.moveDown();
		assert(e.getX() != 1 || e.getY() != 3);
		assert(e1.getX() != 1 || e1.getY() != 10);
		assert(e2.getX() != 0 || e2.getY() != 3);
	}
	
}
