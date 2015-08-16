package com.freezingbear.network.mcpe.protocol.raknet;

import com.freezingbear.network.mcpe.protocol.RaknetPacket;

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
public class UNCONNECTED_PING extends RaknetPacket {

    private long pingId;


    public UNCONNECTED_PING(SocketAddress address, byte[] data){

        super(address, data);
        this.pingId = getBuffer().getLong();
        getBuffer().get(magic);
    }


    public long getPingId(){

        return pingId;
    }
}
