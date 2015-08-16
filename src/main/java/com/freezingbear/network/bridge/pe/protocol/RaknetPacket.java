package com.freezingbear.network.bridge.pe.protocol;

import com.freezingbear.network.bridge.pe.protocol.raknet.RaknetInfo;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

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
