package com.example.cs230pz;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ChessBoard chessBoard = new ChessBoard();
        Scene scene = new Scene(chessBoard,640,640);
        stage.setScene(scene);
        stage.setResizable(false);

        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }
}