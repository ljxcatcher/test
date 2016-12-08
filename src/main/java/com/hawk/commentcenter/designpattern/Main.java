package com.hawk.commentcenter.designpattern;

/**
 * @author junxiong.lang
 * @date 2016/11/29 14:48
 */
public class Main {
    private static int counter = 10;

    public static void main(String[] args) {
        do {
            new MyThread(SingletonFromBlock.getInstance()).start();
        } while(--counter != 0);
    }

}

class MyThread extends Thread {
    private SingletonFromBlock instance;
    public MyThread(SingletonFromBlock singleton) {
        this.instance = singleton;
    }

    @Override
    public void run() {
        System.out.println("呵呵." + instance.getName());
    }
}