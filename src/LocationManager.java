import java.io.*;
import java.util.*;

public class LocationManager {
    private List<City> cities;

    public LocationManager() {
        cities = new ArrayList<>();
    }

    public void loadLocations(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue; // Abaikan baris kosong atau komentar
                }

                // Memecah baris menjadi bagian-bagian berdasarkan "|"
                String[] parts = line.split("\\|");
                if (parts.length < 6) {
                    System.out.println("Format tidak valid: " + line);
                    continue;
                }

                String name = parts[0];
                String province = parts[1];
                String district = parts[2];
                String subDistrict = parts[3];
                String postalCode = parts[4];
                Map<String, Integer> distances = parseDistances(parts[5]);

                City city = new City(name, province, district, subDistrict, postalCode, distances);
                cities.add(city);
            }
        } catch (IOException e) {
            System.err.println("Gagal membaca file lokasi: " + e.getMessage());
        }
    }

    private Map<String, Integer> parseDistances(String distanceString) {
        Map<String, Integer> distances = new HashMap<>();
        String[] pairs = distanceString.split(",");
        for (String pair : pairs) {
            String[] keyValue = pair.split(":");
            if (keyValue.length == 2) {
                String city = keyValue[0].trim();
                int distance = Integer.parseInt(keyValue[1].trim());
                distances.put(city, distance);
            }
        }
        return distances;
    }

    public City findCityByName(String name) {
        for (City city : cities) {
            if (city.getCity().equalsIgnoreCase(name)) { // Gunakan equalsIgnoreCase
                return city;
            }
        }
        return null; // Kota tidak ditemukan
    }
}
