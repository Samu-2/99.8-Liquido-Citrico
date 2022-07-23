package cl.uchile.dcc.citricliquid.model.entity;

import cl.uchile.dcc.citricliquid.model.board.Panel;
import cl.uchile.dcc.citricliquid.model.modules.Norma;

/**
 * This class represents a player in the game 99.7% Citric Liquid.
 *
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater
 *     Muñoz</a>.
 * @version 1.1.222804
 * @since 1.0
 */
public class Player extends Entity {
  /**
   * <b>ATTRIBUTES</b> <br>
   */
  private Norma norma;
  private Panel panel;

  /**
   * <b>CONSTRUCTORS</b> <br>
   * By default, the player has maxHP, atk, def, evd and hp, a random random object and the default norma.
   * @param name the character's name.
   * @param maxHp the maximum hit points of the character.
   * @param atk the base damage the character does.
   * @param def the base defense of the character.
   * @param evd the base evasion of the character.
   * @param hp the hp the character.
   * @param norma the norma of the character.
   */
  private Player(String name, int maxHp, int atk, int def, int evd, int hp, Norma norma) {
    super(name, maxHp, atk, def, evd, hp);
    this.norma = norma;
  }
  public Player(String name, int maxHp, int atk, int def, int evd) {
    this(name, maxHp, atk, def, evd, maxHp, new Norma());
  }

  public Player(String name, int maxHp, int atk, int def, int evd, int hp) {
    this(name, maxHp, atk, def, evd, hp, new Norma());
  }
  public Player(Player player) {
    super(player);
    this.norma = new Norma(player.norma);
  }

  public Player(String name) {
    this(name, 4, 1, -1, 2);
  }

  /**
   * <b>SETTERS</b> <br>
   */
  public void setPanel(Panel panel) {
    this.panel = panel;
  }

  /**
   * <b>GETTERS</b> <br>
   * Returns the asked attribute of the Player.
   */
  public Norma getNorma() { return this.norma;}
  /**
  * <b>SETTERS</b> <br>
  * Sets the asked attribute of the Player.
  */
  public void setNorma(Norma norma) { this.norma = norma; }

  /**
   * <b>OVERRIDES</b> <br> <br>
   * <i>equals</i>
   * @param obj the object to compare with.
   * @return true if the objects are equal, false otherwise.
   */
  @Override
  public boolean equals(final Object obj) {
    if (!(obj instanceof Player p)) return false;
    if (p == this) return true;
    return super.equals(p)
        && this.norma.equals(p.norma);
  }

  public Panel getPanel() {
    return panel;
  }
}