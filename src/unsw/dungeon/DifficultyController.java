package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * The controller for the difficulty screen
 * @author jamespo
 * Handles the button presses for the difficulty screen
 */
public class DifficultyController {

	@FXML
	private Button easyButton;
	private Button mediumButton;
	private Button hardButton;
	private Button backButton;
	private boolean levels = false;
	
	private MenuScreen menuScreen;
	private DungeonScreen dungeonScreen;
	
	@FXML
	public void handleEasyButton(ActionEvent event) throws IOException {
		if (levels) {
			levels = false;
			dungeonScreen.start("1.json", menuScreen.getController().multiplayer);
		} else {
			levels = true;
			dungeonScreen.start("2.json", menuScreen.getController().multiplayer);
		}
	}
	
	@FXML
	public void handleMediumButton(ActionEvent event) throws IOException {
		if (levels) {
			levels = false;
			dungeonScreen.start("3.json", menuScreen.getController().multiplayer);
		} else {
			levels = true;
			dungeonScreen.start("4.json", menuScreen.getController().multiplayer);
		}
	}
	
	@FXML
	public void handleHardButton(ActionEvent event) throws IOException {
		if (levels) {
			levels = false;
			dungeonScreen.start("5.json", menuScreen.getController().multiplayer);
		} else {
			levels = true;
			dungeonScreen.start("6.json", menuScreen.getController().multiplayer);
		}
	}
	
	@FXML
	public void handleBackButton(ActionEvent event)  throws IOException {
		menuScreen.start();
	}
	
	public void setMenuScreen(MenuScreen menuScreen) {
        this.menuScreen = menuScreen;
    }
	
	public void setDungeonScreen(DungeonScreen dungeonScreen) {
		this.dungeonScreen = dungeonScreen;
		
	}
}
