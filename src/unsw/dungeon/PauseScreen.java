package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Pause screen that is displayed to the user when they hit escape during the game
 * @author jamespo
 *
 */
public class PauseScreen {
	private static Stage stage;
    private static String title;
    private PauseController controller;
    private static Scene scene;

    public PauseScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Pause";

        controller = new PauseController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Pause.fxml"));
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

    public PauseController getController() {
        return controller;
    }

}
