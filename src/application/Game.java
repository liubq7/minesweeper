package application;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.GameModel;

import java.util.ArrayList;

public class Game extends HBox {
    private Controller controller;
    public GameModel gameModel;
    public Button restart;
    public ArrayList<Label> flagLabelList;
    private int[] bombNumList;

    public Game() {
        controller = new Controller();
        initUI();
    }

    private void initRestart() {
        restart = new Button();
        Image img = new Image("file:images/smile.png");
        ImageView view = new ImageView(img);
        view.setFitWidth(30);
        view.setFitHeight(30);
        restart.setGraphic(view);
        restart.setTooltip(new Tooltip("New Game"));
    }

    private void initGameModel() {
        gameModel = new GameModel(19, 15, 10, 5, 2);
        gameModel.boardUI();
    }

    private void initFlagLabelList() {
        bombNumList = new int[]{10, 5, 2};
        flagLabelList = new ArrayList<>();
    }

    private void setFlagLabelList() {
        bombNumList = new int[]{10, 5, 2};
        for (int i = 0; i < 3; i++) {
            flagLabelList.get(i).setText(Integer.toString(bombNumList[i]));
        }
    }

    private void initUI() {
        initGameModel();
        initRestart();

        Button level = new Button("LEVEL");


        VBox flagInfo = new VBox();
        flagInfo.setPadding(new Insets(20, 10, 20, 10));

        initFlagLabelList();
        for (int i = 0; i < 3; i++) {
            flagLabelList.add(i, new Label(Integer.toString(bombNumList[i])));
            Image img = new Image("file:images/flag" + (i+1) + ".png");
            ImageView view = new ImageView(img);
            view.setFitWidth(20);
            view.setFitHeight(20);
            flagLabelList.get(i).setGraphic(view);
            flagInfo.getChildren().add(flagLabelList.get(i));
        }
        flagInfo.setAlignment(Pos.CENTER);


        BorderPane info = new BorderPane();
        info.setPadding(new Insets(10, 10, 10, 10));
        info.setPrefWidth(80);
        info.setTop(restart);
        info.setCenter(flagInfo);
        info.setBottom(level);
        info.setAlignment(restart, Pos.CENTER);

        this.getChildren().addAll(info, gameModel);

        controller.initListener(this);
    }

    public void newGame() {
        this.getChildren().remove(gameModel);
        initGameModel();
        this.getChildren().add(gameModel);
        setFlagLabelList();
        controller.initListener(this);
    }
}
