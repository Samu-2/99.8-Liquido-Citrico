package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.entity.Entity;
import cl.uchile.dcc.citricliquid.model.entity.Player;
import cl.uchile.dcc.citricliquid.model.modules.Norma;

public class DropPanel extends Panel {
    public DropPanel() { super(); }

    public PanelType getType(){ return PanelType.DROP; }
    @Override
    Entity applyEffect(Player player) {
        Norma norma = player.getNorma();
        norma.subtractStars(player.roll()*norma.getLevel());
        return null;
    }
}
