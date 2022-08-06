package mcskyscape.com.playerxpsystem;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerXPSystem extends JavaPlugin implements Listener {
    //initialize HashMap
    public HashMap<Player, Integer> map = new HashMap<>();
    //Setup event listener
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }
    //Apply HashMap to Player
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        map.put(e.getPlayer(), 0);
    }
    // When a player runs a command, increment Woodcutting and display it to player
    @EventHandler
    public void onCmd(PlayerCommandPreprocessEvent e) {
        int Woodcutting = map.get(e.getPlayer());
        map.replace(e.getPlayer(), Woodcutting + 1);
        e.getPlayer().sendMessage("Woodcutting = " + Woodcutting);
    }
}