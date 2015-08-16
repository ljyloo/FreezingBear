package com.freezingbear.network.mcpe.protocol.raknet;

import com.freezingbear.network.mcpe.protocol.RaknetPacket;
import com.freezingbear.util.ServerID;

import java.io.IOException;

/**
 * Created by FreezingBear Team.
 */
public class OPEN_CONNECTION_REPLY_1 extends RaknetPacket {

    public OPEN_CONNECTION_REPLY_1(int mtu) throws IOException {
        putByte(OPEN_CONNECTION_REPLY_1);
        put(MAGIC);
        putLong(ServerID.getServerID());
        putByte((byte) 0x00);
        putShort((short) mtu);
    }

}
