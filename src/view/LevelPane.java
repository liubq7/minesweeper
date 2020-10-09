package view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class LevelPane extends GridPane {
    public Button[] buttons;
    public TextField oneInput;
    public TextField twoInput;
    public TextField threeInput;


    public LevelPane() {
        this.setPadding(new Insets(80, 30, 80, 30));
        this.setVgap(10);
        this.setHgap(20);

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
                this.add(label, j, i);
            }
        }

        buttons = new Button[4];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new Button("start");
            buttons[i].setPrefWidth(45);
            this.add(buttons[i], 4, i+1);
        }

        oneInput = new TextField();
        twoInput = new TextField();
        threeInput = new TextField();
        oneInput.setMaxWidth(105);
        twoInput.setMaxWidth(105);
        threeInput.setMaxWidth(110);

        this.add(new Label("Custom"), 0, 4);
        this.add(oneInput, 1, 4);
        this.add(twoInput, 2, 4);
        this.add(threeInput, 3, 4);
    }
}
