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
    private PanelShape shape;

    /**
     * <b>CONSTRUCTOR</b> <br>
     * Constructor of the panel.
     *
     * @param type Type of the panel.
     */

    private Panel(PanelType type, Panel northPanel, Panel southPanel, Panel eastPanel, Panel westPanel, PanelShape shape) {
        this.type       = type;
        this.northPanel = northPanel;
        this.southPanel = southPanel;
        this.eastPanel  = eastPanel;
        this.westPanel  = westPanel;
        this.shape      = shape;
    }
    public Panel(final PanelType type) {
        this(type,
                null,
                null,
                null,
                null,
                null);
    }
    public Panel(Panel panel) {
        this(panel.type,
                panel.northPanel,
                panel.southPanel,
                panel.westPanel,
                panel.eastPanel,
                panel.shape);}

    /**
     * <b>GETTERS</b>
     */

    public PanelType getType()   { return type; }
    public PanelShape getPanelShape()  { return shape; }
    public Panel getNorthPanel() { return northPanel; }
    public Panel getSouthPanel() { return southPanel; }
    public Panel getEastPanel()  { return eastPanel; }
    public Panel getWestPanel()  { return westPanel; }

    /**
     * <b>SETTERS</b>
     */

    public void setType(PanelType type)          { this.type = type; }
    private void setPanelShape(PanelShape shape) { this.shape = shape; }
    private void setNorthPanel(Panel northPanel) {
        if (this.northPanel != null) throw new IllegalArgumentException("North panel already has been set.");
        this.northPanel = northPanel;
        this.updateShape();
    }
    private void setSouthPanel(Panel southPanel) {
        if (this.southPanel != null) throw new IllegalArgumentException("South panel already has been set.");
        this.southPanel = southPanel;
        this.updateShape();
    }
    private void setEastPanel(Panel eastPanel) {
        if (this.eastPanel != null) throw new IllegalArgumentException("East panel already has been set.");
        this.eastPanel = eastPanel;
        this.updateShape();
    }
    private void setWestPanel(Panel westPanel) {
        if (this.westPanel != null) throw new IllegalArgumentException("West panel already has been set.");
        this.westPanel = westPanel;
        this.updateShape();
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
     * Get's the next panel in the direction asked
     */
    public Panel getNextPanelInDirection(Direction direction) throws IllegalArgumentException {
        if (direction == null) throw new IllegalArgumentException("Panel has no pathway");
        return switch (direction) {
            case NORTH -> northPanel;
            case SOUTH -> southPanel;
            case EAST  -> eastPanel;
            case WEST  -> westPanel;
        };
    }

    public Panel autoGetNextPanel(Direction direction) throws IllegalArgumentException {
        if (shape == null || shape == PanelShape.INTERSECTION || shape == PanelShape.DEAD_END || shape == PanelShape.SINGLE)
            throw new IllegalArgumentException("Unknown panel to choose from: " + shape);
        if (shape == PanelShape.CORNER) {
        }
        return getNextPanelInDirection(direction);
    }

    /**
     * Adds a new adjacent panel to this one.
     *
     * @param direction The path way to add.
     * @param panel the panel to be added.
     */
    public void addPanel(Direction direction, Panel panel) throws IllegalArgumentException {
        if (panel == null) throw new IllegalArgumentException("Panel cannot be null");
        switch (direction) {
            case NORTH:
                northPanel = panel;
                break;
            case SOUTH:
                southPanel = panel;
                break;
            case EAST:
                eastPanel  = panel;
                break;
            case WEST:
                westPanel  = panel;
                break;
            default:
                throw new IllegalArgumentException("Unknown path way: " + direction);
        }
    }

    public void updateShape(){
        int near = 0;
        Direction lastPanelDirection = null;
        if (northPanel != null) {
            lastPanelDirection = Direction.NORTH;
            near++;
        }
        if (southPanel != null) {
            lastPanelDirection = Direction.SOUTH;
            near++;
        }
        if (eastPanel  != null) {
            lastPanelDirection = Direction.EAST;
            near++;
        }
        if (westPanel  != null) {
            lastPanelDirection = Direction.WEST;
            near++;
        }
        switch (near) {
            case 0:
                shape = PanelShape.SINGLE;
                break;
            case 1:
                shape = PanelShape.DEAD_END;
                break;
            case 2:
                if (getNextPanelInDirection(lastPanelDirection.getOppositeDirection()) != null)
                    shape = PanelShape.ALLEY;
                else
                    shape = PanelShape.CORNER;
                break;
            default:
                shape = PanelShape.INTERSECTION;
                break;
        }
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
            default       -> { throw new IllegalStateException("Unknown panel type: " + type); }
        }
    }
}