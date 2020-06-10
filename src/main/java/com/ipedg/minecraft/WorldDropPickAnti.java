package com.ipedg.minecraft;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class WorldDropPickAnti extends JavaPlugin implements Listener {

    List<String> worldnames = new ArrayList<>();

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @Override
    public void onEnable() {
        File config = new File(getDataFolder() + File.separator + "config.yml");
        if (!config.exists()) {
            getConfig().options().copyDefaults(true);
        }
        worldnames = getConfig().getStringList("AntiWorld");
        Bukkit.getServer().getPluginManager().registerEvents(this,this);
        super.onEnable();
    }

    @EventHandler
    public void PlayerDropItem(PlayerDropItemEvent event){
        String worldname = event.getPlayer().getWorld().getName();
        if (worldnames.contains(worldname)){
            event.getPlayer().sendMessage("§4§l[提示]你不可以丢弃物品");
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void PlayerInteractEvent(PlayerInteractEvent event){
        String worldname = event.getPlayer().getWorld().getName();
        if (worldnames.contains(worldname)|| worldname.equals("world")){
            if (event.getAction()== Action.RIGHT_CLICK_BLOCK){
                event.getPlayer().sendMessage("§4§l[提示]你不可以在其他世界进行右键交互");
                event.setCancelled(true);
            }
        }
    }

}
