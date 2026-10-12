import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;

public class Screen extends JPanel implements Runnable {

    public Thread thread = new Thread(this);

    public static Image[] tileset_ground = new Image[100];
    public static Image[] tileset_air = new Image[100];
    public static Image[] tileset_res = new Image[100];
    public static Image[] tileset_mob = new Image[100];
    public static Image[] tileset_mobb = new Image[100];
    public static Image[] tileset_mobbb = new Image[100];

    public static int myWidth, myHeight;

    public static int coinage = 10;
    public static int health = 100;

    public static int killed = 0;
    public static int killsToWin = 0;
    public static int level = 1;
    public static int maxlevel = 3;

    public static int winTime = 2000;
    public static int winFrame = 0;

    public static boolean isFirst = true;
    public static boolean isDebug = false;
    public static boolean isWin = false;

    public static Point mse = new Point();

    public static Room room;
    public static Save save;
    public static Store store;

    // All enemy types are now stored in one array
    public static Enemy[] enemies = new Enemy[100];
private GameLevel currentLevel;
    public int spawnTime = 1600;
    public int spawnFrame = 0;

    public Screen(Frame frame) {

        frame.addMouseListener(new KeyHandel());
        frame.addMouseMotionListener(new KeyHandel());

        thread.start();
    }

    public static void hasWon() {

        if (killed == killsToWin) {

            isWin = true;
            killed = 0;
            coinage = 0;
        }
    }

    public void define() {

        room = new Room();
        save = new Save();
        store = new Store();

        currentLevel = new GameLevelBuilder()
        .setLevelNumber(level)
        .setEnemyType(level == 1 || level == 2 ? 1 : level == 3 ? 2 : 3)
        .setSpawnTime(level == 1 || level == 2 ? 1600 : level == 3 ? 1400 : 1200)
        .setMapPath("save/map" + level)
        .setHealth(10)
        .setCoins(10)
        .setKillsToWin(killsToWin)
        .build();

coinage = currentLevel.getCoins();
health = currentLevel.getHealth();
spawnTime = currentLevel.getSpawnTime();
        // Load ground tiles
        for (int i = 0; i < tileset_ground.length; i++) {

            tileset_ground[i] =
                    new ImageIcon("res/tileset_ground.png").getImage();

            tileset_ground[i] =
                    createImage(
                            new FilteredImageSource(
                                    tileset_ground[i].getSource(),
                                    new CropImageFilter(
                                            0,
                                            26 * i,
                                            26,
                                            26
                                    )
                            )
                    );
        }

        // Load air tiles
        for (int i = 0; i < tileset_air.length; i++) {

            tileset_air[i] =
                    new ImageIcon("res/tileset_air.png").getImage();

            tileset_air[i] =
                    createImage(
                            new FilteredImageSource(
                                    tileset_air[i].getSource(),
                                    new CropImageFilter(
                                            0,
                                            26 * i,
                                            26,
                                            26
                                    )
                            )
                    );
        }

        // Load resources
        tileset_res[0] =
                new ImageIcon("res/cell.png").getImage();

        tileset_res[1] =
                new ImageIcon("res/heart.png").getImage();

        tileset_res[2] =
                new ImageIcon("res/coin.png").getImage();

        // Load enemy images
        tileset_mob[0] =
                new ImageIcon("res/mob1.png").getImage();

        tileset_mobb[0] =
                new ImageIcon("res/mob2.png").getImage();

        tileset_mobbb[0] =
                new ImageIcon("res/mob3.png").getImage();

        // Load level map
       save.loadSave(new File(currentLevel.getMapPath()));

        /*
         * Factory Method
         * Screen chooses the appropriate factory,
         * while the factory creates the concrete enemy.
         */
        EnemyFactory factory;

       if (currentLevel.getEnemyType() == 1) {
    factory = new MobFactory();
}
else if (currentLevel.getEnemyType() == 2) {
    factory = new Mob2Factory();
}
else {
    factory = new Mob3Factory();
}

        // Create enemies through the factory
        for (int i = 0; i < enemies.length; i++) {

            enemies[i] = factory.createEnemy();
        }
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        if (isFirst) {

            myWidth = getWidth();
            myHeight = getHeight();

            define();

            isFirst = false;
        }

        g.setColor(
                new Color(70, 70, 70)
        );

        g.fillRect(
                0,
                0,
                getWidth(),
                getHeight()
        );

        room.draw(g);

        // One loop for all enemy types
        for (int i = 0; i < enemies.length; i++) {

            if (enemies[i].inGame) {

                enemies[i].draw(g);
            }
        }

        store.draw(g);

        // Game over
        if (health < 1) {

            g.setColor(
                    new Color(240, 20, 20)
            );

            g.fillRect(
                    0,
                    0,
                    myWidth,
                    myHeight
            );

            g.setColor(
                    new Color(225, 255, 255)
            );

            g.setFont(
                    new Font(
                            "Courier New",
                            Font.BOLD,
                            14
                    )
            );

            g.drawString(
                    "Game Over, Unlucky...:(",
                    10,
                    20
            );
        }

        // Win screen
        if (isWin) {

            g.setColor(
                    new Color(255, 255, 255)
            );

            g.fillRect(
                    0,
                    0,
                    getWidth(),
                    getHeight()
            );

            g.setColor(
                    new Color(0, 0, 0)
            );

            g.setFont(
                    new Font(
                            "Courier New",
                            Font.BOLD,
                            14
                    )
            );

            if (level > maxlevel) {

                g.drawString(
                        "You won the whole game! Please wait and the window will close...",
                        10,
                        20
                );

            } else {

                g.drawString(
                        "You won! Congratulations! Please wait for the next level...",
                        10,
                        20
                );
            }
        }
    }

    /*
     * One spawner for every Enemy type.
     */
    public void spawnEnemy() {

        if (spawnFrame >= spawnTime) {

            for (int i = 0; i < enemies.length; i++) {

                if (!enemies[i].inGame) {

                    enemies[i].spawnMob(
                            Value.mobMonster
                    );

                    break;
                }
            }

            spawnFrame = 0;

        } else {

            spawnFrame++;
        }
    }

    @Override
    public void run() {

        while (true) {

            if (!isFirst && health > 0 && !isWin) {

                room.physic();

                // One spawner instead of three
                spawnEnemy();

                // One physics loop for all enemies
                for (int i = 0; i < enemies.length; i++) {

                    if (enemies[i].inGame) {

                        enemies[i].physic();
                    }
                }

            } else {

                if (isWin) {

                    if (winFrame >= winTime) {

                        if (level > maxlevel) {

                            System.exit(0);

                        } else {

                            define();

                            isWin = false;
                        }

                        winFrame = 0;

                    } else {

                        winFrame++;
                    }
                }
            }

            repaint();

            try {

                Thread.sleep(1);

            } catch (Exception e) {

            }
        }
    }
}