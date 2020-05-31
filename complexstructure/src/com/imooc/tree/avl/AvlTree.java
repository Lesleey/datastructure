package com.imooc.tree.avl;

import java.util.LinkedList;

/**
 * @author Lesleey
 * @date 2020/5/29-12:23
 * @function
 */
public class AvlTree<E extends Comparable<E>> {
    private class Node{
        public E e;
        public int height;
        public Node left, right;

        public Node(E e){
            this.e = e;
            this.height = 1;
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


    //向avl树中添加元素（递归）
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
        root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
        int balanceFactor  = getBanlanceFactor(root);

        //当插入的节点在不平衡节点左侧的左侧，要进行右旋
        if(getBanlanceFactor(root) > 1 && getBanlanceFactor(root.left) >= 0){
            return rightRotate(root);
        }

        //当插入的节点在不平衡节点右侧的右侧，要进行左旋
        if(getBanlanceFactor(root) < -1 && getBanlanceFactor(root.right) <= 0){
            return leftRotate(root);
        }

        //lr
        if(getBanlanceFactor(root) > 1 && getBanlanceFactor(root.left) < 0){
            return rightLeftRotate(root);
        }
        //rl
        if(getBanlanceFactor(root) < -1 && getBanlanceFactor(root.right) > 0){
            return leftRightRotate(root);
        }

        // 构建完成之后，将重构之后的树返回
        return root;
    }

    /*
    *  当插入插入的节点在不平衡节点的左侧的左侧，进行右旋转
    *        y                                                x
    *      /   \                                             / \
    *     x     t4                                          z   y
    *    / \         ==> x.right = y; y.left = t3;        / \   / \
    *    z  t3                                           t1 t2 t3  t4
    *   / \
    *  t1  t2
    * */
    private Node rightRotate(Node root){
        Node newRoot = root.left;
        root.left = newRoot.right;
        newRoot.right = root;
        root.height = Math.max(getHeight(root.left) , getHeight(root.right)) + 1;
        newRoot.height = Math.max(getHeight(newRoot.left), getHeight(newRoot.right)) + 1;
        return newRoot;
    }

    /*
    *  当插入的节点在不平很节点右侧的右侧，进行左旋转
    *        y                                                 x
    *     /    \                                              /  \
    *    t4     x                                            y    z
    *         /   \     ==> x.left = y; y.right = t3;      /  \  / \
    *        t3    z                                      t4  t3 t2 t1
    *             / \
    *            t2  t1
    * */
    private Node leftRotate(Node root){
        Node newRoot = root.right;
        if(newRoot != null) root.right = newRoot.left;
        newRoot.left = root;
        root.height = Math.max(getHeight(root.left) , getHeight(root.right)) + 1;
        newRoot.height = Math.max(getHeight(newRoot.left), getHeight(newRoot.right)) + 1;
        return newRoot;
    }

    /*
     *  当插入插入的节点在不平衡节点的左侧的右侧，先对 x 节点左旋， 在对y右旋
     *       y              y
     *     /  \           /   \
     *    x   t4         z     t4
     *  /  \     ==>    / \
     * t1  z            x  t3
     *    / \          / \
     *  t2 t3         t1  t2
     * */
    public Node leftRightRotate(Node root){
        root.left = leftRotate(root.left);
        return rightRotate(root);
    }

    /*
     *  当插入的节点在不平很节点右侧的左侧，进行x右旋， 在对 y 左旋
     *     y                y
     *    / \            /    \
     *  t4   x           t4     z
     *      / \              /   \
     *    z   t1             t3    x
     *   / \                     / \
     * t3   t2                   t2  t1
     * */
    public Node rightLeftRotate(Node root){
        root.right = rightRotate(root.right);
        return leftRotate(root);
    }

    //删除操作
    public Node remove(Node node, E e){
        if(node == null) return null;
        if(node.e.compareTo(e) < 0){
            node.right = remove(node.right, e);
        }else if(node.e.compareTo(e) > 0){
            node.left = remove(node.left, e);
        //此时找到了代删除的节点
        }else{
            if(node.left == null){
                node = node.right;
                size --;
            }else if(node.right == null){
                node = node.left;
                size --;
             //找到比待删除节点大的最小节点，然后将该节点替换为待删除节点
            }else{
                Node succesor = removeMin(node.right);
                succesor.left = node.left;
                succesor.right = node.right;

                node.left = node.right = null;

                node = succesor;
            }
        }
        // 更新node的高度，然后根据平衡因子判断是否进行左右旋。该部分和添加节点的代码相同
        return node;
    }

    private Node removeMin(Node node){
        Node pre = node;
        while( node != null && node.left != null){
            pre = node;
            node = node.left;
        }
        if(pre != null) pre.left = null;
        return node;
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

    //获得当前节点的高度
    public int getHeight(Node root){
        if(root == null) return 0;
        return root.height;
    }

    //获得当前节点的平衡因子
    public int getBanlanceFactor(Node root){
        if(root == null) return 0;
        return getHeight(root.left) - getHeight(root.right);
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
