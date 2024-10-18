package functionalities;

import java.util.ArrayList;

public interface Movable {

    default ArrayList<String> rookMoves(boolean ascending, boolean isLeftCord, int i, int j, int x,String kingCoordinate) {
        ArrayList<String> coordinateList = new ArrayList<>();
        if (i != x) {
            for (int k = i; (ascending ? k <= x : k >= x); k = (x == 8 ? k + 1 : k - 1)) {
                String coordinate = isLeftCord ? j + "" + k : k + "" + j;
                if (!coordinate.equals(kingCoordinate)) {
                    coordinateList.add(coordinate);
                }
            }
        }
        return coordinateList;
    }



}
