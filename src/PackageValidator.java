import java.util.List;

public class PackageValidator {
    // Validasi properti paket (cairan, baterai, barang ilegal)
    public static boolean validatePackage(boolean isLiquid, boolean containsBattery, boolean isIllegal, boolean isFragile, List<String> availableServices) {
        if (isIllegal) {
            System.out.println("Paket mengandung barang ilegal. Tidak dapat diproses.");
            return false;  // Kembali false jika ilegal
        }
        if (containsBattery || isLiquid) {
            availableServices.remove("Express");
            if (containsBattery) {
                System.out.println("Karena paket mengandung baterai, Anda tidak bisa memilih layanan Express.");
            }
            if (isLiquid) {
                System.out.println("Karena paket mengandung cairan, Anda tidak bisa memilih layanan Express.");
            }
        }
        if (isFragile) {
            System.out.println("Paket rentan pecah.");
        }
        return true;  // Jika semuanya valid
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