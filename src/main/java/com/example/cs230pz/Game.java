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
    private List<Node> chessBoardNodes;
    private BoardLogic boardLogic;
    private boolean isPieceClicked = false;
    private String clickedPieceCoordinate;
    private boolean pieceMoved = false;
    GameState gameState = GameState.getInstance();
    boolean turnPermission = gameState.isWhiteTurn();
    private Boolean isInCheck = false;
    private Castling castling;
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
        chessBoardNodes = board.getChessBoard().getChildren();
        castling = new Castling(this, chessBoardNodes,false, false, false, false);
        board.setStartingPosition();
        board.setButtonHandlers(this);
    }

    public void handleButtonClick(Button squareButton) {
        if (checkForPermission(squareButton) || squareButton.getUserData() == null || isPieceClicked) {
            if (!isPieceClicked && squareButton.getUserData() != null) {
                piece = handleClick(squareButton);
                clickedPieceCoordinate = squareButton.getText();
                boardLogic.updateChessBoardClick(piece);
                isPieceClicked = true;
            } else if (clickedPieceCoordinate.equals(squareButton.getText())) {
                boardLogic.setOriginalColor();
                isPieceClicked = false;
                currentKing = null;
                piece = null;
            } else {
                validatePermissionForMoving(squareButton);
            }
        }
    }

    public void validatePermissionForMoving(Button destinationButton){
        String destinationCoordinate = destinationButton.getText();

        if(!(piece instanceof King)){
            makeInstanceOfKing(destinationButton);

            if(currentKing != null && currentKing.checkForOpponents(currentKing.getCurrentCoordinate(),currentKing.getChessPieceColor())) {
                setToDefaultStateAndHighlight();
            }else{
                if(piece instanceof Rook && castling.validateCastling(currentKing, destinationCoordinate)){
                    executeCastling(destinationButton);
                }else{
                    executeMove(destinationButton);
                }
            }
        } else {
            List<Node> chessboardCopy = deepCopyArrayList(chessBoardNodes);
            boardLogic.updateChessBoardMove(null, clickedPieceCoordinate, destinationButton, chessboardCopy);


            if(destinationButton.getUserData() != null){
                King tempKing = new King(destinationButton.getText(), piece.getChessPieceName(), true, chessBoardNodes);
                String enemyColor = tempKing.getChessPieceColor().equals("white") ? "black" : "white";
                if(tempKing.checkForOpponents(destinationCoordinate, enemyColor)){
                    executeMove(destinationButton);
                }
            }else {
                King tempKing = new King(destinationButton.getText(), piece.getChessPieceName(), true, chessboardCopy);

                if (!tempKing.checkForOpponents(tempKing.getCurrentCoordinate(), tempKing.getChessPieceColor())) {
                    executeMove(destinationButton);
                }
            }
            isPieceClicked = false;
        }
    }

    public void executeMove(Button squareButton){
        boardLogic.updateChessBoardMove(piece, clickedPieceCoordinate, squareButton, chessBoardNodes);
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
        setToDefaultState();
    }

    public void makeInstanceOfKing(Button squareButton){
        if(!(piece instanceof King)){
            List<Node> chessboardCopy = deepCopyArrayList(chessBoardNodes);
            boardLogic.updateChessBoardMove(null, clickedPieceCoordinate, squareButton, chessboardCopy);

            for(Node node : chessBoardNodes){
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

    public void executeCastling(Button destinationButtonRook){
        String kingCoordinate = currentKing.getCurrentCoordinate();
        Button destinationButtonKing = new Button(kingCoordinate.charAt(0) + "7");
        destinationButtonKing.setUserData(currentKing.getChessPieceName());
        boardLogic.updateChessBoardMove(piece, kingCoordinate, destinationButtonKing, board.getChessBoard().getChildren());
        boardLogic.updateChessBoardMove(piece, clickedPieceCoordinate, destinationButtonRook, board.getChessBoard().getChildren());
        pieceMoved = true;
        changeTurn();
        setToDefaultState();
    }

    public boolean checkForPermission(Button button) {
        if (turnPermission) {
            return checkForPlayingPermission(button, "white");
        } else {
            return checkForPlayingPermission(button, "black");
        }
    }

    public void setToDefaultState(){
        isPieceClicked = false;
        isInCheck = false;
        currentKing = null;
        boardLogic.setOpponentPawn(false);
    }

    public void setToDefaultStateAndHighlight(){
        boardLogic.setOriginalColor();
        boardLogic.highlightCheckedKing(piece, board.getChessBoard().getChildren());
        isPieceClicked = false;
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

    public Boolean isInCheck() {
        return isInCheck;
    }

    public void setInCheck(Boolean inCheck) {
        isInCheck = inCheck;
    }
}
