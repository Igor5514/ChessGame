package com.example.cs230pz;

import functionalities.BoardLogic;
import functionalities.Handlers;
import javafx.scene.control.Button;

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

//    public void handleButtonClick(Button squareButton) {
//        if(!isPieceClicked) {
//            Handlers handlers = new Handlers();
//            Move move = handlers.handleClick(squareButton);
//            clickedPiece = squareButton.getText();
//            boardLogic.updateChessBoardClick(move);
//            isPieceClicked = true;
//        } else {
//            if(isPieceClicked && clickedPiece.equals(squareButton.getText())) {
//                boardLogic.setOriginalColor();
//            } else {
//                boardLogic.updateChessBoardMove(clickedPiece, squareButton);
//            }
//            isPieceClicked = false;
//        }
//    }
}

