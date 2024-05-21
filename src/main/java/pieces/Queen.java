package pieces;

import java.util.ArrayList;

public class Queen extends Piece{

    public Queen(String currentCoordinate, String chessPieceName, boolean jump) {
        super(currentCoordinate,chessPieceName,jump);
        playQueen(currentCoordinate);
    }

    public void playQueen(String currentCoordinate){
        int i = currentCoordinate.charAt(0);
        int j = currentCoordinate.charAt(1);

    }

    public ArrayList<ArrayList<String>> getAllCoordinates(){
        ArrayList<ArrayList<String>> coordinatesArrayList = new ArrayList<>();
        return coordinatesArrayList;
    }
}


