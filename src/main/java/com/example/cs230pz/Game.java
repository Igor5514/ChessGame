package com.example.cs230pz;

import functionalities.BoardLogic;
import functionalities.Handlers;
import javafx.scene.control.Button;
import pieces.Piece;

public class Game {

    private ChessBoard board;
    private BoardLogic boardLogic;
    private boolean isPieceClicked = false;
    private String clickedPiece;

    public void setBoard(ChessBoard board) {
        this.board = board;
        this.boardLogic = new BoardLogic(board.getChessBoard());
        board.setStartingPosition();
        board.setButtonHandlers(this);
    }

    public void handleButtonClick(Button squareButton) {
        if(!isPieceClicked) {
            if (squareButton.getUserData() != null) {
                Handlers handlers = new Handlers();
                Piece piece = handlers.handleClick(squareButton);
                clickedPiece = squareButton.getText();
                boardLogic.updateChessBoardClick(piece);
                isPieceClicked = true;
            }
        } else {
            if(isPieceClicked && clickedPiece.equals(squareButton.getText())) {
                boardLogic.setOriginalColor();
            } else {
                boardLogic.updateChessBoardMove(clickedPiece, squareButton);
            }
            isPieceClicked = false;
        }
    }
}

