import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LeaderBoardScene {

    Stage stage;
    Background background;
    LeaderBoard leaderBoard;
    Points currentPoints;
    Scene parentScene;

    LeaderBoardScene(Stage newStage, Background newBackground, Scene newParentScene) {
        stage = newStage;
        background = newBackground;
        leaderBoard = new LeaderBoard("wyniki.txt");
        parentScene = newParentScene;
        paint();
    }

    private void addLeaderBoardDataToVBox(VBox vBox) {
        for (int i = 0; i < leaderBoard.getSize(); i++) {
            HBox oneRecord = new HBox();
            oneRecord.setAlignment(Pos.CENTER);
            oneRecord.setSpacing(40);
            Label points = new Label(Integer.toString(leaderBoard.getArrayOfTop10Results().get(i).score));
            points.setFont(new Font(25));
            Label autor = new Label(leaderBoard.getArrayOfTop10Results().get(i).autorOfScore);
            autor.setFont(new Font(25));
            oneRecord.getChildren().add(points);
            oneRecord.getChildren().add(autor);
            vBox.getChildren().add(oneRecord);
        }
    }

    public void paint() {

        Button goBackButton = new Button("Go Back");
        goBackButton.setFont(new Font(30));
        goBackButton.setMaxSize(200, 100);

        goBackButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                stage.setScene(parentScene);
                stage.show();
            }
        });

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);

        HBox labelBox = new HBox();
        labelBox.setSpacing(40);
        labelBox.setAlignment(Pos.CENTER);
        Label pointsLabel = new Label("Points");
        pointsLabel.setFont(new Font(25));
        Label autorLabel = new Label("Author");
        autorLabel.setFont(new Font(25));
        labelBox.getChildren().add(pointsLabel);
        labelBox.getChildren().add(autorLabel);
        vBox.getChildren().add(labelBox);

        addLeaderBoardDataToVBox(vBox);

        vBox.getChildren().add(goBackButton);

        vBox.setBackground(background);

        Scene startGameScene = new Scene(vBox, 300, 750);
        stage.setScene(startGameScene);

        stage.show();

    }
}
