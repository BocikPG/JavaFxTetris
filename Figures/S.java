package Figures;

import java.awt.Point;

import javafx.scene.paint.Color;

public class S extends Figure {

    S() {
        Point array[] = new Point[4];
        array[0] = new Point(0, 1);
        array[1] = new Point(0, 2);
        array[2] = new Point(1, 0);
        array[3] = new Point(1, 1);
        matrix = new Matrix(array);
        color = Color.PINK;
    }
}
