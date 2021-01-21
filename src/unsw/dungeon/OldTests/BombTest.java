package unsw.dungeon.OldTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Bomb;
import unsw.dungeon.Boulder;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.Entity;
import unsw.dungeon.GoalSingle;
import unsw.dungeon.Player;
import unsw.dungeon.Wall;

class BombTest {
	private Dungeon d;
	private Player p;
	private Bomb b1;
	private Bomb b2;
	private Bomb b3;
	private Enemy e;
	private Boulder b;
	private Wall w;
	
	@BeforeEach
	void setUp() throws Exception {
		d = new Dungeon(10, 10);
		p = new Player(d, 1, 1);
		b1 = new Bomb(1,2);
		b2 = new Bomb(1,3);
		b3 = new Bomb(1,4);
		b = new Boulder(d, 2, 2);
		w = new Wall(2,3);
		d.addEntity(p);
		d.addEntity(b1);
		d.addEntity(b2);
		d.addEntity(b3);
		d.addEntity(b);
		d.addEntity(w);
		d.setPlayer(p);
		d.setGoals(new GoalSingle(d, "boulder"));
	}

	// Testing that a bomb should not be placed if player has no bomb
	@Test
	void testBombWithNoBomb() {
		assert(p.getX() == 1 && p.getY() == 1);
		assert(p.getInventory().size() == 0);
		p.dropBomb(p.getX(), p.getY());
		assert(p.getInventory().size() == 0);
		for(Entity entity : d.getEntities()) {
			if (entity instanceof Bomb) {
				assert(((Bomb) entity).isLit() == false);
			}
		}
	}
	
	// Testing that a bomb should be placed if player has a bomb
	@Test
	void testBombWithBomb() {
		System.out.println("testBombWithBomb");
		// Testing that the player picks up the bomb
		assert(p.getX() == 1 && p.getY() == 1);
		assert(p.getInventory().size() == 0);
		assert(b1.getX() == 1 && b1.getY() == 2);
		p.moveDown();
		assert(p.getInventory().size() == 1);
		assert(p.checkBombInventory() == b1);
		assert(p.getX() == 1 && p.getY() == 2);
		// Testing that the bomb is deleted from the player's inventory when dropped
		p.dropBomb(p.getX(), p.getY());
		assert(p.checkBombInventory() == null);
		assert(p.getInventory().size() == 0);
		// Manually update the bomb's timer for the test
		b1.updateLitBomb();
		b1.updateLitBomb();
		b1.updateLitBomb();
		b1.updateLitBomb();
		b1.updateLitBomb();
		d.bombBlowUp(b1);
		// Checks that the bomb was picked up and no longer part of the dungeon and bomb should have killed
		// the player since they didn't move
		for(Entity entity : d.getEntities()) {
			assert(!(entity instanceof Player));
			assert(entity != b1);
		}
	}
	
	// Testing that the player dies to the bomb
	@Test
	void testPlayerDiesBomb() {
		assert(p.getX() == 1 && p.getY() == 1);
		assert(p.getInventory().size() == 0);
		assert(b1.getX() == 1 && b1.getY() == 2);
		p.moveDown();
		assert(p.getInventory().size() == 1);
		assert(p.checkBombInventory() == b1);
		assert(p.getX() == 1 && p.getY() == 2);
		p.dropBomb(p.getX(), p.getY());
		assert(p.checkBombInventory() == null);
		assert(p.getInventory().size() == 0);
		b1.updateLitBomb();
		b1.updateLitBomb();
		b1.updateLitBomb();
		b1.updateLitBomb();
		b1.updateLitBomb();
		d.bombBlowUp(b1);
		// Checks that there is no longer an instance of the player in the dungeon since they are dead and removed
		for(Entity entity : d.getEntities()) {
			assert(!(entity instanceof Player));
		}
	}
	
	// Testing that the bomb blows up after a set amount of seconds normally
	@Test
	void testBombSetTime() throws InterruptedException {
		assert(p.getX() == 1 && p.getY() == 1);
		assert(p.getInventory().size() == 0);
		assert(b1.getX() == 1 && b1.getY() == 2);
		p.moveDown();
		assert(p.getInventory().size() == 1);
		assert(p.checkBombInventory() == b1);
		assert(p.getX() == 1 && p.getY() == 2);
		p.dropBomb(p.getX(), p.getY());
		// Puts the test on hold for 6 seconds while the bomb's timer runs down
		Thread.sleep(6000);
		// Tests that the bomb blew up and is no longer part of the dungeon and player died
		for(Entity entity : d.getEntities()) {
			assert(!(entity instanceof Player));
			assert(entity != b1);
		}
	}
	
	// Testing that if the set amount of time doesn't pass normally then it wont blow up
	@Test
	void testBombNotEnoughTime() throws InterruptedException {
		System.out.println("testBombNotEnoughTime");
		assert(p.getX() == 1 && p.getY() == 1);
		assert(p.getInventory().size() == 0);
		assert(b1.getX() == 1 && b1.getY() == 2);
		p.moveDown();
		assert(p.getInventory().size() == 1);
		assert(p.checkBombInventory() == b1);
		assert(p.getX() == 1 && p.getY() == 2);
		p.dropBomb(p.getX(), p.getY());
		// Puts the test on hold for 1 second
		Thread.sleep(1000);
		// Tests that a player is still found in the game since not even time has passed for the
		// bomb to blow up even though the player was next to bomb
		Player foundPlayer = null;
		for(Entity entity : d.getEntities()) {
			if (entity instanceof Player) {
				foundPlayer = (Player) entity;
			}
		}
		assert(foundPlayer == p);
	}
	
