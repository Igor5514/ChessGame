package pieces;

import functionalities.Movable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Knight extends Piece implements Movable {

    ArrayList<String> knightMoves = new ArrayList<>();

    public Knight(String currentCoordinate, String chessPieceName, boolean jump) {
        super(currentCoordinate,chessPieceName,jump);
        playKnight(currentCoordinate);
    }

    public void playKnight(String currentCoordinate){
        Set<String> coordinateSet = new HashSet<>();
        int i = Integer.parseInt(String.valueOf(currentCoordinate.charAt(0)));
        int j = Integer.parseInt(String.valueOf(currentCoordinate.charAt(1)));

        //promeniti skroz nacin kako se gledaju coordinate

        coordinateSet.addAll(knightMoves(false,false,false,true,i, i, j, 2,1,2));
        System.out.println(coordinateSet);
        System.out.println("----------------");

        coordinateSet.addAll(knightMoves(false,true,true,true,j, i, j,  1,2,7));
        System.out.println(coordinateSet);
        System.out.println("----------------");
        coordinateSet.addAll(knightMoves(true,false,true,true,i,  i, j, 2,1,7));
        System.out.println(coordinateSet);
        System.out.println("----------------");
        coordinateSet.addAll(knightMoves(false,false,true,false,j, i, j, 1,2,2));
        System.out.println(coordinateSet);
        System.out.println("----------------");

        knightMoves.addAll(coordinateSet);
    }


    @Override
    public List<ArrayList<String>> getAllCoordinates(){
        List<ArrayList<String>> coordinatesArrayList = new ArrayList<>();
        coordinatesArrayList.add(knightMoves);
        return coordinatesArrayList;

    }
}

