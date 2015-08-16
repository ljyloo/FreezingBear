package com.freezingbear;

import com.freezingbear.command.CommandHandler;
import com.freezingbear.network.FreezingNetworkManager;
import com.freezingbear.network.mcpe.FreezingPENetwork;
import com.freezingbear.util.FreezingVersion;
import com.freezingbear.util.ServerID;
import com.freezingbear.util.TitleUpdater;
import com.mojang.authlib.GameProfile;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.UUID;

/**
 * Created by FreezigBear Team.
 */
public class FreezingBear extends JavaPlugin implements Listener{

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

        this.getServer().getPluginManager().registerEvents(this,this);


    }

    @EventHandler
    public void onPlayerJoin(PlayerEggThrowEvent event){
        getLogger().info("PPP");
        Player player = event.getPlayer();
        MinecraftServer nmsServer = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer nmsWorld = ((CraftWorld) Bukkit.getWorlds().get(0)).getHandle();
        EntityPlayer npc = new EntityPlayer(nmsServer, nmsWorld, new GameProfile(UUID.randomUUID(), "[PE] Steven"), new PlayerInteractManager(nmsWorld));
        npc.setLocation(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), 0F, 0F);
        CraftPlayer cp = new CraftPlayer((CraftServer) Bukkit.getServer(), npc);
        cp.setVelocity(new Vector(300,300,300));

        PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
        connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
        connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
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
    
    public void debug(String msg){
        if (debug){
            consoleCommandSender.sendMessage(ChatColor.RED + "[FreezingBear] [Debug] " + msg);
        }
    }

    public FreezingNetworkManager getNetwork() {
        return networkManager;
    }

}
