package com.imooc.stackandqueue;

import com.imooc.array.Array;

/**
 * @author Lesleey
 * @date 2020/5/28-20:44
 * @function
 */
public class ArrayStack<E> implements Stack<E> {
    private Array<E> array = new Array<>();
    @Override
    public void push(E item) {
        array.addLast(item);
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public E peek() {
        return array.get(array.getSize() - 1);
    }

    @Override
    public boolean isEmpty() {
        return array.getSize() != 0;
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("栈底 [");
        for (int i = 0; i < array.getSize(); i++) {
            stringBuilder.append(" " + array.get(i));
        }
        stringBuilder.append("] 栈顶");
        return stringBuilder.toString();
    }
}
