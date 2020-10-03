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
                    if (btn.getText().equals(" ")) {
                        btn.setText("⚑");
                    } else if (btn.getText().equals("⚑")) {
                        btn.setText(" ");
                    }
                } else if (e.getButton() == MouseButton.PRIMARY) {
                    if (btn.bombNum == 0) {
                        btn.setStyle("-fx-background-color: #676d6c");
                        btn.setText(Integer.toString(btn.aroundBombNum));
                    } else if (btn.bombNum != 0) {
                        btn.setStyle("-fx-background-color: #d41736");
                    }
                }
            }
        };
    }
}
