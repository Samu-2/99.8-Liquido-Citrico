package cl.uchile.dcc.citricliquid.model.entity;

import cl.uchile.dcc.citricliquid.model.modules.Norma;
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

    /**
     * <b>SETUP</b> <br>
     */
    @BeforeEach
    public void setUp() {
        suguri = new Player(PLAYER_NAME, 4, 1, -1, 2);
    }
    /**
     * <b>TESTS</b> <br>
     * <i>Equals</i>
     */
    @Test
    public void testEquals() {
        var o = new Object();
        Assertions.assertNotEquals(suguri, o);
        Assertions.assertEquals(suguri, suguri);
        o = new Player(PLAYER_NAME, 4, 1, -1, 2);
        Assertions.assertEquals(o, suguri);
    }
    /**
     * <i>Constructor</i>
     */
    @Test
    public void constructorTest() {
        final var expectedSuguri = new Player(PLAYER_NAME, 4, 1, -1, 2);
        Assertions.assertEquals(expectedSuguri, suguri);
    }
    @Test
    public void constructorCopyTest() {
        final var expectedSuguri = new Player(suguri);
        Assertions.assertEquals(expectedSuguri, suguri);
    }
    /**
     * <i>Getters, setters</i>
     */
    @Test
    public void normaTest(){
        Assertions.assertEquals(new Norma(), suguri.getNorma());
        final Norma newNorma = new Norma();
        suguri.setNorma(newNorma);
        Assertions.assertEquals(newNorma, suguri.getNorma());
    }
}
