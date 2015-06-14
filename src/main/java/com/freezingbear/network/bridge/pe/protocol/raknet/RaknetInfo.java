package com.freezingbear.network.bridge.pe.protocol.raknet;

/**
 * Created by FreezingBear Team.
 */
public interface RaknetInfo {

    /**
     *
     * From the MiNET documentation.
     * @url https://github.com/NiclasOlofsson/MiNET/blob/11beta/src/MiNET/MiNET/Net/MCPE%20Protocol%20Documentation.md
     *
     */
    public static final byte[] MAGIC = new byte[]{0x00, (byte) 0xff, (byte) 0xff, 0x00, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfd, (byte) 0xfd, (byte) 0xfd, (byte) 0xfd, 0x12, 0x34, 0x56, 0x78};

    public final static byte CONNECTED_PING = 0x00;
    public final static byte UNCONNECTED_PING = 0x01;
    public final static byte CONNECTED_PONG = 0x03;
    public final static byte DETECT_LOST_CONNECTIONS = 0x04;
    public final static byte UNCONNECTED_PONG = 0x1c;
    public final static byte OPEN_CONNECTION_REQUEST_1 = 0x05;
    public final static byte OPEN_CONNECTION_REPLY_1 = 0x06;
    public final static byte OPEN_CONNECTION_REQUEST_2 = 0x07;
    public final static byte OPEN_CONNECTION_REPLY_2 = 0x08;
    public final static byte CONNECTION_REQUEST_ACCEPTED = 0x10;
    public final static byte NEW_INCOMING_CONNECTION = 0x13;
    public final static byte DISCONNECTION_NOTIFICATION = 0x15;

    public static final byte ACK = (byte) 0xA0;
    public static final byte NACK = (byte) 0xC0;

}
