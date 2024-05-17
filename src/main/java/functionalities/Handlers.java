package functionalities;

import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.util.ArrayList;

public class Handlers {

    public Handlers(){
    }

    public Move handleClick(Button button){
        String coordinates = button.getText();
        int i = Integer.parseInt(String.valueOf(coordinates.charAt(0)));
        int j = Integer.parseInt(String.valueOf(coordinates.charAt(1)));
        String piece = (String) button.getUserData();
        switch (piece){
            case "white_rook":
            case "black_rook":
                return playRook(piece,i,j);
            case "white_knight":
            case "black_knight":
                return playKnight(piece,i,j);
            case "white_bishop":
            case "black_bishop":
                return playBishop(piece,i,j);
            case "white_king":
            case "black_king":
                return playKing(piece,i,j);
            case "white_queen":
            case "black_queen":
                return playQueen(piece,i,j);
            case "white_pawn":
                return playWhitePawn(piece,i,j);
            case "black_pawn":
                return playBlackPawn(piece,i,j);
            default:
                return null;
        }
    }

    public Move playRook(String piece, int i, int j){
        ArrayList<String> possibleMoves = new ArrayList<>();
        String currentCoordinate = i + "" + j;
        for(int k = 1; k < 9; k++) {
            String coordinate = i + "" + k;
            if(!currentCoordinate.equals(coordinate)){
                possibleMoves.add(coordinate);
            }
        }
        for(int k = 1; k< 9; k++) {
            String coordinate = k + "" + j;
            if(!currentCoordinate.equals(coordinate)){
                possibleMoves.add(coordinate);
            }
        }
        System.out.println(possibleMoves);
        return new Move(currentCoordinate, piece, possibleMoves,false);
    }

    public Move playKnight(String piece, int i, int j){
        return null;
    }

    public Move playBishop(String piece, int i, int j){
        return null;
    }

    public Move playKing(String piece, int i, int j){
        return null;
    }

    public Move playQueen(String piece, int i, int j){
        return null;
    }

    public Move playWhitePawn(String piece, int i, int j){
        return null;
    }

    public Move playBlackPawn(String piece, int i, int j){
        return null;
    }

}
