package controller;

import application.Game;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.GameButton;

public class Controller {
    private EventHandler<MouseEvent> gameButtonListener;
    private EventHandler<MouseEvent> restartListener;

    public void initListener(Game game) {
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
                            game.flagLabelList.get(0).setText(Integer.toString(Integer.parseInt(game.flagLabelList.get(0).getText()) - 1));
                            break;
                        case "file:images/flag1.png" :
                            btn.setImg("file:images/flag2.png");
                            game.flagLabelList.get(0).setText(Integer.toString(Integer.parseInt(game.flagLabelList.get(0).getText()) + 1));
                            game.flagLabelList.get(1).setText(Integer.toString(Integer.parseInt(game.flagLabelList.get(1).getText()) - 1));
                            break;
                        case "file:images/flag2.png" :
                            btn.setImg("file:images/flag3.png");
                            game.flagLabelList.get(1).setText(Integer.toString(Integer.parseInt(game.flagLabelList.get(1).getText()) + 1));
                            game.flagLabelList.get(2).setText(Integer.toString(Integer.parseInt(game.flagLabelList.get(2).getText()) - 1));
                            break;
                        case "file:images/flag3.png" :
                            btn.removeImg();
                            game.flagLabelList.get(2).setText(Integer.toString(Integer.parseInt(game.flagLabelList.get(2).getText()) + 1));
                            break;
                    }
                } else if (e.getButton() == MouseButton.PRIMARY) {
                    if (btn.getBombNum() == 0) {
                        game.gameModel.openAround(x, y);
                    } else if (btn.getBombNum() != 0) {
                        game.gameModel.isDead = true;
                        game.gameModel.revealBomb();
                        btn.setStyle("-fx-background-color: #d41736");

                        Image img = new Image("file:images/dead.png");
                        ImageView view = new ImageView(img);
                        view.setFitWidth(30);
                        view.setFitHeight(30);
                        game.restart.setGraphic(view);
                    }
                }
                game.gameModel.checkWin();
                if (game.gameModel.isWin) {
                    Image img = new Image("file:images/win.png");
                    ImageView view = new ImageView(img);
                    view.setFitWidth(30);
                    view.setFitHeight(30);
                    game.restart.setGraphic(view);
                }
            }
        };

        for (int i = 0; i < game.gameModel.getCol(); i++) {
            for (int j = 0; j < game.gameModel.getRow(); j++) {
                game.gameModel.map[i][j].setOnMouseClicked(gameButtonListener);
            }
        }

        restartListener = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (e.getButton() == MouseButton.PRIMARY) {
                    game.newGame();

                    Image img = new Image("file:images/smile.png");
                    ImageView view = new ImageView(img);
                    view.setFitWidth(30);
                    view.setFitHeight(30);
                    game.restart.setGraphic(view);
                }
            }
        };
        game.restart.setOnMouseClicked(restartListener);
    }
}
