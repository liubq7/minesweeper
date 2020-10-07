package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import view.GamePane;
import view.LevelPane;

public class Main extends Application {

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        GamePane gamePane = new GamePane();
        LevelPane levelPane = new LevelPane();

        Scene gameScene = new Scene(gamePane);
        Scene levelScene = new Scene(levelPane);
        stage.setScene(levelScene);

//        stage.setHeight(405);
//        stage.setMaxHeight(405);
//        stage.setMinHeight(405);
//
//        stage.setWidth(620);
//        stage.setMaxWidth(620);
//        stage.setMinWidth(620);

        stage.setTitle("Minesweeper");
        stage.show();

        levelPane.start1.setOnMouseClicked(e->{
            stage.setScene(gameScene);
        });
    }

}
