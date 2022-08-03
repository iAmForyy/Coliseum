package com.iamforyydev.coliseum.manager;

import com.iamforyydev.coliseum.object.Game;
import com.iamforyydev.coliseum.object.runnable.GameCountdown;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Arrays;

import static com.iamforyydev.coliseum.utils.ColiseumUtils.*;

public class GameManager {

    public void addPlayer(Player player, Game game){
        if(game.containsPlayer(player.getName())){
            player.sendMessage(toLegacyColor("&cYou are already playing!"));
            return;
        }

        game.addPlayer(player.getName());
        player.teleport(game.getArenaSpawn());
        player.sendMessage(toLegacyColor("&eYou have entered the game!"));
    }

    public void removePlayer(Player player, Game game){
        if(!game.containsPlayer(player.getName())){
            player.sendMessage(toLegacyColor("&cYou are not in the game!"));
            return;
        }

        game.removePlayer(player.getName());
        player.sendMessage("You have exited the game!");
    }

    public void removePlayerFromGame(Player player, Game game){
        game.removePlayer(player.getName());
        player.sendMessage("&eYou have been eliminated, thanks for participating!");
    }

    public void startGame(Game game){
        game.setStarted(true);
        game.getGameCountdown().startCountdown();

        sendAnnounce(Arrays.asList(
                "",
                "&6&l    COLISEUM",
                "",
                "&fThe game has started, go fight!",
                "&fUse &e'/coliseum join' &fto enter",
                "",
                ""));
    }

    public void stopGame(Game game){
        game.setStarted(false);
        String winnerName = game.getWinner();
        Player playerWinner = Bukkit.getPlayer(winnerName);
        if(winnerName == null) return;
        playerWinner.sendMessage(ChatColor.YELLOW+"Congratulations! You have won the game.");

        if(game.getGameCountdown() == null){
            game.setGameCountdown(new GameCountdown(game));
        }
        game.getPlayers().clear();
    }


    public Location getSpawn(FileConfiguration configuration){
        String spawnString = configuration.getString("locations.spawn");
        Location spawnLocation = getLocationFromString(spawnString);
        if(spawnLocation == null || spawnLocation.getWorld() == null) return null;
        return spawnLocation;

    }
}
