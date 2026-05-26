package model;

public class InvalidPhone {
    private String phone;
    private String reason;

    public InvalidPhone() {
    }

    public InvalidPhone(String phone, String reason) {
        this.phone = phone;
        this.reason = reason;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
