package pieces;

import java.util.ArrayList;
import java.util.List;

public class WhitePawn extends Piece{

    ArrayList<String> whitePawnMoves = new ArrayList<>();

    public WhitePawn(String currentCoordinate, String chessPieceName, boolean jump) {
        super(currentCoordinate,chessPieceName,jump);
        playWhitePawn(currentCoordinate);
    }

    public void playWhitePawn(String currentCoordinate){
        int i = Integer.parseInt(String.valueOf(currentCoordinate.charAt(0)));
        int j = Integer.parseInt(String.valueOf(currentCoordinate.charAt(1)));
        int k = i - 1;
        int n = i - 2;
        String coordinate1 = k+""+j;
        String coordinate2 = n+""+j;
        if(i == 7){
            whitePawnMoves.add(coordinate1);
            whitePawnMoves.add(coordinate2);
        }else{
            whitePawnMoves.add(coordinate1);
        }

    }

    @Override
    public List<ArrayList<String>> getAllCoordinates(){
        List<ArrayList<String>> coordinatesArrayList = new ArrayList<>();
        coordinatesArrayList.add(whitePawnMoves);

        return coordinatesArrayList;
    }
}


