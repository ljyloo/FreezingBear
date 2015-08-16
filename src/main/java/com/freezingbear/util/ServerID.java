package com.freezingbear.util;

import java.util.Random;

/**
 * Created by FreezingBear Team.
 */
public class ServerID {

    private static long serverID;

    public ServerID(){
        serverID = new Random().nextLong();
    }

    public static long getServerID() {
        return serverID;
    }
}
