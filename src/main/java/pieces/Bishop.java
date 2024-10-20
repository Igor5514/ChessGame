package pieces;

import functionalities.Movable;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece implements Movable {

    private  ArrayList<String> upRight = new ArrayList<>();
    private ArrayList<String> upLeft = new ArrayList<>();
    private ArrayList<String> downRight = new ArrayList<>();
    private ArrayList<String> downLeft = new ArrayList<>();


    public Bishop(String currentCoordinate, String chessPieceName, boolean jump) {
        super(currentCoordinate,chessPieceName,jump);
        playBishop(currentCoordinate);
    }

    public void playBishop(String currentCoordinate){
        int i = Integer.parseInt(String.valueOf(currentCoordinate.charAt(0)));
        int j = Integer.parseInt(String.valueOf(currentCoordinate.charAt(1)));

        upRight = bishopMoves(true, false, i, j, 1, 8, currentCoordinate);
        upLeft = bishopMoves(true, true, i, j, 1, 1, currentCoordinate);
        downRight = bishopMoves(false, false, i, j, 8, 8, currentCoordinate);
        downLeft = bishopMoves(false, true, i, j, 8, 1, currentCoordinate);
    }

    @Override
    public List<ArrayList<String>> getAllCoordinates(){
        List<ArrayList<String>> coordinatesArrayList = new ArrayList<>();
        if(!upRight.isEmpty()){
            coordinatesArrayList.add(upRight);
            upRight.clear();
        }
        if(!upLeft.isEmpty()){
            coordinatesArrayList.add(upLeft);
            upLeft.clear();
        }
        if(!downRight.isEmpty()){
            coordinatesArrayList.add(downRight);
            downRight.clear();
        }
        if(!downLeft.isEmpty()){
            coordinatesArrayList.add(downLeft);
            downLeft.clear();
        }

        return coordinatesArrayList;
    }

}

