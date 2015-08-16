package com.freezingbear.network.mcpe.protocol.raknet;

import com.freezingbear.network.mcpe.protocol.RaknetPacket;

import java.net.SocketAddress;

/**
 * Created by FreezingBear Team.
 */
public class OPEN_CONNECTION_REQUEST_1 extends RaknetPacket {

    public byte protocolVersion;
    public int payload;

    public OPEN_CONNECTION_REQUEST_1(SocketAddress address, byte[] data){
        super(address, data);
        getBuffer().get(magic);
        protocolVersion = getBuffer().get();
        payload = getBuffer().remaining();
    }

}
