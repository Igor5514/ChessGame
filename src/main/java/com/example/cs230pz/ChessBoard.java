package com.example.cs230pz;

import functionalities.ChessPieceImages;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class ChessBoard extends Pane implements ChessPieceImages {

    private GridPane chessBoard;

    public ChessBoard(){
        this.chessBoard = makeChessTable();
    }

    public GridPane makeChessTable(){
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(640, 640);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setStyle("-fx-background-color: #4d3319");

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                Button squareButton = new Button();
                squareButton.setFocusTraversable(false);
                squareButton.setPrefSize(75, 75);
                if((i + j) % 2 == 0){
                    squareButton.setStyle("-fx-background-color: #ffffcc; -fx-text-fill: transparent");
                }else{
                    squareButton.setStyle("-fx-background-color: #86592d; -fx-text-fill: transparent");
                }
                squareButton.setText((i + 1) + "" + (j + 1));
                gridPane.add(squareButton, j, i);
            }
        }
        getChildren().add(gridPane);
        return gridPane;
    }

    public void setButtonHandlers(Game game) {
        for (Node node : chessBoard.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                (button).setOnAction(event -> {
                    game.handleButtonClick(button);
                });
            }
        }
    }

    public void setStartingPosition() {
        for (Node node : getChildren()) {
            for (int i = 0; i < 4; i++) {
                GridPane gridPane = (GridPane) node;
                for (Node node2 : gridPane.getChildren()) {
                    Button button = (Button) node2;
                    String buttonCoordinates = button.getText();
                    if (buttonCoordinates.equals("11") || buttonCoordinates.equals("18")) {
                        button.setGraphic(getBlackRook());
                        button.setUserData("black_rook");
                    } else if (buttonCoordinates.charAt(0) == '2') {
                        button.setGraphic(getBlackPawn());
                        button.setUserData("black_pawn");
                    } else if (buttonCoordinates.charAt(0) == '7') {
                        button.setGraphic(getWhitePawn());
                        button.setUserData("white_pawn");
                    } else if (buttonCoordinates.equals("81") || buttonCoordinates.equals("88")) {
                        button.setGraphic(getWhiteRook());
                        button.setUserData("white_rook");
                    } else if (buttonCoordinates.equals("12") || buttonCoordinates.equals("17")){
                        button.setGraphic(getBlackKnight());
                        button.setUserData("black_knight");
                    }else if (buttonCoordinates.equals("82") || buttonCoordinates.equals("87")){
                        button.setGraphic(getWhiteKnight());
                        button.setUserData("white_knight");
                    }else if (buttonCoordinates.equals("13") || buttonCoordinates.equals("16")){
                        button.setGraphic(getBlackBishop());
                        button.setUserData("black_bishop");
                    }else if (buttonCoordinates.equals("83") || buttonCoordinates.equals("86")){
                        button.setGraphic(getWhiteBishop());
                        button.setUserData("white_bishop");
                    }else if (buttonCoordinates.equals("14")){
                        button.setGraphic(getBlackQueen());
                        button.setUserData("black_queen");
                    }else if (buttonCoordinates.equals("84")){
                        button.setGraphic(getWhiteQueen());
                        button.setUserData("white_queen");
                    }else if (buttonCoordinates.equals("15")){
                        button.setGraphic(getBlackKing());
                        button.setUserData("black_king");
                    }else if (buttonCoordinates.equals("85")){
                        button.setGraphic(getWhiteKing());
                        button.setUserData("white_king");
                    }
                }
            }
        }
    }

    public GridPane getChessBoard() {
        return chessBoard;
    }

    public void setChessBoard(GridPane chessBoard) {
        this.chessBoard = chessBoard;
    }
}

