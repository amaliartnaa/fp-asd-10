import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileExporter {
    public void exportToTxt(SenderReceiverData data, String service, String estimatedTime, 
                            boolean isFragile, boolean isLiquid, boolean containsBattery, 
                            double distance) {
        String fileName = "delivery_summary.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("=== Ringkasan Pengiriman ===\n");
            writer.write("Asal: " + data.getSenderCity() + "\n");
            writer.write("Tujuan: " + data.getReceiverCity() + "\n");
            writer.write("Alamat: " + data.getReceiverAddress() + "\n");
            writer.write("Jarak: " + distance + " KM\n");
            writer.write("Barang mudah pecah? : " + (isFragile ? "Ya" : "Tidak") + "\n");
            writer.write("Apakah terdapat cairan? : " + (isLiquid ? "Ya" : "Tidak") + "\n");
            writer.write("Apakah terdapat baterai? : " + (containsBattery ? "Ya" : "Tidak") + "\n");
            writer.write("Layanan: " + service + "\n");
            writer.write("Estimasi Waktu: " + estimatedTime + "\n");
            writer.write("=====================================\n");
            System.out.println("Ringkasan pengiriman berhasil disimpan ke " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
