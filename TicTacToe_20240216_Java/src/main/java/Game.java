import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Game {

    private final Map<Move, Player> field = new EnumMap<>(Move.class);

    public void move(Player player, Move move) {
        field.put(move, player);
    }

    public Optional<Player> winner() {

        List<Move> a = Arrays.asList(Move.ROW_A_COLUMN_1, Move.ROW_A_COLUMN_2, Move.ROW_A_COLUMN_3);

        if (field.get(a.get(0)) == field.get(a.get(1)) && //
            field.get(a.get(1)) == field.get(a.get(2))) {
            return Optional.of(field.get(a.get(0)));
        }
        return extracted(Move.ROW_A_COLUMN_1, Move.ROW_B_COLUMN_1, Move.ROW_C_COLUMN_1);
    }

    private Optional<Player> extracted(Move a, Move b, Move c) {
        if (field.get(a) == field.get(b) && //
            field.get(b) == field.get(c)) {
            return Optional.of(field.get(a));
        }
        return Optional.empty();
    }
}
