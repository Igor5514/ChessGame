package utils;

import javafx.scene.Node;
import javafx.scene.control.Button;
import objects.Field;

import java.util.ArrayList;
import java.util.List;

public interface Util {

    default List<Node> deepCopyArrayList(List<Node> list){
        List<Node> nodes = new ArrayList<>();

        for(int i = 0; i < list.size(); i++){
            if(list.get(i) instanceof Button button){
                Object data = button.getUserData();
                Button newButton = new Button(button.getText());
                newButton.setUserData(data == null ? null : data.toString());
                nodes.add(newButton);
            }
        }
        return nodes;
    }

    default void printBoard(List<Node> chessboardCopy){
        for (Node cord : chessboardCopy){
            Button button = (Button) cord;

            System.out.print(button.getText() + " ");
            if(button.getUserData() != null){
                System.out.print(button.getUserData().toString() + " | ");
            }else{
                System.out.print("null"  + " | ");
            }

            if(button.getText().endsWith("8")){
                System.out.println();
            }
        }
    }


}
