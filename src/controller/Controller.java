package controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.GameButton;

public class Controller {
    public EventHandler<MouseEvent> gameButtonListener;

    public void initListener() {
        gameButtonListener = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                GameButton btn = (GameButton)e.getSource();
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
                        btn.setStyle("-fx-background-color: #676d6c");
                        btn.setText(Integer.toString(btn.getAroundBombNum()));
                    } else if (btn.getBombNum() != 0) {
                        btn.setStyle("-fx-background-color: #d41736");
                    }
                }
            }
        };
    }
}
