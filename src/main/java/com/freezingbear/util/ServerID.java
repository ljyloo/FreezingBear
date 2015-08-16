package com.freezingbear.util;

import java.util.Random;

/**
 * Created by FreezingBear Team.
 */
public class ServerID {

    public static volatile long serverID;

    public ServerID(){
        serverID = new Random().nextLong();
    }

    public static synchronized long getServerID() {
        return serverID;
    }
}
