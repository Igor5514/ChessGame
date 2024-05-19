package pieces;

public class Piece {

    private String currentCoordinate;
    private String chessPieceName;
    private boolean jump = false;

    public Piece(String currentCoordinate, String chessPieceName, boolean jump) {
        this.currentCoordinate = currentCoordinate;
        this.chessPieceName = chessPieceName;
        this.jump = jump;
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
