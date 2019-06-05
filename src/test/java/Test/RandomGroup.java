package Test;

import java.lang.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomGroup {
    /*
     * 随机生成10组0~9的不重复数字
     */
    public static void main(String[] args) {
//        for (int i = 0; i < 1; i++) {
//            randomGroup();
//            System.out.println(" 第" + (i + 1) + "组");
//        }

        String a  = "124";
        boolean orderNumeric = isOrderNumeric(a);
        System.out.println(orderNumeric);
        System.out.println(Integer.parseInt(a)+1);

        String s = truncateHeadString(a, 1);
        System.out.println(s);

    }

    public static String truncateHeadString(String origin, int count) {
        if (origin == null || origin.length() < count) {
            return null;
        }
        char[] arr = origin.toCharArray();
        char[] ret = new char[arr.length - count];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = arr[i + count];
        }
        return String.copyValueOf(ret);
    }


    //生成10个10以内的随机数
    public static void randomGroup() {
        int[] a = new int[10];

        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * 11);
            for (int j = 0; j < i; j++) {
                if (a[j] == a[i]) {
                    i--;
                }
            }
        }

        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }


    /**
     * 是否是连续数字
     *
     * @param numOrStr
     * @return
     */
    public static boolean isOrderNumeric(String numOrStr) {
        boolean flag = true;
        for (int i = 0; i < numOrStr.length(); i++) {
            if (i > 0) {// 判断如123456
                int num = Integer.parseInt(numOrStr.charAt(i) + "");
                int num_ = Integer.parseInt(numOrStr.charAt(i - 1) + "") + 1;
                if (num != num_) {
                    flag = false;
                    break;
                }
            }
        }
        if (!flag) {
            for (int i = 0; i < numOrStr.length(); i++) {
                if (i > 0) {// 判断如654321
                    int num = Integer.parseInt(numOrStr.charAt(i) + "");
                    int num_ = Integer.parseInt(numOrStr.charAt(i - 1) + "") - 1;
                    if (num != num_) {
                        flag = false;
                        break;
                    }
                }
            }
        }
        return flag;
    }


    public static boolean getArr(int[] arr){
        if (arr.length<3) {
            return false;
        }

        boolean b = false;
        for(int i =1;i<arr.length-1;i++){
            if (arr[i]*2 != arr[i-1]+arr[i+1]) {
                b = false;
                break;
            }
            if (Math.abs(arr[i+1]-arr[i])!=1) {
                b = false;
                break;
            }
            if ((arr[i+1]-arr[i]) != (arr[i]-arr[i-1])) {
                b = false;
                break;
            }
            b =true ;
            continue;
        }
        return b;
    }


}