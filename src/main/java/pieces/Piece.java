package pieces;

import java.util.ArrayList;

public class Piece {
    ArrayList<String> enabledCoordinatesList = new ArrayList<>();
    private String currentCoordinate;
    private String chessPieceName;
    private String chessPieceType;
    private String chessPieceColor;
    private boolean jump = false;

    public Piece(String currentCoordinate, String chessPieceName, boolean jump) {
        this.currentCoordinate = currentCoordinate;
        this.chessPieceName = chessPieceName;
        chessPieceColor = chessPieceName.substring(0, 5);
        chessPieceType = chessPieceName.substring(6);
        this.jump = jump;
    }

    public ArrayList<ArrayList<String>> getAllCoordinates() {
        ArrayList<ArrayList<String>> coordinatesArrayList = new ArrayList<>();
        return coordinatesArrayList;
    }

    public String getChessPieceType() {
        return chessPieceType;
    }

    public String getChessPieceColor() {
        return chessPieceColor;
    }

    public ArrayList<String> getEnabledCoordinatesList() {
        return enabledCoordinatesList;
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
