public class MobFactory extends EnemyFactory {

    @Override
    public Enemy createEnemy() {
        return new Mob();
    }
}