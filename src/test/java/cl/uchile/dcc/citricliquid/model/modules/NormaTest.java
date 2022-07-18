package cl.uchile.dcc.citricliquid.model.modules;

import cl.uchile.dcc.citricliquid.model.modules.Norma;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NormaTest {
  private Norma norma;

  @BeforeEach
  public void setUp() {
    this.norma = new Norma();
  }

  @Test
  public void constructorTest() {
    final var expectedNorma = new Norma();
    Assertions.assertEquals(expectedNorma, norma);
  }

  @Test
  public void testEquals() {
    final var o = new Object();
    Assertions.assertNotEquals(norma, o);
    Assertions.assertEquals(norma, norma);
    Assertions.assertFalse(norma.equals(null));
  }
  @Test
  public void normaCheckTestStar() {
    Assertions.assertFalse(norma.check());
    norma.setStars(10);
    Assertions.assertTrue(norma.check());
    norma.clear();
    norma.addStars(10000);
    norma.setGoal(NormaGoal.WINS);
    Assertions.assertFalse(norma.check());
    norma.setGoal(NormaGoal.STARS);
    Assertions.assertTrue(norma.check());
  }

  @Test
  public void normaCheckTestWin() {
    Assertions.assertFalse(norma.check());
    norma.addWins(2);
    Assertions.assertFalse(norma.check());
    norma.setStars(10);
    Assertions.assertTrue(norma.check());
    norma.clear();
    norma.setGoal(NormaGoal.WINS);
    Assertions.assertTrue(norma.check());
  }
}
