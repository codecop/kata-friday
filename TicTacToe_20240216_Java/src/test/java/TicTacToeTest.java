import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TicTacToeTest {

    @Test
    void shouldDetectThreeInARow() {
        Game game = new Game();
        game.move(Player.X, Move.A1);
        game.move(Player.X, Move.A2);
        game.move(Player.X, Move.A3);
        Player winner = game.winner();
        assertEquals(Player.X, winner);
    }


    @Test
    void shouldDetectNoWinner() {
        Game game = new Game();
        game.move(Player.X, Move.A1);
        game.move(Player.O, Move.A2);
        game.move(Player.X, Move.A3);
        Optional<Player> winner = game.owinner();
        assertTrue(winner.isEmpty());
    }


}
