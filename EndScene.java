import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class EndScene {

    Stage stage;
    Background background;
    LeaderBoard leaderBoard;
    Points currentPoints;

    EndScene(Stage newStage, Background newBackground, Points currePoints) {
        stage = newStage;
        background = newBackground;
        leaderBoard = new LeaderBoard("wyniki.txt");
        currentPoints = currePoints;
    }

    public void paint() {
        Button saveResultsButton = new Button("Save & exit");
        saveResultsButton.setFont(new Font(30));
        saveResultsButton.setMaxSize(200, 100);

        Label label1 = new Label("Nickname:");
        TextField textField = new TextField("Player");
        HBox hb = new HBox();
        hb.getChildren().addAll(label1, textField);
        hb.setSpacing(10);

        saveResultsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                currentPoints.setAutorOfScore(textField.getText());
                leaderBoard.addElement(currentPoints);
                leaderBoard.saveLeaderBoardToFile();
                System.exit(0);
            }
        });

        Button leaderBoardButton = new Button("LeaderBoard");
        leaderBoardButton.setFont(new Font(15));
        leaderBoardButton.setMaxSize(200, 100);

        VBox vBox = new VBox();

        Label resultLabel = new Label(new String(String.format("Your result: %d", currentPoints.getScore())));
        resultLabel.setFont(new Font(25));

        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(resultLabel);
        vBox.getChildren().add(hb);
        vBox.getChildren().add(saveResultsButton);
        vBox.getChildren().add(leaderBoardButton);
        vBox.setBackground(background);
        vBox.setSpacing(40);

        Scene endGameScene = new Scene(vBox, 300, 750);
        stage.setScene(endGameScene);

        leaderBoardButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                new LeaderBoardScene(stage, background, endGameScene);
            }
        });

        stage.show();

    }

}
