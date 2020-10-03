package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        Game game = new Game();
        root.getChildren().add(game);

        Scene scene = new Scene(root);

        stage.setHeight(405);
        stage.setMaxHeight(405);
        stage.setMinHeight(405);

        stage.setWidth(620);
        stage.setMaxWidth(620);
        stage.setMinWidth(620);

        stage.setTitle("Minesweeper");
        stage.setScene(scene);
        stage.show();
    }

}
