package com.imooc.chart.dfs;

import com.imooc.chart.basicshow.Matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lesleey
 * @date 2020/8/25-19:40
 * @function 求无向图的连通分量以及每个连通分量包含的顶点
 *
 */
public class ConnComponentAndItems {

    public static List<List<Integer>> getResByList(Matrix matrix){
        int vertex = matrix.getVertex();
        boolean[] isVisited = new boolean[vertex];
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < vertex; i++) {
            if(! isVisited[i]){
                List<Integer> items = new ArrayList<>();
                dfsRecursionMatrix(matrix, i, isVisited, items);
                res.add(items);
            }
        }
        return res;
    }

    /**
     * @param root 当前正在遍历的顶点
     * @param matrix 图
     * @param isVisited  记录当前顶点是否被访问过
     * */
    public static void dfsRecursionMatrix(Matrix matrix, int root,  boolean[] isVisited, List<Integer> items){
        if(isVisited[root]) return;
        items.add(root);
        isVisited[root] = true;
        for (Integer son : matrix.connectVertex(root)) {
            if(isVisited[son]) continue;
            dfsRecursionMatrix(matrix, son, isVisited, items);
        }
    }

    /**
     * 用数组的层次表示连通分量的数量,数组值相同的表示位于同一个连通分量,这种方式判断两个顶点是否连接（处于同一连通分量），则只需要判断返回来的数组
     *  arr[a] == arr[b] 即可。
     *
     * */
    public static void dfsRecursionMatrix(Matrix matrix, int root,  int[] isVisited, int curlevel){
        if(isVisited[root] != 0) return;
        isVisited[root] = curlevel;
        for (Integer son : matrix.connectVertex(root)) {
            if(isVisited[son] != 0) continue;
            dfsRecursionMatrix(matrix, son, isVisited, curlevel);
        }
    }

    public static int[] getResByArray(Matrix matrix){
        int vertex = matrix.getVertex();
        int[] isVisited = new int[vertex];
        int curLevel = 0;
        for (int i = 0; i < vertex; i++) {
            if( 0 != isVisited[i]){
                dfsRecursionMatrix(matrix, i, isVisited, curLevel ++);
            }
        }
        return isVisited;
    }

}
