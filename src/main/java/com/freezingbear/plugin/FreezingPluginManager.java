package com.freezingbear.plugin;

import java.util.HashMap;

/**
 * Created by FreezingBear Team.
 */
public class FreezingPluginManager {

    private static HashMap<String, FreezingPlugin> plugins;

    public FreezingPluginManager(){
        plugins = new HashMap<String, FreezingPlugin>();
    }

    public static void registerFreezingPlugin(FreezingPlugin plugin){
        plugins.put(plugin.getName(), plugin);
    }

    public HashMap<String, FreezingPlugin> getPlugins() {
        return plugins;
    }

}
