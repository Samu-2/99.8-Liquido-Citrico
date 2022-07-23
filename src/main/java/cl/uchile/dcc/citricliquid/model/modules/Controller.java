package cl.uchile.dcc.citricliquid.model.modules;

import cl.uchile.dcc.citricliquid.model.board.*;
import cl.uchile.dcc.citricliquid.model.entity.Entity;
import cl.uchile.dcc.citricliquid.model.entity.Player;

import java.io.CharArrayReader;
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
    private int iTurnOwner = 0;
    private int chapter;
    private List<Player> players;

    private List<Panel> panels;

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

    /**
     * <b>GETTERS</b> <br>
     */

    public int getChapter() { return chapter; }

    /**
     * <b>SET UP METHODS</b> <br>
     */

    public void createPlayer(String name) {
        Player player = new Player(name);
        players.add(player);
    }

    public void createPanel(PanelType type, Panel northPanel, Panel southPanel, Panel eastPanel, Panel westPanel) {
        Panel panel;
        switch (type) {
            case BONUS      -> panel = new BonusPanel();
            case BOSS       -> panel = new BossPanel();
            case DROP       -> panel = new DropPanel();
            case ENCOUNTER  -> panel = new EncounterPanel();
            case HOME       -> panel = new HomePanel();
            default         -> panel = new NeutralPanel();
        }
        putPanel(panel, northPanel, southPanel, eastPanel, westPanel);
    }

    public void putPanel(Panel panel, Panel northPanel, Panel southPanel, Panel eastPanel, Panel westPanel) {
        panels.add(panel);
        panel.setConnection(northPanel, Direction.NORTH);
        panel.setConnection(southPanel, Direction.SOUTH);
        panel.setConnection(eastPanel, Direction.EAST);
        panel.setConnection(westPanel, Direction.WEST);
    }

    public void putPlayer(Player player, Panel panel) {
        panel.putPlayer(player);
    }

    public void updateChapter() {
        for (Player player : players) {
            if (player.getNorma().getLevel() > chapter) chapter = player.getNorma().getLevel();
        }
    }

    public boolean hasAnybodyWon() {
        updateChapter();
        return chapter == 6;
    }

    public Player getWiner() {
        if (!hasAnybodyWon()) throw new IllegalStateException("No winner yet");
        for (Player player : players) {
            if (player.getNorma().getLevel() == 6) return player;
        }
        return null;
    }

    public void Play() {
        while (!hasAnybodyWon()) {
            for (Player player : players) {
                playTurn(player);
            }
        }
    }

    public void playTurn(Player player){
        if (!player.isAlive()){
            // KO! recovery phase
            int roll = player.roll();
            // Failed recovery
            if (roll < 6 - chapter) return;
        }
        int stars  = chapter >= 5 ? 2 : 1 ;
        player.getNorma().addStars(stars);
        int move = player.roll();
        while (move > 0) {
            playerMove(player, getDirection(player), move);
        }
        switch (player.getPanel().stopCode()) {
            case PLAYER -> {
                // TODO: FIGHT CHOOSE
            }
            case HOME -> {
                // TODO: END TURN
            }
        }
    }

    private Direction getDirection(Player player) {
        List<Panel> list = player.getPanel().getPanels();
        // TODO: Here should ask for input
        Panel panel = null;
        Direction direction = null;
        while(panel == null){
            switch (player.roll()%4){
                case 0  -> {
                    direction = Direction.NORTH;
                    panel = player.getPanel().getPanel(direction);
                }
                case 1  -> {
                    direction = Direction.SOUTH;
                    panel = player.getPanel().getPanel(direction);
                }
                case 2  -> {
                    direction = Direction.EAST;
                    panel = player.getPanel().getPanel(direction);
                }
                default -> {
                    direction = Direction.WEST;
                    panel = player.getPanel().getPanel(direction);
                }
            }
        }
        return direction;
    }

    public int playerMove(Player player, Direction direction, int steps) {
        Panel previousPanel = player.getPanel();
        Panel panel = previousPanel.getPanel(direction);
        if (panel == null || steps == 0) return steps;
        previousPanel.removePlayer(player);
        while (steps > 0 && panel.getNeighbors() == 2) {
            Panel nextPanel = panel.getPanelAuto(previousPanel);
            previousPanel = panel;
            panel = nextPanel;
        }
        if (steps == 0) panel.addPlayer(player);
        else panel.putPlayer(player);
        return steps;
    }
}
