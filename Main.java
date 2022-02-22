import java.io.FileInputStream;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class Main extends Application implements GameStart {

    GameLoop gameLoop;
    Stage stage;

    public Background settingBackGround() {
        Background backGround;
        try {
            FileInputStream inputImage = new FileInputStream("tlo.png");
            Image image = new Image(inputImage);
            BackgroundImage bcImage = new BackgroundImage(image, null, null, BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            backGround = new Background(bcImage);
        } catch (Exception e) {
            backGround = new Background(new BackgroundFill(Color.LIMEGREEN, null, null));
        }
        return backGround;

    }

    @Override
    public void gameStart() {
        gameLoop = new GameLoop(stage, settingBackGround());
        gameLoop.loopStart();
    }

    @Override
    public void start(Stage newStage) {
        stage = newStage;
        MainMenuScene mainMenuScene = new MainMenuScene(stage, settingBackGround());
        mainMenuScene.addListener(this);
        mainMenuScene.paint();

    }

    public static void main(String[] args) {

        Application.launch(args);

    }
}
