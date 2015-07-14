package com.freezingbear.network.bridge.pe.protocol.raknet;

import com.freezingbear.FreezingBear;
import com.freezingbear.network.bridge.pe.protocol.RaknetPacket;
import com.freezingbear.util.FreezingVersion;
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
public class UNCONNECTED_PONG extends RaknetPacket{

    public UNCONNECTED_PONG(long pingId, long server_id, String server_name){
        try{
            putByte(UNCONNECTED_PONG);
            putLong(pingId);
            putLong(server_id);
            put(MAGIC);
            putString("MCPE;" + server_name + ";" + ";PC: " + FreezingVersion.MCPCVERSION + ", PE: " + FreezingVersion.MCPEVERSION + ";" + "0" + ";" + "100");
        }catch(IOException e){
            FreezingBear.getInstance().getLogger().info(e.getMessage());
        }

    }


}
