package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/* The welcome and level choose page. */
public class LevelPane extends BorderPane {
    public Button[] buttons;
    public TextField oneInput;  // customize how many grid have one bomb.
    public TextField twoInput;  // customize how many grid have two bomb.
    public TextField threeInput;  // customize how many grid have three bomb.


    public LevelPane() {
        GridPane level = new GridPane();
        level.setVgap(10);
        level.setHgap(20);

        String[][] labels = new String[][] {
                {"", "One Bomb a grid", "Two Bomb a grid", "Three Bomb a grid"},
                {"Beginner", "20", "0", "0"},
                {"Intermediate", "20", "10", "0"},
                {"Expert", "20", "10", "5"}
        };

        for (int i = 0; i < labels.length; i++) {
            for (int j = 0; j < labels[0].length; j++) {
                Label label = new Label(labels[i][j]);
                label.setPrefWidth(120);
                level.add(label, j, i);
            }
        }

        buttons = new Button[4];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new Button("start");
            buttons[i].setPrefWidth(45);
            level.add(buttons[i], 4, i+1);
        }

        oneInput = new TextField();
        twoInput = new TextField();
        threeInput = new TextField();
        oneInput.setMaxWidth(105);
        twoInput.setMaxWidth(105);
        threeInput.setMaxWidth(110);

        level.add(new Label("Custom"), 0, 4);
        level.add(oneInput, 1, 4);
        level.add(twoInput, 2, 4);
        level.add(threeInput, 3, 4);


        Image image = new Image("file:images/minesweeper.png");
        ImageView view = new ImageView(image);
        view.setFitWidth(600);
        view.setPreserveRatio(true);
        this.setTop(view);
        this.setCenter(level);
        this.setPadding(new Insets(0, 30, 30, 30));
        this.setAlignment(view, Pos.CENTER);
        this.setAlignment(level, Pos.CENTER);
    }
}
