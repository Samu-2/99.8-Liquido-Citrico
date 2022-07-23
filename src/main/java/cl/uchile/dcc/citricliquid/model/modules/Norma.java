package cl.uchile.dcc.citricliquid.model.modules;

/**
 * <b>Norma</b> <br>
 * Norma class, functions like a pocket of norma. Holds and manages everything related.
 */
public class Norma {
    /**
     * <b>ATTRIBUTES</b> <br>
     */
    private int level;
    private int wins;
    private int stars;
    private NormaGoal goal;

    /**
     * <b>CONSTRUCTORS</b> <br>
     * By default, the norma has level 1, wins 0, stars 0 and an objective.
     * @param level
     *    the level of the norma.
     * @param wins
     *    the number of wins of the norma.
     * @param stars
     *   the number of stars of the norma.
     *   @param goal
     *   the curremt objective of the norma.
     */
    private Norma(int level, int wins, int stars, NormaGoal goal) {
        this.level = level;
        this.wins  = wins;
        this.stars = stars;
        this.goal  = goal;
    }
    public Norma(Norma norma) { this(norma.level, norma.wins, norma.stars, norma.goal); }
    public Norma()            { this(1, 0, 0, NormaGoal.STARS); }
    /**
     * <b>GETTERS</b> <br>
     * Returns the asked attribute of the Norma.
     */
    public int getLevel()       { return level; }
    public int getWins()        { return wins; }
    public int getStars()       { return stars; }
    public NormaGoal getGoal()  { return goal; }

    /**
     * <b>SETTERS</b> <br>
     * Sets the asked attribute of the Norma.
     */
    public void setLevel(int level) { this.level = level;}
    public void setWins(int wins)   { this.wins = wins; }
    public void setStars(int stars) { this.stars = stars; }
    public void setGoal(NormaGoal goal) throws IllegalArgumentException {
        if (this.level == 1 && goal == NormaGoal.WINS) throw new IllegalArgumentException("The goal can't be WINS in level 1.");
        this.goal = goal;
    }

    /**
     * <b>METHODS</b> <br> <br>
     * <i>equals</i> <br>
     * It does the clear of the norma. that is, adding one to it's level
     */
    public void clear() {
        this.level += 1;
    }
    /**
     * <i>check</i> <br>
     * It does the check for the norma to see if it's done taking into account the current goal
     * @return It returns true if the norma is done, false otherwise.
     */
    public boolean check() throws IllegalArgumentException, IllegalStateException {
        // This is so it can do the normas in one go
        // But also doens't go up on to 6 without the
        // lvl 1 norma of only stars
        if (this.level == 1) return this.stars >= 10;
        return switch (this.goal) {
            case WINS -> switch (this.level) {
                case 2 -> this.wins >= 2;
                case 3 -> this.wins >= 5;
                case 4 -> this.wins >= 9;
                case 5 -> this.wins >= 14;
                default -> // Something terribly wrong happened.
                        throw new IllegalStateException("Norma level is out of bounds");
            };
            case STARS -> switch (this.level) {
                case 2 -> this.stars >= 30;
                case 3 -> this.stars >= 70;
                case 4 -> this.stars >= 120;
                case 5 -> this.stars >= 200;
                default -> // Something terribly wrong happened.
                        throw new IllegalStateException("Norma level is out of bounds");
            };
        };
    }

    /**
     * <i>addWins</i> <br>
     * @param quantity
     *   the quantity of wins to add to the norma.
     */
    public void addWins(int quantity) {
        if (quantity < 0) throw new IllegalArgumentException("Quantity must be positive: " + quantity);
        this.wins += quantity;
    }

    /**
     * <i>addStars</i> <br>
     * Adds a given amount of stars to this player.
     * @param quantity
     */
    public void addStars(int quantity) {
        if (quantity < 0) throw new IllegalArgumentException("Quantity must be positive: " + quantity);
        this.stars += quantity;
    }

    /**
     * Reduces the stars by the given quantity.
     *
     * <p>The stars count will must always be greater or equal to 0
     */
    public void subtractStars(int quantity) {
        if (quantity < 0) throw new IllegalArgumentException("Quantity must be positive: " + quantity);
        this.stars = Math.max(0, stars - quantity);
    }

    /**
     * <b>OVERRIDES</b> <br> <br>
     * <i>equals</i> <br>
     * @param obj
     *  the object to compare to.
     * @return
     *  true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Norma n)) return false;
        if (n == this) return true;
        return this.level == n.level
                && this.wins  == n.wins
                && this.stars == n.stars
                && this.goal  == n.goal;
    }
}