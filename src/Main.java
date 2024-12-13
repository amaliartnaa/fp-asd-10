import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("===========================================");
        System.out.println("   Halo! Selamat Datang di Layanan Kami!   ");
        System.out.println("===========================================");
        System.out.println("Ada yang bisa kami bantu? 😊");
        System.out.println("Silakan pilih salah satu mode di bawah ini:");
        System.out.println("-------------------------------------------");
        System.out.println("1. Proses Pengiriman Paket");
        System.out.println("2. Mau lihat lokasi");

        int choice = scanner.nextInt();

        if (choice == 1) {
            handlePackageDelivery();
        } else if (choice == 2) {
            handleLocationBST();
        } else {
            System.out.println("Pilihan tidak valid.");
        }
    }

    public static void handlePackageDelivery() {
        LocationManager locationManager = new LocationManager();
        locationManager.loadLocations("src/locations.txt");

        UserInputHandler userInputHandler = new UserInputHandler();
        SenderReceiverData data = userInputHandler.collectSenderReceiverData(locationManager);

        if (data == null) {
            System.out.println("Data tidak valid, program dihentikan.");
            return;
        }

        Graph graph = new Graph();
        graph.loadLocationsFromFile("src/locations.txt");

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Apakah paket mengandung cairan? (Ya/Tidak): ");
            String isLiquidInput = scanner.next();
            boolean isLiquid = isLiquidInput.equalsIgnoreCase("Ya");

            System.out.print("Barang mudah pecah? (Ya/Tidak): ");
            String isFragileInput = scanner.next();
            boolean isFragile = isFragileInput.equalsIgnoreCase("Ya");

            System.out.print("Apakah paket mengandung baterai? (Ya/Tidak): ");
            String containsBatteryInput = scanner.next();
            boolean containsBattery = containsBatteryInput.equalsIgnoreCase("Ya");

            String selectedService = chooseDeliveryService(isLiquid, isFragile, containsBattery, scanner);
            if (selectedService == null) {
                System.out.println("Layanan pengiriman tidak tersedia. Pengiriman dibatalkan.");
                return;
            }


            Map<String, Integer> distances = graph.dijkstra(data.getSenderCity().getName());
            double distance = distances.getOrDefault(data.getReceiverCity().getName(), -1);

            if (distance == -1) {
                System.out.println("Tidak ada jalur yang tersedia antara " + data.getSenderCity() + " dan " + data.getReceiverCity());
                return;
            }

            String estimatedTime = estimateDeliveryTime(distance, selectedService);

            System.out.println("\n=== Ringkasan Pengiriman ===");
            System.out.println("Asal: " + data.getSenderCity());
            System.out.println("Tujuan: " + data.getReceiverCity());
            System.out.println("Alamat: " + data.getReceiverAddress());
            System.out.println("Jarak: " + distance + " KM");
            System.out.println("Barang mudah pecah? : " + (isFragile ? "Ya" : "Tidak"));
            System.out.println("Apakah terdapat cairan? : " + (isLiquid ? "Ya" : "Tidak"));
            System.out.println("Apakah terdapat baterai? : " + (containsBattery ? "Ya" : "Tidak"));
            System.out.println("Layanan: " + selectedService);
            System.out.println("Estimasi Waktu: " + estimatedTime);

            FileExporter exporter = new FileExporter();
            exporter.exportToTxt(data, selectedService, estimatedTime, isFragile, isLiquid, containsBattery, distance);

        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menginput data: " + e.getMessage());
        }
    }

    public static String chooseDeliveryService(boolean isLiquid, boolean isFragile, boolean containsBattery, Scanner scanner) {
        List<String> availableServices = new ArrayList<>(List.of("Kargo", "Reguler", "Express"));

        // Validasi dengan PackageValidator
        if (!PackageValidator.validatePackage(isLiquid, containsBattery, false, isFragile, availableServices)) {
            return null;  // Menghentikan proses jika paket tidak valid
        }

        System.out.println("Pilih layanan pengiriman " + availableServices + ": ");
        String selectedService = scanner.next();

        while (!availableServices.contains(selectedService)) {
            System.out.println("Pilihan tidak valid. Silakan pilih " + availableServices + ".");
            selectedService = scanner.next();
        }

        return selectedService;
    }

    public static String estimateDeliveryTime(double distance, String service) {
        int estimatedDays = 0;

        switch (service) {
            case "Kargo":
                estimatedDays = distance < 500 ? 2 : distance < 1000 ? 3 : 5;
                break;
            case "Reguler":
                estimatedDays = distance < 500 ? 3 : distance < 1000 ? 5 : 7;
                break;
            case "Express":
                estimatedDays = distance < 500 ? 1 : 2;
                break;
            default:
                System.out.println("Layanan tidak dikenal. Menggunakan estimasi default.");
                estimatedDays = 3;
        }

        return estimatedDays + " Hari";
    }

    public static void handleLocationBST() {
        // Membuat pohon lokasi untuk Pulau Jawa
        LocationBST tree = new LocationBST();

        // Menambahkan lokasi-lokasi
        tree.addLocation("Jawa Barat", "Bandung", "Cibeunying|40121");
        tree.addLocation("Jawa Barat", "Bandung", "Antapani|40291");
        tree.addLocation("Jawa Barat", "Bogor", "Cibinong|16911");
        tree.addLocation("Jawa Timur", "Surabaya", "Gubeng|60281");
        tree.addLocation("Jawa Timur", "Surabaya", "Kenjeran|60113");
        tree.addLocation("Jawa Timur", "Malang", "Klojen|65111");
        tree.addLocation("Jawa Timur", "Banyuwangi", "Karangrejo|68411");
        tree.addLocation("Jawa Tengah", "Surakarta", "Kauman|57112");
        tree.addLocation("Jawa Tengah", "Semarang", "Mlatibaru|50122");
        tree.addLocation("Jawa Timur", "Madiun", "Kecamatan Madiun|60150");

        // Scanner untuk opsi pengguna
        Scanner scanner = new Scanner(System.in);
        System.out.println("Pilih Opsi:");
        System.out.println("1. Tampilkan semua lokasi");
        System.out.println("2. Cari lokasi tertentu");
        int subChoice = scanner.nextInt();
        scanner.nextLine(); // Konsumsi newline character

        if (subChoice == 1) {
            // Tampilkan semua lokasi
            System.out.println("Struktur Lokasi Pulau Jawa:");
            tree.tampilkan();
        } else if (subChoice == 2) {
            // Cari lokasi tertentu
            System.out.print("Masukkan nama lokasi yang ingin dicari: ");
            String lokasi = scanner.nextLine();  // Menggunakan nextLine() untuk nama lokasi
            System.out.println("Hasil pencarian untuk \"" + lokasi + "\":");
            tree.searchByInorder(lokasi);  // Pastikan pencarian dilakukan berdasarkan nama lokasi
        } else {
            System.out.println("Pilihan tidak valid.");
        }
    }
}