package com.imooc.chart.dfs;

import com.imooc.chart.basicshow.Matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lesleey
 * @date 2020/8/25-20:24
 * @function 求解两个顶点之间的所有路径（比如顶点0到5的路径）
 */
public class BetweenVertexPath {
    public static void main(String[] args) {
        Matrix matrix = new Matrix("complexstructure/src/com/imooc/chart/graph.txt");
        List<List<Integer>> res = new ArrayList<>();
        dfsRecursionMatrix(matrix, 0, new boolean[8], 6, new ArrayList<>(), res);
        System.out.println(res);
    }
    /**
     * @param root 当前正在遍历的顶点
     * @param matrix 图
     * @param isVisited  记录当前顶点是否被访问过
     * */
    public static void dfsRecursionMatrix(Matrix matrix, int root, boolean[] isVisited, int target, List<Integer> path, List<List<Integer>> res){
        if(isVisited[root]) return;
        if(root == target){
            res.add(new ArrayList<>(path));
            return;
        }
        isVisited[root] = true;
        for (Integer son : matrix.connectVertex(root)) {
            if(isVisited[son]) continue;
            path.add(son);
            dfsRecursionMatrix(matrix, son, isVisited, target, path, res);
            path.remove(path.size() - 1);
        }
        isVisited[root] = false;
    }
}
