package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.entity.Entity;
import cl.uchile.dcc.citricliquid.model.entity.Player;

public class NeutralPanel extends Panel {
    public NeutralPanel() { super(); }
    @Override
    Entity applyEffect(Player player) { return null; }
}
