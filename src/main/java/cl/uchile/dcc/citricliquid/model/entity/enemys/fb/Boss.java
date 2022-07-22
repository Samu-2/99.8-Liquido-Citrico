package cl.uchile.dcc.citricliquid.model.entity.enemys.fb;

import cl.uchile.dcc.citricliquid.model.entity.Entity;
import cl.uchile.dcc.citricliquid.model.entity.Player;

public class Boss extends Entity {
    private static Boss instance = null;

    private Boss(String name, int maxHp, int atk, int def, int evd) {
        super(name, maxHp, atk, def, evd);
    }

    public static Boss getInstance(Player player){
        if (instance == null) {
            switch (player.roll()%3){
                case 0:
                    return new Boss("StoreManager", 8, 3, 2, -1);
                case 1:
                    return new Boss("ShifuRobot", 7, 3, 3, -2);
                case 2:
                    return new Boss("FlyingCastle", 10, 2, 1, -3);
            }
        }
        return instance;
    }
}