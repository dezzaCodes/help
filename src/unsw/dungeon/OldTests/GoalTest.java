package unsw.dungeon.OldTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Boulder;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.Exit;
import unsw.dungeon.GoalAnd;
import unsw.dungeon.GoalComponent;
import unsw.dungeon.GoalOr;
import unsw.dungeon.GoalSingle;
import unsw.dungeon.Plate;
import unsw.dungeon.Player;
import unsw.dungeon.Sword;
import unsw.dungeon.Treasure;

public class GoalTest {
	private Dungeon d;
	private Player p;
	private Boulder b;
	private Plate pl;
	private Treasure t;
	private Exit xx;
	private Enemy en;
	private Sword s;
	
	@BeforeEach
	void setUp() throws Exception {
		d = new Dungeon(10, 10);
		p = new Player(d, 1, 1);
		b = new Boulder(d, 2, 1);
		pl = new Plate(3, 1);
		t = new Treasure(1, 0);
		xx = new Exit(1, 2);
		s = new Sword(1, 2);
		en = new Enemy(d, 1, 4);
		d.addEntity(p);
	}
	
	@Test
    public void testBoulder() {
		GoalComponent g = new GoalSingle(d, "boulders");
		d.setGoals(g);
		d.addEntity(b);
		d.addEntity(pl);
		assert(d.checkGoals("boulders") == false);
		p.moveRight();
		assert(d.checkGoals("boulders") == true);
    }
	
	@Test
    public void testTreasure() {
		GoalComponent g = new GoalSingle(d, "treasure");
		d.setGoals(g);
		d.addEntity(t);
		
		assert(d.checkGoals("treasure") == false);
		p.moveDown();
		assert(d.checkGoals("treasure") == false);
		p.moveUp();
		p.moveUp();
		assert(d.checkGoals("treasure") == true);
    }
	
	@Test
    public void testExit() {
		d.setGoals(new GoalSingle(d, "exit"));
		d.addEntity(xx);
		d.setPlayer(p);

		System.out.println(d.checkGoals("exit"));
		
		assert(d.checkGoals("exit") == false);
		p.moveUp();
		assert(d.checkGoals("exit") == false);
		p.moveDown();
		assert(d.checkGoals("exit") == false);
		p.moveDown();
		assert(d.checkGoals("exit") == true);
    }
	
	@Test
    public void testEnemy() {
		GoalComponent g = new GoalSingle(d, "enemies");
		d.setGoals(g);
		d.addEntity(en);
		d.addEntity(s);

		assert(d.checkGoals("enemies") == false);
		p.moveDown();
		assert(d.checkGoals("enemies") == false);
		p.moveDown();
		assert(d.checkGoals("enemies") == true);
    }
	
	@Test
    public void testComplexAnd() {
		GoalComponent g = new GoalSingle(d, "treasure");
		GoalComponent g1 = new GoalSingle(d, "exit");
		GoalComponent g2 = new GoalAnd("AND");
		g2.addGoal(g);
		g2.addGoal(g1);
		
		d.setGoals(g2);
		d.addEntity(t);
		d.addEntity(xx);
		d.setPlayer(p);
		
		assert(p.checkGoals() == false);
		p.moveDown();
		assert(p.checkGoals() == false);
		p.moveUp();
		assert(p.checkGoals() == false);
		p.moveUp();
		assert(p.checkGoals() == false);
		p.moveDown();
		assert(p.checkGoals() == false);
		p.moveDown();
		assert(p.checkGoals() == true);
	}
	
	@Test
    public void testComplexOr1() {
		GoalComponent g = new GoalSingle(d, "treasure");
		GoalComponent g1 = new GoalSingle(d, "exit");
		GoalComponent g2 = new GoalOr("OR");
		g2.addGoal(g);
		g2.addGoal(g1);
		
		d.setGoals(g2);
		d.addEntity(t);
		d.addEntity(xx);
		d.setPlayer(p);
		
		assert(p.checkGoals() == false);
		p.moveDown();
		assert(p.checkGoals() == true);
	}
	
	@Test
    public void testComplexOr2() {
		GoalComponent g = new GoalSingle(d, "treasure");
		GoalComponent g1 = new GoalSingle(d, "exit");
		GoalComponent g2 = new GoalOr("OR");
		g2.addGoal(g);
		g2.addGoal(g1);
		
		d.setGoals(g2);
		d.addEntity(t);
		d.addEntity(xx);
		d.setPlayer(p);
		
		assert(p.checkGoals() == false);
		p.moveUp();
		assert(p.checkGoals() == true);
	}
	
