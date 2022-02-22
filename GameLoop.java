import javafx.animation.AnimationTimer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import Figures.Matrix;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.paint.*;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.stage.WindowEvent;

public class GameLoop extends AnimationTimer implements GameOver, Runnable {

    Game game;
    Stage stage;
    Scene playScene;
    Background background;
    final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    final ScheduledExecutorService executorService2 = Executors.newSingleThreadScheduledExecutor();

    GameLoop(Stage newStage, Background newBackground) {
        stage = newStage;
        background = newBackground;

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

            @Override
            public void handle(final WindowEvent arg0) {
                gameOver();
            }
        });

    }

    private void paintRectangles(Group group, int width) {
        for (int i = 0; i < game.playField.getY() + game.playField.getBuffer(); i++) {
            for (int j = 0; j < game.playField.getX(); j++) {
                if (game.playField.playField[i][j].isSet) {
                    LinearGradient gradient = new LinearGradient(j * width + 15,
                            (i - game.playField.getBuffer()) * width + 15, j * width + 25,
                            (i - game.playField.getBuffer()) * width + 25, false, CycleMethod.NO_CYCLE,
                            new Stop(0, game.playField.playField[i][j].color), new Stop(1, Color.WHITE));

                    Rectangle piece = new Rectangle(j * width, (i - game.playField.getBuffer()) * width, width, width);

                    piece.setFill(gradient);
                    group.getChildren().add(piece);
                }
            }
        }
    }

    public Group paintPlayField() {
        Group root = new Group();

        Rectangle playFieldBackGroud = new Rectangle(0, 0, 30 * game.playField.getX(), 30 * game.playField.getY());
        playFieldBackGroud.setFill(Color.BLACK);
        root.getChildren().add(playFieldBackGroud);
        game.playField.changeFigureToPlayField(game.currentFigure);
        paintRectangles(root, 30);
        game.playField.removeFigureToPlayField(game.currentFigure);

        return root;
    }

    private void paintMiniNextFigure(Group group, Matrix temp, int width, int offset) {
        for (int i = 0; i < temp.getSize1(); i++) {
            for (int j = 0; j < temp.getSize2(); j++) {
                if (temp.matrix[i][j]) {
                    LinearGradient gradient = new LinearGradient(j * width + 6.6 + offset, i * width + 6.6 + offset,
                            j * width + 16.6 + offset, i * width + 16.6 + offset, false, CycleMethod.NO_CYCLE,
                            new Stop(0, game.nextFigure.color), new Stop(1, Color.WHITE));

                    Rectangle piece = new Rectangle(j * width + offset, i * width + offset, width, width);

                    piece.setFill(gradient);
                    group.getChildren().add(piece);
                }
            }
        }
    }

    public Group paintNextFigure() {
        Group nextFigureGroup = new Group();

        Matrix temp = game.nextFigure.getMatrix();

        Rectangle back = new Rectangle(0, 0, temp.getSize2() * 25, temp.getSize1() * 25);
        nextFigureGroup.getChildren().add(back);

        paintMiniNextFigure(nextFigureGroup, temp, 20, 5);

        return nextFigureGroup;
    }

    public HBox paintTopPanel() {
        HBox hBox = new HBox();
        hBox.setSpacing(20);
        VBox vBox = new VBox();

        Font font = new Font(25);

        Label points = new Label(String.format("Points: %06d", game.currentScore.getScore()));
        points.setFont(font);

        Label lvl = new Label(String.format("LVL: %02d", game.lvl));
        lvl.setFont(font);

        vBox.getChildren().add(points);
        vBox.getChildren().add(lvl);

        hBox.getChildren().add(vBox);
        hBox.getChildren().add(paintNextFigure());
        return hBox;
    }

    public void paintGameScene() {

        BorderPane border = new BorderPane();

        playScene = new Scene(border);

        border.setBackground(background);

        border.setCenter(paintPlayField());
        border.setTop(paintTopPanel());

        stage.setScene(playScene);

        playScene.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case Z:
                        game.tryToRotateLeft();
                        break;
                    case X:
                        game.tryToRotateRight();
                        break;
                    case DOWN:
                        game.tryToFall();
                        break;
                    case RIGHT:
                        game.tryToMoveRight();
                        break;
                    case LEFT:
                        game.tryToMoveLeft();
                    default:
                        break;
                }
            }
        });

        // border.setRight(addFlowPane());

    }

    @Override
    public void handle(long arg0) {
        paintGameScene();
        stage.show();

    }

    @Override
    public void run() {

        game.tryToFall();
        if (executorService.isShutdown()) {
            executorService2.shutdown();
            executorService.scheduleAtFixedRate(this, (long) (3000 / (Math.pow(game.lvl, 2))),
                    (long) (3000 / (Math.pow(game.lvl, 2))), TimeUnit.MILLISECONDS);
        }
        if (executorService2.isShutdown()) {
            executorService.shutdown();
            executorService2.scheduleAtFixedRate(this, (long) (3000 / (Math.pow(game.lvl, 2))),
                    (long) (3000 / (Math.pow(game.lvl, 2))), TimeUnit.SECONDS);
        }
    }

    @Override
    public void gameOver() {

        executorService.shutdown();
        executorService2.shutdown();
        stop();
        EndScene endScene = new EndScene(stage, background, game.currentScore);
        endScene.paint();
    }

    public void loopStart() {
        game = new Game();
        game.addListener(this);
        executorService.scheduleAtFixedRate(this, (long) (3000 / (Math.pow(game.lvl, 2))),
                (long) (3000 / (Math.pow(game.lvl, 2))), TimeUnit.MILLISECONDS);
        start();

    }

}
