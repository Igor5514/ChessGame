package com.example.cs230pz;

import functionalities.BoardLogic;
import functionalities.GameState;
import functionalities.Handlers;
import functionalities.Player;
import javafx.scene.Node;
import javafx.scene.control.Button;
import pieces.King;
import pieces.Piece;

public class Game {

    private ChessBoard board;
    private BoardLogic boardLogic;
    private boolean isPieceClicked = false;
    private String clickedPieceCoordinate;
    private String clickedPieceName;
    private boolean pieceMoved = false;
    GameState gameState = GameState.getInstance();
    boolean isWhiteTurn = gameState.isWhiteTurn();
    private boolean isInCheck = false;
    private final Player player1;
    private final Player player2;
    King currentKing = null;

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
                    clickedPieceCoordinate = squareButton.getText();
                    clickedPieceName = squareButton.getUserData().toString();
                    boardLogic.updateChessBoardClick(piece);
                    if(!(piece instanceof King)){
                        for(Node node : board.getChessBoard().getChildren()){
                            if(node instanceof Button button){
                                if (piece.getChessPieceColor().equals("white") && button.getUserData() != null && button.getUserData().toString().equals("white_king")){
                                    currentKing = new King(button.getText(),button.getUserData().toString(),true);
                                    isPieceClicked = true;
                                } else if (piece.getChessPieceColor().equals("black") && button.getUserData() != null && button.getUserData().toString().equals("black_king")) {
                                    currentKing = new King(button.getText(),button.getUserData().toString(),true);
                                    isPieceClicked = true;
                                }
                            }
                        }
                    }
                    isPieceClicked = true;
                }
            } else {
                if (clickedPieceCoordinate.equals(squareButton.getText())) {
                    boardLogic.setOriginalColor();
                    isPieceClicked = false;
                    currentKing = null;
                } else {
                    if(isInCheck){
                        if(currentKing != null && !currentKing.checkForOpponents(board.getChessBoard(), currentKing.getCurrentCoordinate(),clickedPieceCoordinate, squareButton.getText(), clickedPieceName.substring(0, 5))){
                            executeMove(squareButton);
                            isInCheck = false;
                            isPieceClicked = false;
                            currentKing = null;
                        }
                    }else {
                        if (currentKing != null && currentKing.checkForOpponents(board.getChessBoard(), currentKing.getCurrentCoordinate(),clickedPieceCoordinate, squareButton.getText(), clickedPieceName.substring(0, 5))) {
                            isInCheck = true;
                        } else {
                            executeMove(squareButton);
                            currentKing = null;
                        }
                    }
                }

            }
        }

    }

    public void executeMove(Button squareButton){
        boardLogic.updateChessBoardMove(clickedPieceCoordinate, squareButton);
        Handlers handlers = new Handlers();
        Piece piece = handlers.handleClick(squareButton);
        pieceMoved = true;
        changeTurn();
        if (!piece.getChessPieceType().equals("pawn")) {
            boardLogic.checkForChessState(piece.getAllCoordinates(), piece);
        } else {
            boardLogic.checkForChessStatePawn(piece);
        }
        isPieceClicked = false;
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
        isWhiteTurn = !isWhiteTurn;
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
