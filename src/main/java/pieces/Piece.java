package pieces;

import java.util.ArrayList;
import java.util.List;

public class Piece {

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

    public List<ArrayList<String>> getAllCoordinates() {
        List<ArrayList<String>> coordinatesArrayList = new ArrayList<>();
        return coordinatesArrayList;
    }

    public String getChessPieceType() {
        return chessPieceType;
    }

    public String getChessPieceColor() {
        return chessPieceColor;
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
