package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The main dungeon screen for the game while it is being played
 * @author jamespo
 *
 */
public class DungeonScreen {
	private  Stage stage;
    private  String title;
    private DungeonController controller;
    private  Scene scene;
    public String level;
    public boolean multiplayer;
    BorderPane pane = new BorderPane();
    GridPane grid = new GridPane();
    Parent root;
    ArrayList<String> goals;
    
    /**
     * Constructor for the dungeon screen
     * @param stage - the state of the screen
     * @throws IOException
     */
	public DungeonScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "RUNESCAPE 2.0";

        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("1.json", false);

        controller = dungeonLoader.loadController();
        
        pane = new BorderPane();
        grid = new GridPane();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        scene = new Scene(root);
        root.requestFocus();
        
    	stage.setTitle(title);
        stage.setScene(scene);
        stage.show();

    }

	/**
	 * Function for when the game screen when the game starts
	 * @param string - the level
	 * @param multiplayer - boolean whether or not it is multiplayer
	 * @throws IOException
	 */
    public void start(String string, boolean multiplayer) throws IOException {
    	this.level = string;
    	this.multiplayer = multiplayer;
    	
    	DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(level, multiplayer);

        controller = dungeonLoader.loadController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));        

        pane = new BorderPane();
        grid = new GridPane();
        pane.setBottom(grid);
        
        goals = getController().dungeon.goalsList;
        
        int i = 1;
        grid.add(new Label("GOALS"), 0, 0);
        for (String s : goals) {
        	
        	if (s.equals("AND")) {
        		grid.add(new Label("COMPLETE ALL OF THE FOLLOWING:"), 1, i);
        	} else if (s.equals("OR")) {
        		grid.add(new Label("COMPLETE ONE OF THE FOLLOWING:"), 1, i);
        	} else {
        		if (s.equals("exit")) {
        			grid.add(new Label("Reach the exit"), 1, i);
        		} else if (s.equals("treasure")) {
        			grid.add(new Label("Collect all treasure"), 1, i);
        		} else if (s.equals("enemies")) {
        			grid.add(new Label("Kill all enemies"), 1, i);
        		} else if (s.equals("boulders")) {
        			grid.add(new Label("Push all boulders on plates"), 1, i);
        		}

        		CheckBox check = new CheckBox();
            	check.setFocusTraversable(false);
            	check.selectedProperty().bindBidirectional(getController().dungeon.goalsBoolean.get(i-1));
            	grid.add(check, 0, i);
        	}
        	i++;
        }
        
        grid.setBackground(new Background(new BackgroundFill(Color.rgb(192,192,192),null,null)));
        
        loader.setController(controller);
        root = loader.load();
        pane.setCenter(root);
        
        int pictureSize = 32;
        int ImageX = 32;
        Image invenBorder = new Image("/invenBorder.png");
        for (int j = 0; j < 18 ; j++) {
        	if (j==8 && multiplayer) {
        		ImageX+= pictureSize;
        		continue;
        	}
        	ImageView view = new ImageView(invenBorder);
        	view.setX(ImageX);
            view.setY(0);
            pane.getChildren().add(view);
            ImageX += pictureSize;
        }
        
        scene = new Scene(pane);
        root.requestFocus();
        
    	stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * Function that resumes the game back to the game screen after 
     * you bring up the pause menu
     */
    public void resume() {
    	stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * Similar to the start function, this function is for when the game 
     * screen when the game restarts after the player chooses to do so
     * @throws IOException
     */
    public void restart() throws IOException {
    	pane = new BorderPane();
        grid = new GridPane();
    	
    	DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(level, multiplayer);
        controller = dungeonLoader.loadController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);
        root = loader.load();
        
        int i = 1;
        grid.add(new Label("GOALS"), 0, 0);
        for (String s : goals) {
        	
        	if (s.equals("AND")) {
        		grid.add(new Label("COMPLETE ALL OF THE FOLLOWING:"), 1, i);
        	} else if (s.equals("OR")) {
        		grid.add(new Label("COMPLETE ONE OF THE FOLLOWING:"), 1, i);
        	} else {
        		if (s.equals("exit")) {
        			grid.add(new Label("Reach the exit"), 1, i);
        		} else if (s.equals("treasure")) {
        			grid.add(new Label("Collect all treasure"), 1, i);
        		} else if (s.equals("enemies")) {
        			grid.add(new Label("Kill all enemies"), 1, i);
        		} else if (s.equals("boulders")) {
        			grid.add(new Label("Push all boulders on plates"), 1, i);
        		}

        		CheckBox check = new CheckBox();
            	check.setFocusTraversable(false);
            	check.selectedProperty().bindBidirectional(getController().dungeon.goalsBoolean.get(i-1));
            	grid.add(check, 0, i);
        	}
        	i++;
        }
        
        grid.setBackground(new Background(new BackgroundFill(Color.rgb(192,192,192),null,null)));
        
        pane.setBottom(grid);
		pane.setCenter(root);
		
		int pictureSize = 32;
        int ImageX = 32;
        Image invenBorder = new Image("/invenBorder.png");
        for (int j = 0; j < 18 ; j++) {
        	if (j==8 && multiplayer) {
        		ImageX+= pictureSize;
        		continue;
        	}
        	ImageView view = new ImageView(invenBorder);
        	view.setX(ImageX);
            view.setY(0);
            pane.getChildren().add(view);
            ImageX += pictureSize;
        }
		
        scene = new Scene(pane);
        root.requestFocus();
        
    	stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Getter function for the dungeonscreen controller
     */
    public DungeonController getController() {
        return controller;
    }
}
