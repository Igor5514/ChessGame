package pieces;

import functionalities.BoardLogic;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.*;

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

    public Map<String, ArrayList<String>> getKingCoordiantes(){
        Map<String, ArrayList<String>> coodinatesMap = new HashMap<>();
        if(!up.isEmpty()){
            coodinatesMap.put("up",up);
        }
        if(!right.isEmpty()){
            coodinatesMap.put("right",right);
        }
        if(!down.isEmpty()){
            coodinatesMap.put("down",down);
        }
        if(!left.isEmpty()){
            coodinatesMap.put("left",left);
        }
        if(!upLeft.isEmpty()){
            coodinatesMap.put("upLeft",upLeft);
        }
        if(!upRight.isEmpty()){
            coodinatesMap.put("upRight",upRight);
        }
        if(!downLeft.isEmpty()){
            coodinatesMap.put("downLeft",downLeft);
        }
        if(!downRight.isEmpty()){
            coodinatesMap.put("downRight",downRight);
        }
        return coodinatesMap;
    }

    public boolean checkForOpponents(GridPane chessBoard,String kingCoordinate){
        Map<String, ArrayList<String>> coodinatesMap = getKingCoordiantes();
        checkForChess(kingCoordinate);
        BoardLogic boardLogic = new BoardLogic();
        List<Node> children = chessBoard.getChildren();
        for(Map.Entry<String,ArrayList<String>> entry : coodinatesMap.entrySet()) {
            String arrayListvalue = entry.getKey();
            ArrayList<String> arrayList = entry.getValue();
            if (boardLogic.isIncreasing(arrayList, "white_king")) {
                for (Node node : children) {
                    Button button = (Button) node;
                    if(checkForMatching(arrayListvalue,button.getText(),button.getUserData().toString(),arrayList)){
                        return true;
                    }
                    return false;
                }
            } else {
                for (int j = children.size() - 1; j >= 0; j--) {
                    Button button = (Button) children.get(j);
                    if(checkForMatching(arrayListvalue,button.getText(),button.getUserData().toString(),arrayList)){
                        return true;
                    }
                    return false;
                }
            }
        }
        return false;
    }

    public boolean checkForMatching(String arrayListvalue, String buttonCoordinate, String buttonUserData, ArrayList<String> arrayList){
        String[] parallel = {"up","right","left","down"};
        String[] diagonal = {"upLeft","upRight","downLeft","downRight"};
        if(Arrays.stream(parallel).anyMatch(arrayListvalue::equals)){
            if(arrayList.contains(buttonCoordinate)){
                if(buttonUserData.toString().substring(6).equals("rook") || buttonUserData.toString().substring(6).equals("queen")){
                    return true;
                }
            }
        } else if (Arrays.stream(diagonal).anyMatch(arrayListvalue::equals)) {
            if(arrayList.contains(buttonCoordinate)){
                if(buttonUserData.toString().substring(6).equals("bishop") || buttonUserData.toString().substring(6).equals("queen")){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<ArrayList<String>> getAllCoordinates(){
        List<ArrayList<String>> coordinatesArrayList = new ArrayList<>();

        coordinatesArrayList.add(kingMoves);
        return coordinatesArrayList;
    }
}

