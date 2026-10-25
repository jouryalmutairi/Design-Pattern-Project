import java.awt.Color;

public class MediumLaserAttack implements AttackBehavior {

    @Override
    public void attack(Enemy enemy) {
        enemy.loseHealth(4);
    }

    @Override
    public int getRange() {
        return 130;
    }

    @Override
    public Color getColor() {
        return new Color(0, 153, 0);
    }
}
