package com.freezingbear.network.mcpe.protocol.raknet;

import com.freezingbear.FreezingBear;
import com.freezingbear.network.mcpe.protocol.RaknetPacket;
import com.freezingbear.util.FreezingVersion;

import java.io.IOException;

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
            putString("MCPE;" + server_name + ";" + ";PC: " + FreezingVersion.MCPCVERSION + ", PE: " + FreezingVersion.MCPEVERSION + ";" + FreezingBear.getInstance().getServer().getOnlinePlayers().size() + ";" + FreezingBear.getInstance().getServer().getMaxPlayers());
        }catch(IOException e){
            FreezingBear.getInstance().getLogger().info(e.getMessage());
        }

    }


}
