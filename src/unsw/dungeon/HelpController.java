package unsw.dungeon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controller that handles user input when the player goes to the help screen
 * @author jamespo
 *
 */
public class HelpController {

	@FXML
	private Button backButton;
	private MenuScreen menuScreen;
	private HelpScreen helpScreen;
	private PauseScreen pauseScreen;
	
	@FXML
	public void handleBackButton(ActionEvent event) {
		if (helpScreen.inGame) {
			helpScreen.inGame = false;
			pauseScreen.start();
		} else {
			menuScreen.start();
		}
	}
	
	public void setMenuScreen(MenuScreen menuScreen) {
		this.menuScreen = menuScreen;
	}

	public void setHelpScreen(HelpScreen helpScreen) {
		this.helpScreen = helpScreen;
	}
	
	public void setPauseScreen(PauseScreen pauseScreen) {
		this.pauseScreen = pauseScreen;
	}
}