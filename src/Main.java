import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application implements Config {

    /**
     * Starter JavaFX vindu
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        Canvas canvas = new Canvas();
        canvas.setWidth(WINDOW_X);
        canvas.setHeight(WINDOW_Y);
        root.getChildren().add(canvas);
        Scene scene = new Scene(root, WINDOW_X, WINDOW_Y);
        stage.setScene(scene);
        stage.setTitle(APP_NAME);
        stage.show();
        stage.setX(stage.getX()-WINDOW_X/4);
        Menu menu = new Menu(canvas, stage.getX()+WINDOW_X+WINDOW_X/48, stage.getY(), WINDOW_X/2, WINDOW_Y);

        stage.setOnCloseRequest(e-> menu.close());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
