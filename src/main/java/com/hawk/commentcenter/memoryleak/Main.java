package com.hawk.commentcenter.memoryleak;

import java.util.HashSet;
import java.util.Set;

/**
 * @author junxiong.lang
 * @date 2016/12/6 14:05
 */
public class Main {
    public static void main(String[] args) {
        testMemoryLeak();
    }

    /**
     * 删除前有两个元素是因为Foo实现了equals和hashCode方法，Set去重所以只有2个
     * 删除后还有两个元素是因为元素修改其id的值导致hashcode变化，从而无法定位到原来的hashcode，所以无法删除相应元素从而导致了内存泄漏
     *
     */
    private static void testMemoryLeak() {
        Set<Foo> fooSet = new HashSet<>();
        Foo foo1 = new Foo(1, "ljx");
        Foo foo2 = new Foo(2, "ljx");
        Foo foo3 = new Foo(1, "ljx");
        fooSet.add(foo1);
        fooSet.add(foo2);
        fooSet.add(foo3);

        System.out.println("改id前的foo2.hash:" + foo2.hashCode());
        foo2.setId(5);
        System.out.println("改id后的foo2.hash:" + foo2.hashCode());

        System.out.println("改id前的foo3.hash:" + foo3.hashCode());
        foo3.setId(8);
        System.out.println("改id后的foo3.hash:" + foo3.hashCode());


        System.out.println("删除前的大小size:"+fooSet.size());
        fooSet.remove(foo2);
        System.out.println("删除后的大小size:"+fooSet.size());
    }

    /**
     * 静态内部类
     */
    private static class Foo {
        private Integer id;
        private String name;

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

        public Foo(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Foo foo = (Foo) o;

            if (id != null ? !id.equals(foo.id) : foo.id != null) return false;
            return name != null ? name.equals(foo.name) : foo.name == null;
        }

        @Override
        public int hashCode() {
            int result = id != null ? id.hashCode() : 0;
            result = 31 * result + (name != null ? name.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Foo{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

}
