package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controller for the victory screen
 * @author jamespo
 * Handles user input from the buttons from the victory screen
 */
public class VictoryController {

	@FXML
	private Button playButton;
	private Button menuButton;

	private MenuScreen menuScreen;
	private DungeonScreen dungeonScreen;
	
	@FXML
	public void handlePlayButton(ActionEvent event) throws IOException {
		dungeonScreen.restart();
	}
	
	@FXML
	public void handleMenuButton(ActionEvent event) throws IOException {
		menuScreen.start();
	}
	
	public void setMenuScreen(MenuScreen menuScreen) {
        this.menuScreen = menuScreen;
    }
	
	public void setDungeonScreen(DungeonScreen dungeonScreen) {
        this.dungeonScreen = dungeonScreen;
    }
}
