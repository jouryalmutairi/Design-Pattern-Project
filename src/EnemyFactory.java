public class EnemyFactory {

    public Enemy createEnemy(int enemyType) {

        if (enemyType == 1) {
            return new Mob();
        }
        else if (enemyType == 2) {
            return new Mob2();
        }
        else if (enemyType == 3) {
            return new Mob3();
        }
        else {
            throw new IllegalArgumentException("Invalid enemy type");
        }
    }
}