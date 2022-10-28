package com.jupitertools.datasetroll.expect;

import com.google.common.collect.ImmutableMap;
import com.jupitertools.datasetroll.Bar;
import com.jupitertools.datasetroll.Foo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.annotation.ElementType;
import java.util.Date;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ComplexityDataTypesTest {

	private ComplexityDataTypes complexityDataTypes = new ComplexityDataTypes();

	private static Stream<Arguments> simpleTypes() {
		return Stream.of(Arguments.of(new Boolean(true)),
		                 Arguments.of(new Byte((byte) 64)),
		                 Arguments.of(new Short((short) 512)),
		                 Arguments.of(new Character((char) 55)),
		                 Arguments.of(new Integer(1024)),
		                 Arguments.of(new Long(2048L)),
		                 Arguments.of(new Float(0.123)),
		                 Arguments.of(new Double(0.1234567)),
		                 Arguments.of("test string"),
		                 Arguments.of(ElementType.METHOD),
		                 Arguments.of(123),
		                 Arguments.of(true)
		                );
	}

	private static Stream<Arguments> complexTypes() {
		return Stream.of(Arguments.of(new Bar("1", "data")),
		                 Arguments.of(new Foo("2", new Date(), 123)),
		                 Arguments.of(ImmutableMap.of("id", "3", "field", "xyz")),
		                 Arguments.of(new int[]{1, 2, 3}));
	}

	@ParameterizedTest
	@MethodSource("simpleTypes")
	void simpleType(Object object) {
		// Act
		boolean simple = complexityDataTypes.isComplexType(object);
		// Asserts
		assertThat(simple).isFalse();
	}

	@ParameterizedTest
	@MethodSource("complexTypes")
	void complexType(Object object) {
		// Act
		boolean simple = complexityDataTypes.isComplexType(object);
		// Asserts
		assertThat(simple).isTrue();
	}

}