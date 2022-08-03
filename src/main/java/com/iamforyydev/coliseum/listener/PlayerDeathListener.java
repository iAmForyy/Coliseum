package com.iamforyydev.coliseum.listener;

import com.iamforyydev.coliseum.Coliseum;
import com.iamforyydev.coliseum.object.Game;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerDeathListener implements Listener {

    private final Coliseum plugin = Coliseum.getInstance();

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e){
        Game game = plugin.getGame();
        if(!game.isStarted()) return;
        if(!game.containsPlayer(plugin.getName())) return;

        Player player = e.getPlayer();
        FileConfiguration config = plugin.getConfig();
        player.teleport(plugin.getGameManager().getSpawn(config));

        plugin.getGameManager().removePlayerFromGame(player, game);
        if(game.getPlayers().size() == 1){
            plugin.getGameManager().stopGame(game);
        }
    }

}
