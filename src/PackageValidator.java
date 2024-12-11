public class PackageValidator {
    // Validasi properti paket (cairan, baterai, barang ilegal)
    public static boolean validatePackage(boolean isLiquid, boolean isBattery, boolean isIllegal) {
        if (isIllegal) {
            System.out.println("Paket mengandung barang ilegal. Tidak dapat diproses.");
            return false;
        }
        if (isBattery) {
            System.out.println("Paket mengandung baterai. Harus melalui jalur darat.");
        }
        if (isLiquid) {
            System.out.println("Paket mengandung cairan. Opsi layanan Express tidak tersedia.");
        }
        return true;
    }

    // Validasi berat paket
    public static boolean validateWeight(double weight) {
        if (weight <= 0 || weight > 50) { // Contoh batas maksimum berat: 50 kg
            System.out.println("Berat paket tidak valid (0 - 50 kg).");
            return false;
        }
        return true;
    }

    // Validasi dimensi paket
    public static boolean validateDimensions(double[] dimensions) {
        if (dimensions.length != 3) {
            System.out.println("Dimensi paket tidak lengkap.");
            return false;
        }
        double length = dimensions[0];
        double width = dimensions[1];
        double height = dimensions[2];

        if (length <= 0 || width <= 0 || height <= 0) {
            System.out.println("Dimensi paket tidak valid (semua nilai harus lebih dari 0).");
            return false;
        }

        double totalDimensions = length + width + height;
        if (totalDimensions > 150) { // Contoh batas total dimensi: 150 cm
            System.out.println("Total dimensi paket melebihi batas maksimum (150 cm).");
            return false;
        }
        return true;
    }
}
