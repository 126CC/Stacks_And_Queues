public class Package_Chiu {
    private String packageName;
    private double weight;
    private String dArea;
    private String dAddress;
    private String status;

    public Package_Chiu() {
        this.packageName = null;
        this.weight = 0.0;
        this.dArea = null;
        this.dAddress = null;
        this.status = null;
    }

    public Package_Chiu(String packageName, double weight, String dArea, String dAddress) {
        this.packageName = packageName;
        this.weight = weight;
        this.dArea = dArea;
        this.dAddress = dAddress;
        this.status = "At warehouse";
    }

    public Package_Chiu(String packageName, double weight, String dArea, String dAddress, String status) {
        this.packageName = packageName;
        this.weight = weight;
        this.dArea = dArea;
        this.dAddress = dAddress;
        this.status = status;
    }

    public String getPackageName() {
        return packageName;
    }

    public double getWeight() {
        return weight;
    }

    public String getDArea() {
        return dArea;
    }

    public String getDAddress() {
        return dAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setDArea(String dArea) {
        this.dArea = dArea;
    }

    public void setDAddress(String dAddress) {
        this.dAddress = dAddress;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toString() {
        return packageName + ": " + weight + "lbs, Deliver to: " + dAddress + ", " + dArea + ", Status: " + status;
    }

}



