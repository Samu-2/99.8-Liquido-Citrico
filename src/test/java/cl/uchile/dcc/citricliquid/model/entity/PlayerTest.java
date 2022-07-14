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

  @Test
  public void increaseStarsByTest() {
    suguri.increaseStarsBy(1);
    Assertions.assertEquals(1, suguri.getStars());
    suguri.increaseStarsBy(2);
    Assertions.assertEquals(3, suguri.getStars());
  }

  @Test
  public void increaseWinsByTest(){
    suguri.increaseWinsBy(1);
    Assertions.assertEquals(1, suguri.getWins());
    suguri.increaseWinsBy(2);
    Assertions.assertEquals(3, suguri.getWins());
  }
  @Test
  public void normaClearCheck() {
    suguri.normaClear();
    Assertions.assertEquals(2, suguri.getNorma());
  }

  @Test
  public void copyTest() {
    final var expectedSuguri = new Player(PLAYER_NAME, 4, 1, -1, 2);
    final var actualSuguri = suguri.copy();
    // Checks that the copied player have the same parameters as the original
    Assertions.assertTrue(expectedSuguri.equals(actualSuguri));
    // Checks that the copied player doesn't reference the same object
    Assertions.assertNotSame(expectedSuguri, actualSuguri);
  }

  // region : consistency tests

  @RepeatedTest(100)
  public void normaClearConsistencyTest() {
    final long testSeed = new Random().nextLong();
    // We're gonna test for 0 to 5 norma clears
    final int iterations = Math.abs(new Random(testSeed).nextInt(6));
    final int expectedNorma = suguri.getNorma() + iterations;
    for (int it = 0; it < iterations; it++) {
      suguri.normaClear();
    }
    Assertions.assertEquals(expectedNorma, suguri.getNorma(),
                            "Test failed with random seed: " + testSeed);
  }
  // endregion
}
