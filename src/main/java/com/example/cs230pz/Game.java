package com.example.cs230pz;

import functionalities.BoardLogic;
import functionalities.GameState;
import functionalities.Handlers;
import functionalities.Player;
import javafx.scene.control.Button;
import pieces.Piece;

public class Game {

    private ChessBoard board;
    private BoardLogic boardLogic;
    private boolean isPieceClicked = false;
    private String clickedPiece;
    private boolean pieceMoved = false;
    GameState gameState = GameState.getInstance();
    boolean isWhiteTurn = gameState.isWhiteTurn();
    private Player player1;
    private Player player2;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void setBoard(ChessBoard board) {
        this.board = board;
        this.boardLogic = new BoardLogic(board.getChessBoard());
        board.setStartingPosition();
        board.setButtonHandlers(this);
    }

    public void handleButtonClick(Button squareButton) {
        if (whiteTurn(squareButton) || squareButton.getUserData() == null || isPieceClicked) {
            if (!isPieceClicked) {
                if (squareButton.getUserData() != null) {
                    Handlers handlers = new Handlers();
                    Piece piece = handlers.handleClick(squareButton);
                    clickedPiece = squareButton.getText();
                    boardLogic.updateChessBoardClick(piece);
                    isPieceClicked = true;
                }
            } else {
                if (isPieceClicked && clickedPiece.equals(squareButton.getText())) {
                    boardLogic.setOriginalColor();
                } else {
                    boardLogic.updateChessBoardMove(clickedPiece, squareButton);
                    pieceMoved = true;
                    changeTurn();
                    Handlers handlers = new Handlers();
                    Piece piece = handlers.handleClick(squareButton);
                    boardLogic.checkForChessState(piece.getAllCoordinates(), piece);
                }
                isPieceClicked = false;
            }
        }
    }

    public boolean whiteTurn(Button button) {
        if (isWhiteTurn) {
            if (button.getUserData() != null && button.getUserData().toString().startsWith("white")) {
                if(pieceMoved){
                    pieceMoved = false;
                    return true;
                }else{
                    return true;
                }
            } else {
                return false;
            }
        } else {
            if (button.getUserData() != null && button.getUserData().toString().startsWith("black")) {
                if(pieceMoved){
                    pieceMoved = false;
                    return true;
                }else{
                    return true;
                }
            } else {
                return false;
            }
        }
    }

    public void changeTurn(){
        if(isWhiteTurn){
            isWhiteTurn = false;
        }else{
            isWhiteTurn = true;
        }
    }

    public boolean isWhiteTurn() {
        return GameState.getInstance().isWhiteTurn();
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }
}
