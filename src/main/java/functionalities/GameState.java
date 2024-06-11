package functionalities;

public class GameState {
    private static GameState instance;
    private boolean isWhiteTurn = true;

    private GameState() {}

    public static synchronized GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }
        return instance;
    }

    public boolean isWhiteTurn() {
        return isWhiteTurn;
    }

    public void setWhiteTurn(boolean isWhiteTurn) {
        this.isWhiteTurn = isWhiteTurn;
    }
}

