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
  private PathWay pathWay;

  /**
   * <b>CONSTRUCTOR</b> <br>
   * Constructor of the panel.
   *
   * @param type Type of the panel.
   */

  private Panel(PanelType type, Panel northPanel, Panel southPanel, Panel eastPanel, Panel westPanel, PathWay pathWay) {
    this.type       = type;
    this.northPanel = northPanel;
    this.southPanel = southPanel;
    this.eastPanel  = eastPanel;
    this.westPanel  = westPanel;
    this.pathWay    = pathWay;
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
         panel.pathWay);}

  /**
   * <b>GETTERS</b>
   */

  public PanelType getType()   { return type; }
  public PathWay getPathWay()  { return pathWay; }
  public Panel getNorthPanel() { return northPanel; }
  public Panel getSouthPanel() { return southPanel; }
  public Panel getEastPanel()  { return eastPanel; }
  public Panel getWestPanel()  { return westPanel; }

  /**
   * <b>SETTERS</b>
   */

  public void setType(PanelType type)         { this.type = type; }
  public void setPathWay(PathWay pathWay)     { this.pathWay = pathWay; }
  public void setNorthPanel(Panel northPanel) { this.northPanel = northPanel; }
  public void setSouthPanel(Panel southPanel) { this.southPanel = southPanel; }
  public void setEastPanel(Panel eastPanel)   { this.eastPanel = eastPanel; }
  public void setWestPanel(Panel westPanel)   { this.westPanel = westPanel; }

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
   * Get's the next panel in the direction
   */
  public Panel getNextPanel(PathWay pathWay) throws IllegalArgumentException {
    if (pathWay == null) throw new IllegalArgumentException("Panel has no pathway");
    return switch (pathWay) {
      case NORTH -> northPanel;
      case SOUTH -> southPanel;
      case EAST -> eastPanel;
      case WEST -> westPanel;
    };
  }

  /**
   * Adds a new adjacent panel to this one.
   *
   * @param way The path way to add.
   * @param panel the panel to be added.
   */
  public void addNextPanel(PathWay way, Panel panel) throws IllegalArgumentException {
    if (panel == null) throw new IllegalArgumentException("Panel cannot be null");
    switch (way) {
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
        throw new IllegalArgumentException("Unknown path way: " + way);
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