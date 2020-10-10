package controller;

import javafx.scene.input.*;
import view.ColorPane;
import view.GamePane;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.GameButton;

public class Controller {
    private EventHandler<MouseEvent> gameButtonListener;
    private EventHandler<MouseEvent> gameButtonEnterListener;
    private EventHandler<MouseEvent> gameButtonExitListener;

    private EventHandler<MouseEvent> restartListener;

    private EventHandler<MouseEvent> dragDetector;
    private EventHandler<DragEvent> dragOverListener;
    private EventHandler<DragEvent> dragEnterListener;
    private EventHandler<DragEvent> dragExitListener;
    private EventHandler<DragEvent> dragDropper;
    private EventHandler<DragEvent> dragDoneListener;


    public void initListener(GamePane gamePane) {

        gameButtonListener = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                GameButton btn = (GameButton)e.getSource();
                int x = btn.getX();
                int y = btn.getY();
                /**
                 *  The player could right click once to mark one flag in a cell,
                 *  right click twice to mark two flags,
                 *  right click three times to mark three flags,
                 *  right click again to undo.
                 */
                if (e.getButton() == MouseButton.SECONDARY) {
                    switch (btn.getImg()) {
                        case " " :
                            btn.setImg("file:images/flag1.png");
                            gamePane.flagLabelList.get(0).setText(
                                    Integer.toString(Integer.parseInt(gamePane.flagLabelList.get(0).getText()) - 1));
                            break;
                        case "file:images/flag1.png" :
                            btn.setImg("file:images/flag2.png");
                            gamePane.flagLabelList.get(0).setText(
                                    Integer.toString(Integer.parseInt(gamePane.flagLabelList.get(0).getText()) + 1));
                            gamePane.flagLabelList.get(1).setText(
                                    Integer.toString(Integer.parseInt(gamePane.flagLabelList.get(1).getText()) - 1));
                            break;
                        case "file:images/flag2.png" :
                            btn.setImg("file:images/flag3.png");
                            gamePane.flagLabelList.get(1).setText(
                                    Integer.toString(Integer.parseInt(gamePane.flagLabelList.get(1).getText()) + 1));
                            gamePane.flagLabelList.get(2).setText(
                                    Integer.toString(Integer.parseInt(gamePane.flagLabelList.get(2).getText()) - 1));
                            break;
                        case "file:images/flag3.png" :
                            btn.removeImg();
                            gamePane.flagLabelList.get(2).setText(
                                    Integer.toString(Integer.parseInt(gamePane.flagLabelList.get(2).getText()) + 1));
                            break;
                    }
                } else if (e.getButton() == MouseButton.PRIMARY) {
                    if (btn.getBombNum() == 0) {  // open an empty cell
                        btn.removeImg();
                        gamePane.gameModel.openAround(x, y);
                        gamePane.gameModel.playButtonSound();
                    } else if (btn.getBombNum() != 0) {  // game over
                        gamePane.gameModel.isDead = true;
                        gamePane.gameModel.playBombSound();
                        gamePane.gameModel.revealBomb();
                        btn.setStyle("-fx-background-color: #d41736");

                        Image img = new Image("file:images/dead.png");
                        ImageView view = new ImageView(img);
                        view.setFitWidth(30);
                        view.setFitHeight(30);
                        gamePane.restart.setGraphic(view);

                        gamePane.stopTime();

                        gamePane.gameModel.disableAll();
                    }
                }
                gamePane.gameModel.checkWin();
                if (gamePane.gameModel.isWin) {
                    gamePane.gameModel.playWinSound();
                    Image img = new Image("file:images/win.png");
                    ImageView view = new ImageView(img);
                    view.setFitWidth(30);
                    view.setFitHeight(30);
                    gamePane.restart.setGraphic(view);

                    gamePane.stopTime();

                    gamePane.gameModel.disableAll();
                }
            }
        };

        /* When the mouse hovers over an unopened grid, this grid would turn blue to emphasize. */
        gameButtonEnterListener = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                GameButton btn = (GameButton)e.getSource();
                if (!btn.isOpen) {
                    btn.setStyle("-fx-background-color: #455bee");
                }

            }
        };
        gameButtonExitListener = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                GameButton btn = (GameButton)e.getSource();
                if (!btn.isOpen) {
                    switch (gamePane.gameModel.color) {
                        case 0:
                            btn.setStyle(null);
                            break;
                        case 1:
                            btn.setStyle("-fx-background-color: #FFC0CB");
                            break;
                        case 2:
                            btn.setStyle("-fx-background-color: #87CEFA");
                            break;
                        case 3:
                            btn.setStyle("-fx-background-color: #00FF7F");
                            break;
                        case 4:
                            btn.setStyle("-fx-background-color: #FFFF00");
                            break;
                        case 5:
                            btn.setStyle("-fx-background-color: #EE82EE");
                            break;
                    }
                }
            }
        };


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


        dragDetector = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ColorPane.ColorCir source = (ColorPane.ColorCir) event.getSource();
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");

                /* allow any transfer mode */
                Dragboard db = source.startDragAndDrop(TransferMode.ANY);

                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(source.getColorStr());
                db.setContent(content);

                event.consume();
            }
        };

        dragOverListener = new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");

                /* accept it only if it is  not dragged from the same node
                 * and if it has a string data */
