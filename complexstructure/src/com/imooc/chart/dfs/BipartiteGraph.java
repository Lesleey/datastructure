package com.imooc.chart.dfs;

import com.imooc.chart.basicshow.Matrix;

/**
 * @author Lesleey
 * @date 2020/8/26-19:00
 * @function 二分图检测： 染色(dfs)
 *  具体流程如下： 在遍历一个顶点时对该顶点进行染色， 比如总共包括蓝色和绿色两种，起始节点为蓝色，则将相邻如果的节点染成不同的颜色（绿色）,如果相邻的节点已经被染色了并且与当前节点的颜色相同，则表示
 *  当前图不为二分图,遍历完成所有的节点都被染成蓝色和绿色两种，如果图中所有的边的两个端点都是不同的颜色，则表示当前的图为一个二分图。
 */
public class BipartiteGraph {
    /**
     * @param root 当前正在遍历的顶点
     * @param matrix 图
     * @param isVisited  1: 为蓝色， -1 为绿色
     * */
    public static boolean  dfsRecursionMatrix(Matrix matrix, int root, int[] isVisited){
        for (Integer son : matrix.connectVertex(root)) {
            if(isVisited[son] == 0) {
                isVisited[son] = -isVisited[root];
                if(! dfsRecursionMatrix(matrix, son, isVisited)) return false;
            }else if(isVisited[root] == isVisited[son]){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Matrix matrix = new Matrix("complexstructure/src/com/imooc/chart/graph.txt");
        System.out.println(dfsRecursionMatrix(matrix, 0, new int[]{1,0,0,0}));
    }
}
