public class Points implements Comparable<Points> {

    int score = 0;
    String autorOfScore;

    Points() {

    }

    Points(int newScore, String newAutor) {
        score = newScore;
        autorOfScore = newAutor;
    }

    public void addToScore(int clearedLines) {
        switch (clearedLines) {
            case 1:
                score += 40;
                break;
            case 2:
                score += 100;
                break;
            case 3:
                score += 300;
                break;
            case 4:
                score += 1200;
                break;
            default:
                break;
        }
    }

    public void setAutorOfScore(String autor) {
        autorOfScore = autor;
    }

    public int compareTo(Points points) {
        return score - points.score;
    }

    public int getScore() {
        return score;
    }

    public String getAutorOfScore() {
        return autorOfScore;
    }

}
