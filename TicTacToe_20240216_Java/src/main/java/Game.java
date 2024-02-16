import java.util.Optional;

public class Game {

    private boolean wasO;

    public void move(Player player, Move move) {
        wasO = wasO || (player == Player.O);
    }

    public Player winner() {
        return Player.X;
    }

    public Optional<Player> owinner() {
        if (wasO) {
            return Optional.empty();
        }
        return Optional.ofNullable(winner());
    }
}
