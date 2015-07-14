package com.freezingbear.network.bridge.pe.protocol.raknet;

import com.freezingbear.network.bridge.pe.protocol.RaknetPacket;

import java.net.SocketAddress;

/**
 * Created by FreezingBear Team.
 */
public class OPEN_CONNECTION_REQUEST_2 extends RaknetPacket {

    public short targetPort;
    public short mtu;

    public OPEN_CONNECTION_REQUEST_2(SocketAddress address, byte[] data){
        super(address, data);
        getBuffer().get(magic);
        getBuffer().get();
        getBuffer().getInt();
        //Don't care whatever these things are

        targetPort = getBuffer().getShort();
        mtu = getBuffer().getShort();

    }

}
