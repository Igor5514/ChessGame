package pieces;

import java.util.ArrayList;

public class Piece {

    private String currentCoordinate;
    private String chessPieceName;
    private boolean jump = false;

    public Piece(String currentCoordinate, String chessPieceName, boolean jump) {
        this.currentCoordinate = currentCoordinate;
        this.chessPieceName = chessPieceName;
        this.jump = jump;
    }

    public ArrayList<ArrayList<String>> getAllCoordinates() {
        ArrayList<ArrayList<String>> coordinatesArrayList = new ArrayList<>();
        return coordinatesArrayList;
    }

    public String getCurrentCoordinate() {
        return currentCoordinate;
    }

    public String getChessPieceName() {
        return chessPieceName;
    }

    public boolean isJump() {
        return jump;
    }
}
