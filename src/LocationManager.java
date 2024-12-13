import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LocationManager {
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
                            try {
                                int cityDistanceValue = Integer.parseInt(cityDistance[1].trim());
                                distances.put(cityName, cityDistanceValue);
                            } catch (NumberFormatException e) {
                                System.out.println("Format jarak tidak valid untuk " + cityDistance[1]);
                            }
                        }
                    }

                    // Menambahkan data kota ke database
                    City city = new City(name, province, district, subDistrict, postalCode, distances);
                    cityDatabase.put(name, city);
                } else {
                    continue;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat memuat lokasi: " + e.getMessage());
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

    public void printDistance(long distance) {
        DecimalFormat df = new DecimalFormat("#,###");
        System.out.println("Jarak: " + df.format(distance) + " KM");
    }

    // Fungsi untuk menghitung jarak antar dua kota
    public double calculateDistance(String city1, String city2) {
        City c1 = cityDatabase.get(city1);
        City c2 = cityDatabase.get(city2);

        if (c1 == null || c2 == null) {
            System.out.println("Kota tidak ditemukan: " + (c1 == null ? city1 : city2));
            return -1;
        }

        Integer distanceFromCity1ToCity2 = c1.getDistances().get(city2);
        Integer distanceFromCity2ToCity1 = c2.getDistances().get(city1);

        // Pastikan hanya memilih jarak yang sesuai antara kedua kota
        if (distanceFromCity1ToCity2 != null && distanceFromCity2ToCity1 != null) {
            if (distanceFromCity1ToCity2.equals(distanceFromCity2ToCity1)) {
                printDistance(distanceFromCity1ToCity2);  // Format output
                return distanceFromCity1ToCity2;
            } else {
                // Menangani ketidaksesuaian jarak antar kota
                System.out.println("Jarak tidak konsisten antara " + city1 + " dan " + city2);
                printDistance(distanceFromCity1ToCity2);  // Pilih salah satu untuk output
                return distanceFromCity1ToCity2;
            }
        } else if (distanceFromCity1ToCity2 != null) {
            printDistance(distanceFromCity1ToCity2);
            return distanceFromCity1ToCity2;
        } else if (distanceFromCity2ToCity1 != null) {
            printDistance(distanceFromCity2ToCity1);
            return distanceFromCity2ToCity1;
        } else {
            System.out.println("Jarak antara " + city1 + " dan " + city2 + " tidak ditemukan.");
            return -1;
        }
    }
}