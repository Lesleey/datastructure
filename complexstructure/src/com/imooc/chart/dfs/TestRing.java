package com.imooc.chart.dfs;

import com.imooc.chart.basicshow.Matrix;

/**
 * @author Lesleey
 * @date 2020/8/26-18:34
 * @function 检测无向图（连通分量为 1 ，如果不为 1 需要遍历调用）中的环
 *   注意： 进行dfs,如果当前节点的子孙节点已经被访问过，而且不是当前节点上一个被访问的节点，表示有环
 */
public class TestRing {
    /**
     * @param matrix 图
     * @param root 当前正在遍历的顶点
     * @param parent dfs 当前节点的上一个被遍历的节点。
     * @param isVisited  记录当前顶点是否被访问过
     * */
    public static boolean dfsRecursionMatrix(Matrix matrix, int root, int parent,  int[] isVisited){
        isVisited[root] = parent;
        for (Integer son : matrix.connectVertex(root)) {
            if(isVisited[son] != -1 && son != parent ) return true;
            if(isVisited[son] == -1 && dfsRecursionMatrix(matrix, son, root, isVisited)) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] isVisited = new int[7];
        for (int i = 0; i < isVisited.length; i++) {
            isVisited[i] = -1;
        }
        System.out.println(dfsRecursionMatrix(new Matrix("complexstructure/src/com/imooc/chart/graph.txt"), 0, 0, isVisited));

    }
}
