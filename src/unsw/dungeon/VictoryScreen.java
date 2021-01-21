package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Victory screen that is displayed to the user once that have won a game
 * @author jamespo
 *
 */
public class VictoryScreen {
	private static Stage stage;
    private static String title;
    private VictoryController controller;
    private static Scene scene;

    public VictoryScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Victory";

        controller = new VictoryController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Victory.fxml"));
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

    public VictoryController getController() {
        return controller;
    }

}