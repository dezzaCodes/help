package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controller that handles input from the user during the pause screen
 * @author jamespo
 *
 */
public class PauseController {

	@FXML
	private Button resumeButton;
	private Button helpButton;
	private Button restartButton;
	private Button exitButton;
	
	@FXML
	private MenuScreen menuScreen;
	private DungeonScreen dungeonScreen;
	private HelpScreen helpScreen;
	
	@FXML
	public void handleResumeButton(ActionEvent event) throws IOException {
		dungeonScreen.resume();
	}
	
	@FXML
	public void handleHelpButton(ActionEvent event) throws IOException {
		helpScreen.start(1);
	}
	
	@FXML
	public void handleRestartButton(ActionEvent event) throws IOException {
		dungeonScreen.start(dungeonScreen.level, dungeonScreen.multiplayer);
	}
	
	@FXML
	public void handleExitButton(ActionEvent event) throws IOException {
		menuScreen.start();
	}
	
	public void setMenuScreen(MenuScreen menuScreen) {
        this.menuScreen = menuScreen;
    }
	
	public void setDungeonScreen(DungeonScreen dungeonScreen) {
		this.dungeonScreen = dungeonScreen;
	}
	
	public void setHelpScreen(HelpScreen helpScreen) {
		this.helpScreen = helpScreen;
	}
}
