package pieces;

import functionalities.BoardLogic;
import utils.Movable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import utils.Util;

import java.util.*;
import java.util.stream.Collectors;

public class King extends Piece implements Movable, Util {

    private final String[] parallel = {"up", "right", "left", "down"};
    private final ArrayList<String> kingMoves = new ArrayList<>();
    private final ArrayList<String> pawnCords = new ArrayList<>();
    private final ArrayList<String> knightCords = new ArrayList<>();
    private ArrayList<String> up = new ArrayList<>();
    private ArrayList<String> right = new ArrayList<>();
    private ArrayList<String> down = new ArrayList<>();
    private ArrayList<String> left = new ArrayList<>();
    private ArrayList<String> upRight = new ArrayList<>();
    private ArrayList<String> upLeft = new ArrayList<>();
    private ArrayList<String> downRight = new ArrayList<>();
    private ArrayList<String> downLeft = new ArrayList<>();
    private List<Node> chessboardCopy;

    public King(String currentCoordinate, String chessPieceName, boolean jump, List<Node> chessboardCopy) {
        super(currentCoordinate, chessPieceName, jump);
        this.chessboardCopy = chessboardCopy;
        if(chessboardCopy == null){
            playKing(currentCoordinate);
        }

    }


    public void playKing(String currentCoordinate) {
        int i = Integer.parseInt(String.valueOf(currentCoordinate.charAt(0)));
        int j = Integer.parseInt(String.valueOf(currentCoordinate.charAt(1)));

        for (int k = i - 1; k <= i + 1; k++) {
            for (int n = j - 1; n <= j + 1; n++) {
                String coordinate = k + "" + n;
                if (!coordinate.equals(currentCoordinate)) {
                    kingMoves.add(coordinate);
                }
            }
        }
    }

    public void checkForChess(String kingCoordinate, String kingColor) {
        Set<String> coordinateSet = new HashSet<>();

        int i = Integer.parseInt(String.valueOf(kingCoordinate.charAt(0)));
        int j = Integer.parseInt(String.valueOf(kingCoordinate.charAt(1)));

        up = rookMoves(false, false, i, j, 1, kingCoordinate);
        right = rookMoves(true, true, j, i, 8, kingCoordinate);
        down = rookMoves(true, false, i, j, 8, kingCoordinate);
        left = rookMoves(false, true, j, i, 1, kingCoordinate);

        upRight = bishopMoves(true, false, i, j, 1, 8, kingCoordinate);
        upLeft = bishopMoves(true, true, i, j, 1, 1, kingCoordinate);
        downRight = bishopMoves(false, false, i, j, 8, 8, kingCoordinate);
        downLeft = bishopMoves(false, true, i, j, 8, 1, kingCoordinate);

        coordinateSet.addAll(knightMoves(false,false,false,true,i, i, j, 2,1,2));
        coordinateSet.addAll(knightMoves(false,true,true,true,j, i, j,  1,2,7));
        coordinateSet.addAll(knightMoves(true,false,true,true,i,  i, j, 2,1,7));
        coordinateSet.addAll(knightMoves(false,false,true,false,j, i, j, 1,2,2));


        Set<String> sortedSet =
                coordinateSet.stream()
                        .sorted(Comparator.comparingInt(Integer::parseInt))
                        .collect(Collectors.toCollection(LinkedHashSet::new));

        knightCords.addAll(sortedSet);

        if(kingColor.equals("black")){
            if(i != 8 || j != 1){
                int k = i + 1;
                int n = j - 1;
                String coordinate = k + "" + n;
                pawnCords.add(coordinate);
            }
            if(i != 8 || j != 8){
                int k = i + 1;
                int n = j + 1;
                String coordinate = k + "" + n;
                pawnCords.add(coordinate);
            }
        }else if(kingColor.equals("white")){
            if(i != 1 || j != 1){
                int k = i - 1;
                int n = j - 1;
                String coordinate = k + "" + n;
                pawnCords.add(coordinate);
            }
            if(i != 1 || j != 8){
                int k = i - 1;
                int n = j + 1;
                String coordinate = k + "" + n;
                pawnCords.add(coordinate);
            }
        }
    }

