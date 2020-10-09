package view;

import controller.Controller;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import model.GameModel;

import java.util.ArrayList;

public class GamePane extends HBox {
    private Controller controller;

    public GameModel gameModel;
    public Button restart;
    public Button level;
    private Label timerLabel;
    public ColorPane colorPane;

    private Timeline timeline = new Timeline();
    private IntegerProperty timeSeconds = new SimpleIntegerProperty(0);

    public ArrayList<Label> flagLabelList;
    private int[] bombNumList;

    private int oneBombNum;
    private int twoBombNum;
    private int threeBombNum;

    public GamePane(int obn, int tbn, int thbn) {
        oneBombNum = obn;
        twoBombNum = tbn;
        threeBombNum = thbn;

        controller = new Controller();

        level = new Button("LEVEL");
        level.setTooltip(new Tooltip("Choose Level"));

        colorPane = new ColorPane();

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

    private void initTime() {
        timerLabel.setFont(new Font("Constantia", 20));
        timerLabel.setTextFill(Color.RED);
        timerLabel.setPadding(new Insets(10, 0, 10, 0));
        timerLabel.textProperty().bind(timeSeconds.asString());
        if (timeline != null) {
            timeline.stop();
        }
        timeSeconds.set(0);
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1000),
                        new KeyValue(timeSeconds, 999)));
        timeline.playFromStart();
    }
    public void stopTime() {
        timeline.stop();
    }

    private void initGameModel() {
        gameModel = new GameModel(19, 15, oneBombNum, twoBombNum, threeBombNum);
        gameModel.boardUI();
    }

    private void initFlagLabelList() {
        bombNumList = new int[]{oneBombNum, twoBombNum, threeBombNum};
        flagLabelList = new ArrayList<>();
    }

    private void setFlagLabelList() {
        bombNumList = new int[]{oneBombNum, twoBombNum, threeBombNum};
        for (int i = 0; i < 3; i++) {
            flagLabelList.get(i).setText(Integer.toString(bombNumList[i]));
        }
    }

    private void initUI() {
        initGameModel();
        initRestart();

        timerLabel = new Label();
        initTime();

        BorderPane top = new BorderPane();
        top.setTop(restart);
        top.setCenter(timerLabel);
        top.setAlignment(restart, Pos.CENTER);
        top.setAlignment(timerLabel, Pos.CENTER);


        VBox flagInfo = new VBox();
        flagInfo.setPadding(new Insets(20, 5, 20, 5));

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
        info.setTop(top);
        info.setCenter(flagInfo);
        info.setBottom(level);
        info.setAlignment(level, Pos.CENTER);

        this.getChildren().addAll(info, gameModel, colorPane);

        controller.initListener(this);
    }

    public void newGame() {
        this.getChildren().remove(gameModel);
        this.getChildren().remove(colorPane);
        initGameModel();
        this.getChildren().addAll(gameModel, colorPane);
        setFlagLabelList();
        initTime();
        controller.initListener(this);
    }
}
