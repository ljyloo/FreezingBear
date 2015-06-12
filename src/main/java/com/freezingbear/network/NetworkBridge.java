package com.freezingbear.network;


import com.freezingbear.factory.threads.NetworkThread;

/**
 * Created by FreezingBear Team.
 */
public interface NetworkBridge extends NetworkThread{

    public String getName();

    public void onTick();

}
