package com.freezingbear.network.bridge.pe.binary;

import java.nio.ByteOrder;

/**
 * Created by FreezingBear Team.
 */

/**
 *
 * A port of RakLib by PocketMine Team to Java
 */
public class Binary {

    public static ByteOrder endianness = ByteOrder.BIG_ENDIAN;

    public static byte[] write(long b, int length){
        return write(b, length, ByteOrder.BIG_ENDIAN);
    }

    public static byte[] write(long b, int length, ByteOrder currentEndianness){
        byte[] re = new byte[length];
        for (int i = 0; i < length; i++){
            int order;
            if(currentEndianness == endianness){
                order = i;
            }else {
                order = --length - i;
            }
            re[order] = (byte) (b & 0xff);
        }
        return re;
    }

    //FreezingBear uses ByteBuffer to read packets, but I still wrote the reading part.

    public static long read(byte[] buff){
        return read(buff, 0, buff.length, ByteOrder.BIG_ENDIAN);
    }

    public static long read(byte[] buff, ByteOrder currentEndianness){
        return read(buff, 0, buff.length, currentEndianness);
    }

    public static long read(byte[] buff, int start, int end){
        return read(buff, start, end, ByteOrder.BIG_ENDIAN);
    }

    public static long read(byte[] buff, int start, int end, ByteOrder currentEndianness){
        long b = 0;
        for (int i = 0; i< end; i++){
            int order;
            if(currentEndianness == endianness){
                order = i + start;
            }else{
                order = --end + start - i;
            }
            b = b << 8;
            b = b | (buff[order] & 0xff);
        }
        return b;
    }


}
