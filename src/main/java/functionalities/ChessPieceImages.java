package functionalities;

import javafx.scene.image.ImageView;

import java.io.File;

public interface ChessPieceImages {

    default ImageView getWhitePawn() {
        ImageView imageView = new ImageView(new File("src/main/java/chess_pieces/white_pawn.png").toURI().toString());
        imageView.setFitWidth(30);
        imageView.setFitHeight(50);
        imageView.setTranslateX(8);
        return imageView;
    }

    default ImageView getBlackPawn() {
        ImageView imageView = new ImageView(new File("src/main/java/chess_pieces/pawn.png").toURI().toString());
        imageView.setFitWidth(30);
        imageView.setFitHeight(50);
        imageView.setTranslateX(8);
        return imageView;
    }

    default ImageView getWhiteRook() {
        ImageView imageView = new ImageView(new File("src/main/java/chess_pieces/white_rook.png").toURI().toString());
        imageView.setFitWidth(40);
        imageView.setFitHeight(60);
        imageView.setTranslateX(8);
        return imageView;
    }

    default ImageView getBlackRook() {
        ImageView imageView = new ImageView(new File("src/main/java/chess_pieces/rook.png").toURI().toString());
        imageView.setFitWidth(40);
        imageView.setFitHeight(60);
        imageView.setTranslateX(8);
        return imageView;
    }

    default ImageView getWhiteKnight() {
        ImageView imageView = new ImageView(new File("src/main/java/chess_pieces/white_knight.png").toURI().toString());
        imageView.setFitWidth(40);
        imageView.setFitHeight(60);
        imageView.setTranslateX(10);
        return imageView;
    }

    default ImageView getBlackKnight() {
        ImageView imageView = new ImageView(new File("src/main/java/chess_pieces/knight.png").toURI().toString());
        imageView.setFitWidth(40);
        imageView.setFitHeight(60);
        imageView.setTranslateX(10);
        return imageView;
    }

    default ImageView getWhiteBishop() {
        ImageView imageView = new ImageView(new File("src/main/java/chess_pieces/white_bishop.png").toURI().toString());
        imageView.setFitWidth(35);
        imageView.setFitHeight(65);
        imageView.setTranslateX(9);
        return imageView;
    }

    default ImageView getBlackBishop() {
        ImageView imageView = new ImageView(new File("src/main/java/chess_pieces/bishop.png").toURI().toString());
        imageView.setFitWidth(35);
        imageView.setFitHeight(65);
        imageView.setTranslateX(9);
        return imageView;
    }

    default ImageView getWhiteQueen() {
        ImageView imageView = new ImageView(new File("src/main/java/chess_pieces/white_queen.png").toURI().toString());
        imageView.setFitWidth(35);
        imageView.setFitHeight(65);
        imageView.setTranslateX(9);
        return imageView;
    }

    default ImageView getBlackQueen() {
        ImageView imageView = new ImageView(new File("src/main/java/chess_pieces/queen.png").toURI().toString());
        imageView.setFitWidth(35);
        imageView.setFitHeight(65);
        imageView.setTranslateX(9);
        return imageView;
    }

    default ImageView getWhiteKing() {
        ImageView imageView = new ImageView(new File("src/main/java/chess_pieces/white_king.png").toURI().toString());
        imageView.setFitWidth(35);
        imageView.setFitHeight(65);
        imageView.setTranslateX(9);
        return imageView;
    }

    default ImageView getBlackKing() {
        ImageView imageView = new ImageView(new File("src/main/java/chess_pieces/king.png").toURI().toString());
        imageView.setFitWidth(35);
        imageView.setFitHeight(65);
        imageView.setTranslateX(9);
        return imageView;
    }
}
