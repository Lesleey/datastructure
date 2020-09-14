package com.imooc.chart.basicshow;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * @author Lesleey
 * @date 2020/5/31-17:13
 * @function 使用邻接表表示无向带权图
 */
public class WeightedGraph {
    //顶点数量
    private int vertex;
    //边的数量
    private int edge;
    //表示图的连接表 | 相邻的顶点： 权值
    private TreeMap<Integer, Integer>[] list;

    public WeightedGraph(String fileName){
        File file = new File(fileName);
        try (Scanner scanner = new Scanner(file);){
            this.vertex = scanner.nextInt();
            this.edge = scanner.nextInt();

            if(vertex <0 || edge < 0) throw  new IllegalArgumentException("参数异常！！");
            list = new TreeMap[vertex];
            for (int i = 0; i < vertex; i++) {
                list[i] = new TreeMap<>();
            }

            for (int i = 0; i < edge; i++) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                int weight = scanner.nextInt();
                isValid(a);
                isValid(b);
                if(a == b) throw new IllegalArgumentException("不支持自环边");
                if(list[a].containsKey(b)) throw new IllegalArgumentException("不支持平行边");
                list[a].put(b, weight);
                list[b].put(a, weight);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    //寻找与 v相连的顶点
    public Set<Integer> connectVertex(int v){
        isValid(v);
        return list[v].keySet();
    }

    //获取该顶点的度
    public int degreeVertex(int v){
        return connectVertex(v).size();
    }

    public boolean isExistEdge(int v, int w){
        isValid(v);
        isValid(w);
        return list[v].containsKey(w);
    }

    public Integer getWeight(int v, int w){
        isValid(v);
        return list[v].get(w);
    }

    public int getVertex() {
        return vertex;
    }

    public int getEdge() {
        return edge;
    }

    public void isValid(int v){
        if(v < 0 || v >= vertex) throw new IllegalArgumentException("顶点参数不合法");
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("顶点个数%d, 边的个数%d,\n", vertex, edge));
        for (int i = 0; i < vertex; i++) {
            stringBuilder.append(String.format("%d:", i));
            for (Map.Entry<Integer, Integer> entry: list[i].entrySet())
                   stringBuilder.append("(" + entry.getKey() + ":" + entry.getValue() + ")");
            stringBuilder.append('\n');

        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        WeightedGraph matrix = new WeightedGraph("complexstructure/src/com/imooc/chart/weightgraph.txt");
        System.out.println(matrix);
    }
}
