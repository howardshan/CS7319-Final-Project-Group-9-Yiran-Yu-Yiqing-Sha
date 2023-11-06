package Unselected.Subroutine.MainGame;

public class GamePictures {
    public static String numberToText(int number) {
        switch (number) {
            case 1:
                return "Canada";
            case 2:
                return "China";
            case 3:
                return "Japan";
            case 4:
                return "Korea";
            case 5:
                return "Mexico";
            case 6:
                return "the UK";
            case 7:
                return "USA";
            case 8:
                return "Italy";
            case 9:
                return "Singapore";
            case 10:
                return "Spanish";
            // ... More numbers and corresponding text
            default:
                return "";
        }
    }

}
