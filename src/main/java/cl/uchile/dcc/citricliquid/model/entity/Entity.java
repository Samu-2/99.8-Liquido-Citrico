package cl.uchile.dcc.citricliquid.model.entity;

import java.util.Random;

public class Entity {
  // ================== ATTRIBUTES ================================
  private final Random random;
  private String name;
  private int maxHp;
  private int atk;
  private int def;
  private int evd;
  private int hp;

  /**
   * Creates a new Entity.
   *
   * @param name
   *     the Entity's name.
   * @param hp
   *     the initial (and max) hit points of the Entity.
   * @param atk
   *     the base damage the Entity does.
   * @param def
   *     the base defense of the Entity.
   * @param evd
   *     the base evasion of the Entity.
   */

  // ================= CONSTRUCTORS ===========================
  private Entity( String name,  int maxHp,  int atk,  int def,
                 int evd,  int hp, Random random) {
    this.name   = name;
    this.maxHp  = maxHp;
    this.hp     = hp;
    this.atk    = atk;
    this.def    = def;
    this.evd    = evd;
    this.random = random;
  }

  public Entity( String name, int maxHp, int atk, int def, int evd) {
    this(name, maxHp, atk, def, evd, maxHp, new Random());
  }

  public Entity(final String name, final int maxHp, final int atk, final int def,
                final int evd, int hp) {
    this(name, maxHp, atk, def, evd, hp, new Random());
  }

  // ================ GETTERS =======================
  /**
   * Returns the character's name.
   */
  public String getName() { return this.name; }
  /**
   * Returns the character's max hit points.
   */
  public int getMaxHp() { return maxHp; }

  /**
   * Returns the current hit points of the character.
   */
  public int getHp() { return hp; }

  /**
   * Returns the current character's attack points.
   */
  public int getAtk() { return atk; }

  /**
   * Returns the current character's defense points.
   */
  public int getDef() { return def; }

  /**
   * Returns the current character's evasion points.
   */
  public int getEvd() { return evd; }

  // ================ SETTERS ==============================
  /**
   * Sets the current Entity's hit points.
   *
   * <p>The Entity's hit points have a constraint to always be between 0 and maxHP, both
   * inclusive.
   */
  public void setHp(int quantity) { this.hp = Math.max(Math.min(quantity, maxHp), 0); }
  public void setName (String name) { this.name = name; }
  public void setAtk (int atk) {this.atk = atk; }
  public void setDef (int def) {this.def = def; }
  public void setEvd (int evd) {this.evd = evd; }

  // ================ METHODS ==============================
  /**
   * Returns a uniformly distributed random value in [1, 6].
   */
  public int roll() {
    return random.nextInt(6) + 1;
  }

  /**
   * Set's the seed for this player's random number generator.
   *
   * <p>The random number generator is used for taking non-deterministic decisions, this method is
   * declared to avoid non-deterministic behaviour while testing the code.
   */
  public void setSeed(final long seed) {
    random.setSeed(seed);
  }

  // ================ Obj Utilities ==============================
  public Entity copy() {
    return new Entity(name, maxHp, atk, def, evd, hp, this.random);
  }
  public boolean equals(Object o) {
    if (o == null) {
      return false;
    }
    if (!(o instanceof Entity e)) {
      return false;
    }

    return  this.name.equals(e.name)
            && this.maxHp == e.maxHp
            && this.atk   == e.atk
            && this.def   == e.def
            && this.evd   == e.evd
            && this.hp    == e.hp;
  }
}
