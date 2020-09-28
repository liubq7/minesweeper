import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;

public class GameBoard extends GridPane {
    private int col;  // 列x(i)
    private int row;  // 行y(j)
    private int oneBombNum;
    private int twoBombNum;
    private int threeBombNum;
    private Cell[][] map;

    public GameBoard(int c, int r, int obn, int tbn, int thbn) {
        row = r;
        col = c;
        oneBombNum = obn;
        twoBombNum = tbn;
        threeBombNum = thbn;
        map = generateMap();
    }

    public void boardUI() {
        this.setPadding(new Insets(10,10,10,10));
        this.setHgap(0);
        this.setVgap(0);
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                GameButton btn = new GameButton(" ");
                btn.buttonCell = map[i][j];
                btn.setMinWidth(25);
                btn.setOnMouseClicked(e ->{
                    if (e.getButton() == MouseButton.SECONDARY) {
                        if (btn.getText().equals(" ")) {
                            btn.setText("⚑");
                        } else if (btn.getText().equals("⚑")) {
                            btn.setText(" ");
                        }
                    } else if (e.getButton() == MouseButton.PRIMARY) {
                        if (btn.buttonCell.bombNum == 0) {
                            btn.setStyle("-fx-background-color: #676d6c");
                            btn.setText(Integer.toString(btn.buttonCell.aroundBombNum));
                        } else if (btn.buttonCell.bombNum != 0) {
                            btn.setStyle("-fx-background-color: #d41736");
                        }
                    }
                });
                this.add(btn, i, j);
            }
        }
    }

    private class GameButton extends Button {
        private Cell buttonCell;

        private GameButton(String s) {
            this.setText(s);
        }
    }

    private class Cell {
        private int bombNum;
        private int aroundBombNum;

        private Cell() {
            bombNum = 0;
        }
    }

    private Cell[][] generateMap() {
        Cell[][] map = new Cell[col][row];
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                map[i][j] = new Cell();
            }
        }

        ArrayList<Cell> oneBomb = new ArrayList<>();
        while (oneBomb.size() < oneBombNum){
            int i = (int) (Math.random() * (col));
            int j = (int) (Math.random() * (row));
            if (map[i][j].bombNum == 0) {
                map[i][j].bombNum = 1;
                oneBomb.add(map[i][j]);
            }
        }

        ArrayList<Cell> twoBomb = new ArrayList<>();
        while (twoBomb.size() < twoBombNum){
            int i = (int) (Math.random() * (col));
            int j = (int) (Math.random() * (row));
            if (map[i][j].bombNum == 0) {
                map[i][j].bombNum = 2;
                twoBomb.add(map[i][j]);
            }
        }

        ArrayList<Cell> threeBomb = new ArrayList<>();
        while (threeBomb.size() < threeBombNum){
            int i = (int) (Math.random() * (col));
            int j = (int) (Math.random() * (row));
            if (map[i][j].bombNum == 0) {
                map[i][j].bombNum = 3;
                threeBomb.add(map[i][j]);
            }
        }

        countBomb(map);

        return map;
    }
    /* 计算周围的雷数 */
    private static void countBomb(Cell[][] map) {
        int col = map.length;
        int row = map[0].length;
        // 里面一圈
        for (int i = 1; i < col - 1; i++) {
            for (int j = 1; j < row - 1; j++) {
                map[i][j].aroundBombNum = map[i-1][j-1].bombNum + map[i][j-1].bombNum + map[i+1][j-1].bombNum +
                        map[i-1][j].bombNum + map[i+1][j].bombNum + map[i-1][j+1].bombNum + map[i][j+1].bombNum +
                        map[i+1][j+1].bombNum;
            }
        }
        // 上下
        for (int i = 1; i < col - 1; i++) {
            map[i][0].aroundBombNum = map[i-1][0].bombNum + map[i+1][0].bombNum + map[i-1][1].bombNum +
                    map[i][1].bombNum + map[i+1][1].bombNum;
            map[i][row-1].aroundBombNum = map[i-1][row-1].bombNum + map[i+1][row-1].bombNum + map[i-1][row-2].bombNum +
                    map[i][row-2].bombNum + map[i+1][row-2].bombNum;
        }
        // 左右
        for (int j = 1; j < row - 1; j++) {
            map[0][j].aroundBombNum = map[0][j-1].bombNum + map[1][j-1].bombNum + map[1][j].bombNum +
                    map[0][j+1].bombNum + map[1][j+1].bombNum;
            map[col-1][j].aroundBombNum = map[col-1][j-1].bombNum + map[col-2][j-1].bombNum + map[col-2][j].bombNum +
                    map[col-1][j+1].bombNum + map[col-2][j+1].bombNum;
        }
        // 四角
        map[0][0].aroundBombNum = map[0][1].bombNum + map[1][0].bombNum + map[1][1].bombNum;
        map[0][row-1].aroundBombNum = map[0][row-2].bombNum + map[1][row-2].bombNum + map[1][row-1].bombNum;
        map[col-1][0].aroundBombNum = map[col-2][0].bombNum + map[col-2][1].bombNum + map[col-1][1].bombNum;
        map[col-1][row-1].aroundBombNum = map[col-2][row-2].bombNum + map[col-1][row-2].bombNum + map[col-2][row-1].bombNum;
    }



    public static void main(String arg[]) {
        System.out.println("start");
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
