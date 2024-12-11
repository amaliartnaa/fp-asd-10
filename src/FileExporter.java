import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileExporter {
    public void exportToTxt(SenderReceiverData data, String service) {
        String fileName = "delivery_summary.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("=== Ringkasan Pengiriman ===\n");
            writer.write("Pengirim: " + data.getSenderName() + "\n");
            writer.write("Alamat Pengirim: " + data.getSenderAddress() + "\n");
            writer.write("Kota Pengirim: " + data.getSenderCity() + "\n");
            writer.write("\n");
            writer.write("Penerima: " + data.getReceiverName() + "\n");
            writer.write("Alamat Penerima: " + data.getReceiverAddress() + "\n");
            writer.write("Kota Penerima: " + data.getReceiverCity() + "\n");
            writer.write("\n");
            writer.write("Layanan yang Dipilih: " + service + "\n");
            writer.write("=====================================\n");
            System.out.println("Ringkasan pengiriman berhasil disimpan ke " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
