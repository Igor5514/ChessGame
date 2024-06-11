package pieces;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece{

    ArrayList<String> kingMoves = new ArrayList<>();

    public King(String currentCoordinate, String chessPieceName, boolean jump) {
        super(currentCoordinate,chessPieceName,jump);
        playKing(currentCoordinate);
    }

    public void playKing(String currentCoordinate){
        int i = Integer.parseInt(String.valueOf(currentCoordinate.charAt(0)));
        int j = Integer.parseInt(String.valueOf(currentCoordinate.charAt(1)));

        for(int k= i-1; k <= i+1; k++){
            for(int n = j-1; n<= j+1; n++){
                String coordinate = k+""+n;
                if(!coordinate.equals(currentCoordinate)){
                    kingMoves.add(coordinate);
                }
            }
        }

    }

    @Override
    public List<ArrayList<String>> getAllCoordinates(){
        List<ArrayList<String>> coordinatesArrayList = new ArrayList<>();
        System.out.println(kingMoves);
        coordinatesArrayList.add(kingMoves);
        return coordinatesArrayList;
    }
}

