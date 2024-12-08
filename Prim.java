package Greedy;

import java.util.ArrayList;
import java.util.List;

class Prim {
    static class Edge {
        int vertex1, vertex2, weight;

        Edge(int vertex1, int vertex2, int weight) {
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "(" + vertex1 + ", " + vertex2 + ", " + weight + ")";
        }
    }

    public static List<Edge> prim(int n, int[][] W) {
        int[] nearest = new int[n];
        int[] distance = new int[n];
        List<Edge> F = new ArrayList<>();


        for (int i = 1; i < n; i++) {
            nearest[i] = 0;
            distance[i] = W[0][i];
          //  System.out.println(i + "   "+nearest[i] + "   " + distance[i]);
        }
        System.out.println("--------------------");
        for (int k = 1; k < n; k++) {
            int min = 1000000;
            int vnear = -1;

            // Find the nearest vertex
            for (int i = 1; i < n; i++) {
                if (distance[i] >= 0 && distance[i] < min) {
                    min = distance[i];
                    vnear = i;
                }

            }
          //  System.out.println(min + "  " + vnear);

            if (vnear == -1)
                break;



            F.add(new Edge(nearest[vnear] + 1, vnear + 1, W[vnear][nearest[vnear]]));

            // Mark the vertex as visited
            distance[vnear] = -1;

            // Update distance and nearest arrays
            for (int i = 1; i < n; i++) {
                if (W[i][vnear] < distance[i]) {
                    distance[i] = W[i][vnear];
                    nearest[i] = vnear;
                }
            }
        }

        return F;
    }

    public static void main(String[] args) {
        int[][] W = {
                {0, 1, 3, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {1, 0, 3, 6, Integer.MAX_VALUE},
                {3, 3, 0, 4, 2},
                {Integer.MAX_VALUE, 6, 4, 0, 5},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, 2, 5, 0}
        };

        List<Edge> result = prim(5, W);

        System.out.println("minimum spanning tree:");
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }

}