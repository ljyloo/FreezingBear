package com.freezingbear.network.mcpe.protocol.raknet;

import com.freezingbear.network.mcpe.protocol.RaknetPacket;
import com.freezingbear.util.ServerID;

import java.io.IOException;
import java.net.InetAddress;

/**
 * Created by FreezingBear Team.
 */
public class OPEN_CONNECTION_REPLY_2 extends RaknetPacket {

    public OPEN_CONNECTION_REPLY_2(InetAddress address, short targetPort, short mtu) throws IOException {
        putByte(OPEN_CONNECTION_REPLY_2);
        put(MAGIC);
        putLong(ServerID.getServerID());
        putShort(targetPort);
        putShort(mtu);
        putByte((byte) 0x00);
    }

}