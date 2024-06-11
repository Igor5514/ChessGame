package pieces;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece{
    private ArrayList<String> left = new ArrayList<>();
    private ArrayList<String> right = new ArrayList<>();
    private ArrayList<String> up = new ArrayList<>();
    private ArrayList<String> down = new ArrayList<>();

    public Rook(String currentCoordinate, String chessPieceName, boolean jump) {
        super(currentCoordinate,chessPieceName,jump);
        playRook(currentCoordinate);
    }

    public void playRook(String currentCoordinate){
        int i = Integer.parseInt(String.valueOf(currentCoordinate.charAt(0)));
        int j = Integer.parseInt(String.valueOf(currentCoordinate.charAt(1)));
        if(i != 1){
            for(int k= i ;k>= 1; k--){
                String coordinate = k+""+j;
                up.add(coordinate);
            }
        }
        if(j != 8){
            for(int k= j ;k<= 8; k++){
                String coordinate = i+""+k;
                right.add(coordinate);
            }
        }
        if(i != 8){
            for(int k= i ;k<= 8; k++){
                String coordinate = k+""+j;
                down.add(coordinate);
            }
        }
        if(j != 1){
            for(int k= j ;k>= 1; k--){
                String coordinate = i+""+k;
                left.add(coordinate);
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
        return coordinatesArrayList;
    }

    public ArrayList<String> getLeft() {
        return left;
    }

    public ArrayList<String> getRight() {
        return right;
    }

    public ArrayList<String> getUp() {
        return up;
    }

    public ArrayList<String> getDown() {
        return down;
    }



}