	@Test
    public void testComplexOrAndNested() {
		GoalComponent g = new GoalSingle(d, "treasure");
		GoalComponent g1 = new GoalSingle(d, "exit");
		GoalComponent g2 = new GoalAnd("AND");
		GoalComponent g3 = new GoalOr("OR");
		GoalComponent g4 = new GoalSingle(d, "boulders");
		g2.addGoal(g);
		g2.addGoal(g1);
		g3.addGoal(g2);
		g3.addGoal(g4);
		
		d.setGoals(g3);
		d.addEntity(t);
		d.addEntity(xx);
		d.setPlayer(p);
		d.addEntity(b);
		d.addEntity(pl);
		
		assert(p.checkGoals() == false);
		p.moveRight();
		assert(p.checkGoals() == true);
	}
	
	@Test
    public void testComplexOrAndNested1() {
		GoalComponent g = new GoalSingle(d, "treasure");
		GoalComponent g1 = new GoalSingle(d, "exit");
		GoalComponent g2 = new GoalAnd("AND");
		GoalComponent g3 = new GoalOr("OR");
		GoalComponent g4 = new GoalSingle(d, "boulders");
		g2.addGoal(g);
		g2.addGoal(g1);
		g3.addGoal(g2);
		g3.addGoal(g4);
		
		d.setGoals(g3);
		d.addEntity(t);
		d.addEntity(xx);
		d.setPlayer(p);
		d.addEntity(b);
		d.addEntity(pl);
		
		assert(p.checkGoals() == false);
		p.moveDown();
		assert(p.checkGoals() == false);
		p.moveUp();
		assert(p.checkGoals() == false);
		p.moveUp();
		assert(p.checkGoals() == false);
		p.moveDown();
		assert(p.checkGoals() == false);
		p.moveDown();
		assert(p.checkGoals() == true);
	}
	
	@Test
    public void testComplexAndOrNested() {
		GoalComponent g = new GoalSingle(d, "treasure");
		GoalComponent g1 = new GoalSingle(d, "exit");
		GoalComponent g2 = new GoalOr("OR");
		GoalComponent g3 = new GoalAnd("AND");
		GoalComponent g4 = new GoalSingle(d, "boulders");
		g2.addGoal(g);
		g2.addGoal(g1);
		g3.addGoal(g2);
		g3.addGoal(g4);
		
		d.setGoals(g3);
		d.addEntity(t);
		d.addEntity(xx);
		d.setPlayer(p);
		d.addEntity(b);
		d.addEntity(pl);
		
		assert(p.checkGoals() == false);
		p.moveRight();
		assert(p.checkGoals() == false);
		p.moveLeft();
		assert(p.checkGoals() == false);
		p.moveUp();
		assert(p.checkGoals() == true);
	}
	
	@Test
    public void testComplexAndOrNested1() {
		GoalComponent g = new GoalSingle(d, "treasure");
		GoalComponent g1 = new GoalSingle(d, "exit");
		GoalComponent g2 = new GoalOr("OR");
		GoalComponent g3 = new GoalAnd("AND");
		GoalComponent g4 = new GoalSingle(d, "boulders");
		g2.addGoal(g);
		g2.addGoal(g1);
		g3.addGoal(g2);
		g3.addGoal(g4);
		
		d.setGoals(g3);
		d.addEntity(t);
		d.addEntity(xx);
		d.setPlayer(p);
		d.addEntity(b);
		d.addEntity(pl);
		
		assert(p.checkGoals() == false);
		p.moveRight();
		assert(p.checkGoals() == false);
		p.moveLeft();
		assert(p.checkGoals() == false);
		p.moveDown();
		assert(p.checkGoals() == true);
	}
	
	@Test
    public void testComplexAndOrNested2() {
		GoalComponent g = new GoalSingle(d, "treasure");
		GoalComponent g1 = new GoalSingle(d, "exit");
		GoalComponent g2 = new GoalOr("OR");
		GoalComponent g3 = new GoalAnd("AND");
		GoalComponent g4 = new GoalSingle(d, "boulders");
		g2.addGoal(g);
		g2.addGoal(g1);
		g3.addGoal(g2);
		g3.addGoal(g4);
		
		d.setGoals(g3);
		d.addEntity(t);
		d.addEntity(xx);
		d.setPlayer(p);
		d.addEntity(b);
		d.addEntity(pl);
		
		assert(p.checkGoals() == false);
		p.moveUp();
		assert(p.checkGoals() == false);
		p.moveDown();
		assert(p.checkGoals() == false);
		p.moveDown();
		assert(p.checkGoals() == false);
		p.moveUp();
		assert(p.checkGoals() == false);
		p.moveRight();
		assert(p.checkGoals() == true);
	}
}
