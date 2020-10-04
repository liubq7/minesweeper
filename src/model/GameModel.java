package model;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class GameModel extends GridPane {
    private int col;  // 列x(i)
    private int row;  // 行y(j)
    private int oneBombNum;
    private int twoBombNum;
    private int threeBombNum;
    private GameButton[][] map;
    private ArrayList<GameButton> oneBombList;
    private ArrayList<GameButton> twoBombList;
    private ArrayList<GameButton> threeBombList;


    public GameModel(int c, int r, int obn, int tbn, int thbn) {
        row = r;
        col = c;
        oneBombNum = obn;
        twoBombNum = tbn;
        threeBombNum = thbn;
        generateMap();
        countBomb();

    }

    private void generateMap() {
        map = new GameButton[col][row];
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                map[i][j] = new GameButton(" ", i, j);
                map[i][j].setMinWidth(25);
                map[i][j].setMaxWidth(25);
            }
        }

        ArrayList<GameButton> oneBombList = new ArrayList<>();
        while (oneBombList.size() < oneBombNum){
            int i = (int) (Math.random() * (col));
            int j = (int) (Math.random() * (row));
            if (map[i][j].getBombNum() == 0) {
                map[i][j].setBombNum(1);
                oneBombList.add(map[i][j]);
            }
        }

        ArrayList<GameButton> twoBombList = new ArrayList<>();
        while (twoBombList.size() < twoBombNum){
            int i = (int) (Math.random() * (col));
            int j = (int) (Math.random() * (row));
            if (map[i][j].getBombNum() == 0) {
                map[i][j].setBombNum(2);
                twoBombList.add(map[i][j]);
            }
        }

        ArrayList<GameButton> threeBombList = new ArrayList<>();
        while (threeBombList.size() < threeBombNum){
            int i = (int) (Math.random() * (col));
            int j = (int) (Math.random() * (row));
            if (map[i][j].getBombNum() == 0) {
                map[i][j].setBombNum(3);
                threeBombList.add(map[i][j]);
            }
        }
    }

    private boolean isContain(int x, int y) {
        if (x >= 0 && x < col && y >= 0 && y < row) {
            return true;
        } else {
            return false;
        }
    }

    private ArrayList<GameButton> getNeighbor(int x, int y) {
        int[] neighborX = {-1, 0, 1};
        int[] neighborY = {-1, 0, 1};
        ArrayList<GameButton> neighborList = new ArrayList<>();
        for (int dx : neighborX) {
            for (int dy : neighborY) {
                if (isContain(x+dx, y+dy) && !(dx == 0 && dy == 0)) {
                    neighborList.add(map[x+dx][y+dy]);
                }
            }
        }
        return neighborList;
    }

    public void countBomb() {
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                ArrayList<GameButton> neighborList = getNeighbor(i, j);
                int abn = 0;
                for (GameButton neighborButton : neighborList) {
                    abn += neighborButton.getBombNum();
                }
                map[i][j].setAroundBombNum(abn);
            }
        }
    }

    public void open(int x, int y) {
        map[x][y].setStyle("-fx-background-color: #676d6c");
        map[x][y].setText(Integer.toString(map[x][y].getAroundBombNum()));
    }
    public void openAround(int x, int y) {
        open(x, y);
        if (map[x][y].getAroundBombNum() == 0) {
            ArrayList<GameButton> neighborList = getNeighbor(x, y);
            for (GameButton neighborButton : neighborList) {
                openAround(neighborButton.getX(), neighborButton.getY());
            }
        }
    }

    public void boardUI() {
        this.setPadding(new Insets(10,10,10,10));
        this.setHgap(0);
        this.setVgap(0);
        Controller controller = new Controller();
        controller.initListener(this);
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                map[i][j].setOnMouseClicked(controller.gameButtonListener);
                this.add(map[i][j], i, j);
            }
        }
    }


}
