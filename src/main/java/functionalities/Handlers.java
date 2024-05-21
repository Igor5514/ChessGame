package functionalities;

import javafx.scene.control.Button;
import pieces.*;

import java.util.ArrayList;

public class Handlers {

    public Handlers(){
    }

    public ArrayList<ArrayList<String>> handleClick(Button button){
        String coordinates = button.getText();
        int i = Integer.parseInt(String.valueOf(coordinates.charAt(0)));
        int j = Integer.parseInt(String.valueOf(coordinates.charAt(1)));
        String pieceName = (String) button.getUserData();
        switch (pieceName){
            case "white_rook":
            case "black_rook":
                Piece rook = new Rook(i+" "+j, pieceName, false);
                return rook.getAllCoordinates();
            case "white_knight":
            case "black_knight":
                Piece knight = new Knight(i+" "+j, pieceName, true);
                return knight.getAllCoordinates();
            case "white_bishop":
            case "black_bishop":
                Piece bishop = new Bishop(i+" "+j, pieceName, false);
                return bishop.getAllCoordinates();
            case "white_king":
            case "black_king":
                Piece king = new King(i+" "+j, pieceName, false);
                return king.getAllCoordinates();
            case "white_queen":
            case "black_queen":
                Piece queen = new Queen(i+" "+j, pieceName, false);
                return queen.getAllCoordinates();
            case "white_pawn":
                Piece whitePawn = new WhitePawn(i+" "+j, pieceName, false);
                return whitePawn.getAllCoordinates();
            case "black_pawn":
                Piece blackPawn = new BlackPawn(i+" "+j, pieceName, false);
                return blackPawn.getAllCoordinates();
            default:
                return null;
        }
    }



    public Piece playKnight(String piece, int i, int j){
        return null;
    }

    public Piece playBishop(String piece, int i, int j){
        return null;
    }

    public Piece playKing(String piece, int i, int j){
        return null;
    }

    public Piece playQueen(String piece, int i, int j){

        return null;
    }

    public Piece playWhitePawn(String piece, int i, int j){
        return null;
    }

    public Piece playBlackPawn(String piece, int i, int j){
        return null;
    }

}

