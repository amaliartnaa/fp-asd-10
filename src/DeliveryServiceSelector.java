public class DeliveryServiceSelector {
    public String selectService(double weight, boolean containsLiquid) {
        if (containsLiquid) {
            return weight > 5 ? "Kargo" : "Reguler";
        } else {
            return weight > 5 ? "Kargo" : "Express";
        }
    }
}