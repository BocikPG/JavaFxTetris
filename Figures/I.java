package Figures;

import java.awt.Point;

import javafx.scene.paint.Color;

public class I extends Figure {

    boolean rotateState = true;

    I() {
        Point array[] = new Point[4];
        array[0] = new Point(0, 0);
        array[1] = new Point(0, 1);
        array[2] = new Point(0, 2);
        array[3] = new Point(0, 3);
        matrix = new Matrix(array);
        color = Color.RED;
    }

    public void rotateLeft() {
        super.rotateLeft();
        if (rotateState)
            globalPosition.translate(0, 1);
        else
            globalPosition.translate(0, -1);
        rotateState = !rotateState;

    }

    public void rotateRight() {
        super.rotateRight();
        if (rotateState)
            globalPosition.translate(0, 1);
        else
            globalPosition.translate(0, -1);
        rotateState = !rotateState;
    }
}
