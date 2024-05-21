package pieces;

import java.util.ArrayList;

public class Bishop extends Piece {

    public Bishop(String currentCoordinate, String chessPieceName, boolean jump) {
        super(currentCoordinate,chessPieceName,jump);
        playBishop(currentCoordinate);
    }

    public void playBishop(String currentCoordinate){
        int i = currentCoordinate.charAt(0);
        int j = currentCoordinate.charAt(1);

    }

    public ArrayList<ArrayList<String>> getAllCoordinates(){
        ArrayList<ArrayList<String>> coordinatesArrayList = new ArrayList<>();
        return coordinatesArrayList;
    }
}

