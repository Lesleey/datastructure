package com.imooc.chart.basicshow;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Lesleey
 * @date 2020/5/31-17:13
 * @function 使用邻接表表示无向无权图
 *  空间复杂度 O(V + E)
 *  时间复杂度 建图 O(E * V) 查看两点是否相邻 O(degree(v))  求一个点的相邻节点 O(degree(v))
 *  邻接表的缺点主要在建图（查重） 和 查看两点是否连接，必须遍历链表，改进：不使用链表，使用红黑树(treeSet)或者哈希表（hashSet）
 *  尽量使用红黑树，因为它会保持节点编号有序，这对之后的算法有利
 */
public class GraphList {
    //顶点数量
    private int vertex;
    //边的数量
    private int edge;
    //表示图的连接表
    private LinkedList<Integer>[] list;

    public GraphList(String fileName){
        File file = new File(fileName);
        try (Scanner scanner = new Scanner(file);){
            this.vertex = scanner.nextInt();
            this.edge = scanner.nextInt();

            if(vertex <0 || edge < 0) throw  new IllegalArgumentException("参数异常！！");
            list = new LinkedList[vertex];
            for (int i = 0; i < vertex; i++) {
                list[i] = new LinkedList<>();
            }

            for (int i = 0; i < edge; i++) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                isValid(a);
                isValid(b);
                if(a == b) throw new IllegalArgumentException("不支持自环边");
                if(list[a].contains(b)) throw new IllegalArgumentException("不支持平行边");
                list[a].add(b);
                list[b].add(a);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    //寻找与 v相连的顶点
    public List<Integer> connectVertex(int v){
        isValid(v);
        return list[v];
    }

    //获取该顶点的度
    public int degreeVertex(int v){
        return connectVertex(v).size();
    }

    public boolean isExistEdge(int v, int w){
        isValid(v);
        isValid(w);
        return list[v].contains(w);
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
            stringBuilder.append(Arrays.toString(list[i].toArray()) + '\n');

        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        GraphList matrix = new GraphList("complexstructure/src/com/imooc/chart/graph.txt");
        System.out.println(matrix);
    }
}
