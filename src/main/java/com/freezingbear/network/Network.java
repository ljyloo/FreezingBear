package com.freezingbear.network;

import com.freezingbear.network.mcpe.protocol.Packet;

/**
 * Created by FreezingBear Team.
 */
public class Network {

    public interface NetworkChannel{

        int CHANNEL_NONE = 0;
        int CHANNEL_PRIORITY = 1; //Priority channel, only to be used when it matters
        int CHANNEL_WORLD_CHUNKS = 2; //Chunk sending
        int CHANNEL_MOVEMENT = 3; //Movement sending
        int CHANNEL_BLOCKS = 4; //Block updates or explosions
        int CHANNEL_WORLD_EVENTS = 5; //Entity, level or tile entity events
        int CHANNEL_ENTITY_SPAWNING = 6; //Entity spawn/despawn channel
        int  CHANNEL_TEXT = 7; //Chat and other text stuff
        int  CHANNEL_END = 31;

        //From PocketMine

    }

    private Packet[] packetPool;

    public Network(){
        registerPackets();
    }

    private void registerPackets() {
        //TODO
    }

}
