package pieces;

import java.util.ArrayList;
import java.util.List;

public class BlackPawn extends Piece{

    ArrayList<String> blackPawnMoves = new ArrayList<>();

    public BlackPawn(String currentCoordinate, String chessPieceName, boolean jump) {
        super(currentCoordinate,chessPieceName,jump);
        playBlackPawn(currentCoordinate);
    }

    public void playBlackPawn(String currentCoordinate){
        int i = Integer.parseInt(String.valueOf(currentCoordinate.charAt(0)));
        int j = Integer.parseInt(String.valueOf(currentCoordinate.charAt(1)));
        int k = i + 1;
        int n = i + 2;
        String coordinate1 = k+""+j;
        String coordinate2 = n+""+j;
        if(i == 2){
            blackPawnMoves.add(coordinate1);
            blackPawnMoves.add(coordinate2);
        }else{
            blackPawnMoves.add(coordinate1);
        }

    }

    @Override
    public List<ArrayList<String>> getAllCoordinates(){
        List<ArrayList<String>> coordinatesArrayList = new ArrayList<>();
        coordinatesArrayList.add(blackPawnMoves);

        return coordinatesArrayList;
    }
}


