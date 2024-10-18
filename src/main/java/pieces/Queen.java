package pieces;

import functionalities.Movable;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece implements Movable {

    private final ArrayList<String> upLeft = new ArrayList<>();
    private final ArrayList<String> upRight = new ArrayList<>();
    private final ArrayList<String> downLeft = new ArrayList<>();
    private final ArrayList<String> downRight = new ArrayList<>();
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


        if(!(i==1 || j==1)){
            for(int k= i,n=j; n>= 1 && k>=1; k--, n--){
                String coordinate = k+""+n;
                upLeft.add(coordinate);
            }
        }
        if(!(i== 1 || j==8)){
            for(int k= i,n=j; k>= 1 && n<=8; k--, n++){
                String coordinate = k+""+n;
                upRight.add(coordinate);
            }
        }
        if(!(i== 8 || j==1)){
            for(int k= i,n=j; k<= 8 && n>=1; k++, n--){
                String coordinate = k+""+n;
                downLeft.add(coordinate);
            }
        }
        if(!(i== 8 || j==8)){
            for(int k= i,n=j; k<= 8 && n<=8; k++, n++){
                String coordinate = k+""+n;
                downRight.add(coordinate);
            }
        }
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
        if(!upLeft.isEmpty()){
            coordinatesArrayList.add(upLeft);
        }
        if(!upRight.isEmpty()){
            coordinatesArrayList.add(upRight);
        }
        if(!downLeft.isEmpty()){
            coordinatesArrayList.add(downLeft);
        }
        if(!downRight.isEmpty()){
            coordinatesArrayList.add(downRight);
        }

        return coordinatesArrayList;
    }
}


