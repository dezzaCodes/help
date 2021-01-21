package unsw.dungeon.OldTests;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Boulder;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.GoalSingle;
import unsw.dungeon.Player;
import unsw.dungeon.Potion;
import unsw.dungeon.Sword;
import unsw.dungeon.Wall;

public class BoulderTest {
	Dungeon d = new Dungeon(10, 10);
	Player p = new Player(d, 4, 4);
	Potion po = new Potion(1, 2);
	Sword s = new Sword(2, 2);
	Sword s2 = new Sword(2, 3);
	Enemy e = new Enemy(d, 1, 1);
	Boulder dow = new Boulder(d, 4, 5);
	Boulder dow1 = new Boulder(d, 4, 6);
	Boulder r = new Boulder(d, 5, 4);
	Boulder l = new Boulder(d, 3, 4);
	Boulder u = new Boulder(d, 4, 3);
	Wall w = new Wall(4, 6);
	
	@Test
	public void pushBoulderDown() {
		d.addEntity(dow);
		d.addEntity(p);
		d.setGoals(new GoalSingle(d, "boulder"));
		
		
		p.moveDown();
		assert(p.getX() == 4 && p.getY() == 5);
		assert(dow.getX() == 4 && dow.getY() == 6);
	}
	
	@Test
	public void pushBoulderUp() {
		d.addEntity(u);
		d.addEntity(p);
		d.setGoals(new GoalSingle(d, "boulder"));
		
		p.moveUp();
		assert(p.getX() == 4 && p.getY() == 3);
		assert(u.getX() == 4 && u.getY() == 2);
	}
	
	@Test
	public void pushBoulderLeft() {
		d.addEntity(l);
		d.addEntity(p);
		d.setGoals(new GoalSingle(d, "boulder"));
		
		p.moveLeft();
		assert(p.getX() == 3 && p.getY() == 4);
		assert(l.getX() == 2 && l.getY() == 4);
	}
	
	@Test
	public void pushBoulderRight() {
		d.addEntity(r);
		d.addEntity(p);
		d.setGoals(new GoalSingle(d, "boulder"));
		
		p.moveRight();
		assert(p.getX() == 5 && p.getY() == 4);
		assert(r.getX() == 6 && r.getY() == 4);
	}
	
	@Test
	public void pushBoulderWall() {
		d.addEntity(dow);
		d.addEntity(p);
		d.addEntity(w);
		d.setGoals(new GoalSingle(d, "boulder"));
		
		p.moveDown();
		assert(p.getX() == 4 && p.getY() == 4);
		assert(dow.getX() == 4 && dow.getY() == 5);
	}
	
	@Test
	public void pushTwoBoulders() {
		d.addEntity(dow);
		d.addEntity(dow1);
		d.addEntity(p);
		d.setGoals(new GoalSingle(d, "boulder"));
		
		p.moveDown();
		assert(p.getX() == 4 && p.getY() == 4);
		assert(dow.getX() == 4 && dow.getY() == 5);
	}
}