package com.kthsoft.java8;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class StreamTest {

	@Test
	public void testStreamFuntions() {
		List<String> testList = Arrays.asList("", "ab", "bd", "", "", "kd");
		List<String> filteredList = new ArrayList<String>();
		
		// Count elements
		assertEquals(6, testList.stream().count());
		
		// Filter to count non-empty elements
		assertEquals(3, testList.stream().filter(s -> !s.isEmpty()).count());
		
		// Filter to create new collections without empty elements
		// Using new class Collector to process Collection
		testList.stream().filter(s -> !s.isEmpty()).collect(Collectors.toList())
			.forEach(s -> filteredList.add(s));;
		assertEquals(3, filteredList.size());
		assertEquals("ab", filteredList.stream().findFirst().get());
		
		// Sorted
		assertEquals("ab", filteredList.stream().sorted().findFirst().get());
		
		// Sorted DESC
		assertEquals("kd", filteredList.stream().sorted(Collections.reverseOrder()).findFirst().get());
		
		// Limit the elements
		assertEquals("bd", filteredList.stream().limit(2).sorted(Collections.reverseOrder()).findFirst().get());
	}

}
