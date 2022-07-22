package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.entity.Player;

import org.jetbrains.annotations.NotNull;

/**
 * Class that represents a panel in the board of the game.
 *
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater Muñoz</a>.
 * @version 1.1.222804
 * @since 1.0
 */
public class Panel {

    /**
     * <b>ATTRIBUTES</b> <br>
     */
    private PanelType type;
    private Panel northPanel, southPanel, eastPanel, westPanel;
    private int neighbors;

    private Player[] playersStk;

    private final int MAX_PLAYERS = 8;

    /**
     * <b>CONSTRUCTOR</b> <br>
     * Constructor of the panel.
     *
     * @param type Type of the panel.
     */

    private Panel(PanelType type, Panel northPanel, Panel southPanel, Panel eastPanel, Panel westPanel, int neighbors) {
        this.type       = type;
        this.northPanel = northPanel;
        this.southPanel = southPanel;
        this.eastPanel  = eastPanel;
        this.westPanel  = westPanel;
        this.neighbors  = neighbors;
        this.players    = new Player[MAX_PLAYERS];
    }
    public Panel(final PanelType type) {
        this(type,
                null,
                null,
                null,
                null,
                0);
    }
    public Panel(Panel panel) {
        this(panel.type,
                panel.northPanel,
                panel.southPanel,
                panel.westPanel,
                panel.eastPanel,
                panel.neighbors);}

    /**
     * <b>GETTERS</b>
     */

    public PanelType getType()  { return type; }
    public int getNeighbors()   { return neighbors; }
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
        if (neighbors == 1) return panel;
        // if there's 2 neighbors, check the others, return the one that's not the panel passed as argument
        if (neighbors == 2) {
            // panels can be null, so check if they are null and return the other one that's not null
            // and not the panel passed as argument
            if (northPanel != null && northPanel != panel) return northPanel;
            if (southPanel != null && southPanel != panel) return southPanel;
            if (eastPanel  != null && eastPanel  != panel) return eastPanel;
            if (westPanel  != null && westPanel  != panel) return westPanel;
        }
        throw new IllegalArgumentException("Unknown panel to choose with: " + neighbors + " neighbors");
    }

    public StopCode getStopCode(Player player) {
        if (neighbors >= 3) return StopCode.INTERSECTION;
        if ()
    }

    /**
     * <b>SETTERS</b>
     */
    public void setType(PanelType type) { this.type = type; }
    public void setConnection(Direction direction, Panel panel) throws IllegalArgumentException {
        if (panel == null) throw new IllegalArgumentException("Panel cannot be null");
        neighbors++;
        switch (direction) {
            case NORTH -> northPanel = panel;
            case SOUTH -> southPanel = panel;
            case EAST -> eastPanel = panel;
            case WEST -> westPanel = panel;
            default -> throw new IllegalArgumentException("Unknown path way: " + direction);
        }
        panel.setConnection(direction.getOppositeDirection(), this);
    }

    /**
     * <b>METHODS</b> <br>
     *
     * Restores a player's HP in 1.
     */
    private static void applyHealTo(final @NotNull Player player) {
        player.heal(1);
    }

    /**
     * Reduces the player's star count by the D6 roll multiplied by the player's norma level.
     */
    private static void applyDropTo(final @NotNull Player player) {
        player.getNorma().subtractStars(player.roll() * player.getNorma().getLevel());
    }

    /**
     * Reduces the player's star count by the D6 roll multiplied by the maximum between the player's
     * norma level and three.
     */
    private static void applyBonusTo(final @NotNull Player player) {
        player.getNorma().addStars(player.roll() * Math.min(player.getNorma().getLevel(), 3));
    }


    /**
     * Executes the appropriate action to the player according to this panel's type.
     */
    public void activatedBy(final Player player) throws IllegalStateException {
        switch (type) {
            case BONUS    -> applyBonusTo(player);
            case DROP     -> applyDropTo(player);
            case HOME     -> applyHealTo(player);
            case NEUTRAL  -> {}
            default       -> throw new IllegalStateException("Unknown panel type: " + type);
        }
    }
}