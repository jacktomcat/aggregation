package com.gochinatv.cdn.api.basic.nio;

import java.nio.ByteBuffer;

/**
 * Created by jacktomcat on 17/7/4.
 *
 *
 * http://blog.csdn.net/sunyujia/article/details/2385727
 *
 */
public class ByteBufferTest2 {


    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(100);
        buffer.put((byte)129);
        buffer.put((byte)0x11);
        buffer.putChar('A');
        buffer.putShort((short)3);
        buffer.putInt(10);
        buffer.putFloat(34.5f);
        buffer.putLong(3456);
        buffer.putDouble(7654.d);

        System.out.println(buffer.toString());//pos=1 lim=10 cap=10

        buffer.flip();

        byte first = buffer.get();
        System.out.println("get byte (1bytes): " +first);

        byte second = buffer.get();
        System.out.println("get byte (1bytes): " +second);

        char three = buffer.getChar();
        System.out.println("get char (2bytes): " +three);

        short four = buffer.getShort();
        System.out.println("get short (2bytes): " +four);

        int five = buffer.getInt();
        System.out.println("get int(4bytes): " +five);

        float six = buffer.getFloat();
        System.out.println("get float(4bytes): " +six);

        long seven = buffer.getLong();
        System.out.println("get float(8bytes): " +seven);

        double eight = buffer.getDouble();
        System.out.println("get double(8bytes): " +eight);



        System.out.println(buffer.toString());//pos=2 lim=10 cap=10]
    }

}
