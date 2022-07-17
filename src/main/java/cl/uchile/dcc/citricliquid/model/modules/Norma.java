package cl.uchile.dcc.citricliquid.model.modules;

/**
* This abstract class it's used as a base for all the entities.
* that have any norma related mechanics, it functions like a pocket
* of normas.
*/

public class Norma {
  // ========= ATTRIBUTES ==========
  private int level;
  private int wins;
  private int stars;

  // ========= CONSTRUCTORS =========
  private Norma(int level, int wins, int stars) {
    this.level = level;
    this.wins = wins;
    this.stars = stars;
  }
  public Norma() {
    this(1, 0, 0);
  }

  // ========= GETTERS =========
  public int getLevel() { return level; }

  public int getWins() { return wins; }

  public int getStars() { return stars; }

  // ================ SETTERS ==============================

  public void setLevel(int level) {
    this.level = level;
  }

  public void setWins(int wins) { this.wins = wins; }

  public void setStars(int stars) {
    this.stars = stars;
  }

  // ================ METHODS ==============================

  // TODO: MAKE THIS METHOD PRIVATE, IT SHOULD BE ONLY RESPONSABILITY OF NORMA CLASS DO THE
  //       NORMA CLEAR.
  private void clear() { this.level += 1; }

  // Debug clear so panels tests can run
  public void debugClear() { this.level += 1; }

  public void check() {
    // This is so it can do the normas in one go
    // But also doens't go up on to 6 without the
    // lvl 1 norma of only stars
    if (this.level == 1 && this.stars >= 10) {
      this.clear();
    }
    switch (this.level) {
      case 2:
        if (this.stars >= 30 || this.wins >= 2) {
          this.clear();
        }
      case 3:
        if (this.stars >= 70 || this.wins >= 5) {
          this.clear();
        }
      case 4:
        if (this.stars >= 120 || this.wins >= 9) {
          this.clear();
        }
      case 5:
        if (this.stars >= 200 || this.wins >= 14) {
          this.clear();
        }
      case 6:
        break;
      default:
        // Something terribly wrong happened. just restarting the level
        this.level = 1;
        break;
    }
  }
  public void addWins(int quantity) {
    this.wins += quantity;
  }
  public void addStars(int quantity) {
    this.stars += quantity;
  }

  /**
   * Reduces the stars by the given quantity.
   *
   * <p>The stars count will must always be greater or equal to 0
   */
  public void removeStars(int quantity) { this.stars = Math.max(0, stars - quantity); }

  // ================ Obj Utilities ==============================
  public Norma copy() { return new Norma(level, wins, stars); }

  // ==================== Overrides ==============================
  @Override
  public boolean equals(Object o) {
    if (o == null) {
      return false;
    }
    if (!(o instanceof Norma)) {
      return false;
    }
    if (this == o) {
      return true;
    }
    Norma norma = (Norma) o;
    return level == norma.level &&
           wins == norma.wins &&
           stars == norma.stars;
  }
}