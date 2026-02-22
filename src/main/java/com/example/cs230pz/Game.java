package com.example.cs230pz;

import functionalities.BoardLogic;
import functionalities.Castling;
import functionalities.GameState;
import objects.Player;
import javafx.scene.Node;
import javafx.scene.control.Button;
import pieces.*;
import utils.Util;

import java.util.List;

public class Game implements Util {

    private ChessBoard board;
    private BoardLogic boardLogic;
    private Castling castling;
    private boolean isPieceClicked = false;
    private String clickedPieceCoordinate;
    private boolean pieceMoved = false;
    GameState gameState = GameState.getInstance();
    boolean turnPermission = gameState.isWhiteTurn();
    private boolean isInCheck = false;
    private final Player player1;
    private final Player player2;
    King currentKing = null;
    Piece piece = null;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void setBoard(ChessBoard board) {
        this.board = board;
        this.boardLogic = new BoardLogic(board.getChessBoard(), this);
        board.setStartingPosition();
        board.setButtonHandlers(this);
    }

    public void handleButtonClick(Button squareButton) {
        if (checkForPermission(squareButton) || squareButton.getUserData() == null || isPieceClicked) {
            if (!isPieceClicked) {
                if (squareButton.getUserData() != null) {
                    piece = handleClick(squareButton);
                    clickedPieceCoordinate = squareButton.getText();
                    boardLogic.updateChessBoardClick(piece);
                    isPieceClicked = true;
                }
            } else {
                if (clickedPieceCoordinate.equals(squareButton.getText())) {
                    boardLogic.setOriginalColor();
                    isPieceClicked = false;
                    currentKing = null;
                    piece = null;
                } else {
                    if(!(piece instanceof King)){
                        makeInstanceOfKing(piece, squareButton);

                        if(currentKing != null && currentKing.checkForOpponents(currentKing.getCurrentCoordinate(),currentKing.getChessPieceColor())) {
                            setToDefaultStateAndHighlight();
                        }else{
                            executeMove(squareButton);
                        }
                    }else {
                        List<Node> chessboardCopy = deepCopyArrayList(board.getChessBoard().getChildren());
                        boardLogic.updateChessBoardMove(null, clickedPieceCoordinate, squareButton, chessboardCopy);

                        King tempKing = new King(squareButton.getText(), piece.getChessPieceName(), true,chessboardCopy);

                        if(!tempKing.checkForOpponents(tempKing.getCurrentCoordinate(), tempKing.getChessPieceColor())){
                            executeMove(squareButton);
                        }
                    }

                }
            }
        }
    }

    public void executeMove(Button squareButton){
        boardLogic.updateChessBoardMove(piece, clickedPieceCoordinate, squareButton, board.getChessBoard().getChildren());
        Piece piece = handleClick(squareButton);
        pieceMoved = true;
        changeTurn();
        if (piece.getChessPieceType().equals("pawn")) {
            boardLogic.checkForChessStatePawn(piece);
        }else if(piece.getChessPieceType().equals("knight")){
            boardLogic.checkForChessStateKnight(piece.getAllCoordinates(), piece);
        }else {
            boardLogic.checkForChessState(piece.getAllCoordinates(), piece);
        }
        isPieceClicked = false;
        isInCheck = false;
        currentKing = null;
        boardLogic.setOpponentPawn(false);
    }

    public void makeInstanceOfKing(Piece piece, Button squareButton){
        if(!(piece instanceof King)){
            List<Node> chessboardCopy = deepCopyArrayList(board.getChessBoard().getChildren());
            boardLogic.updateChessBoardMove(null, clickedPieceCoordinate, squareButton, chessboardCopy);

            for(Node node : board.getChessBoard().getChildren()){
                if(node instanceof Button button && button.getUserData() != null){
                    if (piece.getChessPieceColor().equals("white") && button.getUserData().toString().equals("white_king")){
                        currentKing = new King(button.getText(),button.getUserData().toString(),true, chessboardCopy);
                        isPieceClicked = true;
                        break;
                    } else if (piece.getChessPieceColor().equals("black")  && button.getUserData().toString().equals("black_king")) {
                        currentKing = new King(button.getText(),button.getUserData().toString(),true, chessboardCopy);
                        isPieceClicked = true;
                        break;
                    }
                }
            }
        }
    }

    public boolean checkForPermission(Button button) {
        if (turnPermission) {
            return checkForPlayingPermission(button, "white");
        } else {
            return checkForPlayingPermission(button, "black");
        }
    }

    public void setToDefaultStateAndHighlight(){
        boardLogic.setOriginalColor();
        boardLogic.highlightCheckedKing(piece, board.getChessBoard().getChildren());
        isPieceClicked = false;
    }

    public void executeCastling(){
        
    }

    public void executedMovePiece(Piece piece){
        switch (piece.getChessPieceName()){
            case "white_king":
                castling.setWhiteKingMoved(true);
                break;
            case "black_king":
                castling.setBlackKingMoved(true);
                break;
            case "white_rook":
                castling.setWhiteRookMoved(true);
                break;
            case "black_rook":
                castling.setBlackRookMoved(true);
                break;
        }
    }

    public boolean checkForPlayingPermission(Button button, String pieceType){
        if (button.getUserData() != null && button.getUserData().toString().startsWith(pieceType)) {
            if(pieceMoved){
                pieceMoved = false;
            }
            return true;
        } else {
            return false;
        }
    }

    public Piece handleClick(Button button){
        String coordinates = button.getText();
        int i = Integer.parseInt(String.valueOf(coordinates.charAt(0)));
        int j = Integer.parseInt(String.valueOf(coordinates.charAt(1)));
        String pieceName = (String) button.getUserData();
        switch (pieceName){
            case "white_rook":
            case "black_rook":
                return new Rook(i+""+j, pieceName, false);
            case "white_knight":
            case "black_knight":
                return new Knight(i+""+j, pieceName, true);
            case "white_bishop":
            case "black_bishop":
                return new Bishop(i+""+j, pieceName, false);
            case "white_king":
            case "black_king":
                return new King(i + "" + j, pieceName, true, null);
            case "white_queen":
            case "black_queen":
                return new Queen(i+""+j, pieceName, false);
            case "white_pawn":
                return new WhitePawn(i+""+j, pieceName, false);
            case "black_pawn":
                return new BlackPawn(i+""+j, pieceName, false);
            default:
                return null;
        }
    }

    public void changeTurn(){
        turnPermission = !turnPermission;
    }

    public boolean isTurnPermission() {
        return GameState.getInstance().isWhiteTurn();
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }
}
