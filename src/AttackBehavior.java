import java.awt.Color;

public interface AttackBehavior {

    void attack(Enemy enemy);

    int getRange();

    Color getColor();
}
