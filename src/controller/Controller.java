package controller;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.GameButton;
import model.GameModel;

public class Controller {
    public EventHandler<MouseEvent> gameButtonListener;

    public void initListener(GameModel model) {
        gameButtonListener = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                GameButton btn = (GameButton)e.getSource();
                int x = btn.getX();
                int y = btn.getY();
                if (e.getButton() == MouseButton.SECONDARY) {
                    switch (btn.getImg()) {
                        case " " :
                            btn.setImg("file:images/flag1.png");
                            break;
                        case "file:images/flag1.png" :
                            btn.setImg("file:images/flag2.png");
                            break;
                        case "file:images/flag2.png" :
                            btn.setImg("file:images/flag3.png");
                            break;
                        case "file:images/flag3.png" :
                            btn.removeImg();
                            break;
                    }
                } else if (e.getButton() == MouseButton.PRIMARY) {
                    if (btn.getBombNum() == 0) {
                        model.openAround(x, y);
                    } else if (btn.getBombNum() != 0) {
                        model.isDead = true;
                        model.revealBomb();
                        btn.setStyle("-fx-background-color: #d41736");

                        Image img = new Image("file:images/dead.png");
                        ImageView view = new ImageView(img);
                        view.setFitWidth(30);
                        view.setFitHeight(30);
                        model.restart.setGraphic(view);
                    }
                }
                model.checkWin();
                if (model.isWin) {
                    Image img = new Image("file:images/win.png");
                    ImageView view = new ImageView(img);
                    view.setFitWidth(30);
                    view.setFitHeight(30);
                    model.restart.setGraphic(view);
                }
            }
        };

        for (int i = 0; i < model.getCol(); i++) {
            for (int j = 0; j < model.getRow(); j++) {
                model.map[i][j].setOnMouseClicked(gameButtonListener);
            }
        }
    }
}
