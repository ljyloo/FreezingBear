package com.freezingbear.network.bridge.pe.protocol;


import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

import java.nio.ByteOrder;

/**
 * Created by FreezingBear Team.
 */
public class Packet {

    public ByteOrder endianness = ByteOrder.BIG_ENDIAN;

    private ByteInputStream byteInputStream;

    public ByteInputStream getByteInputStream() {
        return byteInputStream;
    }

    public enum PacketType{
        SERVER_TO_CLIENT, CLIENT_TO_SERVER, TWO_WAYS
    }

    public PacketType type;

    public Packet(ByteInputStream byteInputStream){
        this.byteInputStream = byteInputStream;
    }



}
