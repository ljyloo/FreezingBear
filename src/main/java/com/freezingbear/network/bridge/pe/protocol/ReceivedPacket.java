package com.freezingbear.network.bridge.pe.protocol;

import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Created by FreezingBear Team.
 */
public class ReceivedPacket extends Packet {

    public ReceivedPacket(SocketAddress address, byte[] data){

        super(address, data);
    }
}
