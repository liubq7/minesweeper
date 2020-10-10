package model;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/* The playing cell. */
public class GameButton extends Button {
    private int bombNum;  // the number of bomb in the cell
    private int aroundBombNum;  // the number of bomb in adjacent cell
    private int x;  // position x
    private int y;  // position y
    private String img;  // the image of the button
    boolean isOpen;  // if the cell is opened

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

    /* Set the button's image. */
    public void setImg(String path) {
        this.setText("");
        Image image = new Image(path);
        ImageView view = new ImageView(image);
        view.setFitWidth(15);
        view.setFitHeight(15);
        this.setGraphic(view);
        img = path;
    }
    /* Remove the button's image. */
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
