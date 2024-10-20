package pieces;

import functionalities.Movable;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece implements Movable {
    private ArrayList<String> up = new ArrayList<>();
    private ArrayList<String> right = new ArrayList<>();
    private ArrayList<String> down = new ArrayList<>();
    private ArrayList<String> left = new ArrayList<>();

    public Rook(String currentCoordinate, String chessPieceName, boolean jump) {
        super(currentCoordinate,chessPieceName,jump);
        playRook(currentCoordinate);
    }

    public void playRook(String currentCoordinate){
        int i = Integer.parseInt(String.valueOf(currentCoordinate.charAt(0)));
        int j = Integer.parseInt(String.valueOf(currentCoordinate.charAt(1)));

        up = rookMoves(false, false, i, j, 1, currentCoordinate);
        right = rookMoves(true, true, j, i, 8, currentCoordinate);
        down = rookMoves(true, false, i, j, 8, currentCoordinate);
        left = rookMoves(false, true, j, i, 1, currentCoordinate);
    }

    @Override
    public List<ArrayList<String>> getAllCoordinates(){
        List<ArrayList<String>> coordinatesArrayList = new ArrayList<>();
        if(!up.isEmpty()){
            coordinatesArrayList.add(up);
            up.clear();
        }
        if(!right.isEmpty()){
            coordinatesArrayList.add(right);
            right.clear();
        }
        if(!down.isEmpty()){
            coordinatesArrayList.add(down);
            down.clear();
        }
        if(!left.isEmpty()){
            coordinatesArrayList.add(left);
            left.clear();
        }
        return coordinatesArrayList;
    }

}
