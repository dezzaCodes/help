package unsw.dungeon.OldTests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Door;
import unsw.dungeon.Dungeon;
import unsw.dungeon.GoalSingle;
import unsw.dungeon.Key;
import unsw.dungeon.Player;

class DoorTest {
	private Dungeon d;
	private Player p;
	private Door door0;
	private Door door1;
	private Key key0;
	private Key key1;
	
	@BeforeEach
	void setUp() throws Exception {
		d = new Dungeon(10, 10);
		p = new Player(d, 1, 1);
		door0 = new Door(2,2,0);
		door1 = new Door(2,4,1);
		key0 = new Key(1,3,0);
		key1 = new Key(1,5,1);
		d.addEntity(p);
		d.addEntity(door0);
		d.addEntity(door1);
		d.addEntity(key0);
		d.addEntity(key1);
		d.setGoals(new GoalSingle(d, "boulder"));
	}

	// Testing that a closed door doesn't allow a player through
	@Test
	void testDoorClosedBlocking() {
		assert(p.getX() == 1 && p.getY() == 1);
		p.moveDown();
		assert(p.getX() == 1 && p.getY() == 2);
		assert(door0.getX() == 2 && door0.getY() == 2);
		assert(door0.isOpen() == false);
		p.moveRight();
		// Testing that you can't move through a closed Door
		assert(p.getX() == 1 && p.getY() == 2);
	}
	
	// Testing that with the right key, you can open a door and player can walk through
	@Test
	void testOpeningDoor() {
		assert(p.getX() == 1 && p.getY() == 1);
		p.moveDown();
		assert(p.getX() == 1 && p.getY() == 2);
		assert(door0.getX() == 2 && door0.getY() == 2);
		p.moveRight();
		assert(p.getX() == 1 && p.getY() == 2);
		assert(p.checkKeyInventory() == null);
		p.moveDown();
		// Testing that the player picks up the key
		assert(key0.getX() == 1 && key0.getY() == 3);
		assert(p.getX() == 1 && p.getY() == 3);
		assert(p.checkKeyInventory() != null);
		assert(p.checkKeyInventory() == key0);
		p.moveUp();
		assert(p.getX() == 1 && p.getY() == 2);
		// Testing that they key is matching key to the door
		assert(key0.getKeyNo() == door0.getDoorNo());
		// Testing that after the player moves right, the door opens since they have the right key
		assert(door0.isOpen() == false);
		p.moveRight();
		assert(door0.isOpen() == true);
		// Testing that you can now go through the door that was closed in previous test
		assert(p.getX() == 2 && p.getY() == 2);
	}
	
	// Testing that with the wrong key, the door stays closed and then player can't walk through
	@Test
	void testWrongKey() {
		assert(p.getX() == 1 && p.getY() == 1);
		p.moveDown();
		assert(p.getX() == 1 && p.getY() == 2);
		p.moveDown();
		assert(key0.getX() == 1 && key0.getY() == 3);
		assert(p.getX() == 1 && p.getY() == 3);
		p.moveDown();
		assert(p.getX() == 1 && p.getY() == 4);
		assert(door1.getX() == 2 && door1.getY() == 4);
		assert(p.checkKeyInventory() == key0);
		// Testing that the key doesn't match this current door
		assert(key0.getKeyNo() != door1.getDoorNo());
		assert(door1.isOpen() == false);
		p.moveRight();
		// Testing that you can't open a door with a the wrong key
		assert(p.getX() == 1 && p.getY() == 4);
		assert(door1.isOpen() == false);
	}
	
	// Testing that when you open a door, that door is the only door that opens
	@Test
	void testOnlyCorrectDoorOpens() {
		assert(p.getX() == 1 && p.getY() == 1);
		p.moveDown();
		assert(p.getX() == 1 && p.getY() == 2);
		assert(door0.getX() == 2 && door0.getY() == 2);
		p.moveRight();
		assert(p.getX() == 1 && p.getY() == 2);
		assert(p.checkKeyInventory() == null);
		p.moveDown();
		assert(key0.getX() == 1 && key0.getY() == 3);
		assert(p.getX() == 1 && p.getY() == 3);
		assert(p.checkKeyInventory() != null);
		assert(p.checkKeyInventory() == key0);
		p.moveUp();
		assert(p.getX() == 1 && p.getY() == 2);
		assert(key0.getKeyNo() == door0.getDoorNo());
		assert(door0.isOpen() == false);
		p.moveRight();
		assert(door0.isOpen() == true);
		assert(p.getX() == 2 && p.getY() == 2);
		p.moveLeft();
		p.moveDown();
		p.moveDown();
		assert(p.getX() == 1 && p.getY() == 4);
		assert(door1.getX() == 2 && door1.getY() == 4);
		assert(door1.isOpen() == false);
		p.moveRight();
		assert(p.getX() == 1 && p.getY() == 4);
		assert(door1.isOpen() == false);
	}
	
