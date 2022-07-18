package cl.uchile.dcc.citricliquid.model.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * Test suite for the players of the game.
 *
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater M.</a>.
 * @version 1.0.6-rc.1
 * @since 1.0
 */
public class PlayerTest {
  private final static String PLAYER_NAME = "Suguri";
  private Player suguri;

  @BeforeEach
  public void setUp() {
    suguri = new Player(PLAYER_NAME, 4, 1, -1, 2);
  }

  @Test
  public void constructorTest() {
    final var expectedSuguri = new Player(PLAYER_NAME, 4, 1, -1, 2);
    Assertions.assertTrue(expectedSuguri.equals(suguri));
  }

  @Test
  public void testEquals() {
    final var o = new Object();
    Assertions.assertNotEquals(suguri, o);
    Assertions.assertTrue(suguri.equals(suguri));
    final var expectedSuguri = new Player(PLAYER_NAME, 4, 1, -1, 2);
    Assertions.assertTrue(expectedSuguri.equals(suguri));
  }

  // DELETED: increaseStarsByTest(), increaseWinsByTest(), normaClearCheck() RESPONSABILITY OF NORMA CLASS
  @Test
  public void copyTest() {
    final var expectedSuguri = new Player(PLAYER_NAME, 4, 1, -1, 2);
    final var actualSuguri = new Player(suguri);
    // Checks that the copied player have the same parameters as the original
    Assertions.assertTrue(expectedSuguri.equals(actualSuguri));
    // Checks that the copied player doesn't reference the same object
    Assertions.assertNotSame(expectedSuguri, actualSuguri);
  }

  // region : consistency tests
  // DELETED: normaClearConsistencyTest, NOW RESPONSABILITY OF NORMA CLASS
  // endregion
}
