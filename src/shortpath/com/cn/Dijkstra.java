package shortpath.com.cn;

/**
 * Created by GuoXiang on 2017/8/29.
 */

/*
  迪杰斯特拉算法Java实现
 */
public class Dijkstra {
    private static char[] mVexs;   //顶点集合
    private static int[][] mMatrix;  //邻接矩阵
    static final int inf = Integer.MAX_VALUE;


    public static void main(String[] args){
        int g[][] = {{0  ,12 ,inf,inf,inf,16 ,14},
                {12 ,0  ,10 ,inf,inf,7  ,inf},
                {inf,10 ,0  ,3  ,5  ,6  ,inf},
                {inf,inf,3  ,0  ,4  ,inf,inf},
                {inf,inf,5  ,4  ,0  ,2  ,8},
                {16 ,7  ,6  ,inf,2  ,0  ,9},
                {14 ,inf,inf,inf,8  ,9  ,0}};
        char[] vexs = {'A','B','C','D','E','F','G'};
        Dijkstra dj;
        dj= new Dijkstra(vexs,g);
        int[] pre = new int[dj.mVexs.length];
        int[] dist = new int[dj.mVexs.length];
         dj.dijkstra(0,pre,dist);
    }

    //用已经提供的矩阵来创建图
    public Dijkstra(char[] vexs, int[][] matrix) {

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

    //1.source表示源点
    //2.pre表示前驱顶点数组，pre[i],表示源点到i点所经历的全部顶点中，位于顶点i之前的那个点
    public static void dijkstra(int source,int[] pre,int[] dist){
        boolean[] flag =new boolean[mVexs.length];
        //初始化，0，inf,inf,inf,……
        for(int i=0;i<mMatrix.length;i++){
            flag[i] = false;
            pre[i] = 0;
            dist[i] = mMatrix[source][i];
        }

        //对源点进行初始化
        flag[source] = true;
        dist[source] = 0;
        int k = -1; //记录查到的点
        //遍历length-1次，寻找最短路径
        for(int i=1;i<mVexs.length;i++){
            int min = inf;
            for(int j=0;j<mVexs.length;j++){
                if(flag[j]==false&&dist[j]<min){
                    min = dist[j];
                    k = j;
                }
            }
            flag[k] = true;//k就是找到的节点

            //找到当前最短路径节点，修正当前路径的最短路径和前驱节点
            //更新未获取最短路径的点
            for(int j=0;j<mVexs.length;j++){
                int temp = mMatrix[k][j]==inf ? inf:(min+mMatrix[k][j]); //更新矩阵的状态
               if(flag[j]==false&&temp<dist[j]){
                   dist[j] = temp;
                   pre[j] = k;      //记录源点到j前的那一个点
               }
            }
        }
        System.out.printf("%c is the source node: \n", mVexs[source]);
        for (int i=0; i < mVexs.length; i++)
            System.out.printf(" %c ---> %c = %d\n", mVexs[source], mVexs[i], dist[i]);
        //System.out.println(Integer.MAX_VALUE);
    }
}






































