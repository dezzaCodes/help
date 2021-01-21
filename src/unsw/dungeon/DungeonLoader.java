package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;
    boolean multiplayer;
    ArrayList<String> goalsList = new ArrayList<String>();
	private ArrayList<BooleanProperty> goalsBoolean = new ArrayList<BooleanProperty>();

    public DungeonLoader(String filename, boolean multiplayer) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
        this.multiplayer = multiplayer;
    }
    
    /**
     * Parses the JSON to create a dungeon.
     * @return - the instance of the dungeon it created
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        
        JSONObject jsonGoals = json.getJSONObject("goal-condition");
        dungeon.setGoals(loadGoals(dungeon, jsonGoals));
        dungeon.getGoals().goalName();
        dungeon.multiplayer = this.multiplayer;
        dungeon.goalsList = this.goalsList;
        dungeon.goalsBoolean = this.goalsBoolean;
        return dungeon;
    }
    
    /**
     * Parses the json to create the goals for the game
     * @param dungeon - the dungeon the goals are added to
     * @param json - the json file being read
     * @return - the goals for the game
     */
    private GoalComponent loadGoals(Dungeon dungeon, JSONObject json) {
    	String type = json.getString("goal");
    	goalsList.add(type);
    	goalsBoolean.add(new SimpleBooleanProperty(false));
    	GoalComponent goal = null;
    	switch (type) {
    	case "exit":
    		goal = new GoalSingle(dungeon, "exit");
    		break;
    	case "enemies":
    		goal = new GoalSingle(dungeon, "enemies");
    		break;
    	case "treasure":
    		goal = new GoalSingle(dungeon, "treasure");
    		break;
    	case "boulders":
    		goal = new GoalSingle(dungeon, "boulders");
    		break;
    	case "AND":
    		goal = new GoalAnd("AND");
    		for (int i = 0; i < json.getJSONArray("subgoals").length(); i++) {
    			goal.addGoal(loadGoals(dungeon, json.getJSONArray("subgoals").getJSONObject(i)));
    		}
    		break;
    	case "OR":
    		goal = new GoalOr("OR");
    		for (int i = 0; i < json.getJSONArray("subgoals").length(); i++) {
    			goal.addGoal(loadGoals(dungeon, json.getJSONArray("subgoals").getJSONObject(i)));
    		}
    		break;
    	}
    	return goal;
    }
	
    /**
     * Function that loads the entity instances into the game read from the json file
     * @param dungeon - the dungeon instance of the game
     * @param json - the json file being read
     */
	private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            dungeon.setPlayer(player);
            onLoad(player, false, dungeon);
            entity = player;
            
            if (this.multiplayer) {
            	Player p1 = new Player(dungeon, x, y);
            	dungeon.setP1(p1);
                onLoad(p1, true, dungeon);
                dungeon.addEntity(p1);
            }
            
            break;
        case "wall":
            Wall wall = new Wall(x, y);
            onLoad(wall, false);
            entity = wall;
            break;
        case "bomb":
            Bomb unlitBomb = new Bomb(x, y);
            onLoad(unlitBomb, false);
            entity = unlitBomb;
            break;
        case "switch":
            Plate plate = new Plate(x, y);
            onLoad(plate, false);
            entity = plate;
            break;    
        case "boulder":
            Boulder boulder = new Boulder(dungeon, x, y);
            onLoad(boulder, false);
            entity = boulder;
            break;
        case "treasure":
            Treasure treasure = new Treasure(x, y);
            onLoad(treasure, false);
            entity = treasure;
            break;
        case "exit":
            Exit exit = new Exit(x, y);
            onLoad(exit, false);
            entity = exit;
            break;
        case "invincibility":
            Potion potion = new Potion(x, y);
            onLoad(potion, false);
            entity = potion;
            break;
        case "sword":
            Sword sword = new Sword(x, y);
            onLoad(sword, false);
            entity = sword;
            break;
        case "enemy":
            Enemy enemy = new Enemy(dungeon, x, y);
            onLoad(enemy, false);
            entity = enemy;
            break;
        case "key":
        	int id = json.getInt("id");
        	Key key = new Key(x, y, id);
        	onLoad(key, false);
        	entity = key;
        	break;
        case "door":
        	id = json.getInt("id");
        	Door Door = new Door(x, y, id);
        	onLoad(Door, false);
        	entity = Door;
        	break;
        case "hound":
        	EnemyHound hound = new EnemyHound(dungeon,x,y);
        	onLoad(hound, false);
        	entity = hound;
        	break;
        }
        
        dungeon.addEntity(entity);
    }


	/**
	 * Abstract methods for the entities that are to be loaded
	 */
	public abstract void onLoad(Entity player, boolean b, Dungeon dungeon);
    public abstract void onLoad(Wall wall, boolean b);
    public abstract void onLoad(Bomb unlitBomb, boolean b);
    public abstract void onLoad(Plate plate, boolean b);
    public abstract void onLoad(Boulder boulder, boolean b);
    public abstract void onLoad(Treasure treasure, boolean b);
    public abstract void onLoad(Exit exit, boolean b);
    public abstract void onLoad(Enemy enemy, boolean b);
    public abstract void onLoad(Sword sword, boolean b);
    public abstract void onLoad(Potion potion, boolean b);
    public abstract void onLoad(Key key, boolean b);
    public abstract void onLoad(Door Door, boolean b);
    public abstract void onLoad(EnemyHound hound, boolean b);
}
