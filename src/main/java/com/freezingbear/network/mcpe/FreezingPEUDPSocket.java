package com.freezingbear.network.mcpe;

import com.freezingbear.FreezingBear;
import com.freezingbear.factory.threads.NetworkThread;
import com.freezingbear.network.mcpe.protocol.Packet;

import java.net.*;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by FreezigBear Team.
 */
public class FreezingPEUDPSocket extends DatagramSocket implements NetworkThread {

    private FreezingBear plugin;
    private SocketAddress address;
    private Queue<DatagramPacket> queue = new LinkedList<DatagramPacket>();
    public boolean listening = false;

    public FreezingPEUDPSocket(SocketAddress bindaddr) throws SocketException {
        super(bindaddr);
    }

    public void run() {
        Thread.currentThread().setName("FreezingPEUDPSocket");
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

    public boolean send(Packet packet, SocketAddress addr){
        byte[] buffer = packet.getByteOutputStream().toByteArray();
        try {
            DatagramPacket p = new DatagramPacket(buffer, buffer.length, addr);
            super.send(p);
        }catch (Exception e){
            return false;
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
