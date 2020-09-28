import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Game extends Application {

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        HBox root = new HBox();
        initGUI(root);

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

    private void initGUI(Pane root) {
        GameBoard gameBoard = new GameBoard(19, 15, 10, 0, 0);
        gameBoard.boardUI();

        Button buttonRestart = new Button("\uD83D\uDE03");
        Label time = new Label("TIME");
        time.setPadding(new Insets(5, 0, 5, 0));
        VBox topInfo = new VBox();
        topInfo.setAlignment(Pos.CENTER);
        topInfo.getChildren().addAll(buttonRestart, time);


        Button menu = new Button("MENU");


        VBox flag = new VBox();
        flag.setPadding(new Insets(20, 10, 20, 10));
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
        info.setTop(topInfo);
        info.setCenter(flag);
        info.setBottom(menu);

        root.getChildren().addAll(info, gameBoard);
    }
}
