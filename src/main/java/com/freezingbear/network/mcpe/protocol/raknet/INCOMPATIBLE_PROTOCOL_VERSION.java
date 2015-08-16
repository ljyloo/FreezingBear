package com.freezingbear.network.mcpe.protocol.raknet;

import com.freezingbear.network.mcpe.protocol.RaknetPacket;
import com.freezingbear.util.ServerID;

import java.io.IOException;

/**
 * Created by FreezingBear Team.
 */
public class INCOMPATIBLE_PROTOCOL_VERSION extends RaknetPacket {

    public INCOMPATIBLE_PROTOCOL_VERSION() throws IOException {
        putByte(INCOMPATIBLE_PROTOCOL_VERSION);
        put(MAGIC);
        putLong(ServerID.getServerID());
    }

}
