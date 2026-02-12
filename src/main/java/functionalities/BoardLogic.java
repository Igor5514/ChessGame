package functionalities;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import pieces.Piece;
import utils.ChessPieceImages;
import utils.Util;

import java.util.*;

public class BoardLogic implements ChessPieceImages, Util {

    private GridPane chessBoard;
    private final Set<String> enabledCoordinatesList = new HashSet<>();
    private boolean isOpponentPawn = false;


    public BoardLogic(){

    }

    public BoardLogic(GridPane chessBoard) {
        this.chessBoard = chessBoard;
    }

    public void updateChessBoardClick(Piece piece) {
        List<Node> children = chessBoard.getChildren();
        int size = children.size() - 1;
        for (ArrayList<String> coordinateArrayList : piece.getAllCoordinates()) {
            boolean asc = isIncreasing(coordinateArrayList, piece.getChessPieceName());
            int cordListSize = coordinateArrayList.size();

            for (int i = (asc ? 0 : size), k = 0;
                 (asc ? i <= size : i >= 0) && k < cordListSize; i = (asc ? i+1 : i-1)) {

                Button button = (Button) children.get(i);
                String buttonCoordinate = button.getText();
                if(buttonCoordinate.equals(coordinateArrayList.get(k))){
                    if (checkForPiecePosition(piece, coordinateArrayList , button, true)) {
                        break;
                    }
                    k++;
                }else if(checkForPiecePosition(piece, coordinateArrayList , button, false)){
                    break;
                }

                if(k == cordListSize) {
                    break;
                }
            }
        }
        enabledCoordinatesList.clear();
    }

    private boolean checkForPiecePosition(Piece piece, ArrayList<String> coordinateArrayList, Button button, boolean coordinatesMatch) {
        String buttonCoordinate = button.getText();
        String userData = (String) button.getUserData();
        String pieceColor = piece.getChessPieceColor();

        if (userData != null && buttonCoordinate.equals(piece.getCurrentCoordinate())) {
            enabledCoordinatesList.add(buttonCoordinate);
            paintSquare(button, piece);
            return false;
        }else if(!handlePawnMovesAndAttacks(coordinateArrayList,buttonCoordinate,userData, piece, button)) {
            isOpponentPawn = false;
            return false;
        } else if (userData == null && coordinateArrayList.contains(buttonCoordinate)) {
            enabledCoordinatesList.add(buttonCoordinate);
            paintSquare(button, piece);
            return false;
        } else if (userData != null && !userData.substring(0, 5).equals(pieceColor) && coordinatesMatch) {
            enabledCoordinatesList.add(buttonCoordinate);
            paintSquare(button, piece);
            return !piece.isJump();
        } else if (userData != null && userData.substring(0, 5).equals(pieceColor) && coordinatesMatch) {
            return !piece.isJump();
        }
        return false;
    }

