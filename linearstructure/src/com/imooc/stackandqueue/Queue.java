package com.imooc.stackandqueue;

/**
 * @author Lesleey
 * @date 2020/5/28-20:51
 * @function
 */
public interface Queue<E> {
    //获得 队列中的元素个数
    int getSize();

    // 队列是否为空
    boolean isEmpty();

    //入队
    void enqueue(E e);

    //出队
    E dequeue();

    //获得队首元素
    E getFront();
}

