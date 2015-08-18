package com.freezingbear.network.mcpe.binary;

import java.nio.ByteBuffer;
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
            if(currentEndianness != endianness){
                order = i;
            }else {
                order = --length - i;
            }
            re[order] = (byte) (b & 0xFF);
            b = b >> 8;
        }
        return re;
    }

    public static int readLTriad(ByteBuffer byteBuffer){

        //Suck it, Mojang!

        //LITTLE_ENDIAN

        byte[] bytes = new byte[3];
        byteBuffer.get(bytes);
        bytes[1] *= 256;
        bytes[2] *= 65536;
        return bytes[0] + bytes[1] + bytes[2];
    }


}
