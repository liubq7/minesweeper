package controller;

import javafx.scene.input.*;
import javafx.scene.shape.Ellipse;
import view.GamePane;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.GameButton;

public class Controller {
    private EventHandler<MouseEvent> gameButtonListener;
    private EventHandler<MouseEvent> restartListener;

    private EventHandler<MouseEvent> dragDetector;
    private EventHandler<DragEvent> dragOverListener;
    private EventHandler<DragEvent> dragEnterListener;
    private EventHandler<DragEvent> dragExitListener;
    private EventHandler<DragEvent> dragDropper;
    private EventHandler<DragEvent> dragDoneListener;

    GameButton target;


    public void initListener(GamePane gamePane) {
        target = gamePane.gameModel.map[0][0];

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
                Ellipse source = (Ellipse) event.getSource();
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");

                /* allow any transfer mode */
                Dragboard db = source.startDragAndDrop(TransferMode.ANY);

                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString("test");
                db.setContent(content);

                event.consume();
            }
        };
        gamePane.colorPane.pink.setOnDragDetected(dragDetector);

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
                gamePane.gameModel.setBorderColor("#fcb1d0");

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
                System.out.println(db.getString());
                boolean success = false;
                if (db.hasString()) {
                    System.out.println("1");
                    target.setStyle("-fx-background-color: #f6b2eb");


                    gamePane.gameModel.color = 1;
                    gamePane.gameModel.setButtonColor("#EC46AA", "#F6B2EB");


                    System.out.println("2");
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
        gamePane.colorPane.pink.setOnDragDone(dragDoneListener);


        for (int i = 0; i < gamePane.gameModel.getCol(); i++) {
            for (int j = 0; j < gamePane.gameModel.getRow(); j++) {
                gamePane.gameModel.map[i][j].setOnMouseClicked(gameButtonListener);
                gamePane.gameModel.map[i][j].setOnDragOver(dragOverListener);
                gamePane.gameModel.map[i][j].setOnDragEntered(dragEnterListener);
                gamePane.gameModel.map[i][j].setOnDragExited(dragExitListener);
                gamePane.gameModel.map[i][j].setOnDragDropped(dragDropper);
            }
        }

    }
}
