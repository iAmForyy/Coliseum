package com.iamforyydev.coliseum.commands;

import com.iamforyydev.coliseum.Coliseum;
import com.iamforyydev.coliseum.object.Game;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import sun.text.resources.no.CollationData_no;

import static com.iamforyydev.coliseum.utils.ColiseumUtils.getStringFromLocation;
import static com.iamforyydev.coliseum.utils.ColiseumUtils.toLegacyColor;

public class Commands implements CommandExecutor {

    private final Coliseum plugin = Coliseum.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;

        if(args.length == 1){
            player.sendMessage(toLegacyColor("&fWrong argument, usage &e'/coliseum help'"));
            return true;
        }

        Game game = plugin.getGame();
        switch (args[0].toLowerCase()){
            default:
                player.sendMessage(toLegacyColor("&fWrong argument, usage &e'/coliseum help'"));
                return true;
            case "start":
                plugin.getGameManager().startGame(game);
                return true;
            case "join":
                plugin.getGameManager().addPlayer(player, game);;
                return true;
            case "leave":
                plugin.getGameManager().removePlayer(player, game);
                return true;
            case "setspawn":
            case "setarena":
                FileConfiguration configuration = plugin.getConfig();
                if(args[0].equalsIgnoreCase("setspawn")){
                    configuration.set("locations.spawn", getStringFromLocation(player.getLocation()));
                    player.sendMessage(ChatColor.YELLOW+"The spawn has been established");
                }
                if(args[0].equalsIgnoreCase("setarena")){
                    configuration.set("locations.spawnArena", getStringFromLocation(player.getLocation()));
                    player.sendMessage(ChatColor.YELLOW+"The arena spawn has been established");
                }
                plugin.saveConfig();
                return true;

        }
    }
}
