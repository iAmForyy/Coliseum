package com.iamforyydev.coliseum.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;

import java.util.List;

public class ColiseumUtils {
    public static String toLegacyColor(String message){
        return message == null ? "" : ChatColor.translateAlternateColorCodes('&', message);
    }

    public static void sendAnnounce(List<String> list){
        Bukkit.getOnlinePlayers().forEach(player ->
                list.forEach(s -> player.sendMessage(toLegacyColor(s))));
    }

    public static void sendAnnounce(String message){
        Bukkit.getOnlinePlayers().forEach(player ->
                player.sendMessage(toLegacyColor(message)));
    }


    public static String getStringFromLocation(Location location){
        return location.getWorld().getName() + ";" +
                location.getX() + ";" +
                location.getY() + ";" +
                location.getZ() + ";" +
                location.getYaw() + ";" +
                location.getPitch();
    }

    public static Location getLocationFromString(String location){
        String[] split = location.split(";");
        if(split.length < 5 || split[0] == null) return null;
        return new Location(Bukkit.getWorld(split[0]),
                Double.parseDouble(split[1]),
                Double.parseDouble(split[2]),
                Double.parseDouble(split[3]),
                Float.parseFloat(split[4]),
                Float.parseFloat(split[5]));
    }



}
