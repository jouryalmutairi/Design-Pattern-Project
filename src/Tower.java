import java.awt.Color;
public class Tower {

    private AttackBehavior attackBehavior;

    public Tower(AttackBehavior attackBehavior) {
        this.attackBehavior = attackBehavior;
    }
    public void attack(Enemy enemy) {
    attackBehavior.attack(enemy);
}

public int getRange() {
    return attackBehavior.getRange();
}

public Color getColor() {
    return attackBehavior.getColor();
}

public void setAttackBehavior(AttackBehavior attackBehavior) {
    this.attackBehavior = attackBehavior;
}
}