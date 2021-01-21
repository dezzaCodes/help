package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * The defeat screen that is shown after the player dies
 * @author jamespo
 *
 */
public class DefeatScreen {
	private static Stage stage;
    private static String title;
    private DefeatController controller;
    private static Scene scene;

    public DefeatScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Defeat";

        controller = new DefeatController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Defeat.fxml"));
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

    public DefeatController getController() {
        return controller;
    }

}