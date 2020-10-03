package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.GameBoard;

public class Game extends HBox {

    public Game() {
        initUI();
    }

    private void initUI() {
        GameBoard gameBoard = new GameBoard(19, 15, 10, 0, 0);
        gameBoard.boardUI();

        Button restart = new Button("\uD83D\uDE03");
        Button level = new Button("LEVEL");


        VBox flag = new VBox();
        flag.setPadding(new Insets(20, 10, 20, 10));
        // TODO: 写成for循环
        Label flag1 = new Label("7");
        Image img1 = new Image("file:images/flag1.png");
        ImageView view1 = new ImageView(img1);
        view1.setFitHeight(20);
        view1.setFitWidth(20);
        flag1.setGraphic(view1);

        Label flag2 = new Label("5");
        Image img2 = new Image("file:images/flag2.png");
        ImageView view2 = new ImageView(img2);
        view2.setFitHeight(20);
        view2.setFitWidth(20);
        flag2.setGraphic(view2);

        Label flag3 = new Label("2");
        Image img3 = new Image("file:images/flag3.png");
        ImageView view3 = new ImageView(img3);
        view3.setFitHeight(20);
        view3.setFitWidth(20);
        flag3.setGraphic(view3);

        flag.setAlignment(Pos.CENTER);
        flag.getChildren().addAll(flag1, flag2, flag3);


        BorderPane info = new BorderPane();
        info.setPadding(new Insets(10, 10, 10, 10));
        info.setTop(restart);
        info.setCenter(flag);
        info.setBottom(level);
        info.setAlignment(restart, Pos.CENTER);

        this.getChildren().addAll(info, gameBoard);
    }
}
