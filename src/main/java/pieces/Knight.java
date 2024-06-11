package pieces;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Knight extends Piece {

    ArrayList<String> knightMoves = new ArrayList<>();

    public Knight(String currentCoordinate, String chessPieceName, boolean jump) {
        super(currentCoordinate,chessPieceName,jump);
        playKnight(currentCoordinate);
    }

    public void playKnight(String currentCoordinate){
        Set<String> coordinateSet = new HashSet<>();
        int i = Integer.parseInt(String.valueOf(currentCoordinate.charAt(0)));
        int j = Integer.parseInt(String.valueOf(currentCoordinate.charAt(1)));

        if(!(i <= 2)){
            int k = i-2;
            int n = j-1;
            int a = i-2;
            int b = j+1;
            String coordinate1 = k+""+n;
            String coordinate2 = a+""+b;
            if (coordinateSet.add(coordinate1)){
                knightMoves.add(coordinate1);
            }
            if (coordinateSet.add(coordinate2)){
                knightMoves.add(coordinate2);
            }
        }

        if(!(j >= 7)){
            int k = i-1;
            int n = j+2;
            int a = i+1;
            int b = j+2;
            String coordinate1 = k+""+n;
            String coordinate2 = a+""+b;
            if (coordinateSet.add(coordinate1)){
                knightMoves.add(coordinate1);
            }
            if (coordinateSet.add(coordinate2)){
                knightMoves.add(coordinate2);
            }
        }

        if(!(i >= 7)){
            int k = i+2;
            int n = j-1;
            int a = i+2;
            int b = j+1;
            String coordinate1 = k+""+n;
            String coordinate2 = a+""+b;
            if (coordinateSet.add(coordinate1)){
                knightMoves.add(coordinate1);
            }
            if (coordinateSet.add(coordinate2)){
                knightMoves.add(coordinate2);
            }
        }

        if(!(j <= 2)){
            int k = i-1;
            int n = j-2;
            int a = i+1;
            int b = j-2;
            String coordinate1 = k+""+n;
            String coordinate2 = a+""+b;
            if (coordinateSet.add(coordinate1)){
                knightMoves.add(coordinate1);
            }
            if (coordinateSet.add(coordinate2)){
                knightMoves.add(coordinate2);
            }
        }
    }


    @Override
    public List<ArrayList<String>> getAllCoordinates(){
        List<ArrayList<String>> coordinatesArrayList = new ArrayList<>();
        coordinatesArrayList.add(knightMoves);
        return coordinatesArrayList;

    }
}

