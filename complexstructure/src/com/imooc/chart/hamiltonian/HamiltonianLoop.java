package com.imooc.chart.hamiltonian;

import com.imooc.chart.basicshow.Matrix;

import java.util.LinkedList;

/**
 * @author Lesleey
 * @date 2020/9/10-18:13
 * @function 哈密尔顿回路： 从一个点出发，沿着边行走，每个顶点恰好一次，之后再回到出发点。 1. 每个顶点恰好一次， 2. 回到出发点。是否存在哈米热顿路径
 */
public class HamiltonianLoop {
    private static LinkedList<Integer> path = new LinkedList<>();
    private static int head = 0;

    private static int visitNum = 0;
    /**
     * @param root 当前正在遍历的顶点
     * @param matrix 图
     * @param isVisited  记录当前顶点是否被访问过
     * */
    public static boolean dfsRecursionMatrix(Matrix matrix, int root, boolean[] isVisited){
        isVisited[root] = true;
        path.add(root);
        visitNum ++;
        for (Integer son : matrix.connectVertex(root)) {
            if(son == head && visitNum == isVisited.length) return true;
            if(isVisited[son]) continue;
            if(dfsRecursionMatrix(matrix, son, isVisited)) return true;
        }
        isVisited[root] = false;
        visitNum --;
        path.removeLast();
        return false;
        //System.out.println(root); //后序遍历
    }

    public static void main(String[] args) {
        Matrix matrix = new Matrix("complexstructure/src/com/imooc/chart/graph.txt");
        System.out.println(dfsRecursionMatrix(matrix, 0, new boolean[4]));
        path.forEach(System.out::print);
    }
}
