package com.hawk.commentcenter.serialize;

import java.io.*;

/**
 * @author junxiong.lang
 * @date 2016/12/1 16:22
 */
public class Foo implements Serializable {
    private float score = 99.5f;
    private double salary = 2000.5d;
    private String name = "发大水";
    private static String address = "阿肯色州宾夕法尼亚大道";       // can not serialized, value is ...
    private transient String website = "http://doityourself.com";   // can not serialized, value is null
    private transient float rate = 0.33f;                           // can not serialized, value is 0.0

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getAddress() {
        return address;
    }

    public static void setAddress(String address) {
        Foo.address = address;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String toString() {
        return "Foo{" +
                "score=" + score +
                ", salary=" + salary +
                ", name='" + name + '\'' +
                ", website='" + website + '\'' +
                ", rate=" + rate +
                '}';
    }

    public static void write() throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("foo.obj"));
        oos.writeObject(new Foo());
        oos.flush();
        oos.close();
    }

    public static void read() throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("foo.obj"));
        Foo foo = (Foo)ois.readObject();
        ois.close();

        System.out.println(foo);
        System.out.println("address=" + foo.address);
    }

    public static void remove() throws Exception{
        File file = new File("foo.obj");
        if(file.exists()) {
            System.out.println(file.delete());
        }
    }

    public static void main(String[] args) throws Exception {
        write();
        read();
        remove();
    }
}
