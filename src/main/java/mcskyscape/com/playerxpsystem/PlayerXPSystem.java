package mcskyscape.com.playerxpsystem;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerXPSystem extends JavaPlugin implements CommandExecutor, Listener {
    public HashMap<Player, Integer> woodcutting = new HashMap<>();
    public HashMap<Player, Integer> mining = new HashMap<>();
    public HashMap<Player, Integer> combat = new HashMap<>();
    @Override
    public void onEnable(){
        getServer().getPluginManager().registerEvents( this, this);
        this.getCommand("levels").setExecutor(new Commandseelevels());
        this.getCommand("addwoodcutting").setExecutor(new Addwoodcutting());
    }
    @Override
    public void onDisable(){
        
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        woodcutting.put(e.getPlayer(), 0);
        mining.put(e.getPlayer(), 0);
        combat.put(e.getPlayer(), 0);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player p = (Player) e.getPlayer();
        Block b = (Block) e.getBlock();

        if (b.getType() == Material.OAK_LOG) {
            b.setType(Material.AIR);
            p.getInventory().addItem(new ItemStack(Material.OAK_LOG));
            p.sendMessage("+1 woodcutting");
            int woodCuttingLevel = woodcutting.get(e.getPlayer());
            woodcutting.put(e.getPlayer(), woodCuttingLevel + 1);
        }
    }
    //Command to see levels
    public class Commandseelevels implements CommandExecutor {
        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if (label.equals("levels")){
                Player player = (Player) sender;
                if (sender instanceof Player) {
                    int woodCuttingLevel = woodcutting.get(player.getPlayer());
                    player.sendMessage("Your woodcutting is " + woodCuttingLevel);
                    player.sendMessage("Your Mining is 0");
                    player.sendMessage("Your Combat is 0");
                }
                return true;
            }
            return false;
        }
    }
    //test command to increment woodcutting
    public class Addwoodcutting implements CommandExecutor {
        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if (label.equals("addwoodcutting")){
                Player player = (Player) sender;
                if (sender instanceof Player) {
                    int woodCuttingLevel = woodcutting.get(player.getPlayer());
                    woodcutting.put(player.getPlayer(), woodCuttingLevel + 1);
                }
                return true;
            }
            return false;
        }

    }


}