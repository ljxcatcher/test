package com.hawk.commentcenter.anything;

/**
 * @author junxiong.lang
 * @date 2016/12/2 15:39
 */
public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
        testA();
    }

    public static void testA() throws CloneNotSupportedException{
        ObjectA a = new ObjectA(12, "init asdf");
        ObjectA aReference = a;                         // shallow clone
        ObjectA aReplicate = (ObjectA) a.clone();       // deep clone

        // modify the prototype
        a.setNumber(5);
        a.setName("dandy");
        System.out.println(a);
        System.out.println(aReference);
        System.out.println(aReplicate);

        // modify the replicate
        aReplicate.setNumber(8);
        aReplicate.setName("lucky");
        System.out.println(a);
        System.out.println(aReference);
        System.out.println(aReplicate);
    }

}

class ObjectA implements Cloneable {
    private int number;
    private String name;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ObjectA(int number, String name) {
        this.number = number;
        this.name = name;
    }

    @Override
    public String toString() {
        return "ObjectA{" +
                "number=" + number +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
