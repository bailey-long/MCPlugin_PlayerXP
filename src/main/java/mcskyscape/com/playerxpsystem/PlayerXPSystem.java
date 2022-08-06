package mcskyscape.com.playerxpsystem;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.bukkit.plugin.java.JavaPlugin;

public class PlayerXPSystem extends JavaPlugin implements CommandExecutor {
    @Override
    public void onEnable(){
        this.getCommand("levels").setExecutor(new Commandseelevels());
    }
    @Override
    public void onDisable(){
        
    }

    public class Commandseelevels implements CommandExecutor {
        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if (label.equals("levels")){
                Player player = (Player) sender;
                if (sender instanceof Player) {
                    player.sendMessage("Your woodcutting is 0");
                    player.sendMessage("Your Mining is 0");
                    player.sendMessage("Your Combat is 0");
                }
                return true;
            }
            return false;
        }
    }


}