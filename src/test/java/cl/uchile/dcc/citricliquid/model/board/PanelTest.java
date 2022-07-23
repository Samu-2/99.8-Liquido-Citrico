package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.entity.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

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
    private final static int HP = 3;
    private BonusPanel testBonusPanel;
    private HomePanel testHomePanel;
    private NeutralPanel testNeutralPanel;
    private DropPanel testDropPanel;
    private EncounterPanel testEncounterPanel;
    private BossPanel testBossPanel;

    private NeutralPanel panel1, panel2, panel3, panel4;
    private Player suguri;
    private long testSeed;

    /**
     * <b>SETUP</b> <br>
     */
    @BeforeEach
    public void setUp() {
        // Panels Types
        testBonusPanel      = new BonusPanel();
        testBossPanel       = new BossPanel();
        testDropPanel       = new DropPanel();
        testEncounterPanel  = new EncounterPanel();
        testHomePanel       = new HomePanel();
        testNeutralPanel    = new NeutralPanel();

        // Simple 4 panels
        panel1 = new NeutralPanel();
        panel2 = new NeutralPanel();
        panel3 = new NeutralPanel();
        panel4 = new NeutralPanel();

        // Player
        testSeed            = new Random().nextLong();
        suguri              = new Player(PLAYER_NAME, BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD, HP);
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

    /**
     * <i>Getters, setters</i>
     */
    @Test
    public void typeTest(){
        assertEquals(PanelType.BONUS,     testBonusPanel.getType());
        assertEquals(PanelType.BOSS,      testBossPanel.getType());
        assertEquals(PanelType.DROP,      testDropPanel.getType());
        assertEquals(PanelType.ENCOUNTER, testEncounterPanel.getType());
        assertEquals(PanelType.HOME,      testHomePanel.getType());
        assertEquals(PanelType.NEUTRAL,   testNeutralPanel.getType());
    }

    @Test
    public void connectionsTest(){
        // simple circuit
        panel1.setConnection(panel2, Direction.WEST);
        panel2.setConnection(panel3, Direction.SOUTH);
        panel3.setConnection(panel4, Direction.EAST);
        panel4.setConnection(panel1, Direction.NORTH);

        // 1 Way direction test
        assertEquals(panel2, panel1.getPanel(Direction.WEST));
        assertEquals(panel3, panel2.getPanel(Direction.SOUTH));
        assertEquals(panel4, panel3.getPanel(Direction.EAST));
        assertEquals(panel1, panel4.getPanel(Direction.NORTH));

        // 2 way direction test
        assertEquals(panel4, panel1.getPanel(Direction.SOUTH));
        assertEquals(panel3, panel4.getPanel(Direction.WEST));
        assertEquals(panel2, panel3.getPanel(Direction.NORTH));
        assertEquals(panel1, panel2.getPanel(Direction.EAST));

        // Now auto test 1 Way
        assertEquals(panel1, panel4.getPanelAuto(panel3));
        assertEquals(panel2, panel1.getPanelAuto(panel4));
        assertEquals(panel3, panel2.getPanelAuto(panel1));
        assertEquals(panel4, panel3.getPanelAuto(panel2));

        // Now auto test 2 Way
        assertEquals(panel4, panel1.getPanelAuto(panel2));
        assertEquals(panel3, panel4.getPanelAuto(panel1));
        assertEquals(panel2, panel3.getPanelAuto(panel4));
        assertEquals(panel1, panel2.getPanelAuto(panel3));
    }

    @Test
    public void neighborsTest(){
        // simple circuit
        assertEquals(0, panel1.getNeighbors());
        panel1.setConnection(panel2, Direction.WEST);
        assertEquals(1, panel1.getNeighbors());
        panel1.setConnection(panel3, Direction.SOUTH);
        assertEquals(2, panel1.getNeighbors());
    }

    /**
     * <i>Methods</i>
     */

    @Test
    public void playerInteractionTest(){
        assertEquals(0, testHomePanel.getPlayers().size());
        testHomePanel.addPlayer(suguri);
        assertEquals(1, testHomePanel.getPlayers().size());
        assertEquals(suguri, testHomePanel.getPlayers().get(0));
        assertEquals(suguri.getMaxHp(), suguri.getHp());
        assertEquals(StopCode.CLEAR, testHomePanel.stopCode(suguri));
        assertNull(testHomePanel.getHomeOwner());
        testHomePanel.setHomeOwner(suguri);
        assertEquals(suguri, testHomePanel.getHomeOwner());
        assertEquals(StopCode.HOME, testHomePanel.stopCode(suguri));
        testHomePanel.removePlayer(suguri);
        assertEquals(0, testHomePanel.getPlayers().size());
    }
    @Test
    public void homePanelTest() {
        assertEquals(HP, suguri.getHp());
        testHomePanel.activatedBy(suguri);
        assertEquals(suguri.getMaxHp(), suguri.getHp());
        testHomePanel.activatedBy(suguri);
        assertEquals(suguri.getMaxHp(), suguri.getHp());
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