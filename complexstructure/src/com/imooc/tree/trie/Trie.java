package com.imooc.tree.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Lesleey
 * @date 2020/5/30-12:07
 * @function
 */
public class Trie {

    private class Node{
        public boolean isWord;
        public Map<Character, Node> next;
        public Node(boolean isWord){
            this.isWord = isWord;
            next = new HashMap<>();
        }
        public Node(){
            this(false);
        }
    }

    private Node root;
    private int size;

    public int getSize(){
        return size;
    }

    public Trie(){
        root = new Node();
    }
    //向字典树中添加一个元素
    public void add(String word){
        Node cur = root;
        // 遍历 word，将每个字符添加到对应的节点中
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            // 如果字符对应的节点不存在，则添加节点
            cur.next.putIfAbsent(c, new Node());
            // 获取 该节点的下一个节点，重复操作
            cur = cur.next.get(c);
        }
        // 如果word存在，则不更新，如果不存在，则标识当前的节点为字符串的尾部。
        if(!cur.isWord && size ++ >= 0) cur.isWord = true;
    }
    //递归方式向字典树添加一个元素
    public void addRecursion(String word){
        add(root, word, 0);
    }
    private void add(Node node, String word, int index){
        if(word.length() == index){
            if(node.isWord) return;
            node.isWord = true;
            size ++;
        }
        node.next.putIfAbsent(word.charAt(index), new Node());
        add(node.next.get(word.charAt(index)), word, index + 1);
    }

    //从 trie 中查找元素
    public boolean search(String word){
        Node cur = root;
        // 遍历字符串，去字典树中寻找是否有对应的路径可以表示
        for (int i = 0; i < word.length(); i++) {
            Node node = cur.next.get(word.charAt(i));
            if(node == null) return false;
            cur = node;
        }
        return cur.isWord;
    }
    //使用递归方式查找元素
    public boolean searchRecursion(String word){
        return search(root, word, 0);
    }
    private boolean search(Node node, String word, int index){
        if(node == null) return false;
        if(index == word.length()) return node.isWord;
        return search(node.next.get(word.charAt(index)), word, index + 1);
    }
}
