package com.freezingbear.network.mcpe;

import com.freezingbear.network.mcpe.protocol.Packet;
import io.netty.channel.*;

import java.net.SocketAddress;

/**
 * Created by FreezingBear Team.
 */
public class FreezingPENetworkChannel extends AbstractChannel{

    static {
        registerPackets();
    }

    @Override
    protected AbstractUnsafe newUnsafe() {
        return null;
    }

    @Override
    protected boolean isCompatible(EventLoop eventLoop) {
        return false;
    }

    @Override
    protected SocketAddress localAddress0() {
        return null;
    }

    @Override
    protected SocketAddress remoteAddress0() {
        return null;
    }

    @Override
    protected void doBind(SocketAddress socketAddress) throws Exception {

    }

    @Override
    protected void doDisconnect() throws Exception {

    }

    @Override
    protected void doClose() throws Exception {

    }

    @Override
    protected void doBeginRead() throws Exception {

    }

    @Override
    protected void doWrite(ChannelOutboundBuffer channelOutboundBuffer) throws Exception {

    }

    @Override
    public ChannelConfig config() {
        return null;
    }

    @Override
    public boolean isOpen() {
        return false;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public ChannelMetadata metadata() {
        return null;
    }

    public interface NetworkChannel{

        int CHANNEL_NONE = 0;
        int CHANNEL_PRIORITY = 1; //Priority channel, only to be used when it matters
        int CHANNEL_WORLD_CHUNKS = 2; //Chunk sending
        int CHANNEL_MOVEMENT = 3; //Movement sending
        int CHANNEL_BLOCKS = 4; //Block updates or explosions
        int CHANNEL_WORLD_EVENTS = 5; //Entity, level or tile entity events
        int CHANNEL_ENTITY_SPAWNING = 6; //Entity spawn/despawn channel
        int CHANNEL_TEXT = 7; //Chat and other text stuff
        int CHANNEL_END = 31;

        //From PocketMine

    }

    private static Packet[] packetPool;

    private byte channel;

    public FreezingPENetworkChannel(int channel){
        this.channel = (byte) channel;
    }

    public FreezingPENetworkChannel(byte channel){
        this.channel = channel;
    }

    private static void registerPackets() {
        //TODO
    }

}
