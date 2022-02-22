package Figures;

import java.awt.Point;
import javafx.scene.paint.Color;

public abstract class Figure {

    Matrix matrix;
    public Color color;
    Point globalPosition; // central point of matrix

    Figure() {
        globalPosition = new Point(5, 4);
    }

    public int getX() {
        return (int) globalPosition.getX();
    }

    public int getY() {
        return (int) globalPosition.getY();
    }

    public Point getGlobalPosition() {
        return globalPosition;
    }

    public Matrix getMatrix() {
        return matrix;
    }

    public void rotateLeft() {
        matrix.reverseAllRows();
        matrix.transpose();
    }

    public void rotateRight() {
        matrix.transpose();
        matrix.reverseAllRows();
    }

    public boolean fall() {
        globalPosition.translate(1, 0);

        return false;
    }

    public boolean raise() {
        globalPosition.translate(-1, 0);

        return false;
    }

    public boolean moveLeft() {
        globalPosition.translate(0, -1);

        return false;
    }

    public boolean moveRight() {
        globalPosition.translate(0, 1);

        return false;
    }

}