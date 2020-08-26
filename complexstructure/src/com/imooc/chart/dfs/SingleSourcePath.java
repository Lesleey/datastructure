package com.imooc.chart.dfs;

import com.imooc.chart.basicshow.Matrix;

/**
 * @author Lesleey
 * @date 2020/8/25-20:46
 * @function 求解单源路径问题，确定一个起始点，求到其他点的一条路径即可，实际上进行一次深度优先遍历即可，
 *  让isVisited数组记录当前节点的父节点（也就是从哪个节点过来的，作为访问顺序的前一个节点）
 */
public class SingleSourcePath {

    public static void main(String[] args) {
        Matrix matrix = new Matrix("complexstructure/src/com/imooc/chart/graph.txt");
        int[] visited = new int[matrix.getVertex()];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = -1;
        }
        dfsRecursionMatrix(matrix, 0, 0, visited);

        //比如查询到节点 6 的路径
        int tail = 6;
        if(visited[tail] == -1) throw new RuntimeException("不可达到");
        while(visited[tail] != tail){
            System.out.println(tail);
            tail = visited[tail];
        }
    }

    /**
     * @param root 当前正在遍历的顶点
     * @param matrix 图
     * @param isVisited  父节点，访问该节点之前的的上一个节点
     * @param parent 访问顺序中，当前节点的前一个节点
     * */
    public static void dfsRecursionMatrix(Matrix matrix, int root, int parent,  int[] isVisited){
        if(isVisited[root] != -1 ) return;
        isVisited[root] = parent;
        for (Integer son : matrix.connectVertex(root)) {
            if(isVisited[son] != -1) continue;
            dfsRecursionMatrix(matrix, son, root, isVisited);
        }
    }
}
