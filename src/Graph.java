import java.util.*;

public class Graph {
    private Map<String, List<Edge>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    public void addEdge(String source, String destination, int distance) {
        adjacencyList.computeIfAbsent(source, k -> new ArrayList<>()).add(new Edge(destination, distance));
    }

    public Map<String, Integer> dijkstra(String startCity) {
        Map<String, Integer> distances = new HashMap<>();
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Edge::getDistance));
        priorityQueue.add(new Edge(startCity, 0));

        while (!priorityQueue.isEmpty()) {
            Edge currentEdge = priorityQueue.poll();
            String currentCity = currentEdge.getDestinationCity();

            if (!distances.containsKey(currentCity)) {
                distances.put(currentCity, currentEdge.getDistance());
                for (Edge neighbor : adjacencyList.getOrDefault(currentCity, new ArrayList<>())) {
                    if (!distances.containsKey(neighbor.getDestinationCity())) {
                        priorityQueue.add(new Edge(neighbor.getDestinationCity(), currentEdge.getDistance() + neighbor.getDistance()));
                    }
                }
            }
        }
        return distances;
    }
}
