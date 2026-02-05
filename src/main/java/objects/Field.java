package objects;

public class Field {

    private String coordinate;
    private String pieceName = null;

    public Field(String coordinate, String pieceName) {
        this.coordinate = coordinate;
        this.pieceName = pieceName;
    }

    public Field() {
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getPieceName() {
        return pieceName;
    }

    public void setPieceName(String pieceName) {
        this.pieceName = pieceName;
    }
}
