package com.freezingbear.network.mcpe.protocol.raknet;

import com.freezingbear.network.mcpe.protocol.RaknetPacket;

import java.net.SocketAddress;

/**
 * Created by FreezingBear Team.
 */
public class OPEN_CONNECTION_REQUEST_2 extends RaknetPacket {

    public short mtu;
    public long clientID;

    public OPEN_CONNECTION_REQUEST_2(SocketAddress address, byte[] data){
        super(address, data);
        getBuffer().get(magic);
        getBuffer().get();
        getBuffer().getInt();
        getBuffer().getShort();
        //Don't care whatever these things are


        mtu = getBuffer().getShort();
        clientID = getBuffer().getLong();
    }

}
