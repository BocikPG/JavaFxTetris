package Figures;

import java.awt.Point;

public class Matrix {

    public boolean matrix[][];

    public Matrix(Point[] pointArray) {
        matrix = new boolean[determinateMaxX(pointArray)][determinateMaxY(pointArray)];
        resetMatrix();

        setMatrix(pointArray);
    }

    public int getSize1() {
        return matrix.length;
    }

    public int getSize2() {
        return matrix[0].length;
    }

    public void transpose() {
        boolean newMatrix[][] = new boolean[matrix[0].length][matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                newMatrix[j][i] = matrix[i][j];
            }
        }

        matrix = newMatrix;
    }

    public void reverseAllRows() {
        for (int i = 0; i < matrix.length; i++) {
            reverseRow(i);
        }
    }

    public void reverseRow(int rowIndex) {
        for (int i = 0; i < matrix[rowIndex].length / 2; i++) {
            boolean tempValue = matrix[rowIndex][i];
            matrix[rowIndex][i] = matrix[rowIndex][matrix[rowIndex].length - i - 1];
            matrix[rowIndex][matrix[rowIndex].length - i - 1] = tempValue;
        }
    }

    private void setMatrix(Point[] pointArray) {
        for (int i = 0; i < pointArray.length; i++) {
            matrix[(int) pointArray[i].getX()][(int) pointArray[i].getY()] = true;
        }
    }

    private void resetMatrix() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = false;
            }
        }
    }

    private int determinateMaxX(Point[] pointArray) {
        int maxX = 0;
        for (int i = 0; i < pointArray.length; i++) {
            if (pointArray[i].getX() > maxX) {
                maxX = (int) pointArray[i].getX();
            }
        }
        return maxX + 1;
    }

    private int determinateMaxY(Point[] pointArray) {
        int maxY = 0;
        for (int i = 0; i < pointArray.length; i++) {
            if (pointArray[i].getY() > maxY) {
                maxY = (int) pointArray[i].getY();
            }
        }
        return maxY + 1;
    }

}
