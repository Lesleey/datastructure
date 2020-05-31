package com.imooc.stackandqueue;

/**
 * @author Lesleey
 * @date 2020/5/28-20:43
 * @function
 */
public interface Stack<E> {
    //将元素入栈
    public void push(E item) ;
    //将元素出栈
    public  E pop() ;
    // 获取栈顶元素
    public  E peek() ;
    // 栈是否为空
    public boolean isEmpty();
}
