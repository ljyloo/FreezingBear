package com.freezingbear.network.bridge.pe;

import com.freezingbear.FreezingBear;
import com.freezingbear.factory.manager.ThreadManager;
import com.freezingbear.factory.threads.ServerThread;
import com.freezingbear.network.NetworkBridge;
import com.freezingbear.network.bridge.pe.protocol.ReceivedPacket;
import com.freezingbear.network.bridge.pe.protocol.raknet.RaknetInfo;
import com.freezingbear.network.bridge.pe.protocol.raknet.UnconnectedPing;
import com.freezingbear.network.bridge.pe.protocol.raknet.UnconnectedPong;
import com.freezingbear.util.ServerID;
import org.bukkit.ChatColor;

import java.net.*;
import java.nio.ByteBuffer;

/**
 * Created by FreezigBear Team.
 */
public class FreezingPENetwork extends ServerThread implements NetworkBridge {


    private FreezingUDPSocket socket;
    private Thread socketThread;

    private FreezingBear plugin;

    public FreezingPENetwork(FreezingBear plugin){
        this.plugin = plugin;
    }

    public void onTick(){
        DatagramPacket packet = null;
        while ((packet = socket.recive()) != null){
            plugin.consoleCommandSender.sendMessage(ChatColor.RED + "[FreezingBear][DEBUG] Recived Packet!");
            this.handlePacket(packet);
        }
    }

    public void shutdown() {
        socket.close();
    }

    private void handlePacket(DatagramPacket packet) {
        synchronized (this){
            ReceivedPacket p = new ReceivedPacket(packet.getSocketAddress(), packet.getData());
            if(plugin.debug){
                plugin.getLogger().info("Processing Packet PID:" + p.packetId);
            }
            switch (p.getPacketID() & 0xFF){
                case RaknetInfo.UNCONNECTED_PING:
                    plugin.getLogger().info("Processing Packet UNCONNECTED_PING");
                    UnconnectedPing ping = new UnconnectedPing(packet.getSocketAddress(), packet.getData());
                    plugin.getLogger().info("Ping ID:" + String.valueOf(ping.getPingId()));
                    UnconnectedPong pong = new UnconnectedPong(ping.getPingId(), ServerID.getServerID(), ping.magic, "[FreezingBear] Minecraft Server");
                    this.socket.send(pong.getByteOutputStream().toByteArray(), packet.getSocketAddress());
                    break;
                case RaknetInfo.OPEN_CONNECTION_REQUEST_1:
                    break;
                case RaknetInfo.OPEN_CONNECTION_REQUEST_2:
                    break;
                case (byte) 0x80:
                case (byte) 0x81:
                case (byte) 0x82:
                case (byte) 0x83:
                case (byte) 0x84:
                case (byte) 0x85:
                case (byte) 0x86:
                case (byte) 0x87:
                case (byte) 0x88:
                case (byte) 0x89:
                case (byte) 0x8A:
                case (byte) 0x8B:
                case (byte) 0x8C:
                case (byte) 0x8D:
                case (byte) 0x8E:
                case (byte) 0x8F:
                    //data packs
                    break;
                case RaknetInfo.ACK:
                    break;
                case RaknetInfo.NACK:
                    break;
            }
        }
    }

    @Override
    public void run(){
        String ip = (plugin.getConfig().getString("ip") == null)?"0.0.0.0":plugin.getConfig().getString("ip");
        int port = (plugin.getConfig().getInt("port") == 0)?19132:plugin.getConfig().getInt("port");
        try {
            plugin.consoleCommandSender.sendMessage(ChatColor.RED + "Starting Socket...");
            socket = new FreezingUDPSocket(null);
            socket.setBroadcast(true);
            socket.setSendBufferSize(1024 * 1024 * 8);
            socket.setReceiveBufferSize(1024 * 1024);
            socket.bind(new InetSocketAddress(ip, port));
            socketThread = new Thread(socket);
            socketThread.start();
        } catch (Exception e) {
            plugin.getLogger().warning("Can't start socket on " + ip + ":" + port + ", " + e.getMessage());
            plugin.getLogger().info("Disabling FreezingBear due to socket start failed.");
            this.socket.close();
            plugin.getServer().getPluginManager().disablePlugin(plugin);
            return;
        }
        if(socket != null){
            plugin.getLogger().info("Successfully start socket on "  + ip + ":" + port + ".");
        }
    }

}
