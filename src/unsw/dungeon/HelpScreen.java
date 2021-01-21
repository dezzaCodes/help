package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Class for the helpscreen displayed when the player chooses to go the help screen
 * @author jamespo
 *
 */
public class HelpScreen {
	private static Stage stage;
    private static String title;
    private HelpController controller;
    private static Scene scene;
    public boolean inGame = false;

    public HelpScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "HOW TO PLAY";

        controller = new HelpController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Help.fxml"));
        loader.setController(controller);

        // load into a Parent node called root
        Parent root = loader.load();
        scene = new Scene(root, 600, 400);
    }

    public static void start() {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public HelpController getController() {
        return controller;
    }

	public void start(int i) {
		inGame = true;
		stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
	}
}