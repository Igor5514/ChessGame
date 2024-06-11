package functionalities;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import pieces.Piece;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BoardLogic {

    private GridPane chessBoard;
    private Set<String> enabledCoordinatesList = new HashSet<>();

    public BoardLogic(GridPane chessBoard) {
        this.chessBoard = chessBoard;
    }

    public void updateChessBoardClick(Piece piece) {
        List<Node> children = chessBoard.getChildren();
        for (ArrayList<String> coordinateArrayList : piece.getAllCoordinates()) {
            if (isIncreasing(coordinateArrayList, piece.getChessPieceName())) {
                for (Node node : children) {
                    Button button = (Button) node;
                    if (checkForPiecePosition(piece, coordinateArrayList, button)) {
                        break;
                    }
                }
            } else {
                for (int i = children.size() - 1; i >= 0; i--) {
                    Button button = (Button) children.get(i);
                    if (checkForPiecePosition(piece, coordinateArrayList, button)) {
                        break;
                    }
                }
            }
        }
        enabledCoordinatesList.clear();
    }

    private boolean checkForPiecePosition(Piece piece, ArrayList<String> coordinateArrayList, Button button) {
        String buttonCoordinate = button.getText();
        String userData = (String) button.getUserData();
        String pieceColor = piece.getChessPieceColor();

        if (userData != null && buttonCoordinate.equals(piece.getCurrentCoordinate())) {
            enabledCoordinatesList.add(buttonCoordinate);
            paintSquare(button, piece, coordinateArrayList);
            return false;
        }else if(pawnMoves(coordinateArrayList,buttonCoordinate,userData, piece, button)){
            return true;
        }else if (userData == null && coordinateArrayList.contains(buttonCoordinate)) {
            enabledCoordinatesList.add(buttonCoordinate);
            paintSquare(button, piece, coordinateArrayList);
            return false;
        } else if (userData != null && !userData.toString().substring(0, 5).equals(pieceColor) && coordinateArrayList.contains(buttonCoordinate)) {
            enabledCoordinatesList.add(buttonCoordinate);
            paintSquare(button, piece, coordinateArrayList);
            return !piece.isJump();
        } else if (userData != null && userData.toString().substring(0, 5).equals(pieceColor) && coordinateArrayList.contains(buttonCoordinate)) {
            return !piece.isJump();
        }
        return false;
    }

    public void updateChessBoardMove(String clickedButtonCoordinate, Button destinationButton) {
        setOriginalColor();
        ImageView pieceImage = new ImageView();
        String chessPieceName = "";
        List<Node> children = chessBoard.getChildren();

        for (Node node : children) {
            if (node instanceof Button button && button.getText().equals(clickedButtonCoordinate)) {
                chessPieceName = (String) button.getUserData();
                button.setUserData(null);
                pieceImage = (ImageView) button.getGraphic();
                button.setGraphic(null);
                break;
            }
        }

        for (Node node : children) {
            if (node instanceof Button button && button.getText().equals(destinationButton.getText())) {
                button.setGraphic(pieceImage);
                button.setUserData(chessPieceName);
                break;
            }
        }
    }

    public void setOriginalColor() {
        for (Node node : chessBoard.getChildren()) {
            if (node instanceof Button button) {
                int i = button.getText().charAt(0);
                int j = button.getText().charAt(1);
                button.setDisable(false);
                if ((i + j) % 2 == 0) {
                    button.setStyle("-fx-background-color: #ffffcc; -fx-text-fill: transparent");
                } else {
                    button.setStyle("-fx-background-color: #86592d; -fx-text-fill: transparent");
                }
            }
        }
    }

    public boolean isIncreasing(ArrayList<String> coordinateArrayList, String pieceName) {
        int previousRow = -1, previousCol = -1;
        for (String coordinate : coordinateArrayList) {
            int row = Integer.parseInt(String.valueOf(coordinate.charAt(0)));
            int col = Integer.parseInt(String.valueOf(coordinate.charAt(1)));
            if(pieceName.equals("white_pawn")){
                return false;
            }else if (previousRow != -1 && (row > previousRow && col < previousCol)) {
                return true;
            } else if (previousRow != -1 && (row < previousRow && col < previousCol)) {
                return false;
            } else if (previousRow != -1 && (row < previousRow || col < previousCol)) {
                return false;
            }
            previousRow = row;
            previousCol = col;
        }
        return true;
    }

    public boolean pawnMoves(ArrayList<String> coordinateArrayList, String buttonCoordinate, String userData, Piece piece, Button button) {
        String pieceColor = piece.getChessPieceColor();
        String pieceCoordinate = piece.getCurrentCoordinate();
        int x = Integer.parseInt(String.valueOf(pieceCoordinate.charAt(0)));
        int y = Integer.parseInt(String.valueOf(pieceCoordinate.charAt(1)));
        if (piece.getChessPieceType().equals("pawn")) {
            if (pieceColor.equals("white")) {
                String opponentCoordinate1 = (x - 1) + "" + (y - 1);
                String opponentCoordinate2 = (x - 1) + "" + (y + 1);
                System.out.println(opponentCoordinate1 + " " + opponentCoordinate2);
                if (button.getUserData() != null && pieceColor.equals(button.getUserData().toString().startsWith("black"))) {
                    if (opponentCoordinate1.equals(button.getText()) || opponentCoordinate2.equals(button.getText())) {
                        enabledCoordinatesList.add(buttonCoordinate);
                        paintSquare(button, piece, coordinateArrayList);
                        return false;
                    }
                }
            } else if (pieceColor.equals("black")) {
                String opponentCoordinate1 = (x + 1) + "" + (y - 1);
                String opponentCoordinate2 = (x + 1) + "" + (y + 1);
                if (button.getUserData() != null && pieceColor.equals(button.getUserData().toString().startsWith("black"))) {
                    if (opponentCoordinate1.equals(button.getText()) || opponentCoordinate2.equals(button.getText())) {
                        enabledCoordinatesList.add(buttonCoordinate);
                        paintSquare(button, piece, coordinateArrayList);
                        return false;
                    }
                }
            } else if (coordinateArrayList.contains(buttonCoordinate) && userData != null) {
                if (pieceColor.equals("white")) {
                    System.out.println("c");
                    String coordinate1 = (x - 1) + "" + y;
                    String coordinate2 = (x - 2) + "" + y;
                    if (button.getText().equals(coordinate1) || button.getText().equals(coordinate2)) {
                        return true;
                    }
                } else if (pieceColor.equals("black")) {
                    System.out.println("d");
                    String coordinate1 = (x + 1) + "" + y;
                    String coordinate2 = (x + 2) + "" + y;
                    if (button.getText().equals(coordinate1) || button.getText().equals(coordinate2)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public void paintSquare(Button button, Piece piece, ArrayList<String> coordinateArrayList) {
        String buttonText = button.getText();
        Object userData = button.getUserData();

        if (userData != null && buttonText.equals(piece.getCurrentCoordinate())) {
            button.setStyle("-fx-background-color: #66ff1a;-fx-text-fill: transparent;");
        } else if (coordinateArrayList.contains(buttonText) && userData == null) {
            button.setStyle("-fx-background-color: #ffff4d;-fx-text-fill: transparent;");
        } else if (coordinateArrayList.contains(buttonText) && userData != null) {
            button.setStyle("-fx-background-color: #ff1a1a;-fx-text-fill: transparent;");
        }
        disableButtons();
    }

    public void disableButtons() {
        for (Node node : chessBoard.getChildren()) {
            Button button = (Button) node;
            button.setDisable(!enabledCoordinatesList.contains(button.getText()));
        }
    }

    public void checkForChessState(List<ArrayList<String>> coordinatesArrayList, Piece piece) {
        List<Node> children = chessBoard.getChildren();
        for (ArrayList<String> coordinateArrayList : coordinatesArrayList) {
            if (isIncreasing(coordinateArrayList, piece.getChessPieceName())) {
                for (Node node : children) {
                    Button button = (Button) node;
                    if (button.getUserData() != null && coordinateArrayList.contains(button.getText()) && !piece.getChessPieceColor().equals(button.getUserData().toString().substring(0, 5))) {
                        if (button.getUserData().toString().substring(6).equals("king")) {
                            chessState(button);
                        } else {
                            break;
                        }
                    }
                }
            } else {
                for (int i = children.size() - 1; i >= 0; i--) {
                    Button button = (Button) children.get(i);
                    if (button.getUserData() != null && coordinateArrayList.contains(button.getText()) && !piece.getChessPieceColor().equals(button.getUserData().toString().substring(0, 5))) {
                        if (button.getUserData().toString().substring(6).equals("king")) {
                            chessState(button);
                        } else {
                            break;
                        }
                    }
                }
            }
        }
    }

    public void chessState(Button button) {
        button.setStyle("-fx-background-color: #ff1a1a;-fx-text-fill: transparent;");
    }

    public boolean checkForKingMoves() {
        return false;
    }
}
