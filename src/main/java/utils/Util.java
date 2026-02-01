package utils;

import javafx.scene.Node;
import javafx.scene.control.Button;
import pieces.Field;

import java.util.ArrayList;
import java.util.List;

public interface Util {

    default List<Field> deepCopyArrayList(List<Node> list){
        List<Field> nodes = new ArrayList<>();

        for(int i = 0; i < list.size(); i++){
            if(list.get(i) instanceof Button button){
                Object data = button.getUserData();
                nodes.add(new Field(button.getText(), data == null ? null : data.toString()));
            }
        }
        return nodes;
    }


}
