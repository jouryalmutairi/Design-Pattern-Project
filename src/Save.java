import java.io.*;
import java.util.*;

public class Save {

    public void loadSave(File loadPath) {

        try {
            Scanner loadScanner = new Scanner(loadPath);

            while (loadScanner.hasNext()) {

                // Read the old killsToWin value from the file
                // without using it, because GameLevelBuilder now controls it.
                loadScanner.nextInt();

                // Move to the next level after loading the current map
                if (Screen.level <= Screen.maxlevel) {
                    Screen.level += 1;
                }

                for (int y = 0; y < Screen.room.block.length; y++) {
                    for (int x = 0; x < Screen.room.block[0].length; x++) {
                        Screen.room.block[y][x].groundID = loadScanner.nextInt();
                    }
                }

                for (int y = 0; y < Screen.room.block.length; y++) {
                    for (int x = 0; x < Screen.room.block[0].length; x++) {
                        Screen.room.block[y][x].airID = loadScanner.nextInt();
                    }
                }
            }

            loadScanner.close();

        } catch (Exception e) {
        }
    }
}