package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.entity.Entity;
import cl.uchile.dcc.citricliquid.model.entity.Player;
import cl.uchile.dcc.citricliquid.model.modules.Norma;

public class BonusPanel extends Panel {
    public BonusPanel() { super(); }

    public PanelType getType(){ return PanelType.BONUS; }
    @Override
    Entity applyEffect(Player player) {
        Norma norma = player.getNorma();
        int roll = player.roll();
        norma.addStars(roll * Math.min(3, norma.getLevel()));;
        return null;
    }
}
