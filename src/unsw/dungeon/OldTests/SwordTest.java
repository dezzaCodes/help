package unsw.dungeon.OldTests;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.Entity;
import unsw.dungeon.GoalSingle;
import unsw.dungeon.Player;
import unsw.dungeon.Sword;

public class SwordTest {
	Dungeon d = new Dungeon(10, 10);
	Player p = new Player(d, 1, 1);
	Sword s = new Sword(2, 2);
	Sword s2 = new Sword(2, 3);
	Enemy e = new Enemy(d, 1, 1);
	
	
	@Test
    public void pickUpSword() {
		d.addEntity(s);
		d.setGoals(new GoalSingle(d, "boulder"));
		
		p.moveDown();
        p.moveRight();
        assert(p.getInventory().size() == 1);
        for (Entity item : p.getInventory()) {
        	assert (item instanceof Sword);
        }
        
    }
	
	@Test
    public void pickUpSwordTwoSwords() {
		d.addEntity(s);
		d.addEntity(s2);
		d.setGoals(new GoalSingle(d, "boulder"));
		
		
		p.moveDown();
        p.moveRight();
        assert(p.getInventory().size() == 1);
        for (Entity item : p.getInventory()) {
        	assert (item instanceof Sword);
        }
        p.moveDown();
        assert(p.getInventory().size() == 1);
        for (Entity item : p.getInventory()) {
        	assert (item instanceof Sword);
        }
        assert(d.getEntities().size() == 1);
    }
	
	
	@Test
    public void killFiveEnemy() {
		d.addEntity(s);
		d.addEntity(e);
		d.addEntity(e);
		d.addEntity(e);
		d.addEntity(e);
		d.addEntity(e);
		d.setGoals(new GoalSingle(d, "boulder"));
		
		
		p.moveDown();
        p.moveRight();
        assert(d.getEntities().size() == 4);
        p.moveLeft();
        assert(d.getEntities().size() == 3);
        p.moveRight();
        assert(d.getEntities().size() == 2);
        p.moveLeft();
        assert(d.getEntities().size() == 1);
        p.moveRight();
        assert(d.getEntities().size() == 0);
        p.moveLeft();
        
        assert(p.getInventory().size() == 0);
        
    }
	
	@Test
    public void swordOnlySwingsFiveTimes() {
		d.addEntity(s);
		d.addEntity(e);
		d.addEntity(e);
		d.addEntity(e);
		d.addEntity(e);
		d.addEntity(e);
		d.addEntity(e);
		d.setGoals(new GoalSingle(d, "boulder"));
		
		
		p.moveDown();
        p.moveRight();
        assert(d.getEntities().size() == 5);
        p.moveLeft();
        assert(d.getEntities().size() == 4);
        p.moveRight();
        assert(d.getEntities().size() == 3);
        p.moveLeft();
        assert(d.getEntities().size() == 2);
        p.moveRight();
        assert(d.getEntities().size() == 1);
        p.moveLeft();
        assert(d.getEntities().size() == 1);
        for (Entity item : d.getEntities()) {
        	assert (item instanceof Enemy);
        }
        
    }
	
	@Test
    public void dropSwordAfterUsed() {
		d.addEntity(s);
		d.addEntity(e);
		d.addEntity(e);
		d.addEntity(e);
		d.addEntity(e);
		d.addEntity(e);
		d.setGoals(new GoalSingle(d, "boulder"));
		
		
		p.moveDown();
        p.moveRight();
        p.moveLeft();
        assert(p.getInventory().size() == 1);
        p.moveRight();
        p.moveLeft();
        p.moveRight();
        assert(p.getInventory().size() == 0);
    }
	
	@Test
    public void SwingSwordMoreThanFiveTimes() {
        s.useSword();
        assert(s.getSwings() == 4);
        s.useSword();
        assert(s.getSwings() == 3);
        s.useSword();
        assert(s.getSwings() == 2);
        s.useSword();
        assert(s.getSwings() == 1);
        s.useSword();
        assert(s.getSwings() == 0);
        s.useSword();
        assert(s.getSwings() == 0);
    }
}
