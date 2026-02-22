package functionalities;

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

    public Castling(List<Node> chessBoard, boolean whiteKingMoved, boolean blackKingMoved, boolean whiteRookMoved, boolean blackRookMoved) {
        this.chessBoard = chessBoard;
        this.whiteKingMoved = whiteKingMoved;
        this.blackKingMoved = blackKingMoved;
        this.whiteRookMoved = whiteRookMoved;
        this.blackRookMoved = blackRookMoved;
    }

    public boolean validateForCastling(Piece king){
        if(king.getChessPieceColor().equals("white") && !whiteKingMoved && !whiteRookMoved){
            return checkForCastling(false);
        }else if(king.getChessPieceColor().equals("black") && !blackKingMoved && !blackRookMoved){
            return checkForCastling(true);
        }
        return false;
    }

    public boolean checkForCastling(boolean asc){
        int count = 0;
        for(int i = (asc ? 0 : 4); (asc ? i < 4 : i > 0); i = (asc ? i+1 : i-1)){
            Button button = (Button) chessBoard.get(i);
            Object buttonData = button.getUserData();
            if(count == 0 && buttonData != null && !button.getUserData().toString().endsWith("rook")){
                return false;
            }

            if((count == 1 || count == 2) && buttonData != null){
                return false;
            }

            if(count == 3 && !buttonData.toString().endsWith("king")) {
                return false;
            }
            count++;
        }
        return true;

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
