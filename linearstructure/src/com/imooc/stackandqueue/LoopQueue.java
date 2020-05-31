package com.imooc.stackandqueue;

/**
 * @author Lesleey
 * @date 2020/5/28-22:09
 * @function
 */
public class LoopQueue<T> implements Queue<T>{
    private T[] data;
    private int font;
    private int tail;
    private int size;
    public LoopQueue(int cap){
        data = (T[])new Object[cap + 1];
    }
    public LoopQueue(){
        this(4);
    }


    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size != 0;
    }


    @Override
    public void enqueue(T t) {
        //按照图中描述, 当 (tail + 1) % n = font 进行扩容
        if((tail + 1) % data.length == font) resize(data.length * 2);
        // 放置元素， 修改 tail 指向
        data[tail] = t;
        tail = (tail + 1)% data.length;
        size ++;
    }

    @Override
    public T dequeue() {
        if(size == 0) return null;
        //出队，修改 font 指针指向
        T ret = data[font];
        font = (font + 1)% data.length;
        size -- ;
        return ret;
    }

    @Override
    public T getFront() {
        return null;
    }

    private void resize(int newcap){
        T[] newArr =(T[]) new Object[newcap];
        int len = data.length;
        for (int i = 0; i <= size; i++) {
            newArr[i] = data[(font + i) % len];
        }
        this.data = newArr;
        font = 0;
        tail = size;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("数组中的元素 [");
        for (int i = 0; i < data.length; i++) {
            stringBuilder.append(" " + data[i]);
        }
        stringBuilder.append("] , 队列头font = " + font);
        stringBuilder.append(" , 队列尾tail = " + tail);
        return stringBuilder.toString();
    }
}
