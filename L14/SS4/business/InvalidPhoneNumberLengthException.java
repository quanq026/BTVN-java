package business;

public class InvalidPhoneNumberLengthException extends Exception {
    public InvalidPhoneNumberLengthException(String message) {
        super(message);
    }

    public static void validatePhoneNumber(String phone) throws InvalidPhoneNumberLengthException {
        if (phone == null || phone.isEmpty()) {
            throw new InvalidPhoneNumberLengthException("Sai độ dài");
        }

        if (phone.matches(".*\\s+.*")) {
            throw new InvalidPhoneNumberLengthException("Không được chứa khoảng trắng");
        }

        if (!phone.matches("\\d+")) {
            throw new InvalidPhoneNumberLengthException("Chứa ký tự không hợp lệ");
        }

        if (phone.length() != 10) {
            throw new InvalidPhoneNumberLengthException("Sai độ dài");
        }
    }
}
