package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class LevelPane extends GridPane {
    public Button start1;
    public Button start2;
    public Button start3;

    public LevelPane() {
        this.setPadding(new Insets(80, 30, 80, 30));
        this.setVgap(10);
        this.setHgap(10);

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

        start1 = new Button("start");
        start1.setPrefWidth(45);
        this.add(start1, 4, 1);
        start2 = new Button("start");
        start2.setPrefWidth(45);
        this.add(start2, 4, 2);
        start3 = new Button("start");
        start3.setPrefWidth(45);
        this.add(start3, 4, 3);
    }
}
