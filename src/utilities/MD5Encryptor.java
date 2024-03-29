package utilities;

import java.security.MessageDigest;

/**
 * A md5 encryption generator
 */
public class MD5Encryptor {

    // Your can use this function to neglect encryption
//    public static String getMD5(String str){
//        return str;
//    }

    public static String getMD5(String str){
        MessageDigest encryptor = null;
        try{
            encryptor = MessageDigest.getInstance("MD5");
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
        byte[] md5Bytes = encryptor.digest(str.getBytes());
        return bytesToHexString(md5Bytes);
    }

    private static String bytesToHexString(byte[] bytes){
        StringBuilder  stringBuilder = new StringBuilder();
        if(bytes == null || bytes.length == 0){
            return null;
        }
        for(byte b: bytes){
            String hex = Integer.toHexString(b & 0xff);
            if(hex.length() < 2){
                hex = "0" + hex;
            }
            stringBuilder.append(hex);
        }
        return stringBuilder.toString();
    }

}
