import java.util.Map;

public class City {
    private String province;
    private String city;
    private String district;
    private String subdistrict;
    private String postalCode;
    private Map<String, Integer> distanceToOthers;

    // Constructor
    public City(String province, String city, String district, String subdistrict, String postalCode, Map<String, Integer> distanceToOthers) {
        this.province = province;
        this.city = city;
        this.district = district;
        this.subdistrict = subdistrict;
        this.postalCode = postalCode;
        this.distanceToOthers = distanceToOthers;
    }

    // Getters and Setters
    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public Map<String, Integer> getDistanceToOthers() {
        return distanceToOthers;
    }
}
