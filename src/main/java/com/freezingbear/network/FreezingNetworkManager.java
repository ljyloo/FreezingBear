package com.freezingbear.network;


import com.freezingbear.factory.threads.NetworkThread;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by FreezingBear Team.
 */
public class FreezingNetworkManager{

    public HashMap<String, NetworkBridge> networkBridges = new HashMap<String, NetworkBridge>();

    public void registerNetworkBridge(NetworkBridge networkBridge){
        networkBridges.put(networkBridge.getName(), networkBridge);
    }

    public void onTick(){

        Iterator i = networkBridges.keySet().iterator();
        while(i.hasNext()){
            networkBridges.get(i.next()).onTick();
        }
    }

    public void shutdown(){
        Iterator i = networkBridges.keySet().iterator();
        while(i.hasNext()){
            networkBridges.get(i.next()).shutdown();
        }
    }

}
