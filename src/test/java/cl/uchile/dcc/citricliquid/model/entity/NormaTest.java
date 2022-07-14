package cl.uchile.dcc.citricliquid.model.entity;

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
  public void copyTest(){
    final var normaCopy = norma.copy();
    Assertions.assertEquals(norma, normaCopy);
    Assertions.assertNotSame(norma, normaCopy);
  }

  @Test
  public void normaCheckTestStar() {
    norma.check();
    Assertions.assertEquals(1, norma.getLevel());
    norma.addStars(10);
    norma.check();
    Assertions.assertEquals(2, norma.getLevel());
    norma.setStars(70);
    norma.check();
    Assertions.assertEquals(4, norma.getLevel());
    norma.addStars(500);
    norma.check();
    Assertions.assertEquals(6, norma.getLevel());
    norma.setLevel(-1);
    norma.check();
    Assertions.assertEquals(1, norma.getLevel());
  }

  @Test
  public void normaCheckTestWin() {
    norma.check();
    Assertions.assertEquals(1, norma.getLevel());
    norma.addWins(2);
    norma.check();
    Assertions.assertEquals(1, norma.getLevel());
    norma.setStars(10);
    norma.check();
    Assertions.assertEquals(3, norma.getLevel());
    norma.setWins(9);
    norma.check();
    Assertions.assertEquals(5, norma.getLevel());
    norma.addWins(500);
    norma.check();
    Assertions.assertEquals(6, norma.getLevel());
    norma.setLevel(-1);
    norma.check();
    Assertions.assertEquals(1, norma.getLevel());
  }

  @Test
  public void normaClearTest() {
    norma.clear();
    Assertions.assertEquals(2, norma.getLevel());
  }

}
