package kr.co.example.fms.model;

import kr.co.example.fms.util.ByteUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static kr.co.example.fms.util.ByteUtil.byteToHexString;

@Data
@Document(collection = "packet")
@TypeAlias("MOF100Packet")
@NoArgsConstructor
public class MOF100Packet {

    @Version
    private Long version;

    @Id
    private String id;

    public Date createAt;

    private String hostAddress;
    private int sourcePort;
    private int serverPort;
    private byte[] original;
    private byte[] stx;
    private int deviceId;
    private int dataType;
    private int countNum;
    private int dataLength;
    private List<ChannelData> payload = new ArrayList<>();
    private byte[] crc;
    private byte[] etx;


    public MOF100Packet(String hostAddress, int sourcePort, int serverPort, DatagramPacket packet) {

        this.createAt = new Date();

        this.hostAddress = hostAddress;
        this.sourcePort = sourcePort;
        this.serverPort = serverPort;

        this.original = new byte[packet.getLength()];

        System.arraycopy(packet.getData(), packet.getOffset(), original, 0, packet.getLength());

        this.stx = Arrays.copyOfRange(original, 0, 2);
        this.deviceId = Byte.toUnsignedInt(original[2]);
        this.dataType =  Byte.toUnsignedInt(original[3]);
        this.countNum = ByteUtil.byteToInt(Arrays.copyOfRange(original, 4,6));
        this.dataLength = ByteUtil.byteToInt(Arrays.copyOfRange(original, 6,8));

        int payloadOffSet = 8 + dataLength;
        byte[] payloadPacket = Arrays.copyOfRange(original, 8,payloadOffSet);

       for(int i=0; i<dataLength/16; i++){
            payload.add(new ChannelData(i, Arrays.copyOfRange(payloadPacket, i*16, i*16+16)));
        }

        this.crc = Arrays.copyOfRange(original, payloadOffSet, payloadOffSet + 2);
        this.etx = Arrays.copyOfRange(original, payloadOffSet +2, payloadOffSet + 4);
    }


    public List<ChannelData> getPayload() {
        if(this.dataLength==4) return payload.stream().filter(pl-> pl.getNo() ==7 || pl.getNo() ==8).collect(Collectors.toList());
        else return payload.stream().filter(pl-> pl.getNo() ==1 || pl.getNo() ==7).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "MOF100Packet{" +
                "source=" + hostAddress + ":" + sourcePort +
                ", original=" + byteToHexString(original) +
                ", stx=" + byteToHexString(stx) +
                ", deviceId=" + deviceId +
                ", dataType=" + dataType +
                ", countNum=" + countNum +
                ", dataLength=" + dataLength +
                ", payload=" + (payload.stream().map(channel->channel.toString()).collect(Collectors.joining())) +
                ", crc=" + byteToHexString(crc) +
                ", etx=" + byteToHexString(etx) +
                '}';
    }

}


