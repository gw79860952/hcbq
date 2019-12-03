package com.gw.dev.hcbq.util;

public class StringUtil  {

    public static boolean isNotBlank(String s){
        if("".equals(s) || s == null){
            return false;
        }
        return true;
    }
}
