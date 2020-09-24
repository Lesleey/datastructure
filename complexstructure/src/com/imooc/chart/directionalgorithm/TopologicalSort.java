package com.imooc.chart.directionalgorithm;

import com.imooc.chart.basicshow.Matrix;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Lesleey
 * @date 2020/9/22-20:07
 * @function 拓扑排序(同时还可以检查有向图的环检测)
 */
public class TopologicalSort {
    // 拓扑排序路径
    private  List<Integer> path;
    // 当前是否存在环
    private boolean hasCycle;
    public TopologicalSort(Matrix matrix){
        if(! matrix.isDirection()) throw new RuntimeException("只支持有向图");
        path = new ArrayList<>();
        int[] inDgrees = matrix.inDegrees();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDgrees.length; i++) {
            if(inDgrees[i] == 0) queue.offer(i);
        }
        while(!queue.isEmpty()){
            int cur = queue.poll();
            path.add(cur);
            for (int son: matrix.connectVertex(cur)) {
                if(-- inDgrees[son]  == 0) queue.offer(son);
            }
        }
        if(path.size() != matrix.getVertex()){
            hasCycle = true;
            path.clear();
        }
    }
    public  List<Integer> result(){
        return path;
    }
    public boolean hasCycle(){
        return hasCycle;
    }

    /**
     *  第二种方式：深度优先后序遍历(对于一个节点，遍历完其所有的相邻顶点之后，再遍历其自身),逆序就是拓扑排序结果
     *  但是使用这种方式的缺陷是必须保证图是DAG，意味着无法进行环检测，会返回所谓的排序结果，但是存在环是没有拓扑排序结果的。
     * */
    public static List<Integer> getTopResult(Matrix matrix){
        //检测该图是否存在环

        List<Integer> res = new ArrayList<>();


        return res;
    }
    public static void main(String[] args) {
        Matrix matrix = new Matrix("complexstructure/src/com/imooc/chart/graph.txt", true);
        TopologicalSort topologicalSort = new TopologicalSort(matrix);
        System.out.println(topologicalSort.result());
    }
}
