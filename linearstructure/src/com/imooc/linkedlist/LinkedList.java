package com.imooc.linkedlist;

/**
 * @author Lesleey
 * @date 2020/5/29-8:57
 * @function
 */
public class LinkedList<E> {

    private class Node{
        public E e;
        public Node next;

        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }
        public Node(E e){
            this(e, null);
        }
    }

    private Node head;
    private int size;

    //返回链表的元素个数
    public int getSize(){
        return this.size;
    }

    //向索引为 index 的位置添加元素 E
    public void add(int index, E e){
        if(index < 0 || index > size) throw new IllegalArgumentException("参数无效！！");
        size ++;
        if(index == 0){
            head = new Node(e, head);
            return ;
        }
        Node pre = head;
        while(-- index  > 0) pre = pre.next;
        pre.next = new Node(e, pre.next);
    }

    //移除索引 index 位置的元素
    public E remove(int index){
        if(index < 0 || index >= size) throw new IllegalArgumentException("参数无效！！");
        size --;
        E e;
        if(index == 0){
            e = head.e;
            head = head.next;
        }else{
            Node pre = head;
            while( -- index  > 0) pre = pre.next;
            e = pre.e;
            pre.next = pre.next.next;
        }
        return e;
    }
    // 添加元素到头结点
    public void addFirst(E e){
        add(0, e);
    }

    // 添加元素到尾节点
    public void addLast(E e){
        add(size, e);
    }

    //移除头结点
    public E removeFirst(){
        return remove(0);
    }

    //移除尾节点
    public E removeLast(){
        return remove(size - 1);
    }

    public E get(int index){
        if(index < 0 || index >= size) throw new IllegalArgumentException("无效的参数");
        Node node = head;
        while(index -- > 0) node = node.next;
        return node.e;
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Node pre = head;
        while( pre != null){
            stringBuilder.append( pre.e + "--->");
            pre = pre.next;
        }
        stringBuilder.append("null, size = " + size);
        return stringBuilder.toString();
    }
}
