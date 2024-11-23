package org.example.cities;

import java.util.*;

public class Cities {

    public static void showLowCost() {
        Scanner scanner = new Scanner(System.in);

        //The number of tests
        int s = Integer.parseInt(scanner.nextLine());
        if (s > 10) {
            System.err.println("The number of tests cannot be more than 10");
            return;
        }

        //The number of cities
        int n = Integer.parseInt(scanner.nextLine());
        if (n > 10000) {
            System.err.println("The number of cities cannot be more than 10 000");
        }

        while (s-- > 0) {
            //Map of cities name and indexes
            Map<String, Integer> cityIndex = new HashMap<>();
            //Graph of accessible cities with the cost of transportation to them
            List<List<int[]>> graph = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                String cityName = scanner.nextLine();
                //Save the city name and city index
                cityIndex.put(cityName, i);
                //The number of neighbors of city
                int p = Integer.parseInt(scanner.nextLine());

                List<int[]> neighbors = new ArrayList<>();
                for (int j = 0; j < p; j++) {
                    String[] indexCosts = scanner.nextLine().split(" ");
                    int neighborIndex = Integer.parseInt(indexCosts[0]) - 1;
                    int cost = Integer.parseInt(indexCosts[1]);
                    neighbors.add(new int[]{neighborIndex, cost});
                }
                graph.add(neighbors);
            }

            //The number of paths to find
            int r = Integer.parseInt(scanner.nextLine());
            if (r > 100) {
                System.err.println("The number of paths to find cannot be more than 100");
                return;
            }

            for (int i = 0; i < r; i++) {
                String[] cities = scanner.nextLine().split(" ");
                //NAME1 - source
                String NAME1 = cities[0];
                //NAME2 - destination
                String NAME2 = cities[1];

                int result = findTheLowCost(cityIndex.get(NAME1), cityIndex.get(NAME2), graph, n);
                System.out.println(result);
            }
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    private static int findTheLowCost(int source, int destination, List<List<int[]>> graph, int n) {
        //Сreate a queue at the minimum cost
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        //The array stores the minimum known costs
        int[] costs = new int[n];
        //Fill the array with maximum values
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[source] = 0;

        //Starting city with zero cost
        priorityQueue.offer(new int[]{source, 0});

        while (!priorityQueue.isEmpty()) {
            int[] current = priorityQueue.poll();
            int currentCity = current[0];
            int currentCost = current[1];

            if (currentCity == destination) {
                return currentCost; // If the final city agreement
            }

            for (int[] neighbor : graph.get(currentCity)) {
                int nextCity = neighbor[0];
                int edgeCost = neighbor[1];

                if (costs[nextCity] > currentCost + edgeCost) {
                    costs[nextCity] = currentCost + edgeCost;
                    priorityQueue.offer(new int[]{nextCity, costs[nextCity]});
                }
            }
        }
        return -1;
    }
}
