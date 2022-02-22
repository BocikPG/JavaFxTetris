
import javafx.scene.paint.Color;

public class FieldPoint {
    boolean isSet;
    Color color;

    FieldPoint() {
        isSet = false;
        color = Color.GREY;
    }

    public void setPoint(Color newColor) {
        isSet = true;
        color = newColor;
    }

    public void clearPoint() {
        isSet = false;
        color = Color.GREY;
    }
}