	// Testing that the bomb should be placed on the same position as the player when dropped
	@Test
	void testBombDropOnSameSquare() {
		assert(p.getX() == 1 && p.getY() == 1);
		assert(p.getInventory().size() == 0);
		assert(b1.getX() == 1 && b1.getY() == 2);
		p.moveDown();
		assert(b1.getX() == 1 && b1.getY() == 2);
		assert(p.getInventory().size() == 1);
		assert(p.checkBombInventory() == b1);
		p.moveUp();
		p.dropBomb(p.getX(), p.getY());
		// Testing that the dropped bomb is lit and that it's position is the same as the player's position
		assert(b1.isLit() == true);
		assert(b1.getX() == p.getX() && b1.getY() == p.getY());
	}
	
	// Testing that a boulder is destroyed by the bomb
	@Test
	void testBoulderDestroyedBomb() {
		assert(p.getX() == 1 && p.getY() == 1);
		assert(p.getInventory().size() == 0);
		assert(b1.getX() == 1 && b1.getY() == 2);
		p.moveDown();
		assert(p.getInventory().size() == 1);
		assert(p.checkBombInventory() == b1);
		assert(p.getX() == 1 && p.getY() == 2);
		p.dropBomb(p.getX(), p.getY());
		assert(p.checkBombInventory() == null);
		assert(p.getInventory().size() == 0);
		b1.updateLitBomb();
		b1.updateLitBomb();
		b1.updateLitBomb();
		b1.updateLitBomb();
		b1.updateLitBomb();
		d.bombBlowUp(b1);
		// Tests that no instances of boulder are left since the only one was blown up
		for(Entity entity : d.getEntities()) {
			assert(!(entity instanceof Boulder));
		}
	}
	
	// Testing that walls should not be destroyed by a bomb
	@Test
	void testWallNotDestroyed() {
		assert(p.getX() == 1 && p.getY() == 1);
		assert(p.getInventory().size() == 0);
		assert(b1.getX() == 1 && b1.getY() == 2);
		p.moveDown();
		assert(p.getInventory().size() == 1);
		assert(p.checkBombInventory() == b1);
		assert(p.getX() == 1 && p.getY() == 2);
		p.moveDown();
		assert(p.getX() == 1 && p.getY() == 3);
		assert(w.getX() == 2 && w.getY() == 3);
		p.dropBomb(p.getX(), p.getY());
		b1.updateLitBomb();
		b1.updateLitBomb();
		b1.updateLitBomb();
		b1.updateLitBomb();
		b1.updateLitBomb();
		d.bombBlowUp(b1);
		Wall foundWall = null;
		// Checks that an instance of wall that was next to the bomb when it blew up is found since
		// walls can't be destroyed by bombs
		for(Entity entity : d.getEntities()) {
			if (entity instanceof Wall) {
				foundWall = (Wall) entity;
			}
		}
		assert(foundWall == w);
	}
	
	// Testing that a bomb should kill an enemy
	@Test
	void testBombKillsEnemy() {
		System.out.println("testBombKillsEnemy");
		e = new Enemy(d, 2, 1);
		d.addEntity(e);
		p.addEnemy(e);
		assert(p.getX() == 1 && p.getY() == 1);
		assert(p.getInventory().size() == 0);
		assert(b1.getX() == 1 && b1.getY() == 2);
		p.moveDown();
		p.moveDown();
		// Checks that the player can carry multiple bombs
		assert(p.getInventory().size() == 2);
		assert(p.checkBombInventory() == b1);
		// Checks that the enemies position is next to the player when bomb is placed
		assert(p.getX() == 1 && p.getY() == 3);
		assert(e.getX() == 1 && e.getY() == 2);
		p.dropBomb(p.getX(), p.getY());
		b1.updateLitBomb();
		b1.updateLitBomb();
		b1.updateLitBomb();
		b1.updateLitBomb();
		b1.updateLitBomb();
		d.bombBlowUp(b1);
		// There should be no more instances of enemies since the enemy was next to bomb when it
		// blew up
		for(Entity entity : d.getEntities()) {
			assert(!(entity instanceof Enemy));
		}
	}
	
	// Testing that items should not be destroyed by bombs
	@Test
	void testItemsNotDestroyed() {
		assert(p.getX() == 1 && p.getY() == 1);
		assert(p.getInventory().size() == 0);
		assert(b1.getX() == 1 && b1.getY() == 2);
		p.moveDown();
		p.moveDown();
		assert(p.getInventory().size() == 2);
		assert(p.checkBombInventory() == b1);
		// Checks that the player is next to the item that is bomb 3
		assert(p.getX() == 1 && p.getY() == 3);
		assert(b3.getX() == 1 && b3.getY() == 4);
		p.dropBomb(p.getX(), p.getY());
		b1.updateLitBomb();
		b1.updateLitBomb();
		b1.updateLitBomb();
		b1.updateLitBomb();
		b1.updateLitBomb();
		d.bombBlowUp(b1);
		// Since the player collected the only two other bombs, the only bomb found in the dungeon
		// should be bomb 3 which was next to the bomb that blew up
		for(Entity entity : d.getEntities()) {
			if (entity instanceof Bomb) {
				assert(entity == b3);
			}
		}
	}

}
