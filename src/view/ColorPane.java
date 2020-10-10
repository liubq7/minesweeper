package view;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

/* Different color of circle for user to drag and drop to customize the grids color. */
public class ColorPane extends VBox {
    public ColorCir[] colorCirs;

    public class ColorCir extends Ellipse {
        private String colorStr;

        private ColorCir(int x, Color color, String str) {
            super(x, x);
            this.setFill(color);
            colorStr = str;
        }

        public String getColorStr() {
            return colorStr;
        }
    }

    public ColorPane() {
        this.setPadding(new Insets(10, 15, 10, 5));

        colorCirs = new ColorCir[5];

        colorCirs[0] = new ColorCir(15, Color.PINK, "0");  // #FFC0CB
        colorCirs[1] = new ColorCir(15, Color.LIGHTSKYBLUE, "1");  // #87CEFA
        colorCirs[2] = new ColorCir(15, Color.SPRINGGREEN, "2");  // #00FF7F
        colorCirs[3] = new ColorCir(15, Color.YELLOW, "3");  // #FFFF00
        colorCirs[4] = new ColorCir(15, Color.VIOLET, "4");  // #EE82EE

        for (int i = 0; i < colorCirs.length; i++) {
            VBox vBox = new VBox();
            vBox.setPadding(new Insets(0, 0, 10, 0));
            vBox.getChildren().add(colorCirs[i]);
            this.getChildren().add(vBox);
        }

    }


}
