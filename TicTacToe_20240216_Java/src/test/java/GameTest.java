import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class GameTest {

    @Test
    void shouldDetectWinnerXInRowA() {
        Game game = new Game();
        game.move(Player.X, Move.ROW_A_COLUMN_1);
        game.move(Player.X, Move.ROW_A_COLUMN_2);
        game.move(Player.X, Move.ROW_A_COLUMN_3);
        Optional<Player> winner = game.winner();
        assertWinner(Player.X, winner);
    }

    @Test
    void shouldDetectNoWinner() {
        Game game = new Game();
        game.move(Player.X, Move.ROW_A_COLUMN_1);
        game.move(Player.O, Move.ROW_A_COLUMN_2);
        game.move(Player.X, Move.ROW_A_COLUMN_3);
        Optional<Player> winner = game.winner();
        assertFalse(winner.isPresent());
    }

    @Test
    void shouldDetectWinnerOInRowA() {
        Game game = new Game();
        game.move(Player.O, Move.ROW_A_COLUMN_1);
        game.move(Player.O, Move.ROW_A_COLUMN_2);
        game.move(Player.O, Move.ROW_A_COLUMN_3);
        Optional<Player> winner = game.winner();
        assertWinner(Player.O, winner);
    }


    @Test
    void shouldDetectWinnerInColumn1() {
        Game game = new Game();
        game.move(Player.O, Move.ROW_A_COLUMN_1);
        game.move(Player.O, Move.ROW_B_COLUMN_1);
        game.move(Player.O, Move.ROW_C_COLUMN_1);
        Optional<Player> winner = game.winner();
        assertWinner(Player.O, winner);
    }

    private void assertWinner(Player expected, Optional<Player> actual) {
        assertEquals(expected.toString(), extractWinner(actual));
    }

    private String extractWinner(Optional<Player> winner) {
        return winner. //
                map(Player::toString). //
                orElseGet(() -> "No Winner");
    }

}
