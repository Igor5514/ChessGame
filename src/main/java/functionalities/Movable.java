package functionalities;

import java.util.ArrayList;

public interface Movable {

    default ArrayList<String> rookMoves(boolean asc, boolean isLeftCord, int i, int j, int x,String pieceCoordinate) {
        ArrayList<String> coordinateList = new ArrayList<>();
        if (i != x) {
            for (int k = i; (asc ? k <= x : k >= x); k = (x == 8 ? k + 1 : k - 1)) {
                String coordinate = isLeftCord ? j + "" + k : k + "" + j;
                if (!coordinate.equals(pieceCoordinate)) {
                    coordinateList.add(coordinate);
                }
            }
        }
        return coordinateList;
    }

    default ArrayList<String> bishopMoves(boolean asc, boolean asc2, int i, int j, int x,int y,String pieceCoordinate) {
        ArrayList<String> coordinateList = new ArrayList<>();
        if(!(i == x || j == y)){
            for(int k=i, n=j; (asc ? k>=x : k<=x) && (asc2 ? n>=y : n<=y); k+=(asc ? -1 : 1), n+=(asc2 ? -1 : 1)){
                String coordinate = k + "" + n;
                if (!coordinate.equals(pieceCoordinate)) {
                    coordinateList.add(coordinate);
                }
            }
        }
        return coordinateList;
    }


}
