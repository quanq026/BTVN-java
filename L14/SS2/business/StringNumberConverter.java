package business;

import model.ConversionResult;

import java.util.List;

public class StringNumberConverter {
    public ConversionResult convert(List<String> values) {
        ConversionResult result = new ConversionResult();

        for (String value : values) {
            try {
                int number = Integer.parseInt(value.trim());
                result.addValidNumber(number);
            } catch (NumberFormatException e) {
                result.increaseInvalidCount();
            }
        }

        return result;
    }
}
