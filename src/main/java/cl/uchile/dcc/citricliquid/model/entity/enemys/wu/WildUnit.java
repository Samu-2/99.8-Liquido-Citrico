package cl.uchile.dcc.citricliquid.model.entity.enemys.wu;

import cl.uchile.dcc.citricliquid.model.entity.Entity;

import java.util.Random;

public class WildUnit extends Entity {
    public WildUnit(String name, int maxHp, int atk, int def, int evd) {
        super(name, maxHp, atk, def, evd);
    }
}