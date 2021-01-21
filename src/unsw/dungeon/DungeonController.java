package unsw.dungeon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

    @FXML
    private GridPane squares;
   	private DifficultyScreen difficultyScreen;
   	private MenuScreen menuScreen;
    private VictoryScreen victoryScreen;
    private DefeatScreen defeatScreen;
    private List<ImageView> initialEntities;
    private Player player;
    public Dungeon dungeon;
	private Player p1;
	private boolean multiplayer;
	private PauseScreen pauseScreen;
	private DungeonScreen dungeonScreen;
	private ArrayList<String> goalsList;

	/**
	 * Constructor for the dungeon controller
	 * @param dungeon - The reference to the dungeon it is constructing in 
	 * @param initialEntities - list of images of the entity it is constructing
	 */
    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.p1 = dungeon.getP1();
        this.multiplayer = dungeon.multiplayer;
        this.initialEntities = new ArrayList<>(initialEntities);
        this.goalsList = dungeon.goalsList;
    }

    /**
     * Function that initializes all the images of the entities for the dungeon screen
     */
    @FXML
    public void initialize() {
        Image ground = new Image("/dirt_0_new.png");

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);
    }

    /**
     * Function that takes in user input to move the player or display a settings menu on the screen
     * @param event - the keypress event
     * @throws IOException
     */
    @FXML
    public void handleKeyPress(KeyEvent event) throws IOException {
        switch (event.getCode()) {
        case ESCAPE:
        	pauseScreen.start();
        	break;
        case UP:
            player.moveUp();
            break;
        case DOWN:
            player.moveDown();
            break;
        case LEFT:
            player.moveLeft();
            break;
        case RIGHT:
            player.moveRight();
            break;
        case SPACE:
        	player.dropBomb(player.getX(), player.getY());
            break;
        default:
            break;
        }
        
        if (this.multiplayer) {
        	switch (event.getCode()) {
            case W:
                p1.moveUp();
                break;
            case S:
                p1.moveDown();
                break;
            case A:
                p1.moveLeft();
                break;
            case D:
                p1.moveRight();
                break;
            case B:
            	p1.dropBomb(p1.getX(), p1.getY());
                break;
            default:
                break;
            }
        }
        if (dungeon.getGoals().isGoalComplete()) {
        	victoryScreen.start();
        }
        if (dungeon.noPlayersAlive()) {
        	defeatScreen.start();
        }
    }

    /**
     * All the functions below are setters to connect the different menu screens
     */
    
	public void setDifficultyScreen(DifficultyScreen difficultyScreen) {
		this.difficultyScreen = difficultyScreen;
	}

	public void setMenuScreen(MenuScreen menuScreen) {
		this.menuScreen = menuScreen;
		
	}

	public void setPauseScreen(PauseScreen pauseScreen) {
		this.pauseScreen = pauseScreen;
	}

	public void setDungeonScreen(DungeonScreen dungeonScreen) {
		this.dungeonScreen = dungeonScreen;
		
	}

	public void setVictoryScreen(VictoryScreen victoryScreen) {
		this.victoryScreen = victoryScreen;
	}

}

