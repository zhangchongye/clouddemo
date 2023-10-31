package com.zcy.springcloud.config;

public class Test {
    public static void main(String[] args) {
        System.out.println(isNumber("-151.55aa"));
    }
    public static boolean isNumber(String str) {
        String pattern = "-?\\d+(\\.\\d+)?";
        return str.matches(pattern);
    }
}