//                if (event.getGestureSource() != target &&
//                        event.getDragboard().hasString()) {
                    /* allow for both copying and moving, whatever user chooses */
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
//                }

                event.consume();
            }
        };

        dragEnterListener = new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                /* the drag-and-drop gesture entered the target */
                System.out.println("onDragEntered");
                /* show to the user that it is an actual gesture target */
//                if (event.getGestureSource() != target &&
//                        event.getDragboard().hasString()) {
//                    target.setStyle("-fx-border-color: #fcb1d0");
//                }
                switch (event.getDragboard().getString()) {
                    case "0" :
                        gamePane.gameModel.setBorderColor("#fcb1d0");
                        break;
                    case "1" :
                        gamePane.gameModel.setBorderColor("#2CACFC");
                        break;
                    case "2" :
                        gamePane.gameModel.setBorderColor("#00FF5D");
                        break;
                    case "3" :
                        gamePane.gameModel.setBorderColor("#F7FF00");
                        break;
                    case "4" :
                        gamePane.gameModel.setBorderColor("#F84BF8");
                        break;
                }

                event.consume();
            }
        };

        dragExitListener = new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                /* mouse moved away, remove the graphical cues */
//                target.setStyle("-fx-border-color: #ffffff");
//                gamePane.gameModel.setBorderColor("#eaeaef");

                event.consume();
            }
        };

        dragDropper = new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                /* data dropped */
                System.out.println("onDragDropped");
                /* if there is a string data on dragboard, read it and use it */
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasString()) {

                    switch (db.getString()) {
                        case "0" :
                            gamePane.gameModel.color = 1;
                            gamePane.gameModel.setButtonColor("#FFC0CB", "#FC8398");
                            break;
                        case "1" :
                            gamePane.gameModel.color = 2;
                            gamePane.gameModel.setButtonColor("#87CEFA", "#32ACF8");
                            break;
                        case "2" :
                            gamePane.gameModel.color = 3;
                            gamePane.gameModel.setButtonColor("#00FF7F", "#03974C");
                            break;
                        case "3" :
                            gamePane.gameModel.color = 4;
                            gamePane.gameModel.setButtonColor("#FFFF00", "#939303");
                            break;
                        case "4" :
                            gamePane.gameModel.color = 5;
                            gamePane.gameModel.setButtonColor("#EE82EE", "#EC27EC");
                            break;
                    }

                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        };

        dragDoneListener = new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                /* the drag-and-drop gesture ended */
                System.out.println("onDragDone");
                /* if the data was successfully moved, clear it */
                if (event.getTransferMode() == TransferMode.MOVE) {
//                    source.setText("");
                }

                event.consume();
            }
        };



        for (int i = 0; i < gamePane.gameModel.getCol(); i++) {
            for (int j = 0; j < gamePane.gameModel.getRow(); j++) {
                gamePane.gameModel.map[i][j].setOnMouseClicked(gameButtonListener);

                gamePane.gameModel.map[i][j].setOnMouseEntered(gameButtonEnterListener);
                gamePane.gameModel.map[i][j].setOnMouseExited(gameButtonExitListener);

                gamePane.gameModel.map[i][j].setOnDragOver(dragOverListener);
                gamePane.gameModel.map[i][j].setOnDragEntered(dragEnterListener);
                gamePane.gameModel.map[i][j].setOnDragExited(dragExitListener);
                gamePane.gameModel.map[i][j].setOnDragDropped(dragDropper);
            }
        }

        for (int i = 0; i < gamePane.colorPane.colorCirs.length; i++) {
            gamePane.colorPane.colorCirs[i].setOnDragDetected(dragDetector);
            gamePane.colorPane.colorCirs[i].setOnDragDone(dragDoneListener);
        }

    }
}
