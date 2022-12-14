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
    public HashMap<Player, Integer> woodcuttingXP = new HashMap<>();
    public int woodcuttingToGo;
    public int woodcutting2 = 41;
    public int woodcutting3 = 87;
    public int woodcutting4 = 138;
    public HashMap<Player, Integer> mining = new HashMap<>();
    public HashMap<Player, Integer> combat = new HashMap<>();

    @Override
    public void onEnable(){
        getServer().getPluginManager().registerEvents( this, this);
        this.getCommand("levels").setExecutor(new Commandseelevels());
    }
    @Override
    public void onDisable(){
        //Save hashmap to config file
    }
    @EventHandler //Assign hashmaps to player (CHANGE TO ON PLAYER FIRST JOIN ONCE CONFIG HAS BEEN SET UP)
    public void onJoin(PlayerJoinEvent e) {
        woodcutting.put(e.getPlayer(), 1);
        woodcuttingXP.put(e.getPlayer(), 0);
        mining.put(e.getPlayer(), 1);
        combat.put(e.getPlayer(), 1);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player p = (Player) e.getPlayer();
        Block b = (Block) e.getBlock();
        //Add woodcutting if player breaks logs
        if (b.getType() == Material.OAK_LOG) {
            p.sendMessage("+1 woodcutting xp");
            int woodCuttingXP = woodcuttingXP.get(e.getPlayer());
            woodcuttingXP.put(e.getPlayer(), woodCuttingXP + 1);
            // level 2 threshold
            if (woodCuttingXP >= woodcutting2 && woodcutting3 < 174){
                woodcutting.put(e.getPlayer(), 2);
            } else if (woodCuttingXP >= woodcutting3 && woodCuttingXP < woodcutting4) {
                woodcutting.put(e.getPlayer(), 3);
            }
        }
        //Add mining if player mines cobble
        //Add farming if player breaks wheat,potatoes,sugar cane or any other plant type
    }
    //Command to see levels
    public class Commandseelevels implements CommandExecutor {
        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if (label.equals("levels")){
                Player player = (Player) sender;
                if (sender instanceof Player) {
                    int woodCuttingLevel = woodcutting.get(player.getPlayer());
                    int woodCuttingXP = woodcuttingXP.get(player.getPlayer());
                    if (woodCuttingXP >= woodcutting2 && woodCuttingXP < woodcutting3){
                        woodcuttingToGo = woodcutting2;
                    } else if (woodCuttingXP >= woodcutting3 && woodCuttingXP < woodcutting4) {
                        woodcuttingToGo = woodcutting3;
                    }
                    player.sendMessage("Your woodcutting is " + woodCuttingLevel + ", you are at " + woodCuttingXP + " out of "
                    + woodcuttingToGo);
                    player.sendMessage("Your Mining is 0");
                    player.sendMessage("Your Combat is 0");
                }
                return true;
            }
            return false;
        }
    }
}