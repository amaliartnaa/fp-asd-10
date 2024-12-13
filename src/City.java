import java.util.Map;

public class City {
    private String name;
    private String province;
    private String district;
    private String subDistrict;
    private String postalCode;
    private Map<String, Integer> distances;

    public City(String name, String province, String district, String subDistrict, String postalCode, Map<String, Integer> distances) {
        this.name = name;
        this.province = province;
        this.district = district;
        this.subDistrict = subDistrict;
        this.postalCode = postalCode;
        this.distances = distances;
    }

    public void addDistance(String otherCity, int distance) {
        distances.put(otherCity, distance);
    }

    // Getter untuk semua atribut
    public String getName() {
        return name;
    }

    public String getProvince() {
        return province;
    }

    public String getDistrict() {
        return district;
    }

    public String getSubDistrict() {
        return subDistrict;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public Map<String, Integer> getDistances() {
        return distances;
    }

    // Menambahkan toString untuk menampilkan nama kota
    @Override
    public String toString() {
        return name; // Mengembalikan nama kota saat objek City dipanggil
    }
}