package functionalities;

import javafx.scene.control.Button;
import pieces.Piece;
import pieces.Rook;

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
                return new Rook(i+" "+j, pieceName, false);
            case "white_knight":
            case "black_knight":
                return playKnight(pieceName,i,j);
            case "white_bishop":
            case "black_bishop":
                return playBishop(pieceName,i,j);
            case "white_king":
            case "black_king":
                return playKing(pieceName,i,j);
            case "white_queen":
            case "black_queen":
                return playQueen(pieceName,i,j);
            case "white_pawn":
                return playWhitePawn(pieceName,i,j);
            case "black_pawn":
                return playBlackPawn(pieceName,i,j);
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

