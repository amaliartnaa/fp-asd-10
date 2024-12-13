import java.util.Scanner;

public class UserInputHandler {
    public SenderReceiverData collectSenderReceiverData(LocationManager locationManager) {
        Scanner scanner = new Scanner(System.in);

        // Input data pengirim
        System.out.println("Masukkan data pengirim:");
        System.out.print("Nama Pengirim: ");
        String senderName = scanner.nextLine();
        System.out.print("Alamat Pengirim: ");
        String senderAddress = scanner.nextLine();
        System.out.print("Kota Pengirim: ");
        String senderCityName = scanner.nextLine();

        // Cari kota pengirim
        City senderCity = locationManager.getCity(senderCityName);  // Menggunakan getCity
        if (senderCity == null) {
            System.out.println("Kota pengirim tidak ditemukan dalam database.");
            return null;
        }

        // Input data penerima
        System.out.println("Masukkan data penerima:");
        System.out.print("Nama Penerima: ");
        String receiverName = scanner.nextLine();
        System.out.print("Alamat Penerima: ");
        String receiverAddress = scanner.nextLine();
        System.out.print("Kota Penerima: ");
        String receiverCityName = scanner.nextLine();

        // Cari kota penerima
        City receiverCity = locationManager.getCity(receiverCityName);  // Menggunakan getCity
        if (receiverCity == null) {
            System.out.println("Kota penerima tidak ditemukan dalam database.");
            return null;
        }

        // Menanyakan apakah barang ilegal
        System.out.print("Apakah barang ilegal? (Ya/Tidak): ");
        String illegalItem = scanner.nextLine().trim();
        if (illegalItem.equalsIgnoreCase("Ya")) {
            System.out.println("Paket mengandung barang ilegal. Tidak dapat diproses.");
            return null; // Menghentikan proses pengiriman jika barang ilegal
        }

        // Input berat paket
        System.out.print("Masukkan berat paket (kg): ");
        double packageWeight = scanner.nextDouble();

        // Validasi berat paket
        if (!PackageValidator.validateWeight(packageWeight)) {
            return null; // Jika berat tidak valid, proses dihentikan
        }

        return new SenderReceiverData(senderName, senderAddress, senderCity, receiverName, receiverAddress, receiverCity, packageWeight);
    }
}