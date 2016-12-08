package com.hawk.commentcenter.designpattern;

/**
 * 同步块实现的单例
 *
 * @author junxiong.lang
 * @date 2016/11/29 14:42
 */
public class SingletonFromBlock {
    private static volatile SingletonFromBlock instance = null;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private SingletonFromBlock() {
    }

    public static SingletonFromBlock getInstance() {
        if (instance == null) {
            synchronized (SingletonFromBlock.class) {   // the same effect between "synchronized block and synchronized function"
                if (instance == null) {
                    instance = new SingletonFromBlock();
                    instance.setName("first Object");
                }
            }
        }
        return instance;
    }
}

/**
 * 静态内部类实现的单例
 */
class SingletonFromInnerClass {
    private static SingletonFromInnerClass instance;

    public SingletonFromInnerClass() {
    }

    private static class SingletonStaticFactory {
        private static SingletonFromInnerClass instance = new SingletonFromInnerClass();
    }

    public static SingletonFromInnerClass getInstance() {
        if (instance == null) {
            instance = SingletonStaticFactory.instance;
        }

        return instance;
    }
}
