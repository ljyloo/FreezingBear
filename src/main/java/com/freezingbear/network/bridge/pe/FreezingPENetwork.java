package com.freezingbear.network.bridge.pe;

import com.freezingbear.FreezingBear;
import com.freezingbear.event.RakNetPacketReceivedEvent;
import com.freezingbear.factory.threads.ServerThread;
import com.freezingbear.network.NetworkBridge;
import com.freezingbear.network.bridge.pe.protocol.ReceivedPacket;
import com.freezingbear.network.bridge.pe.protocol.raknet.*;
import com.freezingbear.util.ServerID;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;

import java.io.IOException;
import java.net.*;

/**
 * Created by FreezigBear Team.
 */
public class FreezingPENetwork extends ServerThread implements NetworkBridge {


    private FreezingPEUDPSocket socket;
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
            plugin.debug("Processing Packet PID:" + String.valueOf(p.getPacketID(true) & 0xFF));
            RakNetPacketReceivedEvent rakNetPacketReceivedEvent = new RakNetPacketReceivedEvent(packet.getData(), (byte) (p.getPacketID(true) & 0xFF));
            this.plugin.getServer().getPluginManager().callEvent(rakNetPacketReceivedEvent);
            if(!rakNetPacketReceivedEvent.getValid()){
                return;
            }
            switch (p.getPacketID() & 0xFF){
                case RaknetInfo.UNCONNECTED_PING:
                    plugin.debug("Processing Packet UNCONNECTED_PING");
                    UNCONNECTED_PING ping = new UNCONNECTED_PING(packet.getSocketAddress(), packet.getData());
                    plugin.debug("Ping ID:" + String.valueOf(ping.getPingId()));
                    UNCONNECTED_PONG pong = new UNCONNECTED_PONG(ping.getPingId(), ServerID.getServerID(), "[FreezingBear] Minecraft Server");
                    this.socket.send(pong, packet.getSocketAddress());
                    break;
                case RaknetInfo.OPEN_CONNECTION_REQUEST_1:
                    plugin.debug("Processing Packet OPEN_CONNECTION_REQUEST_1");
                    OPEN_CONNECTION_REQUEST_1 open_connection_request_1 = new OPEN_CONNECTION_REQUEST_1(packet.getSocketAddress(), packet.getData());
                    plugin.debug("Raknet Protocol Version: " + open_connection_request_1.protocolVersion);
                    plugin.debug("Payload: " + open_connection_request_1.payload);
//                    if(open_connection_request_1.protocolVersion != (byte)FreezingVersion.PROTOCOL_VESION){
//                        INCOMPATIBLE_PROTOCOL_VERSION incompatible_protocol_version = null;
//                        try {
//                            incompatible_protocol_version = new INCOMPATIBLE_PROTOCOL_VERSION();
//                        } catch (IOException e) {
//                            plugin.getLogger().warning("Wrong during writing packet.");
//                        }
//                        this.socket.send(incompatible_protocol_version, packet.getSocketAddress());
//                        break;
//                    }
                    OPEN_CONNECTION_REPLY_1 open_connection_reply_1 = null;
                    try {
                        open_connection_reply_1 = new OPEN_CONNECTION_REPLY_1(open_connection_request_1.payload + 18);
                    } catch (IOException e) {
                        plugin.getLogger().warning("Wrong during writing packet.");
                    }
                    this.socket.send(open_connection_reply_1, packet.getSocketAddress());
                    break;
                case RaknetInfo.OPEN_CONNECTION_REQUEST_2:
                    plugin.debug("Processing Packet OPEN_CONNECTION_REQUEST_2");
                    OPEN_CONNECTION_REQUEST_2 open_connection_request_2 = new OPEN_CONNECTION_REQUEST_2(packet.getSocketAddress(), packet.getData());
                    OPEN_CONNECTION_REPLY_2 open_connection_reply_2 = null;
                    try {
                        open_connection_reply_2 = new OPEN_CONNECTION_REPLY_2(open_connection_request_2.targetPort, open_connection_request_2.mtu);
                    } catch (IOException e) {
                        plugin.getLogger().warning("Wrong during writing packet.");
                    }
                    this.socket.send(open_connection_reply_2,packet.getSocketAddress());
                    break;
                case RaknetInfo.ACK:
                    break;
                case RaknetInfo.NACK:
                    break;
                default:
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
            socket = new FreezingPEUDPSocket(null);
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
