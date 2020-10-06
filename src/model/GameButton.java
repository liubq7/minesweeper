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
    boolean isOpen;

    public GameButton(String s, int i, int j) {
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
        view.setFitWidth(15);
        view.setFitHeight(15);
        this.setGraphic(view);
        img = path;
    }
    public void removeImg() {
        this.setGraphic(null);
        img = " ";
    }

    public void setBombNum(int n) {
        bombNum = n;
    }
    public void setAroundBombNum(int n) {
        aroundBombNum = n;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }


}
