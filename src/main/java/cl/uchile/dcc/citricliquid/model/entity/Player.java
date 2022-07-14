package cl.uchile.dcc.citricliquid.model.entity;

/**
 * This class represents a player in the game 99.7% Citric Liquid.
 *
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater
 *     Muñoz</a>.
 * @version 1.1.222804
 * @since 1.0
 */
public class Player extends Entity {
  // ============== Attributes ==============
  private final Norma norma;

  // ============= constructors =============
  /**
   * Creates a new character as an entity.
   * @param name the character's name.
   * @param maxHp the maximum hit points of the character.
   * @param atk the base damage the character does.
   * @param def the base defense of the character.
   * @param evd the base evasion of the character.
   * @param hp the hp the character.
   * @param norma the norma of the character.
   */
  private Player(final String name, final int maxHp, final int atk, final int def,
                final int evd, final int hp, final Norma norma) {
    super(name, maxHp, atk, def, evd, hp);
    // The player starts with the basic norma
    this.norma = norma;
  }

  /**
   * Creates a new character.
   * @param name the character's name.
   * @param maxHp the initial (and max) hit points of the character.
   * @param atk the base damage the character does.
   * @param def the base defense of the character.
   * @param evd the base evasion of the character.
   */
  public Player(final String name, final int maxHp, final int atk, final int def,
                final int evd) {
    // The player spawns with maxHP
    this(name, maxHp, atk, def, evd, maxHp, new Norma());
  }

  // ============= Getters =============

  public Norma getNormaCpy() {
    return this.norma.copy();
  }

  /**
   * Returns this player's wins count.
   */
  public int getWins() { return this.norma.getWins(); }

  /**
   * Returns this player's star count.
   */
  public int getStars() { return this.norma.getStars();}

  /**
   * Returns the current norma level.
   */
  public int getNorma() {
    return norma.getLevel();
  }

  // ========= Methods =========
  /**
   * Increases this player's star count by an amount.
   */
  public void increaseStarsBy(final int amount) { this.norma.addStars(amount);  }

  /**
   * Increases this player's wins count by an amount.
   */
  public void increaseWinsBy(final int amount) { this.norma.addWins(amount); }

  /**
   * Reduces this player's star count by a given amount.
   */
  public void reduceStarsBy(final int amount) { this.norma.removeStars(amount); }

  /**
   * Performs a norma clear action; the {@code norma} counter increases in 1.
   *
   * <p>This method is probably outdated
   */
  //TODO: CHECK THE LOGIC BEHIND THE METHOD, NEEDS TO BE REPLACED, NORMA SHOULD BE THE ONE
  //     THAT IS RESPONSIBLE FOR THE NORMA CLEAR
  public void normaClear() { this.norma.clear(); }


  // ================= Obj Utilities ===============

  /**
   * Returns a copy of this character.
   */
  public Player copy() {
    return new Player(this.getName(), this.getMaxHp(), this.getAtk(),
            this.getDef(), this.getEvd(), this.getHp(), this.norma.copy());
  }

  // ================== Overrides ==================

  /**
   * Equals method for the player class.
   */
  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Player player)) {
      return false;
    }
    return super.equals(player) && this.norma.equals(player.getNormaCpy());
  }
}
