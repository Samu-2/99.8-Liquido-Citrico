package cl.uchile.dcc.citricliquid.model.modules;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NormaTest {
    private Norma norma;

    /**
     * <b>SETUP</b> <br>
     */
    @BeforeEach
    public void setUp() {
        this.norma = new Norma();
    }
    /**
     * <b>TESTS</b> <br>
     * <i>Equals</i>
     */
    @Test
    public void testEquals() {
        var o = new Object();
        Assertions.assertNotEquals(norma, o);
        o = new Norma();
        Assertions.assertEquals(norma, o);
    }

    /**
     * <i>Constructors</i>
     */
    @Test
    public void copyConstructorCopy() {
        final var expectedNorma = new Norma(norma);
        Assertions.assertEquals(expectedNorma, norma);
    }
    @Test
    public void defaultConstructorTest() {
        final var expectedNorma = new Norma();
        Assertions.assertEquals(expectedNorma, norma);
    }
    /**
     * <i>Getters, setters</i>
     */
    @Test
    public void levelTest() {
        Assertions.assertEquals(1, norma.getLevel());
        norma.setLevel(3);
        Assertions.assertEquals(3, norma.getLevel());
    }
    @Test
    public void winsTest() {
        Assertions.assertEquals(0, norma.getWins());
        norma.setWins(3);
        Assertions.assertEquals(3, norma.getWins());
    }
    @Test
    public void starsTest() {
        Assertions.assertEquals(0, norma.getStars());
        norma.setStars(3);
        Assertions.assertEquals(3, norma.getStars());
    }
    @Test
    public void goalTest() {
        Assertions.assertEquals(NormaGoal.STARS, norma.getGoal());
        Assertions.assertThrows(IllegalArgumentException.class, () -> norma.setGoal(NormaGoal.WINS));
        norma.setLevel(2);
        norma.setGoal(NormaGoal.WINS);
        Assertions.assertEquals(NormaGoal.WINS, norma.getGoal());
    }

    /**
     * <i>Methods</i>
     */
    @Test
    public void clearTest() {
        norma.clear();
        Assertions.assertEquals(2, norma.getLevel());
        norma.setLevel(3);
        norma.clear();
        Assertions.assertEquals(4, norma.getLevel());
    }
    @Test
    public void checkStarTest() {
        Assertions.assertFalse(norma.check());
        norma.addStars(10000);
        Assertions.assertTrue(norma.check());
        norma.clear();
        norma.setGoal(NormaGoal.WINS);
        Assertions.assertFalse(norma.check());
        norma.setGoal(NormaGoal.STARS);
        Assertions.assertTrue(norma.check());
        norma.clear();
        Assertions.assertTrue(norma.check());
        norma.clear();
        Assertions.assertTrue(norma.check());
        norma.clear();
        Assertions.assertTrue(norma.check());
        norma.clear();
        Assertions.assertThrows(IllegalStateException.class, () -> norma.check());
    }
    @Test
    public void checkWinTest() {
        Assertions.assertFalse(norma.check());
        norma.addWins(10000);
        norma.clear();
        Assertions.assertFalse(norma.check());
        norma.setGoal(NormaGoal.WINS);
        Assertions.assertTrue(norma.check());
        norma.clear();
        Assertions.assertTrue(norma.check());
        norma.clear();
        Assertions.assertTrue(norma.check());
        norma.clear();
        Assertions.assertTrue(norma.check());
        norma.clear();
        Assertions.assertThrows(IllegalStateException.class, () -> norma.check());
    }
    @Test
    public void addWinsTest() {
        norma.addWins(2);
        Assertions.assertEquals(2, norma.getWins());
        Assertions.assertThrows(IllegalArgumentException.class, () -> norma.addWins(-1));
    }
    @Test
    public void addStarsTest() {
        norma.addStars(2);
        Assertions.assertEquals(2, norma.getStars());
        Assertions.assertThrows(IllegalArgumentException.class, () -> norma.addStars(-1));
    }
    @Test
    public void subtractStarsTest() {
        norma.addStars(2);
        norma.subtractStars(1);
        Assertions.assertEquals(1, norma.getStars());
        norma.subtractStars(999);
        Assertions.assertEquals(0, norma.getStars());
        Assertions.assertThrows(IllegalArgumentException.class, () -> norma.subtractStars(-2));
    }
}
