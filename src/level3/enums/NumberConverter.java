package level3.enums;

import java.util.function.Function;

// parser 에서 사용할 타입별 converter 를 정의하는 Enum
public enum NumberConverter {
    INTEGER(Integer.class, Integer::parseInt),
    FLOAT(Float.class, Float::parseFloat),
    LONG(Long.class, Long::parseLong),
    DOUBLE(Double.class, Double::parseDouble);

    private final Class<? extends Number> type;
    // NumberFormatException
    private final Function<String, ? extends Number> converter;

    NumberConverter(Class<? extends Number> type, Function<String, ? extends Number> converter) {
        this.type = type;
        this.converter = converter;
    }

    // targetType이 선언된 타입들 중 하나와 일치하면 문자열을 해당 타입으로 변환하는 메서드
    public static <T extends Number> T convert(String number, Class<T> targetType) throws IllegalArgumentException {
        for (NumberConverter numberConverter : values()) {
            if (numberConverter.type.equals(targetType)) {
                return targetType.cast(numberConverter.converter.apply(number));
            }
        }
        throw new IllegalArgumentException();
    }
}
