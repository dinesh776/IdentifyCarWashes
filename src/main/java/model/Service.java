package model;

public class Service {
    private String name;
    private String mobileNumber;

    public Service(String name, String mobileNumber) {
        this.name = name;
        this.mobileNumber = mobileNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public String toString() {
        return "Service{" +
                "name='" + name + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                '}';
    }
}
