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

        LevelPane levelPane = new LevelPane();
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

        levelPane.buttons[0].setOnMouseClicked(e->{
            GamePane gamePane = new GamePane(20, 0, 0);
            Scene gameScene = new Scene(gamePane);
            stage.setScene(gameScene);

            gamePane.level.setOnMouseClicked(event->{
                stage.setScene(levelScene);
            });
        });
        levelPane.buttons[1].setOnMouseClicked(e->{
            GamePane gamePane = new GamePane(20, 10, 0);
            Scene gameScene = new Scene(gamePane);
            stage.setScene(gameScene);

            gamePane.level.setOnMouseClicked(event->{
                stage.setScene(levelScene);
            });
        });
        levelPane.buttons[2].setOnMouseClicked(e->{
            GamePane gamePane = new GamePane(20, 10, 5);
            Scene gameScene = new Scene(gamePane);
            stage.setScene(gameScene);

            gamePane.level.setOnMouseClicked(event->{
                stage.setScene(levelScene);
            });
        });
        levelPane.buttons[3].setOnMouseClicked(e->{
            String oneValue = levelPane.oneInput.getText();
            String twoValue = levelPane.twoInput.getText();
            String threeValue = levelPane.threeInput.getText();
            GamePane gamePane4 = new GamePane(Integer.parseInt(oneValue), Integer.parseInt(twoValue), Integer.parseInt(threeValue));
            Scene gameScene4 = new Scene(gamePane4);
            stage.setScene(gameScene4);

            gamePane4.level.setOnMouseClicked(event->{
                stage.setScene(levelScene);
            });
        });

    }

}
