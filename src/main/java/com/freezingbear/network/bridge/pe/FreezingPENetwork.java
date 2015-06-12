package com.freezingbear.network.bridge.pe;

import com.freezingbear.FreezingBear;
import com.freezingbear.factory.threads.ServerThread;
import com.freezingbear.network.NetworkBridge;
import com.freezingbear.network.bridge.pe.protocol.ReceivedPacket;
import com.freezingbear.network.bridge.pe.protocol.raknet.RaknetInfo;
import com.freezingbear.network.bridge.pe.protocol.raknet.UnconnectedPing;
import com.freezingbear.network.bridge.pe.protocol.raknet.UnconnectedPong;

import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;

/**
 * Created by FreezigBear Team.
 */
public class FreezingPENetwork extends ServerThread implements NetworkBridge {


    private FreezingUDPSocket socket;

    private FreezingBear plugin;

    public FreezingPENetwork(FreezingBear plugin){
        this.plugin = plugin;
    }

    public void onTick(){
        DatagramPacket packet = null;
        while ((packet = socket.recive()) != null){
            this.handlePacket(packet);
        }
    }

    private void handlePacket(DatagramPacket packet) {
        synchronized (this){
            ReceivedPacket p = new ReceivedPacket(packet.getSocketAddress(), packet.getData());
            ByteBuffer byteBuffer = p.getBuffer();
            byte packetID = byteBuffer.get();
            switch (packetID){
                case RaknetInfo.UNCONNECTED_PING:
                    //UnconnectedPing ping = new UnconnectedPing(byteBuffer);
                    UnconnectedPong pong = new UnconnectedPong();

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
        String ip = (plugin.getConfig().getString("ip") == null)?"localhost":plugin.getConfig().getString("ip");
        int port = (plugin.getConfig().getInt("port") == 0)?19132:plugin.getConfig().getInt("port");
        try {
            this.socket = new FreezingUDPSocket(InetSocketAddress.createUnresolved(ip,port), plugin);
        } catch (SocketException e) {
            plugin.getLogger().warning("Can't start socket on " + ip + ":" + port + ", " + e.getMessage());
            plugin.getLogger().info("Disabling FreezingBear due to socket start failed.");
            this.socket.close();
            plugin.getServer().getPluginManager().disablePlugin(plugin);
        }
        if(socket != null){
            plugin.getLogger().info("Successfully start socket on "  + ip + ":" + port + ".");
        }
    }

}
