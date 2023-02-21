package group7.levels;

import java.util.ArrayList;

public class LevelManager {
    private ArrayList<Level> levels;

    public LevelManager() {
        levels = new ArrayList<>();
        levels.add(new Level());
    }
}
