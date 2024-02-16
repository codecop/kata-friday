import java.util.EnumMap;
import java.util.Optional;

public class Game {

    EnumMap<Move, Player> field = new EnumMap<>(Move.class);

    public void move(Player player, Move move) {
        field.put(move, player);
    }

    public Optional<Object> winner() {
        if (
                field.get(Move.A1) ==
                        field.get(Move.A2) &&
                        field.get(Move.A2) ==
                                field.get(Move.A3)
        ) {
            return Optional.of(field.get(Move.A1));
        }
        return Optional.empty();
    }
}
