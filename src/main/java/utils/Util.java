package utils;

import javafx.scene.Node;
import javafx.scene.control.Button;
import objects.Field;

import java.util.ArrayList;
import java.util.List;

public interface Util {

    default List<Button> deepCopyArrayList(List<Node> list){
        List<Button> nodes = new ArrayList<>();

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


}
