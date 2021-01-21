package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * The difficulty screen that is shown after the player chooses a mode to play in
 * @author jamespo
 *
 */
public class DifficultyScreen {
	private static Stage stage;
    private static String title;
    private DifficultyController controller;
    private static Scene scene;

    public DifficultyScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "DIFFICULTY";

        controller = new DifficultyController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Difficulty.fxml"));
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

    public DifficultyController getController() {
        return controller;
    }
}
