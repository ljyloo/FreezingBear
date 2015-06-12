package com.freezingbear.factory;

import com.freezingbear.factory.manager.ThreadManager;
import com.freezingbear.factory.manager.ThreadManagerDeafult;

/**
 * Created by FreezigBear Team.
 */
public class FactoryDefault implements Factory {

    private static ThreadManagerDeafult threadManager;

    public static ThreadManagerDeafult getThreadManager() {
        if(threadManager == null){
            threadManager = new ThreadManagerDeafult();
            threadManager.start();
        }
        return threadManager;
    }

}
