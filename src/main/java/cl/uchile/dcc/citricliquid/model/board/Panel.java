package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.entity.Entity;
import cl.uchile.dcc.citricliquid.model.entity.Player;
import javafx.scene.paint.Stop;

import java.util.*;

/**
 * Class that represents a panel in the board of the game.
 *
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater Muñoz</a>.
 * @version 1.1.222804
 * @since 1.0
 */
public abstract class Panel {

    /**
     * <b>ATTRIBUTES</b> <br>
     */

    private Panel northPanel, southPanel, eastPanel, westPanel;
    private int neighbors;
    private List<Player> players;

    private List<Panel> panels;
    private final int MAX_PLAYERS = 8;

    /**
     * <b>CONSTRUCTOR</b> <br>
     * Constructor of the panel.
     *
     */

    private Panel(Panel northPanel, Panel southPanel, Panel eastPanel, Panel westPanel, int neighbors) {
        this.northPanel = northPanel;
        this.southPanel = southPanel;
        this.eastPanel  = eastPanel;
        this.westPanel  = westPanel;
        this.neighbors  = neighbors;
        this.players    = new ArrayList<>();
    }
    public Panel() {
        this(null, null, null, null, 0);
    }


    /**
     * <b>GETTERS</b>
     */

    public List<Panel> getPanels(){
        return panels;
    }
    public Panel getPanel(Direction direction) throws IllegalArgumentException {
        if (direction == null) throw new IllegalArgumentException("Panel has no pathway");
        return switch (direction) {
            case NORTH -> northPanel;
            case SOUTH -> southPanel;
            case EAST  -> eastPanel;
            case WEST  -> westPanel;
        };
    }
    public Panel getPanelAuto(Panel panel) throws IllegalArgumentException {
        // check if there's one neighbor, if so return the same panel
        if (neighbors == 1 && panel != this) return this;
        // if there's 2 neighbors, check the others, return the one that's not the panel passed as argument
        if ((neighbors == 2 && panel != this) || neighbors == 1) {
            // panels can be null, so check if they are null and return the other one that's not null
            // and not the panel passed as argument
            if (northPanel != null && northPanel != panel) return northPanel;
            if (southPanel != null && southPanel != panel) return southPanel;
            if (eastPanel  != null && eastPanel  != panel) return eastPanel;
            if (westPanel  != null && westPanel  != panel) return westPanel;
        }
        throw new IllegalArgumentException("Unknown panel to choose with: " + neighbors + " neighbors provided with" + panel);
    }

    public int getNeighbors()   { return neighbors; }
    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    /**
     * <b>SETTERS</b>
     */

    public void setConnection(Panel panel, Direction direction) throws IllegalArgumentException {
        if (panel == null) throw new IllegalArgumentException("Panel cannot be null");
        neighbors++;
        switch (direction) {
            case NORTH -> northPanel = panel;
            case SOUTH -> southPanel = panel;
            case EAST -> eastPanel = panel;
            case WEST -> westPanel = panel;
            default -> throw new IllegalArgumentException("Unknown path way: " + direction);
        }
        panel.neighbors++;
        switch (direction.getOppositeDirection()) {
            case NORTH -> panel.northPanel = this;
            case SOUTH -> panel.southPanel = this;
            case EAST -> panel.eastPanel = this;
            case WEST -> panel.westPanel = this;
            default -> throw new IllegalArgumentException("Unknown path way: " + direction);
        }
        panels.add(panel);
        panel.panels.add(this);
    }

    /**
     * <b>METHODS</b> <br> <br>
     *
     * Adds a player in the panel
     */

    public Entity addPlayer(Player player) {
        if (players.size() >= MAX_PLAYERS) throw new IllegalArgumentException("Panel is full");
        players.add(player);
        player.setPanel(this);
        return applyEffect(player);
    }

    public void putPlayer(Player player) {
        if (players.size() >= MAX_PLAYERS) throw new IllegalArgumentException("Panel is full");
        players.add(player);
        player.setPanel(this);
    }

    /**
     * Checks whether player may stop at the panel
     */

    public StopCode stopCode(Player player) {
        if (players.size() >= 2) return StopCode.PLAYER;
        if (neighbors >= 3) return StopCode.INTERSECTION;
        return StopCode.CLEAR;
    }


    /**
     * Removes a player from the panel
     */
    public void removePlayer(Player player) {
        players.remove(player);
        player.setPanel(null);
    }

    public Entity activatedBy(Player player){
        return applyEffect(player);
    }

    abstract Entity applyEffect(Player player);

    public abstract StopCode stopCode();

    /**
     * Executes the appropriate action to the player according to this panel's type.
     */
}