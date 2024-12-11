public class PackageValidator {
    public boolean validatePackage(boolean isLiquid, boolean isBattery, boolean isIllegal) {
        if (isIllegal) {
            System.out.println("Paket mengandung barang ilegal. Tidak dapat diproses.");
            return false;
        }
        if (isBattery) {
            System.out.println("Paket mengandung baterai. Harus melalui pengecekan lebih lanjut.");
        }
        if (isLiquid) {
            System.out.println("Paket mengandung cairan. Opsi layanan Express tidak tersedia.");
        }
        return true;
    }
}
