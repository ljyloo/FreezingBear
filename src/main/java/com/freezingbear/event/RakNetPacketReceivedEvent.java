package com.freezingbear.event;

/**
 * Created by FreezingBear Team.
 */
public class RakNetPacketReceivedEvent extends FreezingPEEvent {

    private byte[] data;
    private boolean value;
    private byte packetID;

    public RakNetPacketReceivedEvent(byte[] data, byte pid) {
        this.data = data;
        packetID = pid;
    }

    public byte[] getData() {
        return data;
    }

    public void setValid(boolean value){
        this.value = value;
    }

    public boolean getValid(){
        return value;
    }

    public byte getPacketID() {
        return packetID;
    }
}
