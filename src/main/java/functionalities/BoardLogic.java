package functionalities;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import pieces.Piece;

public class BoardLogic {

    private GridPane chessBoard;

    public BoardLogic(GridPane chessBoard) {
        this.chessBoard = chessBoard;
    }

    public void updateChessBoardClick(Piece piece) {
        for (Node node : chessBoard.getChildren()) {
            if (node instanceof Button button) {
                    button.setStyle("-fx-background-color: #ff1a1a;-fx-text-fill: transparent;");
                    button.setStyle("-fx-background-color: #ffff4d;-fx-text-fill: transparent;");
                    button.setStyle("-fx-background-color: #66ff1a;-fx-text-fill: transparent;");
                    button.setDisable(true);

            }
        }
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
}

