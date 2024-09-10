package pieces;

import functionalities.BoardLogic;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.*;

public class King extends Piece {

    ArrayList<String> kingMoves = new ArrayList<>();
    private final ArrayList<String> up = new ArrayList<>();
    private final ArrayList<String> right = new ArrayList<>();
    private final ArrayList<String> down = new ArrayList<>();
    private final ArrayList<String> left = new ArrayList<>();
    private final ArrayList<String> upLeft = new ArrayList<>();
    private final ArrayList<String> upRight = new ArrayList<>();
    private final ArrayList<String> downLeft = new ArrayList<>();
    private final ArrayList<String> downRight = new ArrayList<>();

    public King(String currentCoordinate, String chessPieceName, boolean jump) {
        super(currentCoordinate, chessPieceName, jump);
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

    public void checkForChess(String kingCoordinate) {
        int i = Integer.parseInt(String.valueOf(kingCoordinate.charAt(0)));
        int j = Integer.parseInt(String.valueOf(kingCoordinate.charAt(1)));

        if (i != 1) {
            for (int k = i; k >= 1; k--) {
                String coordinate = k + "" + j;
                if (!coordinate.equals(kingCoordinate)){
                    up.add(coordinate);
                }

            }
        }
        if (j != 8) {
            for (int k = j; k <= 8; k++) {
                String coordinate = i + "" + k;
                if (!coordinate.equals(kingCoordinate)) {
                    right.add(coordinate);
                }
            }
        }
        if (i != 8) {
            for (int k = i; k <= 8; k++) {
                String coordinate = k + "" + j;
                if (!coordinate.equals(kingCoordinate)) {
                    down.add(coordinate);
                }
            }
        }
        if (j != 1) {
            for (int k = j; k >= 1; k--) {
                String coordinate = i + "" + k;
                if (!coordinate.equals(kingCoordinate)) {
                    left.add(coordinate);
                }
            }
        }
        if (!(i == 1 || j == 1)) {
            for (int k = i, n = j; n >= 1 && k >= 1; k--, n--) {
                String coordinate = k + "" + n;
                if (!coordinate.equals(kingCoordinate)) {
                    upLeft.add(coordinate);
                }
            }
        }
        if (!(i == 1 || j == 8)) {
            for (int k = i, n = j; k >= 1 && n <= 8; k--, n++) {
                String coordinate = k + "" + n;
                if (!coordinate.equals(kingCoordinate)) {
                    upRight.add(coordinate);
                }
            }
        }
        if (!(i == 8 || j == 1)) {
            for (int k = i, n = j; k <= 8 && n >= 1; k++, n--) {
                String coordinate = k + "" + n;
                if (!coordinate.equals(kingCoordinate)) {
                    downLeft.add(coordinate);
                }
            }
        }
        if (!(i == 8 || j == 8)) {
            for (int k = i, n = j; k <= 8 && n <= 8; k++, n++) {
                String coordinate = k + "" + n;
                if (!coordinate.equals(kingCoordinate)) {
                    downRight.add(coordinate);
                }
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

    public boolean checkForOpponents(GridPane chessBoard, String kingCoordinate,String updateCoordinate, String kingColor) {
        checkForChess(kingCoordinate);
        Map<String, ArrayList<String>> coordinatesMap = getKingCoordinates();
        BoardLogic boardLogic = new BoardLogic();
        List<Node> children = chessBoard.getChildren();

        for (Map.Entry<String, ArrayList<String>> entry : coordinatesMap.entrySet()) {
            String arrayListValue = entry.getKey();
            ArrayList<String> arrayList = entry.getValue();

            if (boardLogic.isIncreasing(arrayList, "white_king")) {
                for (Node node : children) {
                    Button button = (Button) node;
                    if (button.getUserData() != null) {
                        if (checkForMatching(updateCoordinate, arrayListValue, button.getText(), button.getUserData().toString(), arrayList, kingColor) == 1) {
                            return true;
                        }else if(checkForMatching(updateCoordinate, arrayListValue, button.getText(), button.getUserData().toString(), arrayList, kingColor) == 2){
                            break;
                        }
                    }
                }
            } else {
                for (int j = children.size() - 1; j >= 0; j--) {
                    Button button = (Button) children.get(j);
                    if (button.getUserData() != null) {
                        if (checkForMatching(updateCoordinate, arrayListValue, button.getText(), button.getUserData().toString(), arrayList, kingColor) == 1) {
                            return true;
                        }else if(checkForMatching(updateCoordinate, arrayListValue, button.getText(), button.getUserData().toString(), arrayList, kingColor) == 2){
                            break;
                        }
                    }
                }
            }
        }

        coordinatesMap.clear();
        return false;
    }

    public int checkForMatching(String updateCoordinate,String arrayListValue, String buttonCoordinate, String buttonUserData, ArrayList<String> arrayList,String kingColor) {
        String[] parallel = {"up", "right", "left", "down"};
        String[] diagonal = {"upLeft", "upRight", "downLeft", "downRight"};
        System.out.println(arrayList);
        System.out.println(buttonCoordinate);
        System.out.println(updateCoordinate);
        if (Arrays.asList(parallel).contains(arrayListValue)) {
            System.out.println("usao");
            if (arrayList.contains(buttonCoordinate)) {
                System.out.println("sadrzi");
                if(kingColor.equals("white")){
                    if(buttonUserData.startsWith("white")){
                        System.out.println("bela figura");
                        return 2; //ovde treba da stane ukoliko kraljev put naidje na figru koja je njegoba boja
                    }
                    if(buttonCoordinate.equals(updateCoordinate)){
                        System.out.println("update coordinate");
                        return 2; //isto vazi i ovde u slucaju da igramo i koordianata kojju igramo preprecavan put
                    }
                    if(buttonUserData.equals("black_rook") || buttonUserData.equals("black_queen")){
                        System.out.println("heyy");
                        return 1;
                    }
                }else if(kingColor.equals("black")){
                    if(buttonUserData.startsWith("black")){
                        System.out.println("crna figura");
                        return 2;
                    }
                    if(buttonCoordinate.equals(updateCoordinate)){
                        System.out.println("update coordinate");
                        return 2;
                    }
                    if(buttonUserData.equals("white_rook") || buttonUserData.equals("white_queen")){
                        System.out.println("heyy");
                        return 1;
                    }
                }
                System.out.println("poslednji1");
                return 3;
            }
        } else if (Arrays.asList(diagonal).contains(arrayListValue)) {
            if (arrayList.contains(buttonCoordinate)) {
                if(kingColor.equals("white")){
                    if(buttonUserData.startsWith("white")){
                        System.out.println("bela figura");
                        return 2;
                    }
                    if(buttonCoordinate.equals(updateCoordinate)){
                        System.out.println("update coordinate");
                        return 2;
                    }
                    if ((buttonUserData.equals("black_bishop") || buttonUserData.equals("black_queen"))) {
                        System.out.println("heyy");
                        return 1;
                    }
                }else if(kingColor.equals("black")){
                    if(buttonUserData.startsWith("black")){
                        System.out.println("crna figura");
                        return 2;
                    }
                    if(buttonCoordinate.equals(updateCoordinate)){
                        System.out.println("update coordinate");
                        return 2;
                    }
                    if ((buttonUserData.equals("white_bishop") || buttonUserData.equals("white_queen"))) {
                        System.out.println("heyy");
                        return 1;
                    }
                }
                System.out.println("poslednji2");
                return 3;
            }
        }
        System.out.println("poslednji3");
        return 3;
    }

    @Override
    public List<ArrayList<String>> getAllCoordinates() {
        List<ArrayList<String>> coordinatesArrayList = new ArrayList<>();
        coordinatesArrayList.add(kingMoves);
        return coordinatesArrayList;
    }
}
