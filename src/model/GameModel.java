package model;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class GameModel extends GridPane {
    private int col;  // 列x(i)
    private int row;  // 行y(j)
    private int oneBombNum;
    private int twoBombNum;
    private int threeBombNum;
    public GameButton[][] map;
    private ArrayList<GameButton> oneBombList;
    private ArrayList<GameButton> twoBombList;
    private ArrayList<GameButton> threeBombList;

    public boolean isWin;
    public boolean isDead;

    public Button restart;


    public GameModel(int c, int r, int obn, int tbn, int thbn) {
        row = r;
        col = c;
        oneBombNum = obn;
        twoBombNum = tbn;
        threeBombNum = thbn;
        isWin = false;
        isDead = false;
        generateMap();
        countBomb();
    }

    public int getCol() {
        return col;
    }
    public int getRow() {
        return row;
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

        oneBombList = new ArrayList<>();
        while (oneBombList.size() < oneBombNum){
            int i = (int) (Math.random() * (col));
            int j = (int) (Math.random() * (row));
            if (map[i][j].getBombNum() == 0) {
                map[i][j].setBombNum(1);
                oneBombList.add(map[i][j]);
            }
        }

        twoBombList = new ArrayList<>();
        while (twoBombList.size() < twoBombNum){
            int i = (int) (Math.random() * (col));
            int j = (int) (Math.random() * (row));
            if (map[i][j].getBombNum() == 0) {
                map[i][j].setBombNum(2);
                twoBombList.add(map[i][j]);
            }
        }

        threeBombList = new ArrayList<>();
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

    private void countBomb() {
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
        map[x][y].setStyle("-fx-background-color: #8f9594");
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

    public void revealBomb() {
        for (GameButton oneBomb : oneBombList) {
            oneBomb.setImg("file:images/bomb1.png");
            oneBomb.setStyle("-fx-background-color: #686464");
        }
        for (GameButton twoBomb : twoBombList) {
            twoBomb.setImg("file:images/bomb2.png");
            twoBomb.setStyle("-fx-background-color: #686464");
        }
        for (GameButton threeBomb : threeBombList) {
            threeBomb.setImg("file:images/bomb3.png");
            threeBomb.setStyle("-fx-background-color: #686464");
        }
    }

    private boolean checkOne() {
        for (GameButton gameButton : oneBombList) {
            if (!gameButton.getImg().equals("file:images/flag1.png")) {
                return false;
            }
        }
        return true;
    }
    private boolean checkTwo() {
        for (GameButton gameButton : twoBombList) {
            if (!gameButton.getImg().equals("file:images/flag2.png")) {
                return false;
            }
        }
        return true;
    }
    private boolean checkThree() {
        for (GameButton gameButton : threeBombList) {
            if (!gameButton.getImg().equals("file:images/flag3.png")) {
                return false;
            }
        }
        return true;
    }
    public void checkWin() {
        if (checkOne() && checkTwo() && checkThree()) {
            isWin = true;
        }
    }

    public void boardUI() {
        this.setPadding(new Insets(10,10,10,10));
        this.setHgap(0);
        this.setVgap(0);
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                this.add(map[i][j], i, j);
            }
        }

        restart = new Button();
        Image img = new Image("file:images/smile.png");
        ImageView view = new ImageView(img);
        view.setFitWidth(30);
        view.setFitHeight(30);
        restart.setGraphic(view);
    }


}
