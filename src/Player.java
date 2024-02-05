import java.util.*;
class Player {
    private String name;
    private List<GameBoard> playedGames;

    public Player(String name) {
        this.name = name;
        this.playedGames = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<GameBoard> getPlayedGames() {
        return playedGames;
    }

    public void addGame(GameBoard gameBoard) {
        playedGames.add(gameBoard);
    }
}