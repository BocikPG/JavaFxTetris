import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

interface GameStart {
    void gameStart();
}

public class MainMenuScene {

    Stage stage;
    Background background;

    MainMenuScene(Stage newStage, Background newBackground) {
        stage = newStage;
        background = newBackground;
    }

    private List<GameStart> listeners = new ArrayList<GameStart>();

    public void addListener(GameStart toAdd) {
        listeners.add(toAdd);
    }

    private void sayGameStart() {
        for (GameStart hl : listeners)
            hl.gameStart();
    }

    public void paint() {
        Button startGameButton = new Button("START");
        startGameButton.setFont(new Font(30));
        startGameButton.setMaxSize(200, 100);

        startGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                sayGameStart();
            }
        });

        Button leaderBoardButton = new Button("LeaderBoard");
        leaderBoardButton.setFont(new Font(15));
        leaderBoardButton.setMaxSize(200, 100);

        VBox vBox = new VBox();

        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(startGameButton);
        vBox.getChildren().add(leaderBoardButton);
        vBox.setBackground(background);
        vBox.setSpacing(40);

        Scene startGameScene = new Scene(vBox, 300, 750);
        stage.setScene(startGameScene);

        leaderBoardButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                new LeaderBoardScene(stage, background, startGameScene);
            }
        });

        stage.show();

    }

}