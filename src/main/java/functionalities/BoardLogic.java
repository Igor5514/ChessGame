package functionalities;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import pieces.Piece;

import java.util.ArrayList;

public class BoardLogic {

    private GridPane chessBoard;
    private ArrayList<String> enabledCoordinatesList = new ArrayList<>();

    public BoardLogic(GridPane chessBoard) {
        this.chessBoard = chessBoard;
    }

    public void updateChessBoardClick(Piece piece) {
        for (ArrayList<String> coordinateArrayList : piece.getAllCoordinates()) {
            if(getDirectionOfArrayList(coordinateArrayList)){
                for (Node node : chessBoard.getChildren()) {
                    Button button = (Button) node;
                    if (checkForPiecePostion(piece,coordinateArrayList,button)){
                        break;
                    }
                }
            }else{
                for (int i = chessBoard.getChildren().size() - 1; i >= 0; i--) {
                    Button button = (Button) chessBoard.getChildren().get(i);
                    if (checkForPiecePostion(piece,coordinateArrayList,button)){
                        break;
                    }
                }
            }
        }
        enabledCoordinatesList.clear();
    }

    private boolean checkForPiecePostion(Piece piece, ArrayList<String> coordinateArrayList, Button button) {
        if (button.getUserData() == null && coordinateArrayList.contains(button.getText())){
            enabledCoordinatesList.add(button.getText());
            paintSquare(button,piece,coordinateArrayList);
            return false;
        }else if(button.getUserData() != null && button.getText().equals(piece.getCurrentCoordinate())){
            enabledCoordinatesList.add(button.getText());
            paintSquare(button,piece,coordinateArrayList);
            return false;
        }else if (button.getUserData() != null && !button.getUserData().toString().substring(0,5).equals(piece.getChessPieceColor()) && coordinateArrayList.contains(button.getText())){
            enabledCoordinatesList.add(button.getText());
            paintSquare(button,piece,coordinateArrayList);
            return true;
        }else if (button.getUserData() != null && button.getUserData().toString().substring(0,5).equals(piece.getChessPieceColor()) && coordinateArrayList.contains(button.getText())){
            return true;
        }
        return false;
    }

    public void updateChessBoardMove(String clickedButtonCoordinate, Button destinationButton) {
        setOriginalColor();
        ImageView pieceImage = new ImageView();
        String chessPieceName = "";
        for (Node node : chessBoard.getChildren()) {
            if (node instanceof Button button) {
                if (button.getText().equals(clickedButtonCoordinate)) {
                    chessPieceName = (String) button.getUserData();
                    button.setUserData(null);
                    pieceImage = (ImageView) button.getGraphic();
                    button.setGraphic(null);
                }
            }
        }
        for (Node node : chessBoard.getChildren()) {
            if (node instanceof Button button) {
                if (button.getText().equals(destinationButton.getText())) {
                    button.setGraphic(pieceImage);
                    button.setUserData(chessPieceName);
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

    public boolean getDirectionOfArrayList(ArrayList<String> coordinateArrayList){
        boolean increasing1 = false;
        boolean increasing2 = false;
        for (int i = 0; i < coordinateArrayList.size() - 1; i++) {
            int leftCoordinate = Integer.parseInt(String.valueOf(coordinateArrayList.get(i).charAt(0)));
            int nextLeftCoordinate = Integer.parseInt(String.valueOf(coordinateArrayList.get(i + 1).charAt(0)));
            int rightCoordinate = Integer.parseInt(String.valueOf(coordinateArrayList.get(i).charAt(1)));
            int nextRightCoordinate = Integer.parseInt(String.valueOf(coordinateArrayList.get(i + 1).charAt(1)));
            if (leftCoordinate < nextLeftCoordinate || leftCoordinate == nextLeftCoordinate) {
                increasing1 = true;
            }
            if (rightCoordinate < nextRightCoordinate || rightCoordinate == nextRightCoordinate) {
                increasing2 = true;
            }
        }
        return increasing1 && increasing2;
    }

    public void paintSquare(Button button, Piece piece, ArrayList<String> coordinateArrayList){
         if (button.getUserData() != null && button.getText().equals(piece.getCurrentCoordinate())) {
            button.setStyle("-fx-background-color: #66ff1a;-fx-text-fill: transparent;");
        }else if (coordinateArrayList.contains(button.getText()) && button.getUserData() == null) {
            button.setStyle("-fx-background-color: #ffff4d;-fx-text-fill: transparent;");
        } else if(coordinateArrayList.contains(button.getText()) && button.getUserData() != null){
            button.setStyle("-fx-background-color: #ff1a1a;-fx-text-fill: transparent;");
        }
         disableButtons();
    }

    public void disableButtons(){
        for (Node node : chessBoard.getChildren()) {
            Button button = (Button) node;
            if(!enabledCoordinatesList.contains(button.getText())){
                button.setDisable(true);
            }else{
                button.setDisable(false);
            }
        }
    }

}

