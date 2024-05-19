package pieces;

import java.util.ArrayList;

public class Rook extends Piece{
    private ArrayList<String> left = new ArrayList<>();
    private ArrayList<String> right = new ArrayList<>();
    private ArrayList<String> up = new ArrayList<>();
    private ArrayList<String> down = new ArrayList<>();

    public Rook(String currentCoordinate, String chessPieceName, boolean jump) {
        super(currentCoordinate,chessPieceName,jump);
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
