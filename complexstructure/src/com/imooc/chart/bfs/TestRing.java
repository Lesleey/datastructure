package com.imooc.chart.bfs;

import com.imooc.chart.basicshow.Matrix;

import java.util.LinkedList;

/**
 * @author Lesleey
 * @date 2020/8/26-20:55
 * @function 测试图中是否存在环, 如果遍历过程中遇到了已经被访问的元素，而且该元素不是当前正在被访问元素的上一个元素，则表示有环
 */
public class TestRing {

    public static boolean  bfsMatrix(Matrix matrix){
        int vertex = matrix.getVertex();
        int[] isVisited = new int[vertex];
        LinkedList<Integer> stack = new LinkedList<>();
        int root = 0;
        stack.push(root);
        isVisited[root] = root;
        while(! stack.isEmpty()){
            Integer head  = stack.pollFirst();

            for (Integer son: matrix.connectVertex(head)) {
                if(isVisited[son] != -1 && isVisited[head] != son ) return true;
                if(isVisited[son] == -1){
                    isVisited[son] = head;
                    stack.add(son);
                }
            }
        }
        return false;
    }

}
