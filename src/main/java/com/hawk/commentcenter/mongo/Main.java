package com.hawk.commentcenter.mongo;

import org.bson.types.ObjectId;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;

/**
 * @author junxiong.lang
 * @date 2016/12/7 14:20
 */
public class Main {
    private static Jongo jongo;
    private static MongoCollection foos;

    static {
        JongoFactory jongoFactory = new JongoFactory("127.0.0.1", "27017", "test", "", "");
        try {
            jongo = jongoFactory.getObject();
            foos = jongo.getCollection("foos");
        } catch (Exception e) {
            System.out.println("can not connect current mongodb." + e);
        }
    }

    /**
     * 查询单个元素, #为占位符
     */
    private static void testFindOne() {
        String name = "ljx";
        Foo foo = foos.findOne("{name:#}", name).as(Foo.class);

        System.out.println(foo);
    }

    /**
     * 查询foos集合中的所有元素
     */
    private static void testFind() {
        MongoCursor<Foo> all = foos.find().as(Foo.class);
        while(all.hasNext()) {
            System.out.println(all.next());
        }
    }

    /**
     * 保存-save
     * @throws CloneNotSupportedException
     */
    private static void testSave() throws CloneNotSupportedException {
        Foo foo = new Foo(15, "ljx", "ChungKing");
        foos.save(foo);
        foos.save(foo.clone());
    }

    /**
     * 保存-insert
     * @throws CloneNotSupportedException
     */
    private static void testInsert() throws CloneNotSupportedException {
        Foo foo = new Foo(15, "ljx", "ChungKing");
        foos.insert(foo);
        foos.insert(foo.clone());
    }

    /**
     * 更改
     */
    private static void testUpdate() {
        Integer id1 = 15;
        foos.update("{id:#}", id1).with("{$inc:{id:#}}", 2);                // 更改id

        Integer id2 = 17;
        String name = "bullshit";
        foos.update("{id:#}", id2).with("{$set:{name:#}}", name);           // 更改name

        foos.update("{id:#}", id1).multi().with("{$set:{name:#}}", "holy"); // 批量更改name
    }

    /**
     * 删除
     */
    private static void testRemove() {
        String name = "coco";

        foos.remove("{name:#}", name);                          // 根据匹配条件会全部删除

        foos.remove(new ObjectId("5847b9ce6d9a31cbb91e050f"));  // 删除相应objectId的元素
    }

    private static class Foo implements Cloneable{
        private Integer id;
        private String name;
        private String address;

        public Foo() {
        }

        public Foo(Integer id, String name, String address) {
            this.id = id;
            this.name = name;
            this.address = address;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        @Override
        public String toString() {
            return "Foo{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", address='" + address + '\'' +
                    '}';
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    public static void main(String[] args) throws CloneNotSupportedException {
//        testFindOne();
//        testFind();
//        testSave();
//        testInsert();
//        testUpdate();
        testRemove();
    }

}