	// Testing that the player can only hold one key at a time, and drops the other key
	@Test
	void testOnlyOneKey() {
		assert(p.getX() == 1 && p.getY() == 1);
		assert(p.getInventory().size() == 0);
		p.moveDown();
		p.moveDown();
		assert(p.getX() == 1 && p.getY() == 3);
		assert(key0.getX() == 1 && key0.getY() == 3);
		assert(p.checkKeyInventory() == key0);
		assert(p.getInventory().size() == 1);
		p.moveDown();
		assert(key1.getX() == 1 && key1.getY() == 5);
		p.moveDown();
		assert(p.getX() == 1 && p.getY() == 5);
		// Testing that the player picks up the new key when walking over it
		assert(p.checkKeyInventory() == key1);
		// Testing that the player only can hold one key at a time
		assert(p.getInventory().size() == 1);
		// Testing that the other key then drops onto the position that the player is at
		assert(key0.getX() == 1 && key0.getY() == 5);
		p.moveDown();
		p.moveUp();
		// Walking back over the key will swap it again
		assert(p.getInventory().size() == 1);
		assert(p.checkKeyInventory() == key0);
		assert(key1.getX() == 1 && key1.getY() == 5);
	}
	
	// Testing that swapping the key changing the key ID you have and you can't open the wrong door
	@Test
	void testSwappingKeyWrongDoor() {
		assert(p.getX() == 1 && p.getY() == 1);
		p.moveDown();
		p.moveDown();
		assert(p.checkKeyInventory() == key0);
		p.moveDown();
		p.moveDown();
		assert(p.checkKeyInventory() == key1);
		p.moveUp();
		p.moveUp();
		p.moveUp();
		assert(p.checkKeyInventory() == key1);
		assert(p.getX() == 1 && p.getY() == 2);
		assert(door0.getX() == 2 && door0.getY() == 2);
		assert(door0.isOpen() == false);
		assert(door0.getDoorNo() != key1.getKeyNo());
		p.moveRight();
		assert(p.getX() == 1 && p.getY() == 2);
		assert(door0.isOpen() == false);
	}
	
	// Testing that after using a correct key, it is deleted from the player's inventory
	@Test
	void testRemovalCorrectKey() {
		assert(p.getX() == 1 && p.getY() == 1);
		assert(p.getInventory().size() == 0);
		p.moveDown();
		p.moveDown();
		assert(p.checkKeyInventory() == key0);
		assert(p.getInventory().size() == 1);
		p.moveUp();
		assert(p.getX() == 1 && p.getY() == 2);
		assert(door0.getX() == 2 && door0.getY() == 2);
		assert(door0.isOpen() == false);
		assert(door0.getDoorNo() == key0.getKeyNo());
		p.moveRight();
		assert(p.getX() == 2 && p.getY() == 2);
		assert(door0.isOpen() == true);
		assert(p.getInventory().size() == 0);
		assert(p.checkKeyInventory() == null);
	}
	
	// Testing that after using a wrong key, it is not deleted from the player's inventory
	@Test
	void testRemovalWrongKey() {
		assert(p.getX() == 1 && p.getY() == 1);
		assert(p.getInventory().size() == 0);
		p.moveDown();
		p.moveDown();
		assert(p.checkKeyInventory() == key0);
		assert(p.getInventory().size() == 1);
		p.moveDown();
		assert(p.getX() == 1 && p.getY() == 4);
		assert(door1.getX() == 2 && door1.getY() == 4);
		assert(door1.isOpen() == false);
		assert(door1.getDoorNo() != key0.getKeyNo());
		assert(p.checkKeyInventory() == key0);
		assert(p.getInventory().size() == 1);
		p.moveRight();
		assert(p.getX() == 1 && p.getY() == 4);
		assert(door1.isOpen() == false);
		assert(p.getInventory().size() == 1);
		assert(p.checkKeyInventory() == key0);
	}
	
	// Testing that after opening a door, the door remains open
		@Test
		void testDoorStaysOpen() {
			assert(p.getX() == 1 && p.getY() == 1);
			p.moveDown();
			p.moveDown();
			assert(p.checkKeyInventory() == key0);
			p.moveUp();
			assert(p.getX() == 1 && p.getY() == 2);
			assert(door0.getX() == 2 && door0.getY() == 2);
			assert(door0.isOpen() == false);
			p.moveRight();
			assert(door0.isOpen() == true);
			assert(p.getX() == 2 && p.getY() == 2);
			p.moveRight();
			assert(door0.isOpen() == true);
			assert(p.getX() == 3 && p.getY() == 2);
			p.moveLeft();
			assert(door0.isOpen() == true);
			assert(p.getX() == 2 && p.getY() == 2);
			p.moveLeft();
			assert(door0.isOpen() == true);
			assert(p.getX() == 1 && p.getY() == 2);
		}
	
}