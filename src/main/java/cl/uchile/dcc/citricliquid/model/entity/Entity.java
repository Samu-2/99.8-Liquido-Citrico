package cl.uchile.dcc.citricliquid.model.entity;

import java.util.Random;

/**
 * <b>Entity</b> <br>
 * Entity Class, this is the base class for all the entities in the game.
 */
public class Entity {
  /**
   * <b>ATTRIBUTES</b> <br>
   */
  private final Random random;
  private String name;
  private int maxHp;
  private int atk;
  private int def;
  private int evd;
  private int hp;
  /**
   * <b>CONSTRUCTORS</b> <br>
   * By default, the entity has maxHP, atk, def, evd and hp and a random random object.
   * @param name
   *     (opt) the Entity's name.
   * @param maxHp
   *    the Entity's maxHP.
   * @param hp
   *     the initial (and max) hit points of the Entity.
   * @param atk
   *     the base damage the Entity does.
   * @param def
   *     the base defense of the Entity.
   * @param evd
   *     the base evasion of the Entity.
   * @param random
   *    the random number generator used by the Entity.
   */
  public Entity(String name, int maxHp, int atk, int def, int evd, int hp, Random random) {
    this.name   = name;
    this.maxHp  = maxHp;
    this.hp     = hp;
    this.atk    = atk;
    this.def    = def;
    this.evd    = evd;
    this.random = random;
  }
  public Entity(String name, int maxHp, int atk, int def, int evd, int hp) {
    this(name, maxHp, atk, def, evd, hp, new Random());
  }
  public Entity(String name, int maxHp, int atk, int def, int evd) {
    this(name, maxHp, atk, def, evd, maxHp, new Random());
  }
  public Entity(Entity entity) {
    this(entity.name, entity.maxHp, entity.atk, entity.def, entity.evd, entity.hp, entity.random);
  }
  /**
   * <b>GETTERS</b> <br>
   * Returns the asked attribute of the Entity.
   */
  public String getName() { return this.name; }
  public int getMaxHp()   { return maxHp; }
  public int getHp()      { return hp; }
  public int getAtk()     { return atk; }
  public int getDef()     { return def; }
  public int getEvd()     { return evd; }
  /**
   * <b>SETTERS</b> <br>
   * Sets the asked attribute of the Entity.
   */
  public void setName(String name) { this.name = name; }
  public void setMaxHp(int maxHp)  { this.maxHp = maxHp; }
  public void setHp(int hp)        { this.hp = Math.min(Math.max(hp, 0), this.maxHp); }
  public void setAtk(int atk)      { this.atk = atk; }
  public void setDef(int def)      { this.def = def; }
  public void setEvd(int evd)      { this.evd = evd; }
  public void setSeed(long seed)   { random.setSeed(seed); }
   /**
    * <b>METHODS</b> <br> <br>
    *
    * <i>Roll</i> <br>
    * Sims a 6 sided die and returns the result.
    */
  public int roll() {
    return random.nextInt(6) + 1;
  }
  /**
   * <i>damage</i> <br>
   * Damages the entity by the given amount.
   * @param dmg
   *    the amount of damage to be done.
   * @return true if the entity is still alive, false otherwise.
   */
  public boolean damage(int dmg){
    if (dmg < 0) throw new IllegalArgumentException("Damage must be positive: " + dmg);
    this.hp = Math.max(hp - dmg, 0);
    return hp > 0;
  }
  /**
   * <i>heal</i> <br>
   * Heals the entity by the given amount.
   * @param heal
   * @return true if the entity has reached maxHp, false otherwise.
   */
  public boolean heal(int heal){
    if (heal < 0) throw new IllegalArgumentException("Heal must be positive: " + heal);
    this.hp = Math.min(hp + heal, maxHp);
    return hp == maxHp;
  }
  /**
   * <b>Overrides</b> <br><br>
   * <i>equals</i>
   * @param o the object to compare.
   * @return true if the objects are equal, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Entity e)) return false;
    if (e == this) return true;
    return this.name.equals(e.name)
        && this.maxHp == e.maxHp
        && this.atk   == e.atk
        && this.def   == e.def
        && this.evd   == e.evd
        && this.hp    == e.hp;
  }
}
