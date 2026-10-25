import java.awt.Color;

public class BasicLaserAttack implements AttackBehavior {

    @Override
    public void attack(Enemy enemy) {
        enemy.loseHealth(2);
    }

    @Override
    public int getRange() {
        return 130;
    }

    @Override
    public Color getColor() {
        return Color.YELLOW;
    }
}

