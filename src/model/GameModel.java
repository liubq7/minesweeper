package model;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.util.ArrayList;

/* The model of game grids. */
public class GameModel extends GridPane {
    private int col;  // grids' column number
    private int row;  // grids' row number
    private int oneBombNum;  // the number of grids that has one bomb
    private int twoBombNum;  // the number of grids that has two bomb
    private int threeBombNum;  // the number of grids that has three bomb
    public GameButton[][] map;
    private ArrayList<GameButton> oneBombList;  // the list of grids that has one bomb
    private ArrayList<GameButton> twoBombList;  // the list of grids that has two bomb
    private ArrayList<GameButton> threeBombList;  // the list of grids that has three bomb

    public boolean isWin;
    public boolean isDead;
    public int color;  // the UI color state



    public GameModel(int c, int r, int obn, int tbn, int thbn) {
        row = r;
        col = c;
        oneBombNum = obn;
        twoBombNum = tbn;
        threeBombNum = thbn;
        isWin = false;
        isDead = false;
        color = 0;
        generateMap();
        countBomb();
    }

    public int getCol() {
        return col;
    }
    public int getRow() {
        return row;
    }

    /* Generate a certain number of each type of bombs randomly. */
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

    /* Determines whether a coordinate is in the map. */
    private boolean isContain(int x, int y) {
        if (x >= 0 && x < col && y >= 0 && y < row) {
            return true;
        } else {
            return false;
        }
    }

    /* Get the adjacent cells of cell(x, y). */
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

    /* Count the number of bombs in adjacent cells. */
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

    /* Open cell(x, y), setting a darker color and showing the number of adjacent cell's bomb. */
    public void open(int x, int y) {
        switch (color) {
            case 0 :
                map[x][y].setStyle("-fx-background-color: #8f9594");
                break;
            case 1 :
                map[x][y].setStyle("-fx-background-color: #FC8398");
                break;
            case 2 :
                map[x][y].setStyle("-fx-background-color: #32ACF8");
                break;
            case 3 :
                map[x][y].setStyle("-fx-background-color: #03974C");
                break;
            case 4 :
                map[x][y].setStyle("-fx-background-color: #939303");
                break;
            case 5 :
                map[x][y].setStyle("-fx-background-color: #EC27EC");
                break;
        }

        map[x][y].setText(Integer.toString(map[x][y].getAroundBombNum()));
        map[x][y].isOpen = true;
    }
    /* If the cell's aroundBombNum is 0, open it's adjacent cells until the aroundBombNum isn't 0. */
    public void openAround(int x, int y) {
        open(x, y);
        if (map[x][y].getAroundBombNum() == 0) {
            ArrayList<GameButton> neighborList = getNeighbor(x, y);
            for (GameButton neighborButton : neighborList) {
                if (!neighborButton.isOpen) {
                    openAround(neighborButton.getX(), neighborButton.getY());
                }
            }
        }
    }

    /* Disable all the grid buttons. */
    public void disableAll() {
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                map[i][j].setDisable(true);
            }
        }
    }

    public void playButtonSound() {
        Media bs = new Media(new File("sounds/button.mp3").toURI().toString());
        MediaPlayer bsPlayer = new MediaPlayer(bs);
        bsPlayer.play();
    }
    public void playWinSound() {
        Media ws = new Media(new File("sounds/win.mp3").toURI().toString());
        MediaPlayer wsPlayer = new MediaPlayer(ws);
        wsPlayer.play();
    }
    public void playBombSound() {
        Media bs = new Media(new File("sounds/bomb.mp3").toURI().toString());
        MediaPlayer bsPlayer = new MediaPlayer(bs);
        bsPlayer.play();
    }

    /* Show all bombs' position. */
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

    /**
     *  Set the color of grid button border.
     *  @param color the color hex
     */
    public void setBorderColor(String color) {
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                map[i][j].setStyle("-fx-border-color: " + color);
            }
        }
    }
    /**
     *  Set the color of grid button background.
     *  @param notopen the color hex of not open cell
     *  @param open the color hex of opened cell
     */
    public void setButtonColor(String notopen, String open) {
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                if (map[i][j].isOpen) {
                    map[i][j].setStyle("-fx-background-color: " + open);
                } else {
                    map[i][j].setStyle("-fx-background-color: " + notopen);
                }
            }
        }
    }

    /* Check if mark all grids with one bomb correctly. */
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

    /* Initiate the grids board's UI. */
    public void boardUI() {
        this.setPadding(new Insets(10,10,10,10));
        this.setHgap(1);
        this.setVgap(1);
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                this.add(map[i][j], i, j);
//                map[i][j].setStyle("-fx-border-color: #eaeaef");
            }
        }
    }


}
