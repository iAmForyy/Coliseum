package com.iamforyydev.coliseum.loader;

import com.iamforyydev.coliseum.object.Game;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import static com.iamforyydev.coliseum.utils.ColiseumUtils.getLocationFromString;

public class GameLoader {

    private final Game game;
    private final FileConfiguration configuration;
    public GameLoader(Game game, FileConfiguration configuration){
        this.game = game;
        this.configuration = configuration;
        loadGame();
    }

    private void loadGame(){
        String spawnArenaString = getConfiguration().getString("locations.spawnArena");
        Location location = getLocationFromString(spawnArenaString);
        if(location == null) return;
        getGame().setArenaSpawn(location);
    }


    public Game getGame() {
        return game;
    }

    public FileConfiguration getConfiguration() {
        return configuration;
    }
}
