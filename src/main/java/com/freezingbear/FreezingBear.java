package com.freezingbear;

import com.freezingbear.command.CommandHandler;
import com.freezingbear.network.FreezingNetworkManager;
import com.freezingbear.network.bridge.pe.FreezingPENetwork;
import com.freezingbear.util.FreezingVersion;
import com.freezingbear.util.ServerID;
import com.freezingbear.util.TitleUpdater;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by FreezigBear Team.
 */
public class FreezingBear extends JavaPlugin {

    public boolean debug;

    private FreezingNetworkManager networkManager;
    private static FreezingBear instance;
    private CommandHandler commandHandler;
    private TitleUpdater titleUpdater;

    public ConsoleCommandSender consoleCommandSender;

    public static FreezingBear getInstance() {
        return instance;
    }

    public void onEnable(){
        //Plugin
        consoleCommandSender = ((CraftServer)getServer()).getConsoleSender();
        instance = this;
        saveDefaultConfig();
        this.debug = getConfig().getBoolean("debug");
        consoleCommandSender.sendMessage(ChatColor.GREEN  + "FreezingBear " + ChatColor.BLUE + FreezingVersion.FREEZINGBEARVERISON + " " + FreezingVersion.BUILDINFO + ", for Minecraft " + FreezingVersion.MCPCVERSION + ", Minecraft: Pocket Edition " + FreezingVersion.MCPEVERSION);
        initTimer();
        commandHandler = new CommandHandler();
        getCommand("freezingbear").setExecutor(commandHandler);
        //FreezingBear
        titleUpdater = new TitleUpdater();
        //  -Network
        new ServerID();
        networkManager = new FreezingNetworkManager();
        FreezingPENetwork freezingPENetwork = new FreezingPENetwork(this);
        networkManager.registerNetworkBridge(freezingPENetwork);
        freezingPENetwork.setName("FreezingPENetwork");
        freezingPENetwork.start();
    }

    public void onDisable(){
        networkManager.shutdown();
    }

    private void initTimer() {
        new BukkitRunnable(){

            public void run() {
                FreezingBear.instance.onTick();
            }

        }.runTaskTimerAsynchronously(this, 1, 0);
    }

    private void onTick() {
        networkManager.onTick();
        titleUpdater.onTick();
    }

    public FreezingNetworkManager getNetwork() {
        return networkManager;
    }

}
