package unsw.dungeon.OldTests;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Bomb;
import unsw.dungeon.Boulder;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.GoalSingle;
import unsw.dungeon.Plate;
import unsw.dungeon.PlateActive;
import unsw.dungeon.PlateInactive;
import unsw.dungeon.Player;
import unsw.dungeon.Potion;
import unsw.dungeon.Sword;

public class PlateTest {
	Dungeon d = new Dungeon(10, 10);
	Player p = new Player(d, 1, 1);
	Potion po = new Potion(1, 2);
	Sword s = new Sword(2, 2);
	Sword s2 = new Sword(2, 3);
	Enemy e = new Enemy(d, 1, 1);
	Plate pl = new Plate(1, 3);
	Boulder b = new Boulder (d, 1, 2);
	Bomb bo = new Bomb(1, 2);
	
	
	@Test
    public void plateInactive() {
		d.addEntity(pl);
		d.setGoals(new GoalSingle(d, "boulder"));
		assert (pl.getState() instanceof PlateInactive);
	}
	
	@Test
    public void plateActive() {
		d.addEntity(pl);
		d.addEntity(b);
		d.addEntity(p);
		d.setGoals(new GoalSingle(d, "boulder"));
		
		p.moveDown();
		assert (pl.getState() instanceof PlateActive);
	}
	
	@Test
    public void plateActiveToInactive() {
		d.addEntity(pl);
		d.addEntity(b);
		d.addEntity(p);
		d.setGoals(new GoalSingle(d, "boulder"));
		
		p.moveDown();
		assert (pl.getState() instanceof PlateActive);
		p.moveDown();
		assert (pl.getState() instanceof PlateInactive);
	}
	
	@Test
    public void bombOnPlate() {
		d.addEntity(pl);
		d.addEntity(bo);
		d.addEntity(p);
		d.setGoals(new GoalSingle(d, "boulder"));
		
		p.moveDown();
		p.moveDown();
		p.dropBomb(1, 3);
		assert (pl.getState() instanceof PlateInactive);
	}
	
}
