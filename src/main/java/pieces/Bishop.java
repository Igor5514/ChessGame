package pieces;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    private ArrayList<String> upLeft = new ArrayList<>();
    private ArrayList<String> upRight = new ArrayList<>();
    private ArrayList<String> downLeft = new ArrayList<>();
    private ArrayList<String> downRight = new ArrayList<>();

    public Bishop(String currentCoordinate, String chessPieceName, boolean jump) {
        super(currentCoordinate,chessPieceName,jump);
        playBishop(currentCoordinate);
    }

    public void playBishop(String currentCoordinate){
        int i = Integer.parseInt(String.valueOf(currentCoordinate.charAt(0)));
        int j = Integer.parseInt(String.valueOf(currentCoordinate.charAt(1)));
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

