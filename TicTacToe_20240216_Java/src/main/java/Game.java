import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

public class Game {

    private final Map<Field, Player> field = new EnumMap<>(Field.class);

    public void move(Player player, Field move) {
        field.put(move, player);
    }

    public Optional<Player> winner() {

        // approach 1: iterate list of triples
        //        List<Move> a = Arrays.asList(Move.ROW_A_COLUMN_1, Move.ROW_A_COLUMN_2, Move.ROW_A_COLUMN_3);
        //        if (field.get(a.get(0)) == field.get(a.get(1)) && //
        //            field.get(a.get(1)) == field.get(a.get(2))) {
        //            return Optional.of(field.get(a.get(0)));
        //        }

        // approach 2: chain call of triples
        return Optional.<Player>empty()
                .or(() -> winnerIn(Field.ROW_A_COLUMN_1, Field.ROW_A_COLUMN_2, Field.ROW_A_COLUMN_3))
                .or(() -> winnerIn(Field.ROW_A_COLUMN_1, Field.ROW_B_COLUMN_1, Field.ROW_C_COLUMN_1))
                .or(() -> winnerIn(Field.ROW_A_COLUMN_1, Field.ROW_B_COLUMN_2, Field.ROW_C_COLUMN_3));
    }

    private Optional<Player> winnerIn(Field a, Field b, Field c) {
        if (field.get(a) == field.get(b) && //
            field.get(b) == field.get(c)) {
            return Optional.of(field.get(a));
        }
        return Optional.empty();
    }
}
