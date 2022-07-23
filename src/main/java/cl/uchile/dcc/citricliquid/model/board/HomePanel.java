package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.entity.Entity;
import cl.uchile.dcc.citricliquid.model.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HomePanel extends Panel {
    private Player homeOwner = null;
    public HomePanel(Player player) {
        super();
        setHomeOwner(player);
    }

    public PanelType getType() {
        return PanelType.HOME;
    }

    public HomePanel() {
        super();
    }
    public void setHomeOwner(@NotNull Player player) throws IllegalArgumentException {
        if (homeOwner != null) throw new IllegalStateException("Home owner already set");
        homeOwner = player;
    }

    @Override
    public StopCode stopCode(Player player) {
        if (this.getPlayers().size() >= 2) return StopCode.PLAYER;
        if (homeOwner == player) return StopCode.HOME;
        if (this.getNeighbors() >= 3) return StopCode.INTERSECTION;
        return StopCode.CLEAR;
    }
    public Player getHomeOwner() {
        return homeOwner;
    }

    @Override
    Entity applyEffect(Player player) {
        player.heal(1);
        player.getNorma().check();
        return null;
    }
}
