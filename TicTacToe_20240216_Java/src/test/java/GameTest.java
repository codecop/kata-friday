import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class GameTest {

    @Test
    void shouldDetectWinnerThreeInARowX() {
        Game game = new Game();
        game.move(Player.X, Move.A1);
        game.move(Player.X, Move.A2);
        game.move(Player.X, Move.A3);
        Optional<Object> winner = game.winner();
        assertWinner(Player.X, winner);
    }

    @Test
    void shouldDetectNoWinner() {
        Game game = new Game();
        game.move(Player.X, Move.A1);
        game.move(Player.O, Move.A2);
        game.move(Player.X, Move.A3);
        Optional<Object> winner = game.winner();
        assertFalse(winner.isPresent());
    }

    @Test
    void shouldDetectWinnerThreeInARowO() {
        Game game = new Game();
        game.move(Player.O, Move.A1);
        game.move(Player.O, Move.A2);
        game.move(Player.O, Move.A3);
        Optional<Object> winner = game.winner();
        assertWinner(Player.O, winner);
    }

    private void assertWinner(Player expected, Optional<Object> actual) {
        assertEquals(expected.toString(), extractWinner(actual));
    }

    private String extractWinner(Optional<Object> winner) {
        return winner. //
                map(Player::toString). //
                orElseGet(() -> "No Winner");
    }

}
