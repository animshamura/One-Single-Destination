`Author - Shamura Ahmad 
package com.mycompany.dijkstra;

import java.util.*;
import java.lang.*;
import java.io.*;
import java.io.File;
import java.io.IOException;

public class Dijkstra {

    static int V;

    int minDistance(int dist[], Boolean sptSet[]) {
        int min = Integer.MAX_VALUE, min_index = -1;
        for (int v = 0; v < V; v++) {
            if (sptSet[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        }
        return min_index;
    }

    void printSolution(int dist[], int parent[]) {
        System.out.println("Vertex \t\t Distance from Source");
        for (int i = 0; i < V; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                System.out.println("Node" + i + " can not reach the destination.");
            } else {
                System.out.println("Node " + i + " \t\t " + dist[i]);
                System.out.print("Path : ");
                path(parent, i);
                System.out.println();
            }
        }

    }

    void path(int parent[], int d) {
        if (parent[d] != -1) {

            path(parent, parent[d]);
        }
        System.out.print(d + " ");

    }

    void dijkstra(int graph[][], int src) {
        int dist[] = new int[V];
        int parent[] = new int[V];
        for (int i = 0; i < V; i++) {
            parent[i] = -1;
        }
        Boolean sptSet[] = new Boolean[V];
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }
        dist[src] = 0;
        for (int count = 0; count < V - 1; count++) {
            int u = minDistance(dist, sptSet);
            sptSet[u] = true;

            for (int v = 0; v < V; v++) {

                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                    parent[v] = u;
                }
            }
        }

        printSolution(dist, parent);
    }

    public static void main(String[] args) throws FileNotFoundException {
        int E, s, d, w;
        File file = new File("/home/cse/Documents/Input");
        Scanner sc = new Scanner(file);
        Scanner sp = new Scanner(System.in);
        V = sc.nextInt();
        E = sc.nextInt();
        int graph[][] = new int[V][V];
        int tgraph[][] = new int[V][V];
        for (int i = 0; i < V; i++) {
            s = sc.nextInt();
            d = sc.nextInt();
            w = sc.nextInt();
            graph[s][d] = w;
            tgraph[d][s] = w;
        }
        System.out.println("Enter desrination : ");
        int desr = sp.nextInt();
        Dijkstra t = new Dijkstra();
        t.dijkstra(tgraph, desr);
    }
}