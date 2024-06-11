package com.example.cs230pz;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application {
    static Pane root = new StartingGameScreen();

    @Override
    public void start(Stage stage) {

        Scene scene = new Scene(root,640,640);
        stage.setScene(scene);
        stage.setResizable(false);

        stage.show();


    }

    public static void setRootNode(Pane newRoot) {
        root.getChildren().clear();
        root.getChildren().add(newRoot);
    }


    public static void main(String[] args) {
        launch();
    }
}