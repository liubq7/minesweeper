import javafx.scene.layout.GridPane;

public class GameBoard {
    private int row;  // 行y(j)
    private int col;  // 列x(i)
    private int oneBombNum;
    private int twoBombNum;
    private int threeBombNum;
    private Cell[][] map;

    private GameBoard(int c, int r, int obn, int tbn, int thbn) {
        row = r;
        col = c;
        oneBombNum = obn;
        twoBombNum = tbn;
        threeBombNum = thbn;
        map = generateMap(c, r, obn);
    }

    private class Cell {
        private int bombNum;
        private int aroundBombNum;

        private Cell() {
            bombNum = 0;
        }
    }

    private Cell[][] generateMap(int col, int row, int obn) {
        Cell[][] map = new Cell[col][row];
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                map[i][j] = new Cell();
            }
        }

        // TODO: 雷数有问题
        for (int n = 0; n < obn; n++) {
            int i = (int) (Math.random() * (col));
            int j = (int) (Math.random() * (row));
            if (map[i][j].bombNum == 0) {
                map[i][j].bombNum = 1;
            }
        }

        countBomb(map);

        return map;
    }
    /* 计算周围的雷数（待化简） */
    private void countBomb(Cell[][] map) {
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
        GameBoard gb = new GameBoard(5, 5, 5, 0, 0);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(gb.map[i][j].bombNum);
            }
            System.out.println();
        }
        System.out.println();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(gb.map[i][j].aroundBombNum);
            }
            System.out.println();
        }
    }

}
