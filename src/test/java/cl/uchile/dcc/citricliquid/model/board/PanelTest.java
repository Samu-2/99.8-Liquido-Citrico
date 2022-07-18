package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.entity.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater M.</a>.
 * @version 1.0.6-rc.1
 * @since 1.0
 */
class PanelTest {
  private final static String PLAYER_NAME = "Suguri";
  private final static int BASE_HP = 4;
  private final static int BASE_ATK = 1;
  private final static int BASE_DEF = -1;
  private final static int BASE_EVD = 2;
  private Panel testHomePanel;
  private Panel testNeutralPanel;
  private Panel testBonusPanel;
  private Panel testDropPanel;
  private Panel testEncounterPanel;
  private Panel testBossPanel;
  private Player suguri;
  private long testSeed;

  /**
   * <b>SETUP</b> <br>
   */
  @BeforeEach
  public void setUp() {
    // Panels
    testBonusPanel      = new Panel(PanelType.BONUS);
    testBossPanel       = new Panel(PanelType.BOSS);
    testDropPanel       = new Panel(PanelType.DROP);
    testEncounterPanel  = new Panel(PanelType.ENCOUNTER);
    testHomePanel       = new Panel(PanelType.HOME);
    testNeutralPanel    = new Panel(PanelType.NEUTRAL);
    // Player
    testSeed            = new Random().nextLong();
    suguri              = new Player(PLAYER_NAME, BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);
  }
  /**
   * <b>TESTS</b> <br>
   * <i>Constructors</i>
   */
  @Test
  public void constructorTest() {
    assertEquals(PanelType.BONUS,     testBonusPanel.getType());
    assertEquals(PanelType.BOSS,      testBossPanel.getType());
    assertEquals(PanelType.DROP,      testDropPanel.getType());
    assertEquals(PanelType.ENCOUNTER, testEncounterPanel.getType());
    assertEquals(PanelType.HOME,      testHomePanel.getType());
    assertEquals(PanelType.NEUTRAL,   testNeutralPanel.getType());
  }

  @Test
  public void nextPanelTest() {
  }

  @Test
  public void homePanelTest() {
    assertEquals(suguri.getMaxHp(), suguri.getHp());
    testHomePanel.activatedBy(suguri);
    assertEquals(suguri.getMaxHp(), suguri.getHp());

    suguri.setHp(1);
    testHomePanel.activatedBy(suguri);
    assertEquals(2, suguri.getHp());
  }

  @Test
  public void neutralPanelTest() {
    final var expectedSuguri = new Player(suguri);
    testNeutralPanel.activatedBy(suguri);
    assertEquals(expectedSuguri, suguri);
  }

  // region : Consistency tests
  @RepeatedTest(100)
  public void bonusPanelConsistencyTest() {
    int expectedStars = 0;
    assertEquals(expectedStars, suguri.getNorma().getStars());
    final var testRandom = new Random(testSeed);
    suguri.setSeed(testSeed);
    for (int normaLvl = 1; normaLvl <= 6; normaLvl++) {
      final int roll = testRandom.nextInt(6) + 1;
      testBonusPanel.activatedBy(suguri);
      expectedStars += roll * Math.min(3, normaLvl);
      assertEquals(expectedStars, suguri.getNorma().getStars(), "Test failed with seed: " + testSeed);
      suguri.getNorma().clear();
    }
  }

  @RepeatedTest(100)
  public void dropPanelConsistencyTest() {
    int expectedStars = 30;
    suguri.getNorma().addStars(30);
    assertEquals(expectedStars, suguri.getNorma().getStars());
    final var testRandom = new Random(testSeed);
    suguri.setSeed(testSeed);
    for (int normaLvl = 1; normaLvl <= 6; normaLvl++) {
      final int roll = testRandom.nextInt(6) + 1;
      testDropPanel.activatedBy(suguri);
      expectedStars = Math.max(expectedStars - roll * normaLvl, 0);
      assertEquals(expectedStars, suguri.getNorma().getStars(),
                   "Test failed with seed: " + testSeed);
      suguri.getNorma().clear();
    }
  }
  // endregion
}