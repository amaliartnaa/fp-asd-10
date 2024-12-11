import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LocationManager locationManager = new LocationManager();
        locationManager.loadLocations("src/locations.txt");

        // Mengumpulkan data pengirim dan penerima
        UserInputHandler userInputHandler = new UserInputHandler();
        SenderReceiverData data = userInputHandler.collectSenderReceiverData(locationManager);

        if (data == null) {
            System.out.println("Data tidak valid, program dihentikan.");
            return;
        }

        // Memasukkan detail paket
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan berat paket (kg): ");
        double weight = scanner.nextDouble();
        System.out.print("Masukkan panjang paket (cm): ");
        double length = scanner.nextDouble();
        System.out.print("Masukkan lebar paket (cm): ");
        double width = scanner.nextDouble();
        System.out.print("Masukkan tinggi paket (cm): ");
        double height = scanner.nextDouble();

        // Validasi berat dan dimensi
        if (weight <= 0 || weight > 50) { // Contoh batas berat dummy
            System.out.println("Berat paket tidak valid (0 - 50 kg).");
            return;
        }
        if (length <= 0 || width <= 0 || height <= 0) {
            System.out.println("Dimensi paket tidak valid.");
            return;
        }
        if (length + width + height > 150) { // Contoh batas total dimensi dummy
            System.out.println("Total dimensi paket melebihi batas maksimum (150 cm).");
            return;
        }

        // Validasi tambahan (isLiquid, isBattery, isIllegal)
        System.out.print("Apakah paket mengandung cairan? (true/false): ");
        boolean isLiquid = scanner.nextBoolean();
        System.out.print("Apakah paket mengandung baterai? (true/false): ");
        boolean isBattery = scanner.nextBoolean();
        System.out.print("Apakah paket mengandung barang ilegal? (true/false): ");
        boolean isIllegal = scanner.nextBoolean();

        PackageValidator validator = new PackageValidator();
        if (!validator.validatePackage(isLiquid, isBattery, isIllegal)) {
            System.out.println("Paket tidak memenuhi syarat pengiriman.");
            return;
        }

        // Memilih layanan pengiriman
        DeliveryServiceSelector serviceSelector = new DeliveryServiceSelector();
        String selectedService = serviceSelector.selectService(weight, isLiquid);

        // Menyimpan ringkasan pengiriman
        FileExporter exporter = new FileExporter();
        exporter.exportToTxt(data, selectedService);
    }
}
