package com.imooc.stackandqueue;

import com.imooc.array.Array;

/**
 * @author Lesleey
 * @date 2020/5/28-20:52
 * @function
 */
public class ArrayQueue<E> implements Queue<E> {
    private Array<E> array = new Array<>();
    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return getSize() != 0;
    }

    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    @Override
    public E getFront() {
        return array.get(0);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("队首[");
        for (int i = 0; i < array.getSize(); i++) {
            stringBuilder.append(" " + array.get(i));
        }
        stringBuilder.append("] 队尾");
        return stringBuilder.toString();
    }
}
