package model;

import java.util.ArrayList;
import java.util.List;

public class ConversionResult {
    private final List<Integer> validNumbers = new ArrayList<>();
    private int invalidCount;

    public List<Integer> getValidNumbers() {
        return validNumbers;
    }

    public int getInvalidCount() {
        return invalidCount;
    }

    public int getValidCount() {
        return validNumbers.size();
    }

    public void addValidNumber(int number) {
        validNumbers.add(number);
    }

    public void increaseInvalidCount() {
        invalidCount++;
    }
}
