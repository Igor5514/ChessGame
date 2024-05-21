package pieces;

import java.util.ArrayList;

public class Knight extends Piece {

    public Knight(String currentCoordinate, String chessPieceName, boolean jump) {
        super(currentCoordinate,chessPieceName,jump);
        playKnight(currentCoordinate);
    }

    public void playKnight(String currentCoordinate){
        int i = currentCoordinate.charAt(0);
        int j = currentCoordinate.charAt(1);

    }

    public ArrayList<ArrayList<String>> getAllCoordinates(){
        ArrayList<ArrayList<String>> coordinatesArrayList = new ArrayList<>();
        return coordinatesArrayList;
    }
}
