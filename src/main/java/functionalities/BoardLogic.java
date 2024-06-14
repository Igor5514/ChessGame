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

public class BoardLogic implements ChessPieceImages{

    private GridPane chessBoard;
    private Set<String> enabledCoordinatesList = new HashSet<>();
    private boolean isOpoonentPawn = false;

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
                isOpoonentPawn = false;
            } else {
                for (int i = children.size() - 1; i >= 0; i--) {
                    Button button = (Button) children.get(i);
                    if (checkForPiecePosition(piece, coordinateArrayList, button)) {
                        break;
                    }
                }
                isOpoonentPawn = false;
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
        }else if(!handlePawnMovesAndAttacks(coordinateArrayList,buttonCoordinate,userData, piece, button)) {
            return false;
        } else if (userData == null && coordinateArrayList.contains(buttonCoordinate)) {
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
        if(isOpoonentPawn && button.getText().equals(whiteCoordinate)) {
            return false;
        }else if(isOpoonentPawn && button.getText().equals(blackCoordinate)) {
            return false;
        }
        if (piece.getChessPieceType().equals("pawn") && userData != null && coordinateArrayList.contains(buttonCoordinate)){
            if (pieceColor.equals("white")) {
                String coordinate1 = (x - 1) + "" + y;
                String coordinate2 = (x - 2) + "" + y;

                if (button.getText().equals(coordinate1) || button.getText().equals(coordinate2)) {
                    isOpoonentPawn = true;
                    return false;
                } else {
                    enabledCoordinatesList.add(coordinate1);
                    coordinateArrayList.add(coordinate2);
                    paintSquare(button, piece, coordinateArrayList);
                    return false;
                }
            } else if (pieceColor.equals("black")) {
                String coordinate1 = (x + 1) + "" + y;
                String coordinate2 = (x + 2) + "" + y;

                if (button.getText().equals(coordinate1) || button.getText().equals(coordinate2)) {
                    isOpoonentPawn = true;
                    return false;
                }else{
                    enabledCoordinatesList.add(coordinate1);
                    coordinateArrayList.add(coordinate2);
                    paintSquare(button, piece, coordinateArrayList);
                    return false;
                }
            }
        } else if (piece.getChessPieceType().equals("pawn")) {
            if (pieceColor.equals("white")) {
                String opponentCoordinate1 = (x - 1) + "" + (y - 1);
                String opponentCoordinate2 = (x - 1) + "" + (y + 1);
                if (button.getUserData() != null && userData.startsWith("black")) {
                    if (button.getText().equals(opponentCoordinate1)) {
                        enabledCoordinatesList.add(opponentCoordinate1);
                        coordinateArrayList.add(opponentCoordinate1);
                        paintSquare(button, piece, coordinateArrayList);
                        return false;
                    } else if (button.getText().equals(opponentCoordinate2)) {
                        enabledCoordinatesList.add(opponentCoordinate2);
                        coordinateArrayList.add(opponentCoordinate2);
                        paintSquare(button, piece, coordinateArrayList);
                        return false;
                    }
                }
            } else if (pieceColor.equals("black")) {
                String opponentCoordinate1 = (x + 1) + "" + (y - 1);
                String opponentCoordinate2 = (x + 1) + "" + (y + 1);
                if (button.getUserData() != null && userData.startsWith("white")) {
                    if (button.getText().equals(opponentCoordinate1)) {
                        enabledCoordinatesList.add(opponentCoordinate1);
                        coordinateArrayList.add(opponentCoordinate1);
                        paintSquare(button, piece, coordinateArrayList);
                        return false;
                    } else if (button.getText().equals(opponentCoordinate2)) {
                        enabledCoordinatesList.add(opponentCoordinate2);
                        coordinateArrayList.add(opponentCoordinate2);
                        paintSquare(button, piece, coordinateArrayList);
                        return false;
                    }
                }
            }
        }
        return true;
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

    public void checkForChessStatePawn(Piece piece) {
        int x = Integer.parseInt(String.valueOf(piece.getCurrentCoordinate().charAt(0)));
        int y = Integer.parseInt(String.valueOf(piece.getCurrentCoordinate().charAt(1)));
        List<Node> children = chessBoard.getChildren();
        if (piece.getChessPieceColor().equals("white")){
            String opponentCoordinate1 = (x - 1) + "" + (y - 1);
            String opponentCoordinate2 = (x - 1) + "" + (y + 1);
            for (Node node : children) {
                Button button = (Button) node;
                if(button.getUserData() != null && button.getUserData().equals("black_king")){
                    if(button.getText().equals(opponentCoordinate1) || button.getText().equals(opponentCoordinate2)){
                        chessState(button);
                    }
                }
            }
        }else{
            String opponentCoordinate1 = (x + 1) + "" + (y - 1);
            String opponentCoordinate2 = (x + 1) + "" + (y + 1);
            for (int i = children.size() - 1; i >= 0; i--) {
                Button button = (Button) children.get(i);
                if(button.getUserData() != null && button.getUserData().equals("black_king")){
                    if(button.getText().equals(opponentCoordinate1) || button.getText().equals(opponentCoordinate2)){
                        chessState(button);
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
