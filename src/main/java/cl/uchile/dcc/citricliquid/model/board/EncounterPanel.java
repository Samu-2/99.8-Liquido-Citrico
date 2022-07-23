package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.entity.Entity;
import cl.uchile.dcc.citricliquid.model.entity.Player;
import cl.uchile.dcc.citricliquid.model.entity.enemys.wu.WildUnitFactory;

public class EncounterPanel extends Panel {

    WildUnitFactory wuFactory;
    public EncounterPanel() {
        super();
        wuFactory = new WildUnitFactory();
    }

    public PanelType getType(){ return PanelType.ENCOUNTER; }
    @Override
    Entity applyEffect(Player player){
        return (Entity) wuFactory.getEnemy(player);
    }
}
