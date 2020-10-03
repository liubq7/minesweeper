package model;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;

public class GameBoard extends GridPane {
    private int col;  // 列x(i)
    private int row;  // 行y(j)
    private int oneBombNum;
    private int twoBombNum;
    private int threeBombNum;
    private GameButton[][] map;

    public EventHandler<MouseEvent> gameButtonListener;

    public GameBoard(int c, int r, int obn, int tbn, int thbn) {
        row = r;
        col = c;
        oneBombNum = obn;
        twoBombNum = tbn;
        threeBombNum = thbn;
        map = GameButton.generateMap(c, r, obn, tbn, thbn);
        GameButton.countBomb(map);


        gameButtonListener = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                GameButton btn = (GameButton)e.getSource();
                if (e.getButton() == MouseButton.SECONDARY) {
                    if (btn.getText().equals(" ")) {
                        btn.setText("⚑");
                    } else if (btn.getText().equals("⚑")) {
                        btn.setText(" ");
                    }
                } else if (e.getButton() == MouseButton.PRIMARY) {
                    if (btn.bombNum == 0) {
                        btn.setStyle("-fx-background-color: #676d6c");
                        btn.setText(Integer.toString(btn.aroundBombNum));
                    } else if (btn.bombNum != 0) {
                        btn.setStyle("-fx-background-color: #d41736");
                    }
                }
            }
        };
    }

    public void boardUI() {
        this.setPadding(new Insets(10,10,10,10));
        this.setHgap(0);
        this.setVgap(0);
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                map[i][j].setOnMouseClicked(gameButtonListener);
                this.add(map[i][j], i, j);
            }
        }
    }





    public static void main(String arg[]) {
        int col = 15;
        int row = 12;
        int obn = 7;
        int tbn = 5;
        int thbn = 3;
        GameBoard gb = new GameBoard(col, row, obn, tbn, thbn);

        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                System.out.print(gb.map[i][j].bombNum);
            }
            System.out.println();
        }
        System.out.println();
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                System.out.print(gb.map[i][j].aroundBombNum);
            }
            System.out.println();
        }
    }

}
