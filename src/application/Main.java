package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.GamePane;
import view.LevelPane;

public class Main extends Application {

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        GamePane gamePane1 = new GamePane(20, 0, 0);
        Scene gameScene1 = new Scene(gamePane1);
        GamePane gamePane2 = new GamePane(20, 10, 0);
        Scene gameScene2 = new Scene(gamePane2);
        GamePane gamePane3 = new GamePane(20, 10, 5);
        Scene gameScene3 = new Scene(gamePane3);
        GamePane[] gamePanes = new GamePane[]{gamePane1, gamePane2, gamePane3};


        LevelPane levelPane = new LevelPane();
        Scene levelScene = new Scene(levelPane);
        stage.setScene(gameScene1);

//        stage.setHeight(405);
//        stage.setMaxHeight(405);
//        stage.setMinHeight(405);
//
//        stage.setWidth(620);
//        stage.setMaxWidth(620);
//        stage.setMinWidth(620);

        stage.setTitle("Minesweeper");
        stage.show();

        levelPane.buttons[0].setOnMouseClicked(e->{
            stage.setScene(gameScene1);
        });
        levelPane.buttons[1].setOnMouseClicked(e->{
            stage.setScene(gameScene2);
        });
        levelPane.buttons[2].setOnMouseClicked(e->{
            stage.setScene(gameScene3);
        });

        for (int i = 0; i < gamePanes.length; i++) {
            gamePanes[i].level.setOnMouseClicked(e->{
                stage.setScene(levelScene);
            });
        }
    }

}
