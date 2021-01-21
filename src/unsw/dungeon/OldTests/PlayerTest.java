package unsw.dungeon.OldTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Bomb;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.GoalSingle;
import unsw.dungeon.Key;
import unsw.dungeon.Player;
import unsw.dungeon.Potion;
import unsw.dungeon.Sword;
import unsw.dungeon.Wall;

class PlayerTest {
	private Dungeon d;
		private Player p;
		private Bomb bomb;
		private Key key;
		private Wall wall;
		private Sword sword;
		private Potion potion;
		
	@BeforeEach
	void setUp() throws Exception {
		d = new Dungeon(10, 10);
		p = new Player(d, 1, 1);
		bomb = new Bomb(1,2);
		key = new Key(1,3,0);
		wall = new Wall(2,1);
		sword = new Sword(1,4);
		potion = new Potion(1,5);
		d.addEntity(p);
		d.addEntity(bomb);
		d.addEntity(key);
		d.addEntity(wall);
		d.addEntity(sword);
		d.addEntity(potion);
		d.setGoals(new GoalSingle(d, "boulder"));
	}
	
	// Testing player actually moves downwards
	@Test
	void testMoveDown() {
		assert(p.getX() == 1 && p.getY() == 1);
		p.moveDown();
		assert(p.getX() == 1 && p.getY() == 2);
	}
	
	// Testing player actually moves upwards
	@Test
	void testMoveUp() {
		assert(p.getX() == 1 && p.getY() == 1);
		p.moveUp();
		assert(p.getX() == 1 && p.getY() == 0);
	}
	
	// Testing player actually moves left
	@Test
	void testMoveLeft() {
		assert(p.getX() == 1 && p.getY() == 1);
		p.moveLeft();
		assert(p.getX() == 0 && p.getY() == 1);
	}
	
	// Testing player actually moves right
	@Test
	void testMoveRight() {
		assert(p.getX() == 1 && p.getY() == 1);
		p.moveUp();
		assert(p.getX() == 1 && p.getY() == 0);
		p.moveRight();
		assert(p.getX() == 2 && p.getY() == 0);
	}
	
	// Testing player can't move out of the map
	@Test
	void testMoveOutOfBounds() {
		assert(p.getX() == 1 && p.getY() == 1);
		p.moveUp();
		assert(p.getX() == 1 && p.getY() == 0);
		p.moveUp();
		assert(p.getX() == 1 && p.getY() == 0);
	}
	
	// Testing that the player can not move into a wall
	@Test
	void testMoveIntoWall() {
		assert(p.getX() == 1 && p.getY() == 1);
		assert(wall.getX() == 2 && wall.getY() == 1);
		p.moveRight();
		assert(p.getX() == 1 && p.getY() == 1);
		assert(wall.getX() == 2 && wall.getY() == 1);
	}
	
	// Testing that when a player walks over a bomb, they pick it up
	@Test
	void testPickUpBomb() {
		assert(p.getX() == 1 && p.getY() == 1);
		assert(bomb.getX() == 1 && bomb.getY() == 2);
		assert(p.getInventory().size() == 0);
		assert(p.checkBombInventory() == null);
		Bomb foundBomb = null;
		for (Entity entity: d.getEntities()) {
			if (entity instanceof Bomb) {
				foundBomb = (Bomb) entity;
			}
		}
		assert(foundBomb == bomb);
		p.moveDown();
		assert(p.getX() == 1 && p.getY() == 2);
		assert(p.getInventory().size() == 1);
		assert(p.checkBombInventory() == bomb);
		for (Entity entity: d.getEntities()) {
			assert(!(entity instanceof Bomb));
		}
	}
	
	// Testing that when a player walks over a key, they pick it up
	@Test
	void testPickUpKey() {
		assert(p.getX() == 1 && p.getY() == 1);
		assert(bomb.getX() == 1 && bomb.getY() == 2);
		assert(p.getInventory().size() == 0);
		assert(p.checkKeyInventory() == null);
		Key foundKey = null;
		for (Entity entity: d.getEntities()) {
			if (entity instanceof Key) {
				foundKey = (Key) entity;
			}
		}
		assert(foundKey == key);
		p.moveDown();
		assert(p.getX() == 1 && p.getY() == 2);
		assert(p.getInventory().size() == 1);
		assert(key.getX() == 1 && key.getY() == 3);
		p.moveDown();
		assert(p.getX() == 1 && p.getY() == 3);
		assert(p.getInventory().size() == 2);
		assert(p.checkKeyInventory() == key);
		for (Entity entity: d.getEntities()) {
			assert(!(entity instanceof Key));
		}
	}
	
	// Testing that when a player walks over a sword, they pick it up
	@Test
	void testPickUpSword() {
		assert(p.getX() == 1 && p.getY() == 1);
		assert(p.getInventory().size() == 0);
		p.moveDown();
		assert(p.getX() == 1 && p.getY() == 2);
		assert(p.getInventory().size() == 1);
		p.moveDown();
		assert(p.getInventory().size() == 2);
		Sword foundSword = null;
		for (Entity entity: d.getEntities()) {
			if (entity instanceof Sword) {
				foundSword = (Sword) entity;
			}
		}
		assert(foundSword == sword);
		assert(p.getX() == 1 && p.getY() == 3);
		assert(sword.getX() == 1 && sword.getY() == 4);
		p.moveDown();
		assert(p.getX() == 1 && p.getY() == 4);
		assert(p.getInventory().size() == 3);
		for (Entity entity: d.getEntities()) {
			assert(!(entity instanceof Sword));
		}
	}
	
	// Testing that when a player walks over a bomb, they pick it up but it doesn't go into
	// their inventory but instead gets used
	@Test
	void testPickUpPotion() {
		assert(p.getX() == 1 && p.getY() == 1);
		assert(p.getInventory().size() == 0);
		p.moveDown();
		assert(p.getX() == 1 && p.getY() == 2);
		assert(p.getInventory().size() == 1);
		p.moveDown();
		assert(p.getInventory().size() == 2);
		assert(p.getX() == 1 && p.getY() == 3);
		p.moveDown();
		assert(p.getX() == 1 && p.getY() == 4);
		assert(p.getInventory().size() == 3);
		Potion foundPotion = null;
		for (Entity entity: d.getEntities()) {
			if (entity instanceof Potion) {
				foundPotion = (Potion) entity;
			}
		}
		assert(foundPotion == potion);
		assert(potion.getX() == 1 && potion.getY() == 5);
		assert(!(p.playerIsInvincible()));
		p.moveDown();
		assert(p.playerIsInvincible());
		assert(p.getInventory().size() == 3);
		for (Entity entity: d.getEntities()) {
			assert(!(entity instanceof Potion));
		}
		for (Entity item: p.getInventory()) {
			assert(!(item instanceof Potion));
		}
	}

}
