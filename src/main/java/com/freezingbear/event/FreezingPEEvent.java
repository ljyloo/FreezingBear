package com.freezingbear.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by FreezingBear Team.
 */
public class FreezingPEEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
    
}
