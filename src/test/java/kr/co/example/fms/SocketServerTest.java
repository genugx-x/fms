package kr.co.example.fms;

import kr.co.qsol.fishery.util.ByteUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;


class SocketServerTest {

    @Test
    void byteToLong변환테스트(){
        Assertions.assertEquals(128, ByteUtil.byteToInt(ByteUtil.hexStringToByteArray("8000")));
        Assertions.assertEquals(8403471, ByteUtil.byteToInt(ByteUtil.hexStringToByteArray("0f3a8000")));
    }

    @Test
    void byteOrder변환테스트(){

        //ByteBuffer buffer = ByteBuffer.wrap(ByteUtil.hexStringToByteArray("8000"));
        ByteBuffer buffer = ByteBuffer.wrap(ByteUtil.hexStringToByteArray("0f3a8000"));

        buffer = buffer.order(ByteOrder.LITTLE_ENDIAN);

        System.out.println("1:" + ByteUtil.byteToHexString(buffer.array()));
        System.out.println("1:" + ByteUtil.byteArrayToBinaryString(buffer.array()));
        System.out.println("2:" + buffer.getInt());

    }

}