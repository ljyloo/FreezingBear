package com.freezingbear.network.mcpe;

//import net.minecraft.server.v1_8_R3.PlayerConnection;

import com.freezingbear.network.mcpe.protocol.EncapsulatedPacket;
import com.freezingbear.network.mcpe.protocol.RaknetDatapack;

import java.net.SocketAddress;

/**
 * Created by FreezingBear Team.
 */
public class FreezingPEPlayerConnection /* extends PlayerConnection */ {

    private SocketAddress socketAddress;
    private short mtu;
    private volatile long clientID;
    private int lastSequenceNumber;


    //PlayerConnection is obfuscated so we use our own format. In network converters, we'll create another PlayerConnection.

    public FreezingPEPlayerConnection(FreezingPENetwork freezingPENetwork, SocketAddress socketAddress, long clientID, short mtu){
        this.clientID = clientID;
        this.mtu = mtu;
        this.socketAddress = socketAddress;
    }

    public void tick(){

    }


    public void handlePacket(RaknetDatapack raknetDatapack) {
        if (raknetDatapack.sequenceNumber - this.lastSequenceNumber > 1){

        }
        for (EncapsulatedPacket encapsulatedPacket : raknetDatapack.encapsulatedPackets){
            handleDataPacket(encapsulatedPacket);
        }
    }

    private void handleDataPacket(EncapsulatedPacket encapsulatedPacket) {

        //TODO if (encapsulatedPacket.)
    }

}
