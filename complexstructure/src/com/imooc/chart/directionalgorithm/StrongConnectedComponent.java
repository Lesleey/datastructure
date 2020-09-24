package com.imooc.chart.directionalgorithm;

import com.imooc.chart.basicshow.Matrix;
import com.imooc.chart.dfs.ChartDfs;
import com.imooc.chart.dfs.ConnComponentAndItems;

import java.util.*;

/**
 * @author Lesleey
 * @date 2020/9/22-20:46
 * @function
 *     强连通分量： 在一个强连通分量中，任意两点都是可达的
 *     如果把强连通分量看做一个点，那么根据这些点构成的图，一定是DAG（有向无环图）
 *     Kosaraju算法：把连通分量看做成一个抽象的点，那么根据这些抽象的点构成的图一定是DAG，则对该图进行深度优先后序遍历的顺序在对每个抽象点进行DFS。
 *     在每个点中如果DFS结束，则当前DFS遍历到的节点属于一个强连通分量中。
 *     算法的关键点是 按照抽象图中的深度优先后序遍历顺序得到的抽象点（抽象点实际上是原始点的集合）， 如何保证遍历原始图得到的点的整体顺序和抽象点相同。
 *     （这里的整体顺序指的是在之前出现的抽象点中的某个原始点一定会出现在之后出现的抽象点中的所有原始点之前）
 *     算法步骤：
 *     1. 翻转图（翻转该有向图中的所有指向）然后进行深度优先遍历的后续遍历，然后再将结果逆序 ==》 保证整体顺序
 *     2. 按照第一步得到的节点顺序进行遍历， 然后进行DFS操作， 每次DFS遍历到的节点属于同一个强连通分量。
 *
 *     第一步保证整体顺序的原因：
 *       如果一个强连通分量能到一点，则翻转这张图之后，进行后序遍历，遍历的顺序中该点一定会出现在这些强连通分量中的点之后。如果对这些后序遍历求逆序，则该点
 *     一定会出现这些强连通分量中的点之前。
 *
 */
public class StrongConnectedComponent {

    private Matrix matrix;

    private int[] visiited;
    private int scccount;
    public StrongConnectedComponent(Matrix matrix){
        if(!matrix.isDirection()) throw new RuntimeException("强连通分量不支持无向图");
        Matrix reverseMatrix = matrix.reverseMatrix();
        ChartDfs.dfs(reverseMatrix);
        List<Integer> dfsPath = ChartDfs.postOrder();
        Collections.reverse(dfsPath);
        visiited = new int[matrix.getVertex()];
        for (int root: dfsPath) {
            if(visiited[root] != 0) continue;
            scccount ++;
            ConnComponentAndItems.dfsRecursionMatrix(matrix, root, visiited, scccount);
        }
    }


    public Set<Integer>[] result(){
        Set<Integer>[] strongcc = new HashSet[scccount];
        for (int i = 0; i < strongcc.length; i++) {
            strongcc[i] = new HashSet<>();
        }
        for (int i = 0; i < visiited.length; i++) {
            strongcc[visiited[i]-1].add(i);
        }
        return strongcc;
    }

    public static void main(String[] args) {
        Matrix matrix = new Matrix("complexstructure/src/com/imooc/chart/graph.txt", true);
        StrongConnectedComponent strongConnectedComponent = new StrongConnectedComponent(matrix);
        System.out.println(strongConnectedComponent.scccount);
        for (Set<Integer> integers : strongConnectedComponent.result()) {
            integers.forEach(System.out::print);
            System.out.println();
        }
    }
}
