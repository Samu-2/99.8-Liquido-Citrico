package cl.uchile.dcc.citricliquid.model.board;

public class PanelFactory {
    public static Panel createPanel(PanelType type) {
        return switch (type){
            case BONUS -> new BonusPanel();
            case BOSS  -> new BossPanel();
            case DROP -> new DropPanel();
            case ENCOUNTER -> new EncounterPanel();
            case HOME -> new HomePanel();
            case NEUTRAL -> new NeutralPanel();
        };
    }
}
