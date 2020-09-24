package com.imooc.chart.dfs;

import com.imooc.chart.basicshow.Matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Lesleey
 * @date 2020/8/26-18:34
 * @function 检测有向图（连通分量为 1 ，如果不为 1 需要遍历调用）中的环
 *   注意： 遍历过程和无向图相同，唯一的不同之处，是增加一个标记，标记当前的遍历路径，只有在当子孙节点出现在遍历路径中时，才表示有环
 *   应用： 程序模块的引用、学习计划、任务调度
 */
public class TestDirectionRing {
    /**
     * @param matrix 图
     * @param root 当前正在遍历的顶点
     * @param parent dfs 当前节点的上一个被遍历的节点。
     * @param isVisited  记录当前顶点是否被访问过
     * */
    public static boolean dfsRecursionMatrix(Matrix matrix, int root, int parent,  int[] isVisited){
        path.add(root);
        isVisited[root] = parent;
        for (Integer son : matrix.connectVertex(root)) {
            if(path.contains(son) && son != parent ) return true;
            if(isVisited[son] == -1 && dfsRecursionMatrix(matrix, son, root, isVisited)) return true;
        }
        path.remove(path.size()-1);
        return false;
    }
    private static List<Integer> path = new ArrayList<>();

    public static void main(String[] args) {
        int[] isVisited = new int[7];
        Arrays.fill(isVisited, -1);
        System.out.println(dfsRecursionMatrix(new Matrix("complexstructure/src/com/imooc/chart/graph.txt", true), 0, 0, isVisited));

    }
}