    public Map<String, ArrayList<String>> getKingCoordinates() {
        Map<String, ArrayList<String>> coordinatesMap = new HashMap<>();
        if (!up.isEmpty()) {
            coordinatesMap.put("up", up);
        }
        if (!right.isEmpty()) {
            coordinatesMap.put("right", right);
        }
        if (!down.isEmpty()) {
            coordinatesMap.put("down", down);
        }
        if (!left.isEmpty()) {
            coordinatesMap.put("left", left);
        }
        if (!upLeft.isEmpty()) {
            coordinatesMap.put("upLeft", upLeft);
        }
        if (!upRight.isEmpty()) {
            coordinatesMap.put("upRight", upRight);
        }
        if (!downLeft.isEmpty()) {
            coordinatesMap.put("downLeft", downLeft);
        }
        if (!downRight.isEmpty()) {
            coordinatesMap.put("downRight", downRight);
        }
        return coordinatesMap;
    }

    public boolean checkForOpponents(String kingCoordinate, String kingColor) {
        checkForChess(kingCoordinate, kingColor);
        Map<String, ArrayList<String>> coordinatesMap = getKingCoordinates();
        BoardLogic boardLogic = new BoardLogic();

        for (Map.Entry<String, ArrayList<String>> entry : coordinatesMap.entrySet()) {
            String arrayListValue = entry.getKey();
            ArrayList<String> movementCoordinatesArrayList = entry.getValue();
            int size = chessboardCopy.size() - 1;
            boolean asc = boardLogic.isIncreasing(movementCoordinatesArrayList, "white_king");

            for (int i = (asc ? 0 : size), j = 0;
                 (asc ? i <= size : i >= 0) && j < movementCoordinatesArrayList.size(); i = (asc ? i+1 : i-1)) {
                Button button = (Button) chessboardCopy.get(i);
                String movementCoordinate = movementCoordinatesArrayList.get(j);
                if(button.getText().equals(movementCoordinate)){
                    if(checkForMatching(arrayListValue, button.getUserData(), kingColor).equals(KingAttackStatus.IRRELEVANT_PIECE)){
                        break;
                    }
                    if(checkForMatching(arrayListValue, button.getUserData(), kingColor).equals(KingAttackStatus.ENEMY_PIECE)){
                        return true;
                    }
                    j++;
                }
            }

        }
        clearAllLists();
        return false;
    }

    public boolean pawnValidator(String kingCoordinate, ArrayList<String> pawnCords, Button squareButton){


        return false;
    }

    public boolean knightValidator(String kingCoordinate, ArrayList<String> pawnCords, Button squareButton){


        return false;
    }

    public KingAttackStatus checkForMatching(String arrayListValue, Object userData,String kingColor) {
        String buttonUserData;
        String buttonUserDataColor;
        String checkColor = kingColor.equals("white") ? "black" : "white";

        if(userData != null){
            buttonUserData = userData.toString();
            buttonUserDataColor = buttonUserData.startsWith("white") ? "white" : "black";
            if (Arrays.asList(parallel).contains(arrayListValue)) {
                return checkForMatchingValidator(kingColor, "_rook", buttonUserDataColor, buttonUserData, checkColor);
            } else {
                return checkForMatchingValidator(kingColor, "_bishop", buttonUserDataColor, buttonUserData, checkColor);
            }
        }
        return KingAttackStatus.CONTINUE;
    }

    public KingAttackStatus checkForMatchingValidator(String kingColor, String piece , String buttonUserDataColor, String buttonUserData, String checkColor){
        if(kingColor.equals(buttonUserDataColor)){
            return KingAttackStatus.IRRELEVANT_PIECE;
        }
        if(buttonUserData.equals(checkColor + piece) || buttonUserData.equals(checkColor + "_queen")){
            return KingAttackStatus.ENEMY_PIECE;
        }
        if(buttonUserDataColor.equals(checkColor)){
            return KingAttackStatus.IRRELEVANT_PIECE;
        }
        return KingAttackStatus.CONTINUE;
    }

    public void clearAllLists(){
        kingMoves.clear();
        up.clear();
        right.clear();
        down.clear();
        left.clear();
        upRight.clear();
        upLeft.clear();
        downRight.clear();
        downLeft.clear();
    }

    @Override
    public List<ArrayList<String>> getAllCoordinates() {
        List<ArrayList<String>> coordinatesArrayList = new ArrayList<>();
        coordinatesArrayList.add(kingMoves);
        return coordinatesArrayList;
    }
}
