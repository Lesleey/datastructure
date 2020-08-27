package com.imooc.chart.bfs;

import com.imooc.chart.basicshow.Matrix;

import java.util.LinkedList;

/**
 * @author Lesleey
 * @date 2020/8/26-19:58
 * @function 二分图算法（染色） 1: 蓝色， 0 : 绿色
 */
public class BipartiteGraph {

    public static boolean bfsMatrix(Matrix matrix){
        int vertex = matrix.getVertex();
        int[] isVisited = new int[vertex];
        LinkedList<Integer> stack = new LinkedList<>();
        int root = 0;
        stack.push(root);
        isVisited[root] = 1;
        while(! stack.isEmpty()){
            Integer head  = stack.pollFirst();
            for (Integer son: matrix.connectVertex(head)) {
                if(isVisited[son] == 0) isVisited[son] = - isVisited[head];
                else if(isVisited[son] == isVisited[head]) return false;
                stack.add(son);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        bfsMatrix(new Matrix("complexstructure/src/com/imooc/chart/graph.txt"));

    }
}
