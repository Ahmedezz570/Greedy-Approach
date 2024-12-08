package Greedy;

import java.util.*;

class Edgee implements Comparable<Edgee> {
    int src, dest, weight;

    // Constructor
    public Edgee(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    // For sorting edges by weight
    public int compareTo(Edgee other) {
        return this.weight - other.weight;
    }
}

class Graph {
    int V, E;
    Edgee[] edges;


    public Graph(int V, int E) {
        this.V = V;
        this.E = E;
        edges = new Edgee[E];
    }


    int find(int[] parent, int i) {
        if (parent[i] != i) {
            parent[i] = find(parent, parent[i]);
        }
        return parent[i];
    }


    void union(int[] parent, int[] rank, int x, int y) {
        int rootX = find(parent, x);
        int rootY = find(parent, y);

        if (rootX != rootY) {
            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }


    void kruskalMST() {
        Edgee[] result = new Edgee[V - 1]; // MST
        int[] parent = new int[V];
        int[] rank = new int[V];

        // Initialize parents and ranks
        for (int i = 0; i < V; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        // Step 1: Sort all edges by weight
        Arrays.sort(edges);

        int edgeCount = 0; // Count edges in MST
        int i = 0;         // Index for sorted edges

        while (edgeCount < V - 1 && i < E) {
            Edgee edge = edges[i++];

            int x = find(parent, edge.src);
            int y = find(parent, edge.dest);

            // If including this edge doesn't cause a cycle
            if (x != y) {
                result[edgeCount++] = edge;
                union(parent, rank, x, y);
            }
        }


        System.out.println("Edges in the MST:");
        for (Edgee edge : result) {
            System.out.println(edge.src + " -- " + edge.dest + " == " + edge.weight);
        }
    }
}

public class KruskalExample {
    public static void main(String[] args) {
        int V = 4;
        int E = 5;

        Graph graph = new Graph(V, E);


        graph.edges[0] = new Edgee(0, 1, 10);
        graph.edges[1] = new Edgee(0, 2, 6);
        graph.edges[2] = new Edgee(0, 3, 5);
        graph.edges[3] = new Edgee(1, 3, 15);
        graph.edges[4] = new Edgee(2, 3, 4);


        graph.kruskalMST();
    }
}
