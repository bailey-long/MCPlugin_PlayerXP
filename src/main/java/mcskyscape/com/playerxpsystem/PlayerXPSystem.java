package mcskyscape.com.playerxpsystem;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerXPSystem extends JavaPlugin implements Listener {
    public HashMap<Player, Integer> map = new HashMap<>();

    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        map.put(e.getPlayer(), 0);
    }

    @EventHandler
    public void onCmd(PlayerCommandPreprocessEvent e) {
        int Woodcutting = map.get(e.getPlayer());
        map.replace(e.getPlayer(), Woodcutting + 1);
        e.getPlayer().sendMessage("Woodcutting = " + Woodcutting);
    }
}