package cl.uchile.dcc.citricliquid.model.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class EntityTest {
  private final static String ENTITY_NAME = "Ghost";
  private Entity ghost;

  @BeforeEach
  public void setUp() {
    ghost = new Entity(ENTITY_NAME, 4, 1, -1, 2, 4);
  }

  @Test
  public void constructorTest() {
    final var expectedGhost = new Entity(ENTITY_NAME, 4, 1, -1, 2, 4);
    Assertions.assertEquals(expectedGhost, ghost);
  }

  @Test
  public void testEquals() {
    final var o = new Object();
    Assertions.assertNotEquals(ghost, o);
    Assertions.assertEquals(ghost, ghost);
    final var expectedGhost = new Entity(ENTITY_NAME, 4, 1, -1, 2, 4);
    Assertions.assertEquals(expectedGhost, ghost);
  }

  @Test
  public void hitPointsTest() {
    Assertions.assertEquals(ghost.getMaxHp(), ghost.getHp());
    ghost.setHp(2);
    Assertions.assertEquals(2, ghost.getHp());
    ghost.setHp(-1);
    Assertions.assertEquals(0, ghost.getHp());
    ghost.setHp(5);
    Assertions.assertEquals(4, ghost.getHp());
  }

  @Test
  public void copyTest() {
    final var expectedGhost = new Entity(ENTITY_NAME, 4, 1, -1, 2);
    final var actualGhost = new Entity(ghost);
    // Checks that the copied player have the same parameters as the original
    Assertions.assertTrue(ghost.equals(expectedGhost));
    // Checks that the copied player doesn't reference the same object
    Assertions.assertNotSame(expectedGhost, actualGhost);
  }

  @RepeatedTest(100)
  public void hitPointsConsistencyTest() {
    final long testSeed = new Random().nextLong();
    // We're gonna try and set random hit points in [-maxHP * 2, maxHP * 2]
    final int testHP = new Random(testSeed).nextInt(4 * ghost.getMaxHp() + 1)
            - 2 * ghost.getMaxHp();
    ghost.setHp(testHP);
    Assertions.assertTrue(0 <= ghost.getHp()
                    && ghost.getHp() <= ghost.getMaxHp(),
            ghost.getHp() + "is not a valid HP value"
                    + System.lineSeparator() + "Test failed with random seed: "
                    + testSeed);
  }

  @RepeatedTest(100)
  public void rollConsistencyTest() {
    final long testSeed = new Random().nextLong();
    ghost.setSeed(testSeed);
    final int roll = ghost.roll();
    Assertions.assertTrue(roll >= 1 && roll <= 6,
            roll + "is not in [1, 6]" + System.lineSeparator()
                    + "Test failed with random seed: " + testSeed);
  }
}
