package com.freezingbear.factory.manager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by FreezigBear Team.
 */
public class ThreadManagerDeafult extends Thread implements ThreadManager {

    public ExecutorService networkThreadPool;
    public ExecutorService serverThreadPool;

    public ThreadManagerDeafult() {
        networkThreadPool = Executors.newFixedThreadPool(256);
        serverThreadPool = Executors.newFixedThreadPool(256);
    }

    public void registerNetworkThread(Runnable thread){
        networkThreadPool.execute(thread);
    }

    public void registerServerThread(Runnable thread){
        serverThreadPool.execute(thread);
    }

}
