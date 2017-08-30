package shortpath.com.cn;

import java.awt.*;

/**
 * Created by Administrator on 2017/8/30.
 */
public class Bellman_Ford {

    private static char[] mVexs;
    private static int[][] mMatrix;
    private static final int inf = 10000;
    private static int[] dist;
    private static int[] pre;
    private static int source = 0;
    public static void main(String[] args){
        int g[][] = {
                {0  ,12 ,inf,inf,inf,16 ,14},
                {12 ,0  ,10 ,inf,inf,7  ,inf},
                {inf,10 ,0  ,3  ,5  ,6  ,inf},
                {inf,inf,3  ,0  ,4  ,inf,inf},
                {inf,inf,5  ,4  ,0  ,2  ,8},
                {16 ,7  ,6  ,inf,2  ,0  ,9},
                {14 ,inf,inf,inf,8  ,9  ,0}};
        char[] vexs = {'A','B','C','D','E','F','G'};
        Bellman_Ford bf;
        bf = new Bellman_Ford(vexs,g);
        dist = new int[mVexs.length];
        pre = new int[mVexs.length];
        for(int i=0; i<mVexs.length; i++){//初始化d
            if(i==source)
                dist[i] = 0;
            else
                dist[i] = inf;
        }
        if(bf.bellman_ford()){
            for(int i=0;i<mVexs.length;i++){
                System.out.println(mVexs[source]+"到终点"+mVexs[i]+"的最短路径是："+dist[i]);
                System.out.println("最短路径为：");
                int cur = i;
                System.out.print(mVexs[i]+"<--");
                while(true){
                    cur = pre[cur];
                if(cur==source)
                    break;
                   System.out.print(mVexs[cur]+"<--");
                }
                System.out.println(mVexs[source]);

            }
        }else{
            System.out.println("存在负环！");
        }

    }
    public Bellman_Ford(char[] vexs, int[][] matrix) {

        // 初始化"顶点数"和"边数"
        int vlen = vexs.length;

        // 初始化"顶点"
        mVexs = new char[vlen];
        for (int i = 0; i < mVexs.length; i++)
            mVexs[i] = vexs[i];

        // 初始化"边"
        mMatrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++)
            for (int j = 0; j < vlen; j++)
                mMatrix[i][j] = matrix[i][j];
    }


    /*********以下是Bellman-Ford实现***********/

    public static boolean bellman_ford(){

        for(int i=0;i < mVexs.length-1;i++){
            //对所有的边进行松弛操作
            for(int u = 0;u < mVexs.length;u++){
                for(int v = 0;v < mVexs.length;v++){
                    if(mMatrix[u][v] == inf||dist[u] == inf) continue;
                    if(dist[v]>(dist[u] + mMatrix[u][v]))
                         pre[v] = u;
                     dist[v] = Math.min(dist[v], dist[u] + mMatrix[u][v]);
                }
            }
        }
        //遍历每条边
        for(int u = 0;u < mVexs.length;u++){
            for(int v = 0;v < mVexs.length;v++){
                if(mMatrix[u][v] == inf||dist[u] == inf) continue;
                if(dist[v] > (dist[u] + mMatrix[u][v])) return false;//检查是否存在回路
            }
        }
        return true;
    }

}
