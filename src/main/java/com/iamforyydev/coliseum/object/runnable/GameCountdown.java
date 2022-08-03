package com.iamforyydev.coliseum.object.runnable;

import com.iamforyydev.coliseum.Coliseum;
import com.iamforyydev.coliseum.object.Game;
import org.bukkit.scheduler.BukkitRunnable;

import static com.iamforyydev.coliseum.utils.ColiseumUtils.sendAnnounce;

public class GameCountdown extends BukkitRunnable {

    private final Coliseum plugin = Coliseum.getInstance();
    private final Game game;
    private int countdown;
    public GameCountdown(Game game){
        this.game = game;
        this.countdown = 60;
    }

    public void startCountdown(){
        this.runTaskTimer(plugin, 0L, 20L);
    }

    @Override
    public void run() {
        countdown--;

        if(getCountdown() == 0){
            plugin.getGameManager().startGame(getGame());
            cancel();
            return;
        }

        if(getCountdown() < 10 || getCountdown() == 20 || getCountdown() == 30){
            sendAnnounce("&eThe coliseum event is about to start, it starts in &6"+getCountdown());
        }
    }


    public Game getGame() {
        return game;
    }

    public int getCountdown() {
        return countdown;
    }
}
