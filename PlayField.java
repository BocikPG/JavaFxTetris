import Figures.*;

public class PlayField {
    public FieldPoint playField[][];
    int y;
    int x;
    int buffer;

    PlayField() {
        y = 24;
        buffer = 3;
        x = 10;
        playField = new FieldPoint[y + buffer][x];
        for (int i = 0; i < y + buffer; i++) {
            for (int j = 0; j < x; j++) {
                playField[i][j] = new FieldPoint();
            }
        }
    }

    PlayField(PlayField field) {
        y = field.y;
        x = field.x;
        buffer = field.buffer;
        playField = new FieldPoint[y + buffer][x];
        for (int i = 0; i < y + buffer; i++) {
            for (int j = 0; j < x; j++) {
                playField[i][j] = new FieldPoint();
                if (field.playField[i][j].isSet) {
                    playField[i][j].setPoint(field.playField[i][j].color);
                }
            }
        }
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getBuffer() {
        return buffer;
    }

    public void clearBuffer() {
        for (int i = 0; i < getBuffer() + 1; i++) {
            for (int j = 0; j < getX(); j++)
                playField[i][j].clearPoint();
        }
    }

    public void changeFigureToPlayField(Figure figure) {
        Matrix tempMatrix = figure.getMatrix();
        for (int i = 0; i < tempMatrix.getSize1(); i++) {
            for (int j = 0; j < tempMatrix.getSize2(); j++) {
                if (tempMatrix.matrix[i][j]) {
                    playField[figure.getX() - 1 + i][figure.getY() - 1 + j].setPoint(figure.color);
                }
            }
        }
    }

    public void removeFigureToPlayField(Figure figure) {
        Matrix tempMatrix = figure.getMatrix();
        for (int i = 0; i < tempMatrix.getSize1(); i++) {
            for (int j = 0; j < tempMatrix.getSize2(); j++) {
                if (tempMatrix.matrix[i][j]) {
                    playField[figure.getX() - 1 + i][figure.getY() - 1 + j].clearPoint();
                }
            }
        }
    }

    public int lookForFullRowsAndDestroyThem() {
        int clearedRows = 0;
        boolean isFull = false;
        for (int i = 0; i < playField.length; i++) {
            isFull = true;
            for (int j = 0; j < playField[i].length; j++) {
                if (!playField[i][j].isSet) {
                    isFull = false;
                    continue;
                }
            }
            if (isFull) {
                removeRow(i);
                i--;
                clearedRows++;
            }

        }
        return clearedRows;
    }

    private void removeRow(int index) {
        for (int i = index; i > 0; i--) {
            for (int j = 0; j < playField[index].length; j++) {
                if (playField[i - 1][j].isSet)
                    playField[i][j].setPoint(playField[i - 1][j].color);
                else
                    playField[i][j].clearPoint();
            }
        }

    }
}
