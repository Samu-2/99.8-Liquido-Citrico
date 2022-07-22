package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HomePanel extends Panel {
    private Player homeOwner = null;

    public HomePanel(Player player) {
        super();
        setHomeOwner(player);
    }

    public HomePanel() {
        super();
    }

    public void setHomeOwner(@NotNull Player player) throws IllegalArgumentException {
        if (homeOwner != null) throw new IllegalStateException("Home owner already set");
        homeOwner = player;
    }

    public Player getHomeOwner() {
        return homeOwner;
    }
}
