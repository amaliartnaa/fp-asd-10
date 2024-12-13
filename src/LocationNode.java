class LocationNode {
    String provinsi;
    String kota;
    String kecamatan;
    String kodePos;
    LocationNode kiri, kanan;

    public LocationNode(String provinsi, String kota, String kecamatan, String kodePos) {
        this.provinsi = provinsi;
        this.kota = kota;
        this.kecamatan = kecamatan;
        this.kodePos = kodePos;
        this.kiri = null;
        this.kanan = null;
    }
}