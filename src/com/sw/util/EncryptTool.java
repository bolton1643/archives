package com.sw.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptTool {

  /**
    * ������ת�ַ�
    * @param b
    * @return
    */
   public static String byte2hex(byte[] b) {
       String hs = "";
       String stmp = "";
       for (int n = 0; n < b.length; n++) {
           stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
           if (stmp.length() == 1)
               hs = hs + "0" + stmp;
           else
               hs = hs + stmp;
       }
       return hs.toUpperCase();
   }

   /**
    * �û�������м���
    * ���ַ����md5������ϢժҪ
    * ��������㷨��������
    * @param in ����ܵ��ַ�
    * @return ���ܺ�Ĵ�д�ַ�
    */
   public static String passwdEncrypt(String in){

       try{
        MessageDigest algorithm=MessageDigest.getInstance("MD5");
        algorithm.update(in.getBytes());
        byte[] digest=algorithm.digest();

        return byte2hex(digest);

       }catch(NoSuchAlgorithmException alge){
          alge.printStackTrace();
       }

       return null;
   }


}
