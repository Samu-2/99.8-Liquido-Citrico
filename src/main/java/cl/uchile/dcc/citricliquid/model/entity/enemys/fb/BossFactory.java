package cl.uchile.dcc.citricliquid.model.entity.enemys.fb;

import cl.uchile.dcc.citricliquid.model.entity.Entity;
import cl.uchile.dcc.citricliquid.model.entity.Player;
import cl.uchile.dcc.citricliquid.model.entity.enemys.wu.WildUnitFactory;
import cl.uchile.dcc.citricliquid.model.modules.Controller;

public class BossFactory {
    private WildUnitFactory wuFactory;

    public BossFactory() {wuFactory = new WildUnitFactory();}
    public Entity getEnemy(Player player) {
        Controller controller = Controller.getInstance();
        if (controller.getChapter() < 4 || !Boss.getInstance(player).isAlive()) {
            return wuFactory.getEnemy(player);
        }
        return Boss.getInstance(player);
    }
}
