public class SenderReceiverData {
    private String senderName;
    private String senderAddress;
    private City senderCity;
    private String receiverName;
    private String receiverAddress;
    private City receiverCity;
    private double packageWeight;

    public SenderReceiverData(String senderName, String senderAddress, City senderCity,
                              String receiverName, String receiverAddress, City receiverCity, double packageWeight) {
        this.senderName = senderName;
        this.senderAddress = senderAddress;
        this.senderCity = senderCity;
        this.receiverName = receiverName;
        this.receiverAddress = receiverAddress;
        this.receiverCity = receiverCity;
        this.packageWeight = packageWeight;
    }

//    public String getSenderName() {
//        return senderName;
//    }

//    public String getSenderAddress() {
//        return senderAddress;
//    }

    public City getSenderCity() {
        return senderCity;
    }

//    public String getReceiverName() {
//        return receiverName;
//    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public City getReceiverCity() {
        return receiverCity;
    }

//    public double getPackageWeight() {
//        return packageWeight;
//    }
}