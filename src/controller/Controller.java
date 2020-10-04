package controller;

import javafx.event.EventHandler;
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
                        model.revealBomb();
                        btn.setStyle("-fx-background-color: #d41736");
                    }
                }
            }
        };
    }
}
