package business;

import model.PhoneValidationResult;

public class PhoneNumberManager {
    public PhoneValidationResult checkPhones(String input) {
        PhoneValidationResult result = new PhoneValidationResult();
        String[] phones = input.split(",");

        for (String rawPhone : phones) {
            String phone = rawPhone.trim();

            try {
                InvalidPhoneNumberLengthException.validatePhoneNumber(phone);
                result.addValidPhone(phone);
            } catch (InvalidPhoneNumberLengthException e) {
                result.addInvalidPhone(phone, e.getMessage());
            }
        }

        return result;
    }
}
