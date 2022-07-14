package cl.uchile.dcc.citricliquid.model.entity;

public class WildUnit extends Entity {
  private Norma norma;

  public WildUnit(Player player) {
    super("Default Enemy", 3, -1, -1, -1);
    this.norma = new Norma();

  }
  private void defineFate (int random) {
    this.setHp(3);
    switch (random%3){
      case 0:
        this.setName("Chicken");
        this.setAtk(-1);
        this.setDef(-1);
        this.setEvd(1);
        break;
      case 1:
        this.setName("Robo Ball");
        this.setAtk(-1);
        this.setDef(1);
        this.setEvd(-1);
        break;
      case 2:
        this.setName("Seagull");
        this.setAtk(1);
        this.setDef(-1);
        this.setEvd(-1);
        break;
    }
  }

}
