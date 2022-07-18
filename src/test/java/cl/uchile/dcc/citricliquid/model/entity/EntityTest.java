package cl.uchile.dcc.citricliquid.model.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class EntityTest {
  private final static String ENTITY_NAME = "Ghost";
  private Entity ghost;

  /**
   * <b>SETUP</b> <br>
   */
  @BeforeEach
  public void setUp() {
    ghost = new Entity(ENTITY_NAME, 4, 1, -1, 2, 4);
  }

  /**
   * <b>TESTS</b> <br>
   * <i>Equals</i>
   */

  @Test
  public void testEquals() {
    var o = new Object();
    Assertions.assertNotEquals(ghost, o);
    Assertions.assertEquals(ghost, ghost);
    o = new Entity(ENTITY_NAME, 4, 1, -1, 2, 4);
    Assertions.assertEquals(o, ghost);
  }

  /**
   * <i>Constructor</i>
   */

  @Test
  public void copyConstructorCopy() {
    final var expectedGhost = new Entity(ghost);
    Assertions.assertEquals(expectedGhost, ghost);
  }

  @Test
  public void defaultConstructorTest() {
    final var expectedGhost = new Entity(ENTITY_NAME, 4, 1, -1, 2);
    Assertions.assertEquals(expectedGhost, ghost);
  }

  /**
   * <i>Getters, setters</i>
   */
  @Test
  public void nameTest() {
    Assertions.assertEquals(ENTITY_NAME, ghost.getName());
    ghost.setName("New name");
    Assertions.assertEquals("New name", ghost.getName());
  }
  @Test
  public void maxHpTest() {
    Assertions.assertEquals(4, ghost.getMaxHp());
    ghost.setMaxHp(5);
    Assertions.assertEquals(5, ghost.getMaxHp());
  }
  @Test
  public void hpTest() {
    Assertions.assertEquals(4, ghost.getHp());
    ghost.setHp(2);
    Assertions.assertEquals(2, ghost.getHp());
  }
  @Test
  public void atkTest() {
    Assertions.assertEquals(1, ghost.getAtk());
    ghost.setAtk(2);
    Assertions.assertEquals(2, ghost.getAtk());
  }
  @Test
  public void defTest() {
    Assertions.assertEquals(-1, ghost.getDef());
    ghost.setDef(2);
    Assertions.assertEquals(2, ghost.getDef());
  }
  @Test
  public void evdTest() {
    Assertions.assertEquals(2, ghost.getEvd());
    ghost.setEvd(3);
    Assertions.assertEquals(3, ghost.getEvd());
  }

  /**
   * <i>Methods</i>
   */
  @Test
  public void damageTest() {
    Assertions.assertTrue  (ghost.damage(2));
    Assertions.assertEquals(2, ghost.getHp());
    Assertions.assertFalse (ghost.damage(3));
    Assertions.assertEquals(0, ghost.getHp());
  }
  @Test
  public void healTest() {
    Assertions.assertTrue(ghost.heal(2));
    Assertions.assertEquals(4, ghost.getHp());
    ghost.setHp(0);
    Assertions.assertFalse(ghost.heal(1));
    Assertions.assertEquals(1, ghost.getHp());
    Assertions.assertTrue(ghost.heal(999));
    Assertions.assertEquals(4, ghost.getHp());
  }

  /**
   * <b>REPEATED TESTS</b> <br>
   */
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