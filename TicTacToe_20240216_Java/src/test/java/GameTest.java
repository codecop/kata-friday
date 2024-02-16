import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Optional;

import org.junit.jupiter.api.Test;

class GameTest {

    Game game = new Game();

    @Test
    void shouldDetectWinnerXInRowA() {
        game.move(Player.X, Field.ROW_A_COLUMN_1);
        game.move(Player.X, Field.ROW_A_COLUMN_2);
        game.move(Player.X, Field.ROW_A_COLUMN_3);

        assertWinner(Player.X);
    }

    @Test
    void shouldDetectNoWinner() {
        game.move(Player.X, Field.ROW_A_COLUMN_1);
        game.move(Player.O, Field.ROW_A_COLUMN_2);
        game.move(Player.X, Field.ROW_A_COLUMN_3);
        Optional<Player> winner = game.winner();

        assertFalse(winner.isPresent());
    }

    @Test
    void shouldDetectWinnerOInRowA() {
        game.move(Player.O, Field.ROW_A_COLUMN_1);
        game.move(Player.O, Field.ROW_A_COLUMN_2);
        game.move(Player.O, Field.ROW_A_COLUMN_3);

        assertWinner(Player.O);
    }

    @Test
    void shouldDetectWinnerInColumn1() {
        game.move(Player.O, Field.ROW_A_COLUMN_1);
        game.move(Player.O, Field.ROW_B_COLUMN_1);
        game.move(Player.O, Field.ROW_C_COLUMN_1);

        assertWinner(Player.O);
    }

    @Test
    void shouldDetectWinnerXInDiagonal() {
        game.move(Player.X, Field.ROW_A_COLUMN_1);
        game.move(Player.X, Field.ROW_B_COLUMN_2);
        game.move(Player.X, Field.ROW_C_COLUMN_3);

        assertWinner(Player.X);
    }

    private void assertWinner(Player expected) {
        String actual = game.winner(). // 
                map(Player::toString). // hack to have error massage
                orElseGet(() -> "No Winner");
        assertEquals(expected.toString(), actual);
    }

}
