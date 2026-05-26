package model;

import java.util.ArrayList;
import java.util.List;

public class PhoneValidationResult {
    private final List<String> validPhones = new ArrayList<>();
    private final List<InvalidPhone> invalidPhones = new ArrayList<>();

    public List<String> getValidPhones() {
        return validPhones;
    }

    public List<InvalidPhone> getInvalidPhones() {
        return invalidPhones;
    }

    public void addValidPhone(String phone) {
        validPhones.add(phone);
    }

    public void addInvalidPhone(String phone, String reason) {
        invalidPhones.add(new InvalidPhone(phone, reason));
    }
}
