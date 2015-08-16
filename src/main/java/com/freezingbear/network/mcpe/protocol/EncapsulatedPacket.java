package com.freezingbear.network.mcpe.protocol;

import com.freezingbear.network.mcpe.binary.Binary;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Created by FreezingBear Team.
 */
public class EncapsulatedPacket extends RaknetPacket {

    public int splitIndex;
    public short splitID;
    public int splitCount;
    public int orderIndex;
    public byte reliability;
    public boolean doSplit;
    public int messageIndex;
    public byte[] packetData;

    public EncapsulatedPacket(ByteBuffer buffer) {
        buffer.order(ByteOrder.BIG_ENDIAN);//Suck it, Mojang!

        byte stu = buffer.get();
        this.reliability = (byte) (stu >> 5);
        this.doSplit = (stu & 16) == 16;
        int length = (buffer.getShort() & 65528) >> 3;
        if (reliability == 2 || reliability == 3 || reliability == 4 || reliability == 6 || reliability == 7) {
            this.messageIndex = Binary.readLTriad(buffer);
        }
        if(reliability == 1 || reliability == 3 || reliability == 4 || reliability == 7){
            this.orderIndex = Binary.readLTriad(buffer);
        }
        if(doSplit){
            this.splitCount = buffer.getInt();
            this.splitID = buffer.getShort();
            this.splitIndex = buffer.getInt();
        }
        this.packetData = new byte[length];
        buffer.get(packetData);
    }
}
