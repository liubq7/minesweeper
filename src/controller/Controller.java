package controller;

import view.GamePane;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.GameButton;

public class Controller {
    private EventHandler<MouseEvent> gameButtonListener;
    private EventHandler<MouseEvent> restartListener;

    public void initListener(GamePane gamePane) {
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
                            gamePane.flagLabelList.get(0).setText(Integer.toString(Integer.parseInt(gamePane.flagLabelList.get(0).getText()) - 1));
                            break;
                        case "file:images/flag1.png" :
                            btn.setImg("file:images/flag2.png");
                            gamePane.flagLabelList.get(0).setText(Integer.toString(Integer.parseInt(gamePane.flagLabelList.get(0).getText()) + 1));
                            gamePane.flagLabelList.get(1).setText(Integer.toString(Integer.parseInt(gamePane.flagLabelList.get(1).getText()) - 1));
                            break;
                        case "file:images/flag2.png" :
                            btn.setImg("file:images/flag3.png");
                            gamePane.flagLabelList.get(1).setText(Integer.toString(Integer.parseInt(gamePane.flagLabelList.get(1).getText()) + 1));
                            gamePane.flagLabelList.get(2).setText(Integer.toString(Integer.parseInt(gamePane.flagLabelList.get(2).getText()) - 1));
                            break;
                        case "file:images/flag3.png" :
                            btn.removeImg();
                            gamePane.flagLabelList.get(2).setText(Integer.toString(Integer.parseInt(gamePane.flagLabelList.get(2).getText()) + 1));
                            break;
                    }
                } else if (e.getButton() == MouseButton.PRIMARY) {
                    if (btn.getBombNum() == 0) {
                        gamePane.gameModel.openAround(x, y);
                    } else if (btn.getBombNum() != 0) {
                        gamePane.gameModel.isDead = true;
                        gamePane.gameModel.revealBomb();
                        btn.setStyle("-fx-background-color: #d41736");

                        Image img = new Image("file:images/dead.png");
                        ImageView view = new ImageView(img);
                        view.setFitWidth(30);
                        view.setFitHeight(30);
                        gamePane.restart.setGraphic(view);
                    }
                }
                gamePane.gameModel.checkWin();
                if (gamePane.gameModel.isWin) {
                    Image img = new Image("file:images/win.png");
                    ImageView view = new ImageView(img);
                    view.setFitWidth(30);
                    view.setFitHeight(30);
                    gamePane.restart.setGraphic(view);
                }
            }
        };

        for (int i = 0; i < gamePane.gameModel.getCol(); i++) {
            for (int j = 0; j < gamePane.gameModel.getRow(); j++) {
                gamePane.gameModel.map[i][j].setOnMouseClicked(gameButtonListener);
            }
        }

        restartListener = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (e.getButton() == MouseButton.PRIMARY) {
                    gamePane.newGame();

                    Image img = new Image("file:images/smile.png");
                    ImageView view = new ImageView(img);
                    view.setFitWidth(30);
                    view.setFitHeight(30);
                    gamePane.restart.setGraphic(view);
                }
            }
        };
        gamePane.restart.setOnMouseClicked(restartListener);
    }
}
