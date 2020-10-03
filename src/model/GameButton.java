package model;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class GameButton extends Button {
    private int bombNum;
    private int aroundBombNum;
    private int x;
    private int y;
    private String img;

    private GameButton(String s, int i, int j) {
        super(s);
        x = i;
        y = j;
        img = s;
    }

    public int getBombNum() {
        return bombNum;
    }
    public int getAroundBombNum() {
        return aroundBombNum;
    }
    public String getImg() {
        return img;
    }

    public void setImg(String path) {
        this.setText("");
        Image image = new Image(path);
        ImageView view = new ImageView(image);
        view.setFitWidth(13);
        view.setFitHeight(13);
        this.setGraphic(view);
        img = path;
    }
    public void removeImg() {
        this.setGraphic(null);
        img = " ";
    }

    public static GameButton[][] generateMap(int col, int row, int obn, int tbn, int thbn) {
        GameButton[][] map = new GameButton[col][row];
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                map[i][j] = new GameButton(" ", i, j);
                map[i][j].setMinWidth(25);
                map[i][j].setMaxWidth(25);
            }
        }

        int[] numList = {obn, tbn, thbn};
        for (int n = 0; n < numList.length; n++) {
            ArrayList<GameButton> bombList = new ArrayList<>();
            while (bombList.size() < numList[n]){
                int i = (int) (Math.random() * (col));
                int j = (int) (Math.random() * (row));
                if (map[i][j].bombNum == 0) {
                    map[i][j].bombNum = n + 1;
                    bombList.add(map[i][j]);
                }
            }
        }

        return map;
    }

    private static boolean isContain(GameButton[][] map, int x, int y) {
        int col = map.length;
        int row = map[0].length;
        if (x >= 0 && x < col && y >= 0 && y < row) {
            return true;
        } else {
            return false;
        }
    }

    private ArrayList<GameButton> getNeighbor(GameButton[][] map) {
        int[] neighborX = {-1, 0, 1};
        int[] neighborY = {-1, 0, 1};
        ArrayList<GameButton> neighborList = new ArrayList<>();
        for (int dx : neighborX) {
            for (int dy : neighborY) {
                if (isContain(map, x+dx, y+dy) && !(dx == 0 && dy == 0)) {
                    neighborList.add(map[x+dx][y+dy]);
                }
            }
        }
        return neighborList;
    }

    public static void countBomb(GameButton[][] map) {
        int col = map.length;
        int row = map[0].length;
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                ArrayList<GameButton> neighborList = map[i][j].getNeighbor(map);
                for (GameButton neighborButton : neighborList) {
                    map[i][j].aroundBombNum += neighborButton.bombNum;
                }
            }
        }
    }

    public void open() {
        setStyle("-fx-background-color: #676d6c");
        setText(Integer.toString(getAroundBombNum()));
    }
    public void openAround(GameButton[][] map) {
        open();
        ArrayList<GameButton> neighborList = getNeighbor(map);
        for (GameButton neighborButton : neighborList) {
            if (neighborButton.aroundBombNum != 0) {
                neighborButton.open();
            } else {
                neighborButton.openAround(map);
            }
        }
    }
}
