package cl.uchile.dcc.citricliquid.model.modules;

import cl.uchile.dcc.citricliquid.model.entity.Player;

import java.util.*;

/**
 * <b>Controller</b> <br>
 * It's an interface to control the flow of the game.
 */
public class Controller {
    /**
     * <b>ATRIBUTTES</b> <br>
     */
    private static Controller instance = null;
    private Player turnOwner;
    private int chapter;
    private List<Player> players;
    /**
     * <b>CONSTRUCTOR</b> <br>
     * Constructor of the Controller.
     */
    private Controller() {
        this.turnOwner = null;
        this.chapter = 0;
        this.players = new ArrayList<>();
    }
    public static Controller getInstance() {
        if (instance == null) instance = new Controller();
        return instance;
    }

    public int getChapter() { return chapter; }

}
