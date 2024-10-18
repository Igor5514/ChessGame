package pieces;

import functionalities.Movable;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece implements Movable {

    private ArrayList<String> upRight = new ArrayList<>();
    private ArrayList<String> upLeft = new ArrayList<>();
    private ArrayList<String> downRight = new ArrayList<>();
    private ArrayList<String> downLeft = new ArrayList<>();
    private ArrayList<String> left = new ArrayList<>();
    private ArrayList<String> right = new ArrayList<>();
    private ArrayList<String> up = new ArrayList<>();
    private ArrayList<String> down = new ArrayList<>();

    public Queen(String currentCoordinate, String chessPieceName, boolean jump) {
        super(currentCoordinate,chessPieceName,jump);
        playQueen(currentCoordinate);
    }

    public void playQueen(String currentCoordinate){
        int i = Integer.parseInt(String.valueOf(currentCoordinate.charAt(0)));
        int j = Integer.parseInt(String.valueOf(currentCoordinate.charAt(1)));

        up = rookMoves(false,false,i,j, 1, currentCoordinate);
        right = rookMoves(true,true,j,i, 8, currentCoordinate);
        down = rookMoves(true,false,i,j, 8, currentCoordinate);
        left = rookMoves(false,true,j,i, 1, currentCoordinate);

        upRight = bishopMoves(true, false, i, j, 1, 8, currentCoordinate);
        upLeft = bishopMoves(true, true, i, j, 1, 1, currentCoordinate);
        downRight = bishopMoves(false, false, i, j, 8, 8, currentCoordinate);
        downLeft = bishopMoves(false, true, i, j, 8, 1, currentCoordinate);

        System.out.println(up);
    }

    @Override
    public List<ArrayList<String>> getAllCoordinates(){
        List<ArrayList<String>> coordinatesArrayList = new ArrayList<>();
        if(!up.isEmpty()){
            coordinatesArrayList.add(up);
        }
        if(!right.isEmpty()){
            coordinatesArrayList.add(right);
        }
        if(!down.isEmpty()){
            coordinatesArrayList.add(down);
        }
        if(!left.isEmpty()){
            coordinatesArrayList.add(left);
        }
        if(!upRight.isEmpty()){
            coordinatesArrayList.add(upRight);
        }
        if(!upLeft.isEmpty()){
            coordinatesArrayList.add(upLeft);
        }
        if(!downLeft.isEmpty()){
            coordinatesArrayList.add(downLeft);
        }


        return coordinatesArrayList;
    }
}


