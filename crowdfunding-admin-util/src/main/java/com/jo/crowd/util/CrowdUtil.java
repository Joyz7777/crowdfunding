package com.jo.crowd.util;


import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CrowdUtil {

    /**
     * 判断当前请求是否为Ajax请求
     *
     * @param request 请求对象
     * @return true：当前请求是Ajax请求
     * false：当前请求不是Ajax请求
     */
    public static boolean judgeRequestType(HttpServletRequest request) {

        // 1.获取请求消息头
        String acceptHeader = request.getHeader("Accept");
        String xRequestHeader = request.getHeader("X-Requested-With");

        // 2.判断

        return (acceptHeader != null && acceptHeader.contains("application/json"))
                ||

                (xRequestHeader != null && xRequestHeader.equals("XMLHttpRequest"));
    }


    public static String md5(String source ){
        if (source == null || source.length() ==0){

            throw new RuntimeException("");
        }

        try {
            String algorithm = "md5";

            MessageDigest instance = MessageDigest.getInstance(algorithm);

            byte[] input = source.getBytes();

            byte[] output = instance.digest(input);

            BigInteger bigInteger = new BigInteger(output);

            int radix = 16;

            String encoded = bigInteger.toString(radix);

            return encoded;

        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return  null;


    }

}
