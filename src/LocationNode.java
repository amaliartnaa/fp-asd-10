import java.util.ArrayList;
import java.util.List;

class LocationNode {
    private String name;
    private String postalCode; // null jika bukan kecamatan
    private List<LocationNode> children;

    // Constructor untuk lokasi tanpa kode pos
    public LocationNode(String name) {
        this.name = name;
        this.postalCode = null;
        this.children = new ArrayList<>();
    }

    // Constructor untuk lokasi dengan kode pos
    public LocationNode(String name, String postalCode) {
        this.name = name;
        this.postalCode = postalCode;
        this.children = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public List<LocationNode> getChildren() {
        return children;
    }

    public void addChild(LocationNode child) {
        this.children.add(child);
    }

    @Override
    public String toString() {
        return postalCode == null
                ? name
                : name + " (Kode Pos: " + postalCode + ")";
    }
}
