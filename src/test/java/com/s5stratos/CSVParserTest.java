package com.s5stratos;

import java.io.InputStream;
import java.io.IOException;

import org.junit.Test;
import static org.junit.Assert.*;

public class CSVParserTest {
	@Test
	public void testSimpleParse() throws IOException {
		InputStream is = getClass().getClassLoader().getResourceAsStream("basic.csv");
		CSVParser parser = new CSVParser(is);
		assertArrayEquals(header, parser.readRow());
		assertArrayEquals(new String[] { "Falcon Heavy", "141000lb", "" }, parser.readRow());
		assertArrayEquals(new String[] { "Falcon 9", "6761kb", "" }, parser.readRow());
		assertArrayEquals(new String[] { "Saturn V", "271000lb", "1973" }, parser.readRow());
	}

	@Test
	public void testQuotedParse() throws IOException {
		InputStream is = getClass().getClassLoader().getResourceAsStream("basic.csv");
		System.out.println(is);
		CSVParser parser = new CSVParser(is);
		assertArrayEquals(header, parser.readRow());
		assertArrayEquals(new String[] { "Falcon Heavy", "141,000lb", "" }, parser.readRow());
		assertArrayEquals(new String[] { "Falcon 9", "6,761kb", "" }, parser.readRow());
		assertArrayEquals(new String[] { "Saturn V", "271,000lb", "1973" }, parser.readRow());
	}

	@Test
	public void testSeparatorChange() throws IOException {
		InputStream is = getClass().getClassLoader().getResourceAsStream("basic_pipe.csv");
		CSVParser parser = new CSVParser(is, new CSVParser.Options().setSeparator('|'));
		assertArrayEquals(header, parser.readRow());
		assertArrayEquals(new String[] { "Falcon Heavy", "141000lb", "" }, parser.readRow());
		assertArrayEquals(new String[] { "Falcon 9", "6761kb", "" }, parser.readRow());
		assertArrayEquals(new String[] { "Saturn V", "271000lb", "1973" }, parser.readRow());
	}

	@Test
	public void testEnforceRowWidth() throws IOException {
		InputStream is = getClass().getClassLoader().getResourceAsStream("basic_ragged.csv");
		CSVParser parser = new CSVParser(is, new CSVParser.Options().setEnforceRowWidth(true));
		assertArrayEquals(header, parser.readRow());
		assertArrayEquals(new String[] { "Falcon Heavy", "141000lb", "" }, parser.readRow());

		try {
			parser.readRow();
			fail("Expected BadCsvException here");
		} catch (BadCsvException e) {

		}

	}

	// What additional test do you think is missing from this?
	
	private static final String[] header = new String[] { "Name", "Lift Capacity", "Retired" };
}
