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
}
