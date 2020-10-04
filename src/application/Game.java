package application;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.GameModel;

public class Game extends HBox {
    private Controller controller;
    public GameModel gameModel;
    public Button restart;

    public Game() {
        controller = new Controller();
        initUI();
    }

    private void setRestart() {
        restart = new Button();
        Image img = new Image("file:images/smile.png");
        ImageView view = new ImageView(img);
        view.setFitWidth(30);
        view.setFitHeight(30);
        restart.setGraphic(view);
    }

    private void setGameModel() {
        gameModel = new GameModel(19, 15, 10, 5, 2);
        gameModel.boardUI();
    }

    private void initUI() {
        setGameModel();
        setRestart();

        Button level = new Button("LEVEL");


        VBox flagInfo = new VBox();
        flagInfo.setPadding(new Insets(20, 10, 20, 10));
        // TODO: 添加listener/image单独的view？/label值修改
        for (int i = 0; i < 3; i++) {
            Label flag = new Label("5");
            Image img = new Image("file:images/flag" + (i+1) + ".png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            flag.setGraphic(view);
            flagInfo.getChildren().add(flag);
        }
        flagInfo.setAlignment(Pos.CENTER);


        BorderPane info = new BorderPane();
        info.setPadding(new Insets(10, 10, 10, 10));
        info.setTop(restart);
        info.setCenter(flagInfo);
        info.setBottom(level);
        info.setAlignment(restart, Pos.CENTER);

        this.getChildren().addAll(info, gameModel);

        controller.initListener(this);
    }

    public void newGame() {
        this.getChildren().remove(gameModel);
        setGameModel();
        this.getChildren().add(gameModel);
        controller.initListener(this);
    }
}
