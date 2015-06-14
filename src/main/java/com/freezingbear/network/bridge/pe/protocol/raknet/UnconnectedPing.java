package com.freezingbear.network.bridge.pe.protocol.raknet;

import com.freezingbear.network.bridge.pe.protocol.RaknetPacket;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

import java.net.SocketAddress;

/**
 * Created by FreezingBear Team.
 */

/**
 *
 * Raknet ID_UNCONNECTED_PING
 * C->S
 *
 */
public class UnconnectedPing extends RaknetPacket {

    private long pingId;


    public UnconnectedPing(SocketAddress address, byte[] data){

        super(address, data);
        this.pingId = getBuffer().getLong();
        getBuffer().get(magic);
    }


    public long getPingId(){

        return pingId;
    }
}
