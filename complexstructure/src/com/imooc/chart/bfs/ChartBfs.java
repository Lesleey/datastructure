package com.imooc.chart.bfs;

import com.imooc.chart.basicshow.Matrix;

import java.util.LinkedList;

/**
 * @author Lesleey
 * @date 2020/8/26-19:58
 * @function 广度优先遍历
 */
public class ChartBfs {

    public static void bfsMatrix(Matrix matrix){
        int vertex = matrix.getVertex();
        boolean[] isVisited = new boolean[vertex];
        LinkedList<Integer> stack = new LinkedList<>();
        int root = 0;
        stack.push(root);
        isVisited[root] = true;
        while(! stack.isEmpty()){
            Integer head  = stack.pollFirst();
            System.out.println(head);
            for (Integer son: matrix.connectVertex(head)) {
                if(! isVisited[son]){
                    stack.addLast(son);
                    isVisited[son] = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        bfsMatrix(new Matrix("complexstructure/src/com/imooc/chart/graph.txt"));

    }
}
