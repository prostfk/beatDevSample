package by.medvedev.task.model.util;


import org.apache.commons.codec.digest.DigestUtils;

import java.util.Date;

public class StringsUtil {

    public static String hash(String str){
        return DigestUtils.md5Hex(str);
    }


    public static boolean isUserOffline(long lastVisited, long current){
        long minutes = (current - lastVisited)/60000;
        System.out.println("minutes: " + minutes);
        return minutes > 1;



    }


}
