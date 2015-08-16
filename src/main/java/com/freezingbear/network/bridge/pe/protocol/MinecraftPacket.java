package com.freezingbear.network.bridge.pe.protocol;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

import java.net.SocketAddress;

/**
 * Created by FreezingBear Team.
 */
public class MinecraftPacket extends RaknetPacket {

    public MinecraftPacket(SocketAddress address, byte[] data){

        super(address, data);
    }
}
