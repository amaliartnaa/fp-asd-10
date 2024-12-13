import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Graph {
    private Map<String, Map<String, Integer>> adjacencyList;

    // Konstruktor untuk menginisialisasi adjacency list
    public Graph() {
        adjacencyList = new HashMap<>();
    }

    // Memuat lokasi dari file
    public void loadLocationsFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Mengabaikan baris komentar
                if (line.startsWith("#")) {
                    continue;
                }

                // Memecah data dengan pemisah '|'
                String[] parts = line.split("\\|");
                // Pastikan data memiliki 6 bagian
                if (parts.length != 6) {
                    continue;  // Lewati baris ini jika formatnya salah
                }

                String city = parts[0].trim();
                String province = parts[1].trim();
                String district = parts[2].trim();
                String subdistrict = parts[3].trim();
                String postalCode = parts[4].trim();
                String distanceData = parts[5].trim();

                // Memproses jarak ke kota lain
                Map<String, Integer> neighbors = new HashMap<>();
                String[] distanceParts = distanceData.split(",");
                for (String distance : distanceParts) {
                    String[] distInfo = distance.split(":");
                    if (distInfo.length == 2) {
                        try {
                            String neighborCity = distInfo[0].trim();
                            int distanceValue = Integer.parseInt(distInfo[1].trim());
                            neighbors.put(neighborCity, distanceValue);

                            // Tambahkan hubungan dua arah
                            adjacencyList.putIfAbsent(neighborCity, new HashMap<>());
                            adjacencyList.get(neighborCity).put(city, distanceValue);
                        } catch (NumberFormatException e) {
                            System.out.println("Format jarak tidak valid: " + distance);
                        }
                    }
                }

                // Menambahkan kota dan tetangganya ke adjacency list
                adjacencyList.putIfAbsent(city, new HashMap<>());
                adjacencyList.get(city).putAll(neighbors);
            }
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat membaca file: " + e.getMessage());
        }
    }

    // Implementasi algoritma Dijkstra untuk mencari jarak terpendek
    public Map<String, Integer> dijkstra(String start) {
        if (!adjacencyList.containsKey(start)) {
            System.out.println("Kota asal tidak ditemukan: " + start);
            return Collections.emptyMap();
        }

        Map<String, Integer> distances = new HashMap<>();
        PriorityQueue<String> pq = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        // Inisialisasi jarak ke semua kota sebagai tak terhingga (Integer.MAX_VALUE)
        for (String city : adjacencyList.keySet()) {
            distances.put(city, Integer.MAX_VALUE);
        }
        distances.put(start, 0); // Jarak ke kota asal adalah 0

        pq.add(start);

        while (!pq.isEmpty()) {
            String currentCity = pq.poll();
            int currentDistance = distances.get(currentCity);

            // Menelusuri tetangga-tetangga dari kota saat ini
            for (Map.Entry<String, Integer> neighbor : adjacencyList.get(currentCity).entrySet()) {
                String neighborCity = neighbor.getKey();
                int weight = neighbor.getValue();

                // Jika jarak ke tetangga lebih pendek, update jaraknya
                int newDistance = currentDistance + weight;
                if (newDistance < distances.get(neighborCity)) {
                    distances.put(neighborCity, newDistance);
                    pq.add(neighborCity);  // Menambahkan tetangga baru ke PriorityQueue
                }
            }
        }

        return distances;
    }

    // Fungsi untuk memeriksa apakah kota ada dalam graph
    public void checkCitiesInGraph(String sender, String receiver) {
        if (!adjacencyList.containsKey(sender)) {
            System.out.println("Kota pengirim tidak ditemukan: " + sender);
        }
        if (!adjacencyList.containsKey(receiver)) {
            System.out.println("Kota penerima tidak ditemukan: " + receiver);
        }
    }

    // Fungsi untuk menampilkan adjacency list
    public void printAdjacencyList() {
        for (String city : adjacencyList.keySet()) {
            System.out.println(city + ": " + adjacencyList.get(city));
        }
    }
}
