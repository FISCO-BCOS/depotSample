package org.bcos.depot.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class HexadecimalUtil {
    private static Logger logger = LoggerFactory.getLogger(HexadecimalUtil.class);

    /**
     * Creates byte array representation of HEX string.<br>
     *
     * @param s
     *            string to parse
     * @return
     */
    public static byte[] fromHexString(String s) {
        int length = s.length() / 2;
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++) {
            bytes[i] = (byte) ((Character.digit(s.charAt(i * 2), 16) << 4) | Character
                    .digit(s.charAt((i * 2) + 1), 16));
        }
        return bytes;
    }

    /**
     * Creates HEX String representation of supplied byte array.<br/>
     * Each byte is represented by a double character element from 00 to ff
     *
     * @param fieldData
     *            to be tringed
     * @return
     */
    public static String toHexString(byte[] fieldData) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fieldData.length; i++) {
            int v = (fieldData[i] & 0xFF);
            if (v <= 0xF) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString();
    }

    public  static int searchByte(byte[] data, byte value) {
        int size = data.length;
        for (int i = 0; i < size; ++i) {
            if (data[i] == value) {
                return i;
            }
        }
        return -1;
    }
    public static String hexToString(String hex){

        StringBuilder finalString = new StringBuilder();
        StringBuilder tempString = new StringBuilder();

        for( int i=0; i<hex.length()-1; i+=2 ){
            String output = hex.substring(i, (i + 2));
            int decimal = Integer.parseInt(output, 16);
            finalString.append((char)decimal);
            tempString.append(decimal);
        }
        return finalString.toString();
    }

    public  static String getByteString(byte[] data) {
        String info2 = new String();
        int offset = searchByte(data,(byte)0);
        try {
            //logger.error("decode getByteString {} {} {}",Arrays.toString(data),offset,toHexString(data));
            info2 = new String(data, 0, offset,"UTF-8");
            //logger.error("info2: {}", info2.getBytes().toString());
            //logger.error("decode getByteString {}",info2);
    }
    catch (Exception e) {
            logger.error("decode getByteString {}", Arrays.toString(data), e);
        }
        return info2;
    }
}
