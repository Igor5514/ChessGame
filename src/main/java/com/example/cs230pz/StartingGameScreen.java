package com.example.cs230pz;

import functionalities.Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.File;

public class StartingGameScreen extends BorderPane {

    private ImageView imageView = new ImageView(new File("src/main/java/chess_pieces/background.png").toURI().toString());
    private Label titleLabel = new Label("Chess game");
    Button startButton = new Button("Start Game");
    private Label player1NameLabel = new Label("Player 1 Name:");
    private Label player1ErrorLabel = new Label("");
    private TextField player1NameInput = new TextField();
    private Label player2NameLabel = new Label("Player 2 Name:");
    private Label player2ErrorLabel = new Label("");
    private TextField player2NameInput = new TextField();
    HBox player1NameBox = new HBox(10);
    HBox player2NameBox = new HBox(10);
    VBox input1Vox = new VBox();
    VBox input2Vox = new VBox();
    VBox playersBox = new VBox(20);

    StartingGameScreen(){
        makeGameStartingGameScreen();
    }

    public void makeGameStartingGameScreen() {


        playersBox.setPadding(new Insets(20));
        playersBox.setMaxWidth(this.getMaxWidth());
        playersBox.setAlignment(Pos.CENTER);

        player1NameBox.setAlignment(Pos.CENTER);
        player2NameBox.setAlignment(Pos.CENTER);

        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        BorderPane.setAlignment(playersBox,Pos.CENTER);
        BorderPane.setAlignment(startButton, Pos.CENTER);

        //LABELS

        titleLabel.setPadding(new Insets(50,0,0,0));
        titleLabel.setStyle("-fx-font-size: 40px; -fx-text-fill: #ffffcc; -fx-font-weight: bold");

        player1NameLabel.setTranslateY(-10);
        player2NameLabel.setTranslateY(-10);
        player1NameBox.setStyle("-fx-font-size: 15px; -fx-font-weight: bold ");
        player2NameBox.setStyle("-fx-font-size: 15px; -fx-font-weight: bold ");

        player1ErrorLabel.setStyle("-fx-text-fill: red");
        player2ErrorLabel.setStyle("-fx-text-fill: red");

        //INPUTS

        player1NameInput.setStyle("-fx-background-color:  #ffffcc");
        player2NameInput.setStyle("-fx-background-color:  #ffffcc");

        //BUTTON

        startButton.setPadding(new Insets(7, 37, 7, 37));
        startButton.setFocusTraversable(false);
        startButton.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;-fx-text-fill: white ;-fx-background-color:  #86592d");
        BorderPane.setMargin(startButton,new Insets(0,0,100,0));

        startButton.setOnMouseEntered(event -> {
            startButton.setStyle("-fx-font-size: 15px; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-color: #ff8000;");
        });
        startButton.setOnMouseExited(event -> {
            startButton.setStyle("-fx-font-size: 15px; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-color: #86592d");
        });

        startButton.setOnAction(e -> {
            String player1Name = player1NameInput.getText();
            String player2Name = player2NameInput.getText();

            if(player1Name.isEmpty()){
                player1ErrorLabel.setText("Enter your name");
            } else if (player2Name.isEmpty()){
                player2ErrorLabel.setText("Enter your name");
                player1ErrorLabel.setText("");
            }else{
                player1ErrorLabel.setText("");
                player2ErrorLabel.setText("");
                Player player1 = new Player(player1Name,0,0,0,0);
                Player player2 = new Player(player1Name,0,0,0,0);
                ChessBoard chessBoard = new ChessBoard();
                Game game = new Game(player1,player2);
                game.setBoard(chessBoard);
                Main.setRootNode(chessBoard);

            }
        });




        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage background = new BackgroundImage(imageView.getImage(), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        input1Vox.getChildren().addAll(player1NameInput,player1ErrorLabel);
        input2Vox.getChildren().addAll(player2NameInput,player2ErrorLabel);
        player1NameBox.getChildren().addAll(player1NameLabel,input1Vox);
        player2NameBox.getChildren().addAll(player2NameLabel,input2Vox);
        playersBox.getChildren().addAll(player1NameBox,player2NameBox);

        this.setBackground(new Background(background));
        this.setTop(titleLabel);
        this.setCenter(playersBox);
        this.setBottom(startButton);

    }
}

