package com.imooc.chart.dfs;

import com.imooc.chart.basicshow.Matrix;

/**
 * @author Lesleey
 * @date 2020/8/26-17:43
 * @function 判断两个顶点是否连接，不必进行dfs，浪费资源，应该提前终止
 */
public class VertexIsConnect {


    /**
     * @param matrix 图
     * @param root 当前正在遍历的顶点
     * @param parent 当前顶点的前一个顶点
     * @param target 要寻找的第二个顶点
     * @param isVisited  isVisited[i] = j, 表示访问顶点i 之前访问的是顶 点j ,j 是I 的前一个顶点。
     * */
    public static boolean dfsRecursionMatrix(Matrix matrix, int root, int parent, int target,  int[] isVisited){
        if(isVisited[root] != -1 ) return false;
        if(root == target){
            isVisited[target] = parent;
            return true;
        }
        isVisited[root] = parent;
        for (Integer son : matrix.connectVertex(root)) {
            if(isVisited[son] != -1 ) continue;
            if( dfsRecursionMatrix(matrix, son, root, target, isVisited)) return true;
        }
        //System.out.println(root); //后序遍历
        return false;
    }

    public static void main(String[] args) {
        Matrix matrix = new Matrix("complexstructure/src/com/imooc/chart/graph.txt");
        int[] isVisited = new int[7];
        for (int i = 0; i < isVisited.length; i++) {
            isVisited[i] = -1;
        }
        dfsRecursionMatrix(matrix, 0, 0, 5, isVisited);
        for (int i = 0; i < isVisited.length; i++) {
            System.out.println(isVisited[i]);
        }
    }
}
