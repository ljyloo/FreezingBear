package com.freezingbear.network.mcpe.protocol;

import com.freezingbear.network.mcpe.protocol.raknet.RaknetInfo;

import java.net.SocketAddress;
import java.nio.ByteOrder;

/**
 * Created by FreezingBear Team.
 */
public class RaknetPacket extends Packet implements RaknetInfo {

    public RaknetPacket(SocketAddress address, byte[] data){

        super(address, data);
    }


    public RaknetPacket(){

    }


    public RaknetPacket(ByteOrder endianness){

        super(endianness);

    }
}
