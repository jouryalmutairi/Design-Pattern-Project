import java.awt.Color;

public class StrongLaserAttack implements AttackBehavior {

    @Override
    public void attack(Enemy enemy) {
        enemy.loseHealth(10);
    }

    @Override
    public int getRange() {
        return 200;
    }

    @Override
    public Color getColor() {
        return new Color(51, 153, 255);
    }
}
