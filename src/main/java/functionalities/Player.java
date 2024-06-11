package functionalities;

public class Player {

    String name;
    Integer gamesPlayed;
    Integer wins = 0;
    Integer draws = 0;
    Integer loses = 0;

    public Player(String name, Integer gamesPlayed, Integer wins, Integer draws, Integer loses) {
        this.name = name;
        this.gamesPlayed = gamesPlayed;
        this.wins = wins;
        this.draws = draws;
        this.loses = loses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(Integer gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public Integer getDraws() {
        return draws;
    }

    public void setDraws(Integer draws) {
        this.draws = draws;
    }

    public Integer getLoses() {
        return loses;
    }

    public void setLoses(Integer loses) {
        this.loses = loses;
    }
}
