
import java.io.File;

public class GameFacade {

    private Room room;
    private Save save;
    private Store store;
    private GameLevel currentLevel;

    public GameFacade() {
        // Objects will be initialized when setupGame() is called.
    }

    // Initialize the game components and level
    public void setupGame(int levelNumber) {

        // 1. Create game components
        room = new Room();
        save = new Save();

        Screen.room = room;

        store = new Store();
        Screen.save = save;
        Screen.store = store;

        // 2. Configure the level using Builder
        currentLevel = new GameLevelBuilder()
                .setLevelNumber(levelNumber)
                .setEnemyType(
                        levelNumber == 1 ? 1 :
                        levelNumber == 2 ? 2 : 3
                )
                .setSpawnTime(
                        levelNumber == 1 ? 1600 :
                        levelNumber == 2 ? 1400 : 1200
                )
                .setMapPath("save/map" + levelNumber)
                .setHealth(10)
                .setCoins(10)
                .setKillsToWin(
                        levelNumber == 1 ? 10 :
                        levelNumber == 2 ? 20 : 30
                )
                .build();

        // 3. Apply level settings
        Screen.coinage = currentLevel.getCoins();
        Screen.health = currentLevel.getHealth();
        Screen.killsToWin = currentLevel.getKillsToWin();

        // 4. Load the level map
        save.loadSave(new File(currentLevel.getMapPath()));

        // 5. Create enemies using Simple Factory
        EnemyFactory factory = new EnemyFactory();

        for (int i = 0; i < Screen.enemies.length; i++) {
            Screen.enemies[i] =
                    factory.createEnemy(currentLevel.getEnemyType());
        }
    }

    // Return the configured spawn time
    public int getSpawnTime() {
        return currentLevel.getSpawnTime();
    }

    // Access game components
    public Room getRoom() {
        return room;
    }

    public Store getStore() {
        return store;
    }

}

