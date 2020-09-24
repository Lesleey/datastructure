package com.imooc.chart.shortestpath;

import com.imooc.chart.basicshow.WeightMatrix;

import java.util.*;

/**
 * @author Lesleey
 * @date 2020/9/17-17:53
 * @function Dijkstra（迪杰斯特拉）算法是无法解决负权边的问题的
 *  算法思想： 首先初始化一个数组，树长度为图中节点的数量。数组中的每个值初始化为正无穷，表示起点到对应顶点的路径长度
 *  1. 确定起始点和目标顶点，将起始定点的对应的数组位置的值设置为0
 *  2. 从当前节点开始扫描所有没有确定的、相连的顶点，更新对应位置的值
 *  3. 寻找当前循环中顶点对应的数组中值最小的元素，则该值就确定为起点到该顶点的最短路径长度，然后重复 2-3 直到确定的顶点为 目标顶点，则返回。
 */
public class Dijkstra {
    //确定的路径顺序
    private List<Integer> path;

    //存储最短路径的值
    private int pathVal;

    //存储当前最短路径的数组
    private int[] dis;

    // 当前的无向有权连通图
    private WeightMatrix weightMatrix;

    // 存储当前已被访问过，但是没有确定的节点
    private PriorityQueue<Node> visited;

    //时间复杂度为 O(ElogE)
    public Dijkstra(WeightMatrix weightMatrix, int root, int target){
        weightMatrix.isValid(root);
        weightMatrix.isValid(target);
        dis = new int[weightMatrix.getVertex()];
        Arrays.fill(dis, Integer.MAX_VALUE);
        this.path = new ArrayList<>();
        this.weightMatrix = weightMatrix;
        this.visited = new PriorityQueue<>();
        dis[root] = 0;
        visited.add(new Node(root, dis[root]));
        while(! path.contains(target)){
            root = visited.poll().node;
            path.add(root);
            for (int son: weightMatrix.connectVertex(root)) {
                 if(path.contains(son)) continue;
                 dis[son] = Math.min(dis[root] + weightMatrix.getWeight(root, son), dis[son]);
                Node node = new Node(son, dis[son]);
                if(visited.contains(node)) visited.remove(node);
                 visited.add(node);
            }
        }
        pathVal = dis[target];
    }

    public List<Integer> resultPath(){
        return path;
    }

    public int result(){
        return pathVal;
    }


    class Node implements  Comparable<Node>{
        protected int node;
        protected int dis;
        public Node(int node, int dis){
            this.node = node;
            this.dis = dis;
        }
        @Override
        public int compareTo(Node o) {
            return dis - o.dis;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node1 = (Node) o;
            return node == node1.node;
        }

    }
    public static void main(String[] args) {
        WeightMatrix matrix = new WeightMatrix("complexstructure/src/com/imooc/chart/weightgraph.txt");
        for (int i = 0; i <1 ; i++) {
            Dijkstra dijkstra = new Dijkstra(matrix, 0, 3);
            dijkstra.path.forEach(System.out::print);
        }

    }

}
