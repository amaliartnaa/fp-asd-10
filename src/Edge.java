public class Edge {
    private String destinationCity;
    private int distance;

    public Edge(String destinationCity, int distance) {
        this.destinationCity = destinationCity;
        this.distance = distance;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public int getDistance() {
        return distance;
    }
}
