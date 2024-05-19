package pieces;

import java.util.ArrayList;

public class Rook {
    private String currentCoordinate;
    private String chessPieceName;
    private boolean jump = false;
    private ArrayList<String> left = new ArrayList<>();
    private ArrayList<String> right = new ArrayList<>();
    private ArrayList<String> up = new ArrayList<>();
    private ArrayList<String> down = new ArrayList<>();

    public Rook(String currentCoordinate, String chessPieceName, boolean jump) {
        this.currentCoordinate = currentCoordinate;
        this.chessPieceName = chessPieceName;
        this.jump = jump;
    }

    public boolean isJump() {
        return jump;
    }

    public String getCurrentCoordinate() {
        return currentCoordinate;
    }

    public String getChessPieceName() {
        return chessPieceName;
    }

    public ArrayList<String> getLeft() {
        return left;
    }

    public ArrayList<String> getRight() {
        return right;
    }

    public ArrayList<String> getUp() {
        return up;
    }

    public ArrayList<String> getDown() {
        return down;
    }



}
