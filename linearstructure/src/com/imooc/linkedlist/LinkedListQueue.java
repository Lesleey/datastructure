package com.imooc.linkedlist;

import com.imooc.stackandqueue.Queue;

/**
 * @author Lesleey
 * @date 2020/5/29-11:05
 * @function
 */
public class LinkedListQueue <E> implements Queue<E> {

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
        public Node(){}
    }

    private Node head, tail;
    private int size;
    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size != 0;
    }

    @Override
    public void enqueue(E e) {
        if(tail == null){
            tail = new Node(e);
            head = tail;
        }else{
            tail.next = new Node(e);
            tail = tail.next;
        }
        size ++;

    }

    @Override
    public E dequeue() {
        if(size == 0) throw new IllegalArgumentException("队列中没有元素");
        E e = head.e;
        head = head.next;
        if(head == null) tail = null;
        size --;
        return e;
    }

    @Override
    public E getFront() {
        if(size == 0) throw new IllegalArgumentException("队列中没有元素");
        return head.e;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue: front ");

        Node cur = head;
        while(cur != null) {
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL tail");
        return res.toString();
    }
}
