public class GameLevel {

    private int levelNumber;
    private int enemyType;
    private int spawnTime;
    private String mapPath;
    private int health;
    private int coins;
    private int killsToWin;


    public GameLevel() {

}

public void setLevelNumber(int levelNumber) {
    this.levelNumber = levelNumber;
}

public void setEnemyType(int enemyType) {
    this.enemyType = enemyType;
}

public void setSpawnTime(int spawnTime) {
    this.spawnTime = spawnTime;
}

public void setMapPath(String mapPath) {
    this.mapPath = mapPath;
}

public void setHealth(int health) {
    this.health = health;
}

public void setCoins(int coins) {
    this.coins = coins;
}

public void setKillsToWin(int killsToWin) {
    this.killsToWin = killsToWin;
}
public int getLevelNumber() {
    return levelNumber;
}

public int getEnemyType() {
    return enemyType;
}

public int getSpawnTime() {
    return spawnTime;
}

public String getMapPath() {
    return mapPath;
}

public int getHealth() {
    return health;
}

public int getCoins() {
    return coins;
}

public int getKillsToWin() {
    return killsToWin;
}



}