package com.freezingbear.network.bridge.pe.protocol.raknet;

import com.freezingbear.network.bridge.pe.protocol.RaknetPacket;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

/**
 * Created by FreezingBear Team.
 */

/**
 *
 * Raknet ID_UNCONNECTED_PING
 * C->S
 *
 */
public class UnconnectedPing extends RaknetPacket {

    private long pingID;

    public UnconnectedPing(ByteInputStream byteInputStream){
        super(byteInputStream);
    }


    public long getPingID() {
        return pingID;
    }
}
