import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LocationManager {
    // Map untuk menyimpan nama kota sebagai key dan objek City sebagai value
    private Map<String, City> cityDatabase = new HashMap<>();

    // Fungsi untuk memuat lokasi dari file
    public void loadLocations(String filePath) {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("#")) continue;  // Mengabaikan baris komentar

                String[] parts = line.split("\\|");  // Memisahkan berdasarkan tanda '|'
                if (parts.length >= 6) {
                    String name = parts[0].trim();
                    String province = parts[1].trim();
                    String district = parts[2].trim();
                    String subDistrict = parts[3].trim();
                    String postalCode = parts[4].trim();

                    // Memproses jarak ke kota lain
                    Map<String, Integer> distances = new HashMap<>();
                    String[] distanceData = parts[5].split(",");
                    for (String distance : distanceData) {
                        String[] cityDistance = distance.split(":");
                        if (cityDistance.length == 2) {
                            String cityName = cityDistance[0].trim();
                            int cityDistanceValue = Integer.parseInt(cityDistance[1].trim());
                            distances.put(cityName, cityDistanceValue);
                        }
                    }

                    // Menambahkan data kota ke database
                    City city = new City(name, province, district, subDistrict, postalCode, distances);
                    cityDatabase.put(name, city);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan: " + e.getMessage());
        }
    }

    // Fungsi untuk memeriksa apakah kota ada di database
    public boolean isCityAvailable(String city) {
        return cityDatabase.containsKey(city);
    }

    // Fungsi untuk mendapatkan objek City berdasarkan nama kota
    public City getCity(String cityName) {
        return cityDatabase.get(cityName);
    }
}
