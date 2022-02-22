import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Figures.*;

interface GameOver {
    void gameOver();
}

public class Game {

    PlayField playField;
    Random random = new Random();
    Figures figures = new Figures();
    Figure nextFigure;
    Figure currentFigure;
    int lvl;
    int brokenLines;
    Points currentScore;

    Game() {
        playField = new PlayField();
        currentFigure = figures.randNewFigure(random.nextInt(7));
        nextFigure = figures.randNewFigure(random.nextInt(7));
        lvl = 3;
        brokenLines = 0;
        currentScore = new Points();
    }

    public PlayField getPlayField() {
        playField.changeFigureToPlayField(currentFigure);
        return playField;
    }

    private boolean raiseCurrentFigure() {
        currentFigure.raise();
        playField.changeFigureToPlayField(currentFigure);
        tryToDestroyRows();
        currentFigure = nextFigure;
        if (checkCollision()) {
            return true;
        }

        nextFigure = figures.randNewFigure(random.nextInt(7));
        return false;
    }

    // own event

    private List<GameOver> listeners = new ArrayList<GameOver>();

    public void addListener(GameOver toAdd) {
        listeners.add(toAdd);
    }

    private void sayGameOver() {
        for (GameOver hl : listeners)
            hl.gameOver();
    }

    // end own event

    public boolean tryToFall() { // if returns true - end of the game
        playField.removeFigureToPlayField(currentFigure);
        currentFigure.fall();
        try {
            if (checkCollision()) {
                if (raiseCurrentFigure())
                    sayGameOver();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            if (raiseCurrentFigure())
                sayGameOver();
        }

        return false;

    }

    public void tryToMoveLeft() {
        playField.removeFigureToPlayField(currentFigure);
        currentFigure.moveLeft();
        try {
            if (checkCollision()) { // exception can happen
                currentFigure.moveRight();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            currentFigure.moveRight();
        }

    }

    public void tryToMoveRight() {
        playField.removeFigureToPlayField(currentFigure);
        currentFigure.moveRight();
        try {
            if (checkCollision()) { // exception can happen
                currentFigure.moveLeft();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            currentFigure.moveLeft();
        }
    }

    public void tryToRotateRight() {
        playField.removeFigureToPlayField(currentFigure);
        currentFigure.rotateRight();
        try {
            if (checkCollision()) { // exception can happen
                currentFigure.rotateLeft();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            currentFigure.rotateLeft();
        }

    }

    public void tryToRotateLeft() {
        playField.removeFigureToPlayField(currentFigure);
        currentFigure.rotateLeft();
        try {
            if (checkCollision()) { // exception can happen
                currentFigure.rotateRight();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            currentFigure.rotateRight();
        }

    }

    private void tryToDestroyRows() {
        int newBrokenLines = playField.lookForFullRowsAndDestroyThem();
        currentScore.addToScore(newBrokenLines);

        if (newBrokenLines + brokenLines >= ((int) ((brokenLines + 10) / 10) * 10)) {
            lvl++;
        }
        brokenLines += newBrokenLines;
    }

    private boolean checkCollision() {
        Matrix tempMatrix = currentFigure.getMatrix();
        for (int i = 0; i < tempMatrix.getSize1(); i++) {
            for (int j = 0; j < tempMatrix.getSize2(); j++) {
                if (playField.playField[currentFigure.getX() - 1 + i][currentFigure.getY() - 1 + j].isSet
                        && tempMatrix.matrix[i][j]) {
                    return true;
                }
            }
        }

        return false;
    }

    public void print() // test function
    {
        System.out.println("-------------------------------------------");
        playField.changeFigureToPlayField(currentFigure);
        playField.clearBuffer();
        // playField.removeFigureToPlayField(currentFigure);
        for (int i = 0; i < playField.getBuffer(); i++) {
            for (int j = 0; j < playField.getX(); j++) {
                if (playField.playField[i][j].isSet) {
                    // System.out.println(brokenLines+" "+lvl);
                }
            }
        }

        for (int i = 0; i < playField.getY() + playField.getBuffer(); i++) {
            System.out.print("|");
            for (int j = 0; j < playField.getX(); j++) {
                if (playField.playField[i][j].isSet) {
                    System.out.print("+");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.print("|");
            System.out.println();
        }
        playField.removeFigureToPlayField(currentFigure);
        System.out.println("-------------------------------------------");
    }

}
