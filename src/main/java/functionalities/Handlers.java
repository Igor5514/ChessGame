package functionalities;

import javafx.scene.control.Button;
import pieces.*;

public class Handlers {

    public Handlers(){
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
                return new King(i+""+j, pieceName, true);
            case "white_queen":
            case "black_queen":
                return new Queen(i+""+j, pieceName, false);
            case "white_pawn":
                return new WhitePawn(i+""+j, pieceName, true);
            case "black_pawn":
                return new BlackPawn(i+""+j, pieceName, true);
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

