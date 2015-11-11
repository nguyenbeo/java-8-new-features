package com.kthsoft.java8;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;

public class OptionalTest {

	private static final String DEFAULT_STRING = "Default String";
	private static final String NOT_NULL_STRING = "Not null string";
	private static final String NULL_STRING = null;

	@Test
	public void testOptionalNull() {
		Optional<String> testString = Optional.ofNullable(null);
		
		//Using isPresent of Optional to check null instead of object != null
		assertFalse(testString.isPresent());
	}
	
	@Test
	public void testOptional() {
		Optional<String> testStringOptional = Optional.of(NOT_NULL_STRING);
		Optional<String> nullStringOptional = Optional.ofNullable(NULL_STRING);
		
		String testString = testStringOptional.orElse(DEFAULT_STRING);
		assertTrue(testStringOptional.isPresent());
		assertEquals(NOT_NULL_STRING, testString);
		
		String defaultString = nullStringOptional.orElse(DEFAULT_STRING);
		assertEquals(DEFAULT_STRING, defaultString);
		
		// Using lambda () -> DEFAULT_STRING ~ Supplier<String>
		String testOrElseGetString = nullStringOptional.orElseGet(() -> DEFAULT_STRING);
		assertEquals(DEFAULT_STRING, testOrElseGetString);
	}
	
	/**
	 * Use map and filter with lambda
	 */
	@Test
	public void testOptionalMore() {
		Optional<String> testStringOptional = Optional.of(NOT_NULL_STRING);
		
		Optional<Person> mapStringOptional = testStringOptional.map(s -> new Person("Length is" + s.length()));
		assertTrue(mapStringOptional.get().getName().equals("Length is" + NOT_NULL_STRING.length()));
		
		assertTrue(testStringOptional.filter(s -> s.contains("string")).isPresent());
		assertTrue(testStringOptional.filter(s -> s.length() > 0).isPresent());
		
		assertFalse(testStringOptional.filter(s -> s.contains("stuff")).isPresent());
	}

}
