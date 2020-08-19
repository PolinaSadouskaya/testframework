package helpers;

public class Generator {

    public static String generateId(int numberOfSymbols){
        return String.valueOf(Math.round(Math.random()*(Math.pow(10,numberOfSymbols))));
    }
}
