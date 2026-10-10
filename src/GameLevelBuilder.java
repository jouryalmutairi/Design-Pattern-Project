
public class GameLevelBuilder {

    private GameLevel gameLevel;

    public GameLevelBuilder() {
        gameLevel = new GameLevel();
    }

    public GameLevelBuilder setLevelNumber(int levelNumber) {
        gameLevel.setLevelNumber(levelNumber);
        return this;
    }

    public GameLevelBuilder setEnemyType(int enemyType) {
        gameLevel.setEnemyType(enemyType);
        return this;
    }

    public GameLevelBuilder setSpawnTime(int spawnTime) {
        gameLevel.setSpawnTime(spawnTime);
        return this;
    }

    public GameLevelBuilder setMapPath(String mapPath) {
        gameLevel.setMapPath(mapPath);
        return this;
    }

    public GameLevelBuilder setHealth(int health) {
        gameLevel.setHealth(health);
        return this;
    }

    public GameLevelBuilder setCoins(int coins) {
        gameLevel.setCoins(coins);
        return this;
    }

    public GameLevelBuilder setKillsToWin(int killsToWin) {
        gameLevel.setKillsToWin(killsToWin);
        return this;
    }

    public GameLevel build() {
        return gameLevel;
    }

}
