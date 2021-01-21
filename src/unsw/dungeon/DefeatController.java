package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
/**
 * Controller class for the defeat screen
 * @author jamespo
 *	Handles the button presses for the DefaultScreen shown after the player dies
 */
public class DefeatController {

	@FXML
	private Button playButton;
	private Button menuButton;
	
	@FXML
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
