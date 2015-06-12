package com.freezingbear.network.bridge.pe.protocol;

import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Created by FreezingBear Team.
 */
public class ReceivedPacket extends Packet {

    public SocketAddress getSocketAddress() {
        return socketAddress;
    }

    private final SocketAddress socketAddress;

    public ByteBuffer getBuffer() {
        return buffer;
    }

    private final ByteBuffer buffer;

    public ReceivedPacket(SocketAddress address, byte[] buffer){
        this.socketAddress = address;
        this.buffer = ByteBuffer.wrap(buffer).order(ByteOrder.LITTLE_ENDIAN);
    }

}
