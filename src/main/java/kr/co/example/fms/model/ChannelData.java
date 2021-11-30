package kr.co.qsol.fishery.model;

import kr.co.qsol.fishery.util.ByteUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Data
@NoArgsConstructor
public class ChannelData {

    private Integer no;
    private Integer max;
    private Integer min;
    private Integer deviation;
    private Integer avg;

    public ChannelData(int no, byte[] payload) {

        this.no = no+1;
        max = ByteUtil.byteToInt(Arrays.copyOfRange(payload, 0,4));
        min = ByteUtil.byteToInt(Arrays.copyOfRange(payload, 4,8));
        deviation = ByteUtil.byteToInt(Arrays.copyOfRange(payload, 8,12));
        avg = ByteUtil.byteToInt(Arrays.copyOfRange(payload, 12,16));
    }

    @Override
    public String toString() {
        return "ch" +no+ "{" +
                ", max=" + max +
                ", min=" + min +
                ", deviation=" + deviation +
                ", avg=" + avg +
                '}';
    }

}
