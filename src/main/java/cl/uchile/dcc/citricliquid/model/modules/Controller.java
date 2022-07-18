package cl.uchile.dcc.citricliquid.model.modules;

/**
 * <b>Controller</b> <br>
 * It's an interface to control the flow of the game.
 */
public class Controller {
    /**
     * <b>ATRIBUTTES</b> <br>
     */
    private static Controller instance = null;

    /**
     * <b>CONSTRUCTOR</b> <br>
     * Constructor of the Controller.
     */
    private Controller() {}
    public static Controller getInstance() {
        if (instance == null) instance = new Controller();
        return instance;
    }
}
