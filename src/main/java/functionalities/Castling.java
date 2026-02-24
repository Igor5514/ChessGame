package functionalities;

import com.example.cs230pz.Game;
import javafx.scene.Node;
import javafx.scene.control.Button;
import pieces.Piece;

import java.util.List;

public class Castling {

    private List<Node> chessBoard;
    private boolean whiteKingMoved;
    private boolean blackKingMoved;
    private boolean whiteRookMoved;
    private boolean blackRookMoved;
    private Game game;

    public Castling(Game game, List<Node> chessBoard, boolean whiteKingMoved, boolean blackKingMoved, boolean whiteRookMoved, Boolean blackRookMoved) {
        this.chessBoard = chessBoard;
        this.whiteKingMoved = whiteKingMoved;
        this.blackKingMoved = blackKingMoved;
        this.whiteRookMoved = whiteRookMoved;
        this.blackRookMoved = blackRookMoved;
        this.game = game;
    }

    public boolean validateCastling(Piece king, String destinationCoordinate){
        if(!game.isInCheck()){
            if(king.getChessPieceColor().equals("white") && !whiteKingMoved && !whiteRookMoved){
                return validateCastlingPositions(king, destinationCoordinate);
            }else if(king.getChessPieceColor().equals("black") && !blackKingMoved && !blackRookMoved){
                return validateCastlingPositions(king , destinationCoordinate);
            }
        }

        return false;
    }

    public boolean validateCastlingPositions(Piece king, String destinationCoordinate){
        int count = 0;
        boolean asc = king.getChessPieceColor().equals("black");
        if(checkForMatchingCoordinates(destinationCoordinate, king)){
            for(int i = (asc ? 4 : 63); (asc ? i <= 7 : i >= 60); i = (asc ? i+1 : i-1)){
                Button button = (Button) chessBoard.get(i);
                Object buttonData = button.getUserData();
                if(count == 0 && buttonData != null && !button.getUserData().toString().endsWith("rook")){
                    return false;
                }

                if((count == 1 || count == 2) && buttonData != null){
                    return false;
                }

                if(count == 3 && buttonData != null && !buttonData.toString().endsWith("king")) {
                    return false;
                }
                count++;
            }
        }

        return true;
    }

    public boolean checkForMatchingCoordinates(String destinationCoordinate, Piece king){
        if(king.getChessPieceColor().equals("white") && destinationCoordinate.equals("86")){
            return true;
        } else return king.getChessPieceColor().equals("black") && destinationCoordinate.equals("16");
    }

    public boolean isWhiteKingMoved() {
        return whiteKingMoved;
    }

    public void setWhiteKingMoved(boolean whiteKingMoved) {
        this.whiteKingMoved = whiteKingMoved;
    }

    public boolean isBlackKingMoved() {
        return blackKingMoved;
    }

    public void setBlackKingMoved(boolean blackKingMoved) {
        this.blackKingMoved = blackKingMoved;
    }

    public boolean isWhiteRookMoved() {
        return whiteRookMoved;
    }

    public void setWhiteRookMoved(boolean whiteRookMoved) {
        this.whiteRookMoved = whiteRookMoved;
    }

    public boolean isBlackRookMoved() {
        return blackRookMoved;
    }

    public void setBlackRookMoved(boolean blackRookMoved) {
        this.blackRookMoved = blackRookMoved;
    }
}
