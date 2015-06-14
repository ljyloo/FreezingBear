package com.freezingbear.network.bridge.pe.protocol;


import com.freezingbear.network.bridge.pe.binary.Binary;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.ByteArrayOutputStream;
import java.io.Flushable;
import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Created by FreezingBear Team.
 */
public class Packet implements Flushable {

    public ByteOrder getEndianness(){

        return endianness;
    }


    public ByteOrder endianness = ByteOrder.BIG_ENDIAN;

    public byte packetId;

    private ByteArrayOutputStream byteArrayOutputStream;

    private SocketAddress address;
    private byte[] data;
    private ByteBuffer buffer;
    public byte[] magic = new byte[16];


    /**
     *
     * Packet Reading Part
     *
     */


    public Packet(SocketAddress address, byte[] data){
        this.address = address;
        this.data = data;
        this.buffer = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN);
    }

    public ByteBuffer getBuffer(){

        return buffer;
    }


    /**
     *
     * Packet Writing Part
     *
     */

    public Packet(){
        this(ByteOrder.BIG_ENDIAN);
    }

    public Packet(ByteOrder endianness){
        this.endianness = endianness;
        this.byteArrayOutputStream = new ByteArrayOutputStream();
    }

    public ByteArrayOutputStream getByteOutputStream(){

        return byteArrayOutputStream;
    }

    public void switchEndianess(){
        if(endianness == ByteOrder.BIG_ENDIAN){
            endianness = ByteOrder.LITTLE_ENDIAN;
        }else{
            endianness = ByteOrder.BIG_ENDIAN;
        }
    }

    public void put(byte[] data) throws IOException{
        byteArrayOutputStream.write(data);
    }

    public void put(long data, int length) throws IOException{
        byteArrayOutputStream.write(Binary.write(data,length, endianness));
    }

    public void putString(String s) throws IOException{
        putString(s, 2);
    }

    public void putString(String s, int len) throws IOException{
        put(s.length(), len);
        byteArrayOutputStream.write(s.getBytes());
    }

    public void putByte(byte b){
        byteArrayOutputStream.write(b);

    }

    public void putShort(short s) throws IOException{
        put(s, 2);
    }

    public void putInt(int i) throws IOException{
        put(i, 4);
    }

    public void putLong(long l) throws IOException{
        put(l, 8);
    }

    public void putFloat(float f) throws IOException{
        this.buffer.putFloat(f);
        byteArrayOutputStream.write(buffer.array());
        buffer.clear();
    }

    public void putTraid(int t) throws IOException{
        switchEndianess();
        put(t, 3);
        switchEndianess();
    }

    @Override
    public void flush() throws IOException{
        byteArrayOutputStream.flush();
    }
}
