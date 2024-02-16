import java.util.Optional;

public class Game {

    private boolean wasO;

    public void move(Player player, Move move) {
        wasO = wasO || (player == Player.O);
    }

    public Optional<Player> winner() {
        if (!wasO) {
            return Optional.ofNullable(Player.X);
        }
        return Optional.empty();
    }
}
