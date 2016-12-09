package com.hawk.commentcenter.collection;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * 简单实现一个双向链表(注意不是循环链表)，并提供基本方法
 *
 * @author junxiong.lang
 * @date 2016/12/9 14:14
 */
public class MainExt {
    private static MyLinkedList myLinkedList;

    static {
        myLinkedList = new MyLinkedList<>();
        myLinkedList.add("abc");
        myLinkedList.add("def");
        myLinkedList.add("ghi");
    }

    public static void main(String[] args) {
//       testAdd();
//        testRemove();
//        testRemoveFirst();
        testGetFirst();
        testGetLast();
    }

    private static void testAdd() {
        myLinkedList.add("xyz");
        System.out.println(myLinkedList.toString());
    }

    private static void testRemove() {
        System.out.println(myLinkedList.remove("def"));

        System.out.println(myLinkedList);
    }

    private static void testRemoveFirst() {
        System.out.println(myLinkedList.removeFirst());

        System.out.println(myLinkedList);
    }

    private static void testGetFirst() {
        System.out.println(myLinkedList.getFirst());
    }

    private static void testGetLast() {
        System.out.println(myLinkedList.getLast());
    }

    private static class MyLinkedList<E> implements List<E> {
        private int size;                   // 大小
        private transient Node<E> first;    // 头结点
        private transient Node<E> last;     // 尾结点

        @Override
        public int size() {
            return size;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator<E> iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return null;
        }

        /**
         * 增加元素
         *
         * @param e
         * @return
         */
        @Override
        public boolean add(E e) {
            Node<E> cur = new Node<>(last, e, null);
            if (last == null) {
                first = cur;        // 链表中无数据,设置头结点与尾结点均为当前结点
                last = cur;
            } else {
                cur.prev = last;
                last.next = cur;    // 链表中有数据,设置last.next为当前节点
                last = cur;
            }

            this.size++;            // 链表size+1
            return true;            // 未出现异常，返回true
        }

        /**
         * 移除元素
         *
         * @param o
         * @return
         */
        @Override
        public boolean remove(Object o) {
            if (size < 1) {
                throw new RuntimeException("Empty list can not remove any element");
            }

            Node<E> traverseNode = first;
            do {
                if (traverseNode.item.equals(o)) {
                    Node<E> prev = traverseNode.prev;
                    Node<E> next = traverseNode.next;
                    if (prev == null) {
                        // 删除的是链表中的头结点
                        first = next;
                        first.prev = null;
                    } else if (next == null) {
                        // 删除的是链表中的尾结点
                        last = prev;
                        last.next = null;
                    } else {
                        // 删除的是链表中间的结点
                        prev.next = next;
                        next.prev = prev;
                    }

                    traverseNode.item = null;         // gc
                    size--;
                    return true;                      // 遍历到指定元素，删除后退出
                }

                traverseNode = traverseNode.next;
            } while (traverseNode != null);

            return false;
        }

        /**
         * 移除第一个元素
         *
         * @return
         */
        public boolean removeFirst() {
            if (size < 1) {
                throw new RuntimeException("Empty list can not remove any element");
            }

            Node<E> secondNode = first.next;
            first.next = null;          // gc
            first = secondNode;
            if (secondNode != null) {   // 第二个结点不为空，设置前结点为空
                secondNode.prev = null;
            } else {
                last = null;            // 第二个节点为空，设置尾结点为空
            }

            size--;
            return true;
        }

        /**
         * 获取头结点元素
         *
         * @return
         */
        public E getFirst() {
            if (size < 1) {
                throw new RuntimeException("Empty list can not remove any element");
            }

            return first.item;
        }

        /**
         * 获取尾结点元素
         *
         * @return
         */
        public E getLast() {
            if (size < 1) {
                throw new RuntimeException("Empty list can not remove any element");
            }

            return last.item;
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends E> c) {
            return false;
        }

        @Override
        public boolean addAll(int index, Collection<? extends E> c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {
        }

        @Override
        public E get(int index) {
            return null;
        }

        @Override
        public E set(int index, E element) {
            return null;
        }

        @Override
        public void add(int index, E element) {

        }

        @Override
        public E remove(int index) {
            return null;
        }

        @Override
        public int indexOf(Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(Object o) {
            return 0;
        }

        @Override
        public ListIterator<E> listIterator() {
            return null;
        }

        @Override
        public ListIterator<E> listIterator(int index) {
            return null;
        }

        @Override
        public List<E> subList(int fromIndex, int toIndex) {
            return null;
        }

        /**
         * 迭代器难写，只好以toString代替了 ╯﹏╰
         *
         * @return
         */
        @Override
        public String toString() {
            return "MyLinkedList{" +
                    "size=" + size +
                    ", first=" + first +
                    ", last=" + last +
                    '}';
        }

        /**
         * 结点
         *
         * @param <E>
         */
        private class Node<E> {
            E item;         // 当前结点元素
            Node<E> prev;   // 前一个结点
            Node<E> next;   // 后一个结点

            private Node(Node<E> prev, E item, Node<E> next) {
                this.item = item;
                this.prev = prev;
                this.next = next;
            }

            @Override
            public String toString() {
                return "Node{" +
                        "item=" + item +
                        '}';
            }
        }
    }
}
