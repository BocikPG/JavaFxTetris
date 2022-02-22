import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class LeaderBoard {

    ArrayList<Points> arrayOfTop10Results;
    String nameOfFile;

    BufferedReader reader;

    LeaderBoard() {
        arrayOfTop10Results = new ArrayList<Points>();
    }

    LeaderBoard(String newNameOfFile) {
        nameOfFile = newNameOfFile;
        arrayOfTop10Results = new ArrayList<Points>();
        File myObj = new File(nameOfFile);
        try {
            myObj.createNewFile();
        } catch (IOException e) {
            System.out.println("Countn't create file");
        }
        try {
            Scanner scanner = new Scanner(myObj);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                int score = Integer.parseInt(data);
                data = scanner.nextLine();
                arrayOfTop10Results.add(new Points(score, data));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Countn't find file");
        }

    }

    public int getSize() {
        return arrayOfTop10Results.size();
    }

    public ArrayList<Points> getArrayOfTop10Results() {
        arrayOfTop10Results.sort((points1, points2) -> points2.compareTo(points1));
        return arrayOfTop10Results;
    }

    public void addElement(Points newScore) {
        arrayOfTop10Results.add(newScore);
        if (arrayOfTop10Results.size() > 9) {
            arrayOfTop10Results.sort((points1, points2) -> points1.compareTo(points2));
            arrayOfTop10Results.remove(0);
        }
    }

    public void saveLeaderBoardToFile() {
        arrayOfTop10Results.sort((points1, points2) -> points2.compareTo(points1));
        try (PrintWriter fw = new PrintWriter(new File(nameOfFile))) {
            for (Points point : arrayOfTop10Results) {
                fw.println(point.score);
                fw.println(point.autorOfScore);
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Countn't create file");
        }
    }

}
