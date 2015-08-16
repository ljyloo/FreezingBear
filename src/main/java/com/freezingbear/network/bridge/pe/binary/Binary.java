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


}
