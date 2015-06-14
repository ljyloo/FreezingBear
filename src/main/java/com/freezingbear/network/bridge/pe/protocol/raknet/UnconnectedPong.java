package com.freezingbear.network.bridge.pe.protocol.raknet;

import com.freezingbear.network.bridge.pe.protocol.RaknetPacket;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteOrder;

/**
 * Created by FreezingBear Team.
 */

/**
 *
 * Raknet ID_UNCONNECTED_PONG
 * S->C
 *
 */
public class UnconnectedPong extends RaknetPacket{

    public UnconnectedPong(long pingId, long server_id, byte[] magic, String server_name){
        byte[] bytes = server_name.getBytes();
        try{
            putByte(UNCONNECTED_PONG);
            putLong(pingId);
            putLong(server_id);
            put(magic);
            put(bytes);
        }catch(IOException e){

        }
    }


}
