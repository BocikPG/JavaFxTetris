package Figures;

public class Figures {

    public Figure randNewFigure(int number) {
        switch (number % 7) {
            case 0:
                return new I();
            case 1:
                return new J();
            case 2:
                return new L();
            case 3:
                return new O();
            case 4:
                return new S();
            case 5:
                return new T();
            case 6:
                return new Z();
        }
        return new Z();
    }

}
