package com.freezingbear.network.bridge.pe;

import com.freezingbear.FreezingBear;
import com.freezingbear.factory.threads.NetworkThread;
import org.apache.logging.log4j.Level;

import java.io.IOException;
import java.net.*;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by FreezigBear Team.
 */
public class FreezingUDPSocket extends DatagramSocket implements NetworkThread {

    private final FreezingBear plugin;
    private SocketAddress address;
    private Queue<DatagramPacket> queue = new LinkedList<DatagramPacket>();
    public boolean listening = false;

    public FreezingUDPSocket(SocketAddress bindaddr, FreezingBear plugin) throws SocketException {
        super();
        this.address = bindaddr;
        this.plugin = plugin;
        setBroadcast(true);
        setSendBufferSize(1024 * 1024 * 8);
        setReceiveBufferSize(1024 * 1024);
        bind(address);
    }

    public void run() {
        Thread.currentThread().setName("FreezingUDPSocket");
        listening = true;
        while (listening){
            try {
                DatagramPacket packet = new DatagramPacket(new byte[1024 * 1024], 1024 * 1024);
                super.receive(packet);
                synchronized(queue.getClass()) {
                    queue.offer(packet);
                }
            } catch (Exception e) {
                plugin.getLogger().warning(e.getMessage());
            }
        }
        close();
    }

    public boolean send(byte[] buffer, SocketAddress addr){
        try {
            DatagramPacket p = new DatagramPacket(buffer, buffer.length, addr);
            super.send(p);
        }catch (Exception e){
            return false;
        }
        if(plugin.getConfig().getBoolean("debug")){
            //TODO
            //plugin.getLogger().info("");
        }
        return true;
    }

    public synchronized DatagramPacket recive(){
        if(queue.isEmpty()){
            return null;
        }
        return queue.poll();
    }

    @Override
    public void close(){
        listening = false;
        super.close();
    }

}
