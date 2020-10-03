package model;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

public class GameBoard extends GridPane {
    private int col;  // 列x(i)
    private int row;  // 行y(j)
    private int oneBombNum;
    private int twoBombNum;
    private int threeBombNum;
    private GameButton[][] map;
    private Controller controller;


    public GameBoard(int c, int r, int obn, int tbn, int thbn) {
        row = r;
        col = c;
        oneBombNum = obn;
        twoBombNum = tbn;
        threeBombNum = thbn;
        map = GameButton.generateMap(c, r, obn, tbn, thbn);
        GameButton.countBomb(map);
        controller = new Controller();
    }

    public void boardUI() {
        this.setPadding(new Insets(10,10,10,10));
        this.setHgap(0);
        this.setVgap(0);
        controller.initListener();
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                map[i][j].setOnMouseClicked(controller.gameButtonListener);
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
