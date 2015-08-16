package com.freezingbear.network.bridge.pe.protocol;

import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Objects;

/**
 * Created by FreezingBear Team.
 */
public class ReceivedPacket extends Packet {

    public ReceivedPacket(SocketAddress address, byte[] data){

        super(address, data);
    }

    public byte getPacketID(boolean clear){
        if (!clear)
            return getBuffer().get();
        byte pid = getBuffer().get();
        getBuffer().rewind();
        return pid;
    }

    public byte getPacketID(){
        return getPacketID(false);
    }

}
