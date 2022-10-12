package com.jupitertools.datasetroll.expect.match.simple;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigInteger;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created on 19.12.2018.
 *
 * @author Korovin Anatoliy
 */
class MatchNumberTest {

    private static Stream<Arguments> dataSetEqual() {
        return Stream.of(Arguments.of(1, 1, true),
                         Arguments.of(1L, 1L, true),
                         // Long + Integer
                         Arguments.of(123, 123L, true),
                         Arguments.of(1234L, 1234, true),
                         Arguments.of(Long.MAX_VALUE, Long.MAX_VALUE, true),
                         Arguments.of(123, 1000, false),
                         Arguments.of(13L, 1000, false),
                         Arguments.of(Integer.valueOf(15), 15, true),
                         Arguments.of(Integer.valueOf(17), Integer.valueOf(17), true),
                         Arguments.of(Integer.valueOf(270), Long.valueOf(270), true),
                         Arguments.of(Long.valueOf(1024), Integer.valueOf(1024), true),
                         Arguments.of(Long.valueOf(2048), Long.valueOf(2048), true),
                         Arguments.of(Integer.MAX_VALUE + 1, ((long) Integer.MAX_VALUE) + 1, false),
                         // BigIntegers:
                         Arguments.of(BigInteger.valueOf(1234), BigInteger.valueOf(1234), true),
                         Arguments.of(BigInteger.valueOf(2048), BigInteger.valueOf(1024), false),
                         Arguments.of(BigInteger.valueOf(2048), 32, false),
                         Arguments.of(BigInteger.valueOf(2048), 512L, false),
                         Arguments.of(BigInteger.valueOf(1234), 1234, true),
                         Arguments.of(BigInteger.valueOf(1234), 1234L, true),
                         Arguments.of(1234, BigInteger.valueOf(1234), true),
                         Arguments.of(1234L, BigInteger.valueOf(1234), true),
                         // Float
                         Arguments.of(0.123, 0.123, true),
                         Arguments.of(0.123, 0, false),
                         Arguments.of(0.3E+8, 0.3E+8, true),
                         Arguments.of(4.7f, 4.7, true),
                         Arguments.of(4.7, 4.7f, true),
                         Arguments.of(4.7f, 4.7f, true),
                         Arguments.of(4.7f, 4.8f, false),
                         Arguments.of(4.9, 4.7f, false),
                         Arguments.of(4.71, 4.7f, false),
                         Arguments.of(4.71f, 4.7f, false),
                         Arguments.of(4.71, 4.7, false),
                         Arguments.of(4.7777776f, 4.777777f, false),
                         Arguments.of(4.77777776f, 4.7777777f, true),
                         Arguments.of(4.77777776f, 4.7777777, true),
                         Arguments.of(0.123, 0.124, false));
    }

    @ParameterizedTest
    @MethodSource("dataSetEqual")
    void matchNumbers(Object original, Object expected, boolean matchResult) {
        // Act
        boolean match = new MatchNumber().match(original, expected);
        // Assert
        assertThat(match).isEqualTo(matchResult);
    }
}