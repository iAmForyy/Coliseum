package com.iamforyydev.coliseum.object;

import com.iamforyydev.coliseum.object.runnable.GameCountdown;
import org.bukkit.Location;

import java.util.HashSet;
import java.util.Set;

public class Game {

    private final Set<String> players;
    private Location arenaSpawn;
    private boolean started;
    private GameCountdown gameCountdown;


    public Game(){
        this.players = new HashSet<>();
        this.started = false;
        this.gameCountdown = new GameCountdown(this);
    }

    public Set<String> getPlayers() {
        return players;
    }

    public void addPlayer(String playerName){
        this.getPlayers().add(playerName);
    }

    public void removePlayer(String playerName){
        this.getPlayers().remove(playerName);
    }

    public boolean containsPlayer(String playerName){
        return getPlayers().contains(playerName);
    }

    public Location getArenaSpawn() {
        return arenaSpawn;
    }

    public void setArenaSpawn(Location arenaSpawn) {
        this.arenaSpawn = arenaSpawn;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public GameCountdown getGameCountdown() {
        return gameCountdown;
    }

    public void setGameCountdown(GameCountdown gameCountdown) {
        this.gameCountdown = gameCountdown;
    }

    public String getWinner(){
        if(getPlayers().size() > 1) return null;
        for(String s : getPlayers()){
            return s;
        }

        return "";
    }

}
