import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class  GameTest {

    @Test
    void shouldDetectWinnerXInRowA() {
        Game game = new Game();
        game.move(Player.X, Field.ROW_A_COLUMN_1);
        game.move(Player.X, Field.ROW_A_COLUMN_2);
        game.move(Player.X, Field.ROW_A_COLUMN_3);
        Optional<Player> winner = game.winner();
        assertWinner(Player.X, game);
    }

    @Test
    void shouldDetectNoWinner() {
        Game game = new Game();
        game.move(Player.X, Field.ROW_A_COLUMN_1);
        game.move(Player.O, Field.ROW_A_COLUMN_2);
        game.move(Player.X, Field.ROW_A_COLUMN_3);
        Optional<Player> winner = game.winner();
        assertFalse(winner.isPresent());
    }

    @Test
    void shouldDetectWinnerOInRowA() {
        Game game = new Game();
        game.move(Player.O, Field.ROW_A_COLUMN_1);
        game.move(Player.O, Field.ROW_A_COLUMN_2);
        game.move(Player.O, Field.ROW_A_COLUMN_3);
        assertWinner(Player.O, game);
    }


    @Test
    void shouldDetectWinnerInColumn1() {
        Game game = new Game();
        game.move(Player.O, Field.ROW_A_COLUMN_1);
        game.move(Player.O, Field.ROW_B_COLUMN_1);
        game.move(Player.O, Field.ROW_C_COLUMN_1);
        assertWinner(Player.O, game);
    }

    @Test
    void shouldDetectWinnerXInDiagonal() {
        Game game = new Game();
        game.move(Player.X, Field.ROW_A_COLUMN_1);
        game.move(Player.X, Field.ROW_B_COLUMN_2);
        game.move(Player.X, Field.ROW_C_COLUMN_3);
        assertWinner(Player.X, game);
    }

    private void assertWinner(Player player, Game game) {
        Optional<Player> actual = game.winner();
        assertEquals(player.toString(), actual. //
                map(Player::toString). //
                orElseGet(() -> "No Winner"));
    }

}
