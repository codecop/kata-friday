import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

public class Game {

    private final Map<Move, Player> field = new EnumMap<>(Move.class);

    public void move(Player player, Move move) {
        field.put(move, player);
    }

    public Optional<Player> winner() {
        if (field.get(Move.ROW_A_COLUMN_1) == field.get(Move.ROW_A_COLUMN_2) && //
            field.get(Move.ROW_A_COLUMN_2) == field.get(Move.ROW_A_COLUMN_3)) {
            return Optional.of(field.get(Move.ROW_A_COLUMN_1));
        }
        if (field.get(Move.ROW_A_COLUMN_1) == field.get(Move.ROW_B_COLUMN_1) && //
                field.get(Move.ROW_B_COLUMN_1) == field.get(Move.ROW_C_COLUMN_1)) {
            return Optional.of(field.get(Move.ROW_A_COLUMN_1));
        }
        return Optional.empty();
    }
}
