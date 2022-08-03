package com.iamforyydev.coliseum;

import com.iamforyydev.coliseum.commands.Commands;
import com.iamforyydev.coliseum.listener.PlayerDeathListener;
import com.iamforyydev.coliseum.loader.GameLoader;
import com.iamforyydev.coliseum.manager.GameManager;
import com.iamforyydev.coliseum.object.Game;
import org.bukkit.plugin.java.JavaPlugin;

public final class Coliseum extends JavaPlugin {

    private static Coliseum instance;
    private Game game;
    private GameManager gameManager;

    @Override
    public void onEnable() {
        instance = this;

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        this.game = new Game();
        this.gameManager = new GameManager();
        new GameLoader(game, getConfig());

        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
        getCommand("colisemun").setExecutor(new Commands());

    }

    public static Coliseum getInstance() {
        return instance;
    }

    public Game getGame() {
        return game;
    }

    public GameManager getGameManager() {
        return gameManager;
    }
}
