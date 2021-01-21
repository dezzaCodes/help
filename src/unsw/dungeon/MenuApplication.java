package unsw.dungeon;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main application starter for the game, takes user to the main menu when launched
 * @author jamespo
 *
 */
public class MenuApplication extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		 
		MenuScreen menuScreen = new MenuScreen(primaryStage);
		DifficultyScreen difficultyScreen = new DifficultyScreen(primaryStage);
		DungeonScreen dungeonScreen = new DungeonScreen(primaryStage);
		VictoryScreen victoryScreen = new VictoryScreen(primaryStage);
		PauseScreen pauseScreen = new PauseScreen(primaryStage);
		DefeatScreen defeatScreen = new DefeatScreen(primaryStage);
		HelpScreen helpScreen = new HelpScreen(primaryStage);
        // Both controllers need to know about the other screen.

        menuScreen.getController().setDifficultyScreen(difficultyScreen);
        menuScreen.getController().setPauseScreen(pauseScreen);
        menuScreen.getController().setDungeonScreen(dungeonScreen);
        menuScreen.getController().setMenuScreen(menuScreen);
        menuScreen.getController().setHelpScreen(helpScreen);
        
        difficultyScreen.getController().setDungeonScreen(dungeonScreen);
        difficultyScreen.getController().setMenuScreen(menuScreen);
        
        helpScreen.getController().setMenuScreen(menuScreen);
        helpScreen.getController().setHelpScreen(helpScreen);
        helpScreen.getController().setPauseScreen(pauseScreen);
        
        pauseScreen.getController().setHelpScreen(helpScreen);
        pauseScreen.getController().setDungeonScreen(dungeonScreen);
        pauseScreen.getController().setMenuScreen(menuScreen);
        
        victoryScreen.getController().setMenuScreen(menuScreen);
        victoryScreen.getController().setDungeonScreen(dungeonScreen);
        
        defeatScreen.getController().setMenuScreen(menuScreen);
        defeatScreen.getController().setDungeonScreen(dungeonScreen);
        
        dungeonScreen.getController().setPauseScreen(pauseScreen);
        dungeonScreen.getController().setMenuScreen(menuScreen);
        dungeonScreen.getController().setDungeonScreen(dungeonScreen);
        dungeonScreen.getController().setVictoryScreen(victoryScreen);
	    
		menuScreen.start();
	    }

	    public static void main(String[] args) {
	        launch(args);
	    }
		
	}