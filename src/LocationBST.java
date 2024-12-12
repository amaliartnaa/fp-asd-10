import java.util.Map;
import java.util.TreeMap;

class LocationBST {
    private LocationNode root;

    public LocationBST() {
        root = null;
    }

    // Menambahkan lokasi ke dalam BST
    public void addLocation(String provinsi, String kota, String locationDetails) {
        String[] parts = locationDetails.split("\\|");
        if (parts.length == 2) {
            String kecamatan = parts[0];
            String kodePos = parts[1];
            root = addLocation(root, provinsi, kota, kecamatan, kodePos);
        }
    }

    // Fungsi rekursif untuk menambahkan lokasi
    private LocationNode addLocation(LocationNode node, String provinsi, String kota, String kecamatan, String kodePos) {
        if (node == null) {
            return new LocationNode(provinsi, kota, kecamatan, kodePos);
        }

        // Urutkan berdasarkan urutan abjad di seluruh level
        int compare = compareLocation(provinsi, kota, kecamatan, node.provinsi, node.kota, node.kecamatan);

        if (compare < 0) {
            node.kiri = addLocation(node.kiri, provinsi, kota, kecamatan, kodePos);  // Ke kiri jika lebih kecil
        } else if (compare > 0) {
            node.kanan = addLocation(node.kanan, provinsi, kota, kecamatan, kodePos); // Ke kanan jika lebih besar
        }
        return node;
    }

    // Membandingkan lokasi berdasarkan urutan alfabet provinsi, kota, kecamatan
    private int compareLocation(String provinsi, String kota, String kecamatan, String provinsiNode, String kotaNode, String kecamatanNode) {
        int provinsiCompare = provinsi.compareTo(provinsiNode);
        if (provinsiCompare != 0) return provinsiCompare;

        int kotaCompare = kota.compareTo(kotaNode);
        if (kotaCompare != 0) return kotaCompare;

        return kecamatan.compareTo(kecamatanNode); // Terakhir, bandingkan kecamatan
    }

    // Menampilkan seluruh isi pohon secara terstruktur
    public void tampilkan() {
        Map<String, Map<String, Map<String, String>>> lokasiMap = new TreeMap<>();
        traverseAndBuildMap(root, lokasiMap);
        printMap(lokasiMap, 0);
    }

    // Traverse tree untuk mengisi struktur map yang terurut
    private void traverseAndBuildMap(LocationNode node, Map<String, Map<String, Map<String, String>>> lokasiMap) {
        if (node != null) {
            lokasiMap
                    .computeIfAbsent(node.provinsi, k -> new TreeMap<>())
                    .computeIfAbsent(node.kota, k -> new TreeMap<>())
                    .put(node.kecamatan, node.kodePos);

            traverseAndBuildMap(node.kiri, lokasiMap);
            traverseAndBuildMap(node.kanan, lokasiMap);
        }
    }

    // Menampilkan struktur lokasi berdasarkan map yang sudah dibangun
    private void printMap(Map<String, Map<String, Map<String, String>>> lokasiMap, int indentLevel) {
        for (Map.Entry<String, Map<String, Map<String, String>>> provinsiEntry : lokasiMap.entrySet()) {
            String provinsi = provinsiEntry.getKey();
            System.out.println(" ".repeat(indentLevel * 2) + provinsi);
            for (Map.Entry<String, Map<String, String>> kotaEntry : provinsiEntry.getValue().entrySet()) {
                String kota = kotaEntry.getKey();
                System.out.println(" ".repeat((indentLevel + 1) * 2) + kota);
                for (Map.Entry<String, String> kecamatanEntry : kotaEntry.getValue().entrySet()) {
                    String kecamatan = kecamatanEntry.getKey();
                    String kodePos = kecamatanEntry.getValue();
                    System.out.println(" ".repeat((indentLevel + 2) * 2) + kecamatan + " (Kode Pos: " + kodePos + ")");
                }
            }
        }
    }

    // Pencarian lokasi menggunakan Inorder Traversal
    public void searchByInorder(String keyword) {
        System.out.println("Hasil pencarian untuk: " + keyword);
        boolean found = searchInorder(root, keyword);
        if (!found) {
            System.out.println("Lokasi tidak ditemukan.");
        }
    }

    private boolean searchInorder(LocationNode node, String keyword) {
        if (node == null) return false;

        boolean found = false;

        // Traverse ke kiri
        found |= searchInorder(node.kiri, keyword);

        // Periksa node saat ini
        if (node.provinsi.equalsIgnoreCase(keyword) ||
                node.kota.equalsIgnoreCase(keyword) ||
                node.kecamatan.equalsIgnoreCase(keyword)) {
            System.out.println("Provinsi: " + node.provinsi + ", Kota: " + node.kota + ", Kecamatan: " + node.kecamatan + ", Kode Pos: " + node.kodePos);
            found = true;
        }

        // Traverse ke kanan
        found |= searchInorder(node.kanan, keyword);

        return found;
    }
}
