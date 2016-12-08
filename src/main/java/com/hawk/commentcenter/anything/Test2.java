package com.hawk.commentcenter.anything;

/**
 * @author junxiong.lang
 * @date 2016/12/5 17:38
 */
public class Test2 {
    public static void main(String[] args) {
        Long sum = 0l;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }

        System.out.println(sum);
    }
}
