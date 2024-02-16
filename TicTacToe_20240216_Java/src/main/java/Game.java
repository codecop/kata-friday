import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.zip.CRC32;

public class Game {

    private boolean wasO;
    Map<Move, Player> field = new HashMap<>();

    public void move(Player player, Move move) {
        wasO = wasO || (player == Player.O);
        field.put(move, player);
    }

    public Optional<Player> winner() {
        if (!wasO) {
            return Optional.ofNullable(Player.X);
        }

        if (
                field.get(Move.A1) ==
                field.get(Move.A2) &&
                field.get(Move.A2) ==
                field.get(Move.A3)
        ) return Optional.of(field.get(Move.A1));
        return Optional.empty();
    }
}
