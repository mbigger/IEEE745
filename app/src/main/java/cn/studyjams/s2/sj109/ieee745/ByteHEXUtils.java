package cn.studyjams.s2.sj109.ieee745;

/**
 * Created by Administrator on 2017/6/8.
 */

public class ByteHEXUtils {
    static final byte[] HEX_TABLE = {0,0x1,0x2,0x3,0x4,0x5,0x6,0x7,0x8,0x9,0xA,0xB,0xC,0xD,0xE,0xF};
    static final char[] HEX_CHAR_TABLE = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};

    public static String toHexString(byte[] data) throws Exception{
        if(data==null || data.length==0)return null;
        byte[] hex = new byte[data.length*2];
        int index = 0;
        for(byte b: data){
            int v = b & 0xFF;
            hex[index++] = (byte)HEX_CHAR_TABLE[v>>>4];
            hex[index++] = (byte)HEX_CHAR_TABLE[v&0xF];
        }
        return new String(hex);
    }

    public static byte[] hexToByte(String hexString){
        if(hexString==null || hexString.length()==0) return null;
        if(hexString.length()%2!=0) throw new RuntimeException();
        byte[] data = new byte[hexString.length()/2];
        char[] chars = hexString.toCharArray();
        for(int i=0;i<hexString.length();i=i+2){
            data[i/2] = (byte)(HEX_TABLE[getHexCharValue(chars[i])]<<4 | HEX_TABLE[getHexCharValue(chars[i+1])]);
        }
        return data;
    }

    private static int getHexCharValue(char c){
        int index = 0;
        for(char c1: HEX_CHAR_TABLE){
            if(c==c1){
                return index;
            }
            index++;
        }
        return 0;
    }
}
