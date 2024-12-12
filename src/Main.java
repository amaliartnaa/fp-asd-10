import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Pilihan program
        Scanner scanner = new Scanner(System.in);
        System.out.println("Pilih Mode:");
        System.out.println("1. Proses Pengiriman Paket");
        System.out.println("2. Mau lihat lokasi");
        int choice = scanner.nextInt();

        if (choice == 1) {
            handlePackageDelivery();  // Menangani paket pengiriman
        } else if (choice == 2) {
            handleLocationBST();  // Menangani BST lokasi
        } else {
            System.out.println("Pilihan tidak valid.");
        }
    }

    public static void handlePackageDelivery() {
        // Membaca data lokasi (Asal dan Tujuan)
        LocationManager locationManager = new LocationManager();
        locationManager.loadLocations("src/locations.txt");

        // Mengumpulkan data pengirim dan penerima
        UserInputHandler userInputHandler = new UserInputHandler();
        SenderReceiverData data = userInputHandler.collectSenderReceiverData(locationManager);

        if (data == null) {
            System.out.println("Data tidak valid, program dihentikan.");
            return;
        }

        // Membuat objek Graph dan memuat data lokasi
        Graph graph = new Graph();
        graph.loadLocationsFromFile("src/locations.txt");

        // Memasukkan informasi tambahan terkait paket
        try (Scanner scanner = new Scanner(System.in)) {
            // Validasi apakah ada cairan
            System.out.print("Apakah paket mengandung cairan? (Ya/Tidak): ");
            String isLiquidInput = scanner.next();
            boolean isLiquid = isLiquidInput.equalsIgnoreCase("Ya");

            // Pilihan layanan berdasarkan kondisi cairan
            String selectedService = chooseDeliveryService(isLiquid, scanner);

            // Menghitung jarak antar kota dengan algoritma Dijkstra
            Map<String, Integer> distances = graph.dijkstra(data.getSenderCity().getName());
            double distance = distances.getOrDefault(data.getReceiverCity().getName(), -1);

            if (distance == -1) {
                System.out.println("Tidak ada jalur yang tersedia antara " + data.getSenderCity() + " dan " + data.getReceiverCity());
                return;
            }

            // Estimasi waktu pengiriman berdasarkan jarak dan layanan
            String estimatedTime = estimateDeliveryTime(distance, selectedService);

            // Menyimpan ringkasan pengiriman
            System.out.println("\n=== Ringkasan Pengiriman ===");
            System.out.println("Asal: " + data.getSenderCity());
            System.out.println("Tujuan: " + data.getReceiverCity());
            System.out.println("Alamat: " + data.getReceiverAddress());
            System.out.println("Jarak: " + distance + " KM");
            System.out.println("Layanan: " + selectedService);
            System.out.println("Estimasi Waktu: " + estimatedTime);

            // Menyimpan data ke file jika perlu
            FileExporter exporter = new FileExporter();
            exporter.exportToTxt(data, selectedService, estimatedTime);

        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menginput data: " + e.getMessage());
        }
    }

    // Memilih layanan berdasarkan apakah barang mengandung cairan atau tidak
    public static String chooseDeliveryService(boolean isLiquid, Scanner scanner) {
        String selectedService = null;
        if (isLiquid) {
            System.out.println("Karena paket mengandung cairan, Anda tidak bisa memilih layanan Express.");
            System.out.println("Pilih layanan pengiriman (Kargo/Reguler): ");
            selectedService = scanner.next();
            while (!selectedService.equalsIgnoreCase("Kargo") && !selectedService.equalsIgnoreCase("Reguler")) {
                System.out.println("Pilihan tidak valid. Silakan pilih Kargo atau Reguler.");
                selectedService = scanner.next();
            }
        } else {
            System.out.println("Pilih layanan pengiriman (Kargo/Reguler/Express): ");
            selectedService = scanner.next();
            while (!selectedService.equalsIgnoreCase("Kargo") && !selectedService.equalsIgnoreCase("Reguler") && !selectedService.equalsIgnoreCase("Express")) {
                System.out.println("Pilihan tidak valid. Silakan pilih Kargo, Reguler, atau Express.");
                selectedService = scanner.next();
            }
        }
        return selectedService;
    }

    // Menentukan estimasi waktu berdasarkan jarak dan layanan
    public static String estimateDeliveryTime(double distance, String service) {
        int estimatedDays = 0;

        if (service.equals("Kargo")) {
            if (distance < 500) {
                estimatedDays = 2;
            } else if (distance < 1000) {
                estimatedDays = 3;
            } else {
                estimatedDays = 5;
            }
        } else if (service.equals("Reguler")) {
            if (distance < 500) {
                estimatedDays = 3;
            } else if (distance < 1000) {
                estimatedDays = 5;
            } else {
                estimatedDays = 7;
            }
        } else if (service.equals("Express")) {
            if (distance < 500) {
                estimatedDays = 1;
            } else {
                estimatedDays = 2;
            }
        }

        return estimatedDays + " Hari";
    }

    public static void handleLocationBST() {
        // Membuat pohon lokasi untuk Pulau Jawa
        LocationBST tree = new LocationBST("Pulau Jawa");

        // Tambahkan lokasi secara efisien
        tree.addLocation("Jawa Barat", "Bandung", "Cibeunying|40121");
        tree.addLocation("Jawa Barat", "Bandung", "Antapani|40291");
        tree.addLocation("Jawa Barat", "Bogor", "Cibinong|16911");
        tree.addLocation("Jawa Timur", "Surabaya", "Gubeng|60281");
        tree.addLocation("Jawa Timur", "Surabaya", "Kenjeran|60113");
        tree.addLocation("Jawa Timur", "Malang", "Klojen|65111");

        // Cetak pohon lokasi
        System.out.println("Struktur Lokasi Pulau Jawa:");
        tree.printTree();
    }

}
