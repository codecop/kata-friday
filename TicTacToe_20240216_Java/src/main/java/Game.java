import java.util.Optional;

public class Game {

    public void move(Player player, Move move) {

    }

    public Player winner() {
        return Player.X;
    }

    public Optional<Player> owinner() {
        return Optional.ofNullable(winner());
    }
}
