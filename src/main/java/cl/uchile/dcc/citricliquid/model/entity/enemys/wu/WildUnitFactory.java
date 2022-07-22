package cl.uchile.dcc.citricliquid.model.entity.enemys.wu;
import cl.uchile.dcc.citricliquid.model.entity.Player;


public class WildUnitFactory {
    private WildUnit enemy = new WildUnit("", 0, 0, 0, 0);
    public WildUnitFactory() {}
    public WildUnit getEnemy(Player player) {
        if (!enemy.isAlive()) {
            switch (player.roll() % 3) {
                case 0 -> enemy = new Seagull();
                case 1 -> enemy = new RoboBall();
                case 2 -> enemy = new Chicken();
            }
        }
        return enemy;
    }
}
