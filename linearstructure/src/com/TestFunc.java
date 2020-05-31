package com;

import com.imooc.array.Array;
import com.imooc.linkedlist.LinkedList;
import com.imooc.linkedlist.VirtualHeadLinkedList;
import com.imooc.stackandqueue.*;
import org.junit.Test;

/**
 * @author Lesleey
 * @date 2020/5/28-20:34
 * @function
 */
public class TestFunc {
    @Test
    public void testArray(){
        Array<Integer> array = new Array<>();
        array.addLast(1);
        array.addLast(2);
        array.addLast(3);
        array.addLast(4);
        System.out.println(array);
        array.remove(2);
        System.out.println(array);
        array.removeFirst();
        System.out.println(array);
        array.removeLast();
        System.out.println(array);
        array.add(1, 3);
        System.out.println(array);
    }

    @Test
    public void testStack(){
        Stack<Integer> stack = new ArrayStack<>();
        stack.push(1);
        System.out.println(stack);
        stack.push(2);
        System.out.println(stack);
        stack.pop();
        System.out.println(stack);
    }
    @Test
    public void testQueue(){
        Queue<Integer> queue = new ArrayQueue<>();
        queue.enqueue(1);
        System.out.println(queue);
        queue.enqueue(2);
        System.out.println(queue);
        queue.dequeue();
        System.out.println(queue);
    }

    @Test
    public void testLoopQueue(){
        Queue<Integer> queue = new LoopQueue<>();
        queue.enqueue(1);
        System.out.println(queue);
        queue.enqueue(2);
        System.out.println(queue);
        queue.enqueue(3);
        queue.enqueue(4);
        System.out.println(queue);
        queue.dequeue();
        System.out.println(queue);
        queue.dequeue();
        System.out.println(queue);
        queue.enqueue(5);
        System.out.println(queue);
        queue.enqueue(6);
        System.out.println(queue);
    }

    @Test
    public void testLinkedList(){
        VirtualHeadLinkedList<Integer> linkedList = new VirtualHeadLinkedList<>();
        //LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addFirst(1);
        linkedList.addFirst(3);
        linkedList.addFirst(4);
        linkedList.add(1, 2);
        System.out.println(linkedList);
        linkedList.remove(2);
        System.out.println(linkedList);
        linkedList.removeLast();
        System.out.println(linkedList);

    }
}
