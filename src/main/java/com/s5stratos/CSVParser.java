package com.s5stratos;

import java.io.InputStream;
import java.io.IOException;

public class CSVParser {

	public static class Options {
		private String lineEnd;
		private char separator;
		private boolean enforceRowWidth;

		public Options() {
			// Technically CRLF is the line end in csv, but shrug
			lineEnd = "\n";
			separator = ',';
			enforceRowWidth = false;
		}

		public Options setSeparator(char s) {
			separator = s;
			return this;
		}

		public char getSeparator() {
			return separator;
		}

		public Options setEnforceRowWidth(boolean e) {
			enforceRowWidth = e;
			return this;
		}

		public boolean getEnforceRowWidth() {
			return enforceRowWidth;
		}

		public String getLineEnd() {
			return lineEnd;
		}
	}

	private final InputStream stream;
	private final Options options;

	public CSVParser(InputStream stream) {
		this(stream, new Options());
	}

	public CSVParser(InputStream stream, Options opts) {
		this.stream = stream;
		this.options = opts;
	}

	public String[] readRow() throws IOException {
		throw new RuntimeException("not yet implemented");
	}
}
