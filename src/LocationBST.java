class LocationBST {
    private LocationNode root;

    public LocationBST(String rootName) {
        this.root = new LocationNode(rootName);
    }

    public LocationNode getRoot() {
        return root;
    }

    // Tambahkan lokasi secara hierarki
    public void addLocation(String... locationHierarchy) {
        LocationNode current = root;
        for (int i = 0; i < locationHierarchy.length; i++) {
            String currentLocation = locationHierarchy[i]; // Tanpa 'final'
            String postalCode = null;

            // Jika currentLocation mengandung kode pos, pisahkan
            if (i == locationHierarchy.length - 1 && currentLocation.contains("|")) {
                String[] parts = currentLocation.split("\\|");
                currentLocation = parts[0];  // Mengubah nilai currentLocation
                postalCode = parts[1];       // Menyimpan kode pos
            }

            // Simpan currentLocation di variabel lokal untuk digunakan di dalam lambda
            String finalCurrentLocation = currentLocation;  // Variabel lokal untuk lambda
            LocationNode child = current.getChildren().stream()
                    .filter(node -> node.getName().equals(finalCurrentLocation)) // Menggunakan variabel lokal
                    .findFirst()
                    .orElse(null);

            if (child == null) {
                child = postalCode == null
                        ? new LocationNode(finalCurrentLocation)
                        : new LocationNode(finalCurrentLocation, postalCode);
                current.addChild(child);
            }

            current = child;  // Mengubah current setelah keluar dari stream
        }
    }

    public void printTree() {
        printNode(root, 0);
    }

    private void printNode(LocationNode node, int depth) {
        System.out.println("  ".repeat(depth) + node);
        for (LocationNode child : node.getChildren()) {
            printNode(child, depth + 1);
        }
    }
}
