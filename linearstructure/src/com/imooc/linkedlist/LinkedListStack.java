package com.imooc.linkedlist;

import com.imooc.stackandqueue.Stack;

/**
 * @author Lesleey
 * @date 2020/5/29-11:00
 * @function
 */
public class LinkedListStack<E> implements Stack<E> {
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
    private Node stackTop ;
    private int size;
    //入栈
    @Override
    public void push(E item) {
        stackTop = new Node(item, stackTop);
        size ++;
    }
    //出栈
    @Override
    public E pop() {
        if(size <= 0) throw new IllegalArgumentException("无效的参数");
        size --;
        E e = stackTop.e;
        stackTop = stackTop.next;
        return e;
    }

    @Override
    public E peek() {
        if(size <= 0) throw new IllegalArgumentException("无效的参数");
        return stackTop.e;
    }

    @Override
    public boolean isEmpty() {
        return size != 0;
    }
}
