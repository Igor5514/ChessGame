package functionalities;

import java.util.ArrayList;

public class Move {
    private String currentCoordinate;
    private String chessPieceName;
    private ArrayList<String> possibleMoves;
    private boolean jump = false;

    public Move(String currentCoordinate, String chessPieceName, ArrayList<String> possibleMoves, boolean jump) {
        this.currentCoordinate = currentCoordinate;
        this.chessPieceName = chessPieceName;
        this.possibleMoves = possibleMoves;
        this.jump = jump;
    }

    public boolean isJump() {
        return jump;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }

    public String getCurrentCoordinate() {
        return currentCoordinate;
    }

    public void setCurrentCoordinate(String currentCoordinate) {
        this.currentCoordinate = currentCoordinate;
    }

    public String getChessPieceName() {
        return chessPieceName;
    }

    public void setChessPieceName(String chessPieceName) {
        this.chessPieceName = chessPieceName;
    }

    public ArrayList<String> getPossibleMoves() {
        return possibleMoves;
    }

    public void setPossibleMoves(ArrayList<String> possibleMoves) {
        this.possibleMoves = possibleMoves;
    }

    @Override
    public String toString() {
        return "Move{" +
                "currentCoordinate='" + currentCoordinate + '\'' +
                ", chessPieceName='" + chessPieceName + '\'' +
                ", possibleMoves=" + possibleMoves +
                '}';
    }
}
