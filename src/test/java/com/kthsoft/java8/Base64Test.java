package com.kthsoft.java8;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.UUID;

import org.junit.Test;

public class Base64Test {

	private static final String URL_NEEDS_TO_BE_ENCODED = "https://test.com";
	private static final String STRING_NEEDS_TO_BE_ENCODED = "String needs to be encoded";

	@Test
	public void testEncodeAndDecode() {
		String base64EncodedString = Base64.getEncoder().encodeToString(
				STRING_NEEDS_TO_BE_ENCODED.getBytes());

		String base64DecodedString = new String(Base64.getDecoder().decode(
				base64EncodedString));

		assertEquals(STRING_NEEDS_TO_BE_ENCODED, base64DecodedString);
	}

	@Test
	public void testEncodeAndDecodeUrl() {
		String base64EncodedUrl = Base64.getUrlEncoder().encodeToString(
				URL_NEEDS_TO_BE_ENCODED.getBytes());

		String base64DecodedUrl = new String(Base64.getUrlDecoder().decode(
				base64EncodedUrl));

		assertEquals(URL_NEEDS_TO_BE_ENCODED, base64DecodedUrl);
	}

	@Test
	public void testEncodeAndDecodeMIME() throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder();
		for (int t = 0; t < 10; ++t) {
			sb.append(UUID.randomUUID().toString());
		}

		byte[] toEncodeBytes = sb.toString().getBytes("utf-8");
		String mimeEncodedString = Base64.getMimeEncoder().encodeToString(toEncodeBytes);
		
		String mimeDecodedString = new String(Base64.getMimeDecoder().decode(mimeEncodedString));
		
		assertEquals(sb.toString(), mimeDecodedString);
	}
}
