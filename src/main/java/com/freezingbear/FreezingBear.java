package com.freezingbear;

import com.freezingbear.command.CommandHandler;
import com.freezingbear.network.FreezingNetworkManager;
import com.freezingbear.network.bridge.pe.FreezingPENetwork;
import com.freezingbear.util.FreezingVersion;
import com.freezingbear.util.ServerID;
import com.freezingbear.util.TitleUpdater;
import org.bukkit.Color;
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


    public static FreezingBear getInstance() {
        return instance;
    }

    public void onEnable(){
        instance = this;
        saveDefaultConfig();
        this.debug = getConfig().getBoolean("debug");
        getLogger().info(Color.GREEN + "FreezingBear " + FreezingVersion.FREEZINGBEARVERISON + " " + FreezingVersion.BUILDINFO + ", for Minecraft " + FreezingVersion.MCPCVERSION + ", Minecraft: Pocket Edition " + FreezingVersion.MCPEVERSION);
        initTimer();
        new ServerID();
        this.commandHandler = new CommandHandler();
        this.getCommand("freezingbear").setExecutor(commandHandler);
        this.titleUpdater = new TitleUpdater();
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
