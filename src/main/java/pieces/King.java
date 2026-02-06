package pieces;

import functionalities.BoardLogic;
import utils.Movable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.*;

public class King extends Piece implements Movable {

    private final String[] parallel = {"up", "right", "left", "down"};
    private final String[] diagonal = {"upLeft", "upRight", "downLeft", "downRight"};
    private boolean isUpdateCoordinatePresent = false;
    private boolean isPieceOnKingsPath = false;
    private boolean isEnemyPieceOnKingsEnd = false;
    private final ArrayList<String> kingMoves = new ArrayList<>();
    private final ArrayList<String> pawnCords = new ArrayList<>();
    private final ArrayList<String> knightCords = new ArrayList<>();
    private final Set<String> checkForCheckSet = new HashSet<>();
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
        playKing(currentCoordinate);
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

        knightCords.addAll(coordinateSet);

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

    public boolean checkForOpponents(GridPane chessBoard, String kingCoordinate,String clickedPieceCoordinate,String updateCoordinate, String kingColor) {
        checkForChess(kingCoordinate, kingColor);
        Map<String, ArrayList<String>> coordinatesMap = getKingCoordinates();
        BoardLogic boardLogic = new BoardLogic();
        List<Node> children = chessBoard.getChildren();
        for (Map.Entry<String, ArrayList<String>> entry : coordinatesMap.entrySet()) {
            String arrayListValue = entry.getKey();
            ArrayList<String> movementCoordinatesArrayList = entry.getValue();
            int size = children.size() -1;
            boolean asc = boardLogic.isIncreasing(movementCoordinatesArrayList, "white_king");
            for (int j = (asc ? 0 : size); (asc ? j <= size : j>= 0); j+=(asc ? 1 : -1)) {
                Button button = (Button) children.get(j);
                if(preValidator(button,updateCoordinate,movementCoordinatesArrayList,arrayListValue,clickedPieceCoordinate,kingColor) == 1){
                    clearAllLists();
                    return true;
                }else if(preValidator(button,updateCoordinate,movementCoordinatesArrayList,arrayListValue,clickedPieceCoordinate,kingColor) == 2){
                    break;
                }
            }
            if(isEnemyPieceOnKingsEnd){
                if(!checkForCheckSet.contains(updateCoordinate)){
                    clearAllLists();
                    return true;
                }
            }

            isUpdateCoordinatePresent = false;
            isEnemyPieceOnKingsEnd = false;
            isPieceOnKingsPath = false;
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

    public int preValidator(Button button, String updateCoordinate, ArrayList<String> movementCoordinatesArrayList, String arrayListValue, String clickedPieceCoordinate, String kingColor){
        if(button.getText().equals(updateCoordinate)) {
            updateChecker(updateCoordinate, button.getText(), movementCoordinatesArrayList);
        }
        if (button.getUserData() != null) {
            if (checkForMatching(arrayListValue,clickedPieceCoordinate, button.getText(), button.getUserData().toString(), movementCoordinatesArrayList, kingColor) == 1) {
                return 1;
            }else if(checkForMatching(arrayListValue,clickedPieceCoordinate, button.getText(), button.getUserData().toString(), movementCoordinatesArrayList, kingColor) == 2){
                return 2;
            }
        }
        if(movementCoordinatesArrayList.contains(button.getText())){
            checkForCheckSet.add(button.getText());
        }
        return 3;
    }

    public void updateChecker(String updateCoordinate,String squareCoordinate, ArrayList<String> movementCoordinatesArrayList){
        if (movementCoordinatesArrayList.contains(squareCoordinate)) {
            if(squareCoordinate.equals(updateCoordinate)){
                isUpdateCoordinatePresent = true;
            }
        }
    }



    public int checkForMatching(String arrayListValue,String clickedPieceCoordinate, String squareCoordinate, String buttonUserData, ArrayList<String> movementCoordinatesArrayList,String kingColor) {
        if (Arrays.asList(parallel).contains(arrayListValue)) {
            if (movementCoordinatesArrayList.contains(squareCoordinate)) {
                if(squareCoordinate.equals(clickedPieceCoordinate)){
                    isPieceOnKingsPath = true;
                    return 3;
                }
                if(kingColor.equals("white")){
                    if(buttonUserData.startsWith("white")){
                        return 2;
                    }
                    if(buttonUserData.equals("black_rook") || buttonUserData.equals("black_queen")){
                        if(isPieceOnKingsPath){
                            isEnemyPieceOnKingsEnd = true;
                        }
                        if(isUpdateCoordinatePresent){
                            return 2;
                        }
                        return 1;
                    }
                    if(buttonUserData.startsWith("black")){
                        return 2;
                    }
                }else if(kingColor.equals("black")){
                    if(buttonUserData.startsWith("black")){
                        return 2;
                    }
                    if(buttonUserData.equals("white_rook") || buttonUserData.equals("white_queen")){
                        if(isPieceOnKingsPath){
                            isEnemyPieceOnKingsEnd = true;
                        }
                        if(isUpdateCoordinatePresent){
                            return 2;
                        }
                        return 1;
                    }
                    if(buttonUserData.startsWith("white")){
                        return 2;
                    }
                }
                return 3;
            }
        } else if (Arrays.asList(diagonal).contains(arrayListValue)) {
            if (movementCoordinatesArrayList.contains(squareCoordinate)) {
                if(squareCoordinate.equals(clickedPieceCoordinate)){
                    isPieceOnKingsPath = true;
                    return 3;
                }
                if(kingColor.equals("white")){
                    if(buttonUserData.startsWith("white")){
                        return 2;
                    }
                    if ((buttonUserData.equals("black_bishop") || buttonUserData.equals("black_queen"))) {
                        if(isPieceOnKingsPath){
                            isEnemyPieceOnKingsEnd = true;
                        }
                        if(isUpdateCoordinatePresent){
                            return 2;
                        }
                        return 1;
                    }
                    if(buttonUserData.startsWith("black")){
                        return 2;
                    }
                }else if(kingColor.equals("black")){
                    if(buttonUserData.startsWith("black")){
                        return 2;
                    }
                    if ((buttonUserData.equals("white_bishop") || buttonUserData.equals("white_queen"))) {
                        if(isPieceOnKingsPath){
                            isEnemyPieceOnKingsEnd = true;
                        }
                        if(isUpdateCoordinatePresent){
                            return 2;
                        }
                        return 1;
                    }
                    if(buttonUserData.startsWith("white")){
                        return 2;
                    }
                }
                return 3;
            }
        }
        return 3;
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
