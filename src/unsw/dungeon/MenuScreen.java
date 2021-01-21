package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * The menu screen that is displayed to the user at launch or when they choose to go
 * back to the main menu
 * @author jamespo
 *
 */
public class MenuScreen {
	private static Stage stage;
    private static String title;
    private MenuController controller;
    private static Scene scene;

    public MenuScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "RUNESCAPE 2.0";

        controller = new MenuController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
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

    public MenuController getController() {
        return controller;
    }

	public void close() {
		stage.close();
	}

}