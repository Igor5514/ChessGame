package pieces;

import java.util.ArrayList;

public class King extends Piece{

    public King(String currentCoordinate, String chessPieceName, boolean jump) {
        super(currentCoordinate,chessPieceName,jump);
        playKing(currentCoordinate);
    }

    public void playKing(String currentCoordinate){
        int i = currentCoordinate.charAt(0);
        int j = currentCoordinate.charAt(1);

    }

    public ArrayList<ArrayList<String>> getAllCoordinates(){
        ArrayList<ArrayList<String>> coordinatesArrayList = new ArrayList<>();
        return coordinatesArrayList;
    }
}

