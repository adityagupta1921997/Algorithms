import java.util.*;

public class Graph {
    private static final String NEWLINE = System.getProperty("line.separator");
    private final int V;
    private int E;
    private int[][] adjMatrix;
    boolean[] visited;

    //empty graph with V vertices
    public Graph(int V){
        if(V < 0) throw new IllegalArgumentException("Too few vertices");
        this.V = V;
        this.E = 0;
        this.adjMatrix = new int[V][V];
        visited = new boolean[V];
    }

    //random graph with V verices and E edges
    public Graph(int V, int E){
        this(V);
        if(E > (long)V*(V-1)/2 + V) throw new IllegalArgumentException("Too many edges");
        if(E < 0) throw new IllegalArgumentException("Too few edges");
        Random random = new Random();

        //can be inefficient
        while(this.E!=E){
            int u = random.nextInt(V);
            int v = random.nextInt(V);
            addEdge(u,v);
        }
        visited = new boolean[V];
    }

    //number of vertices and edges
    public int V(){
        return V;
    }
    public int E(){
        return E;
    }

    //add undirected edge u-v
    public void addEdge(int u, int v){
        if(adjMatrix[u][v] == 0)
            E++;
        adjMatrix[u][v] = 1;
        adjMatrix[v][u] = 1;
    }

    //does the graph contain the edge u-v?
    public int contains(int u, int v){
        return adjMatrix[u][v];
    }

    // string representation of Graph - takes quadratic time
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("Undirected Graph" + NEWLINE);
        s.append("Vertices:"+ V +"and Edges:"+ E + NEWLINE);
        for(int u=0; u<V; u++){
            s.append(u+":");
            for(int v=0; v<V; v++){
                s.append(String.format("%7s",adjMatrix[v][u]+" "));
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    public void DFS(){
        //Visit nodes using a Stack to store "to visit" nodes
        Stack<Integer> s = new Stack<>();
        clearVisted();                                              //Set all Visited[i]=0
        s.push(0);

        //Loop as long as there are "active" nodes
        while(!s.isEmpty()){
            int nextNode;                                           //Next node to visit
            int i;
            nextNode = s.pop();
            if(!visited[nextNode]){
                visited[nextNode] = true;
                System.out.println("Next Node = "+nextNode);

                for(i=0; i<V; i++)
                    if((adjMatrix[nextNode][i]==1) && !visited[i])
                        s.push(i);
            }
        }
    }

    public void BFS(){
        //BFS uses queue data structure
        Queue<Integer> q = new LinkedList<>();
        clearVisted();
        q.add(0);
        //Loop as long as there are active nodes
        while (!q.isEmpty()){
            int nextNode;
            int i;
            nextNode = q.remove();
            if(!visited[nextNode]){
                visited[nextNode] = true;
                System.out.println("Next Node = "+nextNode);

                for(i=0; i<V; i++)
                    if((adjMatrix[nextNode][i]==1) && !visited[i])
                        q.add(i);
            }
        }
    }

    public void clearVisted(){
        for (int i =0; i<V; i++)
            visited[i] = false;
    }

    public static void main(String[] args){
        int V = 7;
        int E = 10;
        Graph G = new Graph(V, E);
        System.out.println(G.toString());
        G.DFS();
        G.BFS();
    }
}
