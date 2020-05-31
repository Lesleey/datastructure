package com.imooc.tree.binarysearchtree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Lesleey
 * @date 2020/5/29-12:23
 * @function
 */
public class BST<E extends Comparable<E>> {
    private class Node{
        public E e;
        public Node left, right;

        public Node(E e){
            this.e = e;
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
    }

    /**
     *       12
     *    8     18
     * */
    //向二分搜索树中添加元素（递归）
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
        // 构建完成之后，将重构之后的树返回
        return root;
    }


    //向二分搜索树中添加元素（非递归）
    public void addNodeLoop(E e){
        if(root == null){
            root = new Node(e);
            return;
        }
        Node head = root;
        while(head != null){
            if(head.e.compareTo(e) > 0){
                if(head.left == null){
                    head.left = new Node(e);
                    size ++;
                    return;
                }
                head = head.left;
            }else if(head.e.compareTo(e) < 0){
                if(head.right == null){
                    head.right = new Node(e);
                    size ++;
                    return;
                }
                head = head.right;
            }else{
                return;
            }
        }
    }

    //查找树中是否存在e
    public boolean search(E e){
        return searchNode(root, e);
    }
    //使用递归方式查询树中是否存在 e
    private boolean searchNode(Node root, E e){
        if(root == null) return false;
        if(root.e.compareTo(e) > 0){
            return searchNode(root.left, e);
        }else if(root.e.compareTo(e) < 0){
           return searchNode(root.right, e);
        }
        return root.e.equals(e);
    }
    //使用非递归方式查找树中是否存在 e
    public boolean searchLoop(E e){
        Node tmp = root;
        while(tmp != null){
            if(tmp.e.compareTo(e) > 0){
                tmp = tmp.left;
            }else if(tmp.e.compareTo(e) < 0){
                tmp = tmp.right;
            }else{
                return true;
            }
        }
        return false;
    }

    //先序遍历
    public void preOrder(){
        preOrderRecursion(root);
        System.out.println();
        preOrder(root);
    }
    //先序遍历的递归实现
    private void preOrderRecursion(Node root){
        if(root == null) return;
        System.out.print(root.e + " ");
        preOrderRecursion(root.left);
        preOrderRecursion(root.right);
    }
    //先序遍历的非递归实现
    private void preOrder(Node node){
        if(node == null) return;
        LinkedList<Node> stack = new LinkedList<>();
        stack.addFirst(node);
        while(!stack.isEmpty()){
            Node root = stack.removeFirst();
            System.out.print(root.e + " ");
            if(root.right != null) stack.addFirst(root.right);
            if(root.left != null) stack.addFirst(root.left);
        }

    }
    //中序遍历
    public void inOrder(){
        inOrderRecursion(root);
        System.out.println();
        inOrder(root);
    }
    //中序遍历的递归实现
    private void inOrderRecursion(Node root){
        if(root == null) return;
        inOrderRecursion(root.left);
        System.out.print(root.e + " ");
        inOrderRecursion(root.right);
    }
    //中序遍历的非递归实现
    private void inOrder(Node root){
        if(root == null) return;
        LinkedList<Node> stack = new LinkedList<>();
        Node tmp = root ;

        while(! stack.isEmpty() || tmp != null){
            //处理当前节点的所有左节点
            while(tmp != null){
                stack.addFirst(tmp);
                tmp = tmp.left;
            }
            // 当前节点的左节点处理完，处理右节点
            if(! stack.isEmpty()){
                tmp = stack.removeFirst();
                System.out.print(tmp.e + " ");
                tmp = tmp.right;
            }
        }
    }
    //后序遍历
    public void postOrder(){
        postOrderRecursion(root);
        System.out.println();
        postOrder(root);
    }
    //后序遍历递归实现
    private void postOrder(Node root){
        if(root == null) return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.e + " ");
    }
    //后续遍历的非递归实现
    private void postOrderRecursion(Node root){
        if(root == null) return;
        LinkedList<Node> src = new LinkedList<>();
        LinkedList<Node> res = new LinkedList<>();
        src.addFirst(root);
        while(! src.isEmpty()){
            Node node = src.removeFirst();
            res.push(node);

            if(node.left != null){
                src.push(node.left);
            }
            if(node.right != null){
                src.push(node.right);
            }
        }
        while(! res.isEmpty()){
            System.out.print(res.removeFirst().e + " ");
        }
    }

    //树的层次遍历
    public void levelOrder(){
        if(root == null) return;
        Node ret = root;
        LinkedList<Node> queue = new LinkedList<>();
        queue.addLast(ret);
        while(!queue.isEmpty()){
            // 将节点出队，进行访问操作
            Node node = queue.removeFirst();
            System.out.print(node.e + " ");
            //将节点左右孩子入队
            if(node.left != null) queue.addLast(node.left);
            if(node.right != null) queue.addLast(node.right);
        }
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
