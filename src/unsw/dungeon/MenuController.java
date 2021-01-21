package unsw.dungeon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controller that handles user input on the main menu
 * @author jamespo
 *
 */
public class MenuController {

	@FXML
	private Button singleButton;
	private Button coopButton;
	private Button helpButton;
	private Button exitButton;
	
	private DifficultyScreen difficultyScreen;
	private DungeonScreen dungeonScreen;
	private PauseScreen pauseScreen;
	
	public boolean multiplayer;
	private MenuScreen menuScreen;
	private HelpScreen helpScreen;
	
	@FXML
	public void handleSingleButton(ActionEvent event) {
		multiplayer = false;
		difficultyScreen.start();
	}
	
	@FXML
	public void handleCoopButton(ActionEvent event) {
		multiplayer = true;
		difficultyScreen.start();
	}
	
	@FXML
	public void handleHelpButton(ActionEvent event) {
		helpScreen.start();
	}
	
	@FXML
	public void handleExitButton(ActionEvent event) {
		menuScreen.close();
	}
	
	public void setDifficultyScreen(DifficultyScreen difficultyScreen) {
        this.difficultyScreen = difficultyScreen;
    }

	public void setDungeonScreen(DungeonScreen dungeonScreen) {
		this.dungeonScreen = dungeonScreen;
	}

	public void setPauseScreen(PauseScreen pauseScreen) {
		this.pauseScreen = pauseScreen;
	}
	
	public void setMenuScreen(MenuScreen menuScreen) {
		this.menuScreen = menuScreen;
	}

	public void setHelpScreen(HelpScreen helpScreen) {
		this.helpScreen = helpScreen;
		
	}
}