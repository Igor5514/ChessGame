package pieces;

import java.util.ArrayList;

public class WhitePawn extends Piece{

    public WhitePawn(String currentCoordinate, String chessPieceName, boolean jump) {
        super(currentCoordinate,chessPieceName,jump);
        playWhitePawn(currentCoordinate);
    }

    public void playWhitePawn(String currentCoordinate){
        int i = currentCoordinate.charAt(0);
        int j = currentCoordinate.charAt(1);

    }

    public ArrayList<ArrayList<String>> getAllCoordinates(){
        ArrayList<ArrayList<String>> coordinatesArrayList = new ArrayList<>();
        return coordinatesArrayList;
    }
}



