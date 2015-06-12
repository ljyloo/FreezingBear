package com.freezingbear.network.bridge.pe.protocol;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

/**
 * Created by FreezingBear Team.
 */
public class MinecraftPacket extends RaknetPacket {

    public MinecraftPacket(ByteInputStream byteInputStream) {
        super(byteInputStream);
    }
}