    public void updateChessBoardMove(String clickedButtonCoordinate, Button destinationButton, List<Node> chessBoard) {
        setOriginalColor();
        ImageView pieceImage = new ImageView();
        String chessPieceName = "";

        for (Node node : chessBoard) {
            if (node instanceof Button button && button.getText().equals(clickedButtonCoordinate)) {
                chessPieceName = (String) button.getUserData();
                button.setUserData(null);
                pieceImage = (ImageView) button.getGraphic();
                button.setGraphic(null);
                break;
            }
        }

        for (Node node : chessBoard) {
            if (node instanceof Button button && button.getText().equals(destinationButton.getText())) {
                if(checkForPromotion(chessPieceName, destinationButton) != null){
                    button.setUserData(checkForPromotion(chessPieceName, destinationButton));
                    if(chessPieceName.startsWith("white")){
                        button.setGraphic(getWhiteQueen());
                    }else if(chessPieceName.startsWith("black")){
                        button.setGraphic(getBlackQueen());
                    }
                    break;
                }else {
                    button.setGraphic(pieceImage);
                    button.setUserData(chessPieceName);
                    break;
                }
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

    private boolean handlePawnMovesAndAttacks(ArrayList<String> coordinateArrayList, String buttonCoordinate, String userData, Piece piece, Button button) {
        String pieceColor = piece.getChessPieceColor();
        String pieceCoordinate = piece.getCurrentCoordinate();
        int x = Integer.parseInt(String.valueOf(pieceCoordinate.charAt(0)));
        int y = Integer.parseInt(String.valueOf(pieceCoordinate.charAt(1)));
        String blackCoordinate = (x + 2) + "" + y;
        String whiteCoordinate = (x - 2) + "" + y;
        if(isOpponentPawn && button.getText().equals(whiteCoordinate)) {
            return false;
        }else if(isOpponentPawn && button.getText().equals(blackCoordinate)) {
            return false;
        }
        boolean isWhite = pieceColor.equals("white");
        if (piece.getChessPieceType().equals("pawn") && userData != null && coordinateArrayList.contains(buttonCoordinate)){
            String coordinate1 = (isWhite ? (x - 1) : (x + 1))  + "" + y;
            String coordinate2 = (isWhite ? (x - 2) : (x + 2)) + "" + y;
            if (button.getText().equals(coordinate1) || button.getText().equals(coordinate2)) {
                isOpponentPawn = true;
                return false;
            } else {
                enabledCoordinatesList.add(coordinate1);
                coordinateArrayList.add(coordinate2);
                paintSquare(button, piece);
                return false;
            }
        } else if (piece.getChessPieceType().equals("pawn")) {
            String opponentCoordinate1 = (isWhite ? (x - 1) : (x + 1)) + "" + (y - 1);
            String opponentCoordinate2 = (isWhite ? (x - 1) : (x + 1)) + "" + (y + 1);
            if (button.getUserData() != null && userData.startsWith(isWhite ? "black" : "white")) {
                String selectedCoordinate = null;
                if (button.getText().equals(opponentCoordinate1)) {
                    selectedCoordinate = opponentCoordinate1;
                } else if (button.getText().equals(opponentCoordinate2)) {
                    selectedCoordinate = opponentCoordinate2;
                }
                if (selectedCoordinate != null) {
                    enabledCoordinatesList.add(selectedCoordinate);
                    coordinateArrayList.add(selectedCoordinate);
                    paintSquare(button, piece);
                    return false;
                }
            }
        }
        return true;
    }


    public void paintSquare(Button button, Piece piece) {
        String buttonText = button.getText();
        Object userData = button.getUserData();

        if (userData != null && buttonText.equals(piece.getCurrentCoordinate())) {
            button.setStyle("-fx-background-color: #66ff1a;-fx-text-fill: transparent;");
        } else if (userData == null) {
            button.setStyle("-fx-background-color: #ffff4d;-fx-text-fill: transparent;");
        } else {
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
            boolean asc = isIncreasing(coordinateArrayList, piece.getChessPieceName());
            int size = children.size() - 1;
            int cordListSize = coordinateArrayList.size();

            for (int i = (asc ? 0 : size), k = 0;
                 (asc ? i <= size : i >= 0) && k < cordListSize; i = (asc ? i + 1 : i - 1)) {
                Button button = (Button) children.get(i);
                Object data = button.getUserData();

                if (button.getText().equals(coordinateArrayList.get(k))) {
                    if (data != null && !data.toString().startsWith(piece.getChessPieceColor())) {
                        if (button.getUserData().toString().endsWith("king")) {
                            chessState(button);
                            return;
                        }
                    }

                    k++;
                    if (k == cordListSize) {
                        break;
                    }
                }
            }
        }
    }

    public void checkForChessStatePawn(Piece piece) {
        int x = Integer.parseInt(String.valueOf(piece.getCurrentCoordinate().charAt(0)));
        int y = Integer.parseInt(String.valueOf(piece.getCurrentCoordinate().charAt(1)));
        List<Node> children = chessBoard.getChildren();
        boolean isWhite = piece.getChessPieceColor().equals("white");
        String opponentCoordinate1 = (isWhite ? (x - 1) : (x + 1)) + "" + (y - 1);
        String opponentCoordinate2 = (isWhite ? (x - 1) : (x + 1)) + "" + (y + 1);
        int size = children.size() - 1;
        for (int i = (isWhite ? size : 0); (isWhite ? i >= 0 : i<=size); i = (isWhite ? i-1 : i+1)) {
            Button button = (Button) children.get(i);
            if(button.getUserData() != null && button.getUserData().equals(isWhite ? "black_king" : "white_king")){
                if(button.getText().equals(opponentCoordinate1) || button.getText().equals(opponentCoordinate2)){
                    chessState(button);
                }
            }
        }
    }

    public void highlightCheckedKing(Piece piece, List<Node> board){
        for (Node node : board) {
            Button button = (Button) node;
            String matchingWord = piece.getChessPieceColor() + "_" + "king";
            if (button.getUserData() != null && button.getUserData().equals(matchingWord)) {
                chessState(button);
            }
        }
    }

    public String checkForPromotion(String clickedPieceName, Button button){
        if(clickedPieceName.equals("white_pawn")){
            int x = Integer.parseInt(String.valueOf(button.getText().charAt(0)));
            if(x == 1){
                return "white_queen";
            }
        }else if(clickedPieceName.equals("black_pawn")){
            int x = Integer.parseInt(String.valueOf(button.getText().charAt(0)));
            if(x == 8){
                return "black_queen";
            }
        }
        return null;
    }

    public void sortListOfStringCoordinates(){


    }



    public void chessState(Button button) {
        button.setStyle("-fx-background-color: #ff1a1a;-fx-text-fill: transparent;");
    }

    public boolean checkForKingMoves() {
        return false;
    }

    public boolean isOpponentPawn() {
        return isOpponentPawn;
    }

    public void setOpponentPawn(boolean opponentPawn) {
        isOpponentPawn = opponentPawn;
    }
}
