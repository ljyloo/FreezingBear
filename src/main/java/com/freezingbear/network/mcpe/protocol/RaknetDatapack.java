package com.freezingbear.network.mcpe.protocol;

import com.freezingbear.FreezingBear;
import com.freezingbear.network.mcpe.binary.Binary;

import java.net.SocketAddress;
import java.util.ArrayList;

/**
 * Created by FreezingBear Team.
 */
public class RaknetDatapack extends RaknetPacket {

    public int sequenceNumber;
    public ArrayList<EncapsulatedPacket> encapsulatedPackets = new ArrayList<EncapsulatedPacket>();

    public RaknetDatapack(SocketAddress socketAddresses, byte[] data){
        super(socketAddresses, data);
        this.sequenceNumber = Binary.readLTriad(getBuffer());
        while (getBuffer().hasRemaining()){
            FreezingBear.getInstance().debug("Reading datapack position: " + getBuffer().position());
            EncapsulatedPacket encapsulatedPacket = new EncapsulatedPacket(getBuffer());
            encapsulatedPackets.add(encapsulatedPacket);
        }
    }
}
