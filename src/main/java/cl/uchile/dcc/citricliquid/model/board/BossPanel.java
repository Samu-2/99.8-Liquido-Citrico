package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.entity.Entity;
import cl.uchile.dcc.citricliquid.model.entity.Player;
import cl.uchile.dcc.citricliquid.model.entity.enemys.fb.BossFactory;

public class BossPanel extends Panel {
    BossFactory bossFactory;
    public BossPanel() {
        super();
        bossFactory = new BossFactory();
    }

    @Override
    Entity applyEffect(Player player) {
        return null;
    }

}

