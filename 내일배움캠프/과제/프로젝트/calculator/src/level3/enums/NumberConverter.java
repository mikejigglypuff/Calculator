package level3.enums;

import java.util.function.Function;

public enum NumberConverter {
    INTEGER(Integer.class, Integer::parseInt),
    FLOAT(Float.class, Float::parseFloat),
    LONG(Long.class, Long::parseLong),
    DOUBLE(Double.class, Double::parseDouble);

    private final Class<? extends Number> type;
    private final Function<String, ? extends Number> converter;

    NumberConverter(Class<? extends Number> type, Function<String, ? extends Number> converter) {
        this.type = type; this.converter = converter;
    }

    public static <T extends Number> T convert(String number, Class<T> targetType) {
        for(NumberConverter numberConverter : values()) {
            if(numberConverter.type.equals(targetType)) {
                return targetType.cast(numberConverter.converter.apply(number));
            }
        }
        throw new IllegalArgumentException("Unsupported target type: " + targetType.getName());
    }
}
