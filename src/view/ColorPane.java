package view;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class ColorPane extends VBox {
    public Ellipse pink;

    public ColorPane() {
        this.setPadding(new Insets(10, 15, 10, 5));
        pink = new Ellipse(15,15);
        pink.setFill(Color.PINK);
        this.getChildren().add(pink);
    }
}
