package pieces;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece{

    ArrayList<String> kingMoves = new ArrayList<>();
    private ArrayList<String> upLeft = new ArrayList<>();
    private ArrayList<String> upRight = new ArrayList<>();
    private ArrayList<String> downLeft = new ArrayList<>();
    private ArrayList<String> downRight = new ArrayList<>();
    private ArrayList<String> left = new ArrayList<>();
    private ArrayList<String> right = new ArrayList<>();
    private ArrayList<String> up = new ArrayList<>();
    private ArrayList<String> down = new ArrayList<>();

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

    public void checkForChess(String kingCoordiante){
        int i = Integer.parseInt(String.valueOf(kingCoordiante.charAt(0)));
        int j = Integer.parseInt(String.valueOf(kingCoordiante.charAt(1)));
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

    public List<ArrayList<String>> getKingCoordiantes(){
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

    public boolean checkForOpponents(GridPane chessBoard){
        List<Node> children = chessBoard.getChildren();



        return false;
    }


    @Override
    public List<ArrayList<String>> getAllCoordinates(){
        List<ArrayList<String>> coordinatesArrayList = new ArrayList<>();
        System.out.println(kingMoves);
        coordinatesArrayList.add(kingMoves);
        return coordinatesArrayList;
    }
}

