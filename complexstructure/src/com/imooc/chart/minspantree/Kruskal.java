package com.imooc.chart.minspantree;


import com.imooc.chart.basicshow.WeightMatrix;
import com.imooc.tree.unionfindset.UnionFindOne;
import com.imooc.tree.unionfindset.UnionFindSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *  Kruskal 算法：（贪心算法）首先按照边的权值进行排序，然后从小到大进行选择边，如果该边和已选择的边不会构成边，我们就选择该边作为最小生成树的边。
 *               否则，放弃该边，尝试其他边。
 *       正确性：kruskal算法每次选择一个最短边，如果该边没有形成环：相当于是对一个切分，选择了最短横切边（不太严谨）。
 *       时间复杂度 O(ElogE)级别
 * */
public class Kruskal {

    private  WeightMatrix weightMatrix;
    private  List<WeightEdge> spantree = new ArrayList<>();
    public Kruskal(WeightMatrix weightMatrix){
        //判断是否是连通图，省略

        List<WeightEdge> paths = new ArrayList<>();
        IntStream.range(0, weightMatrix.getVertex()).forEach((v)->{
             weightMatrix.connectVertex(v).stream().filter((w) -> v < w).collect(Collectors.toList()).forEach((w) -> {
                  paths.add(new WeightEdge(v, w, weightMatrix.getWeight(v, w))); });
        });

        Collections.sort(paths);
        //使用并查集判断是否形成了环，也就是在添加该边之前，改边的两个顶点是否已经形成了通路
        UnionFindSet unionFindSet = new UnionFindOne(weightMatrix.getVertex());
        for (WeightEdge path : paths) {
            int v = path.v;
            int w = path.w;
            if(! unionFindSet.isConnected(v, w)){
                spantree.add(path);
                unionFindSet.union(v, w);
            }
        }

    }
    public List<WeightEdge> result(){
        return spantree;
    }
}
