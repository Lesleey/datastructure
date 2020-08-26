package com.imooc.chart.dfs;

import com.imooc.chart.basicshow.GraphList;
import com.imooc.chart.basicshow.Matrix;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Lesleey
 * @date 2020/8/25-18:33
 * @function 求无向图的连通分量
 *
 * 应用：比如在一个公路系统有多少个独立的区域，或者在社交区域中两个人是否可以通过朋友认识，也就是社交网络中，有多少个团体
 */
public class ConnectionComponent {
    public static void main(String[] args) {
        Matrix matrix = new Matrix("complexstructure/src/com/imooc/chart/graph.txt");
    }
    /**
     * @param root 当前正在遍历的顶点
     * @param matrix 图
     * @param isVisited  记录当前顶点是否被访问过
     * */
    public static void dfsRecursionMatrix(Matrix matrix, int root,  boolean[] isVisited){
        if(isVisited[root]) return;
        System.out.println(root);
        isVisited[root] = true;
        for (Integer son : matrix.connectVertex(root)) {
            if(isVisited[son]) continue;
            dfsRecursionMatrix(matrix, son, isVisited);
        }
    }

    public static int getConnCompoent(Matrix matrix){
        int vertex = matrix.getVertex();
        boolean[] isVisited = new boolean[vertex];
        int res = 0;
        for (int i = 0; i < vertex; i++) {
            if(! isVisited[i]){
                dfsRecursionMatrix(matrix, i, isVisited);
                res ++;
            }
        }
        return res;
    }


}
