package com.imooc.tree.redblacktree;

import java.util.LinkedList;

/**
 * @author Lesleey
 * @date 2020/5/29-12:23
 * @function
 */
public class RBTree<E extends Comparable<E>> {
    private class Node{
        public static final boolean RED = true;
        public static final boolean BLACK = true;
        public E e;
        public Node left, right;
        public boolean color;
        public Node(E e){
            this.e = e;
            //红黑树添加新节点时，永远添加红节点，表示和父节点融合（2-3树）
            this.color = RED;
        }
    }

    private Node root;
    private int size;

    //返回树的节点个数
    public int getSize(){
        return size;
    }

    //添加元素
    public void addNode(E e){
        root = addNode(root, e);
        root.color = Node.BLACK;
    }


    private Node addNode(Node root, E e){
        // 使用e 构建子树，返回重构之后的树
        if(root == null) {
            size ++;
            return new Node(e);
        }
        // 根据 e 的值和当前值作比较，决定将 e 插入到左（右）子树中
        if(root.e.compareTo(e) < 0){
            root.right = addNode(root.right, e);
        }else if(root.e.compareTo(e) > 0){
            root.left = addNode(root.left, e);
        }

        //进行左旋
        if(isRed(root.right) && !isRed(root.left)){
            root = leftRotate(root);
        }
        //进行右旋
        if(isRed(root.left) && isRed(root.left.left)){
            root = rightRotate(root);
        }
        //颜色翻转
        if(isRed(root.left) && isRed(root.right)){
            root = flipColors(root);
        }
        // 构建完成之后，将重构之后的树返回
        return root;
    }

    /*
    *  当新添加节点在二节点右侧时，根据2-3树的性质，红节点只能左倾斜，所以进行左旋,左旋并不维持红黑树的性质
    *     node             x
    *    /  \             / \
    *   T1  X            node T3
    *      / \          / \
    *     T2 T3        T1 T2
    * */
    private Node leftRotate(Node node){
        Node x = node.right;
        node.right = x.left;
        x.left = node;
        // 由于 x 节点要替换到原来 node 的位置，所以x的颜色要和node一致
        x.color = node.color;
        // 由于node节点要与 x 节点表示2-3 树的2节点， 所以描为红色
        node.color = Node.RED;
        return x;
    }
    /*
    *  颜色翻转，当插入的元素在3节点的右侧时，会变成4节点，在2-3树中，4节点会分裂成树， 所以只需要将3个节点的颜色变为黑色，就表示分裂成了3个2节点，
    *  因为头结点要和父节点融合，所以将头结点变为红色。
    * */
    private Node flipColors(Node node){
        node.color = Node.RED;

        node.left.color = Node.BLACK;
        node.right.color = Node.BLACK;
        return node;
    }
    /*
    *  当插入的节点在3节点的左侧时，会变成 red -> red -> black 连续出现了两个红节点，所以进行右旋
    *      node                     x
    *      / \                     / \
    *  RED x  t2  ---右旋-->      y    node
    *     / \                        / \
    *RED Y  T1                       T1 T2
    * */
    private Node rightRotate(Node node){
        Node x = node.left;
        node.left = x.right;
        x.right = node;

        // 由于 x 节点要替换到原来 node 的位置，所以x的颜色要和node一致
        x.color = node.color;
        // 由于node要与父节点表示3节点，着红色
        node.color =  Node.RED;
        return flipColors(x);
    }
    //返回节点的颜色
    public boolean isRed(Node root){
        if(root == null) return false;
        return root.color == Node.RED;
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        print(root, 0, stringBuilder);
        return stringBuilder.toString();
    }
    private void print(Node node, int depth, StringBuilder stringBuilder){
        if(node == null) return;
        for (int i = 0; i < depth; i++) {
            stringBuilder.append("--");
        }
        stringBuilder.append(node.e + "\n");
        print(node.left, depth + 1, stringBuilder);
        print(node.right, depth + 1, stringBuilder);
    }
}
