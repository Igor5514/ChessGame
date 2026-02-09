package pieces;

import utils.Movable;

import java.util.*;
import java.util.stream.Collectors;

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

        coordinateSet.addAll(knightMoves(false,false,false,true,i, i, j, 2,1,2));
        coordinateSet.addAll(knightMoves(false,true,true,true,j, i, j,  1,2,7));
        coordinateSet.addAll(knightMoves(true,false,true,true,i,  i, j, 2,1,7));
        coordinateSet.addAll(knightMoves(false,false,true,false,j, i, j, 1,2,2));

        Set<String> sortedSet =
                coordinateSet.stream()
                        .sorted(Comparator.comparingInt(Integer::parseInt))
                        .collect(Collectors.toCollection(LinkedHashSet::new));

        knightMoves.addAll(sortedSet);
    }


    @Override
    public List<ArrayList<String>> getAllCoordinates(){
        List<ArrayList<String>> coordinatesArrayList = new ArrayList<>();
        coordinatesArrayList.add(knightMoves);
        return coordinatesArrayList;

    }
}

