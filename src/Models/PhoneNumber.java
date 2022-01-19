package Models;

public class PhoneNumber {
    private String region;
    private String number;

    public PhoneNumber(String region, String number) {
        this.region = region;
        this.number = number;
    }

    public String getRegion() {
        return this.region;
    }

    public String getNumber() {
        return this.number;
    }
}
