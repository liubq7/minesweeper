import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Game extends Application {

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
        GridPane gameBoard = new GridPane();
        gameBoard.setPadding(new Insets(10,10,10,10));
        gameBoard.setHgap(0);
        gameBoard.setVgap(0);
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 15; j++) {
                Button btn = new Button(" ");
                btn.setMinWidth(25);
                btn.setOnMouseClicked(e ->{
                    if (e.getButton() == MouseButton.SECONDARY) {
                        btn.setText("⚑");
                    }
                });
                gameBoard.add(btn, i, j);
            }
        }

        Button buttonRestart = new Button("Restart");
        Label time = new Label("TIME");
        Button menu = new Button("MENU");

        VBox flag = new VBox();
        flag.setPadding(new Insets(20, 10, 20, 10));
        Label flag1 = new Label("⚑" + 3);
        Label flag2 = new Label("⚑⚑" + 2);
        Label flag3 = new Label("⚑⚑⚑" + 2);
        flag.getChildren().addAll(flag1, flag2, flag3);

        BorderPane info = new BorderPane();
        info.setPadding(new Insets(10, 10, 10, 10));
        info.setTop(buttonRestart);
        info.setCenter(flag);
        info.setBottom(menu);

        root.getChildren().addAll(info, gameBoard);
    }
}
