import java.util.*;

public class main {
    static class Edge { int to, w; 
        Edge(int t,int w){ this.to=t; this.w=w; }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();
        int E = sc.nextInt();
        int C = sc.nextInt();          // max edge weight
        List<List<Edge>> adj = new ArrayList<>();
        for(int i=0;i<V;i++) adj.add(new ArrayList<>());
        for(int i=0;i<E;i++){
            int u = sc.nextInt(), v = sc.nextInt(), w = sc.nextInt();
            adj.get(u).add(new Edge(v,w));
            // if undirected: adj.get(v).add(new Edge(u,w));
        }
        int src = sc.nextInt();

        final int INF = Integer.MAX_VALUE;
        int maxDist = C * (V-1);
        int[] dist = new int[V];
        Arrays.fill(dist, INF);
        dist[src] = 0;

        // Buckets for distances [0..maxDist]
        List<Queue<Integer>> bucket = new ArrayList<>(maxDist+1);
        for(int i=0;i<=maxDist;i++) bucket.add(new ArrayDeque<>());
        bucket.get(0).add(src);

        int idx = 0;
        while(idx <= maxDist){
            // find next non‑empty bucket
            if(bucket.get(idx).isEmpty()){
                idx++;
                continue;
            }
            int u = bucket.get(idx).remove();
            // skip stale entries
            if(dist[u] != idx) continue;

            // relax all edges from u
            for(Edge e: adj.get(u)){
                int v = e.to, w = e.w;
                int nd = dist[u] + w;
                if(nd < dist[v] && nd <= maxDist){
                    dist[v] = nd;
                    bucket.get(nd).add(v);
                }
            }
        }

        // Print results
        System.out.println("Shortest distances from node " + src + ":");
        for(int i=0;i<V;i++){
            System.out.print("To " + i + " -> ");
            if(dist[i] == INF) System.out.println("INF");
            else             System.out.println(dist[i]);
        }
        sc.close();
}
}
