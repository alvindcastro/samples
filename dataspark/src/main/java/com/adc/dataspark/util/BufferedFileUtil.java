package com.adc.dataspark.util;

import static java.lang.Integer.parseInt;
import static java.nio.charset.Charset.forName;
import static java.nio.file.Files.newBufferedReader;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Logger;

public class BufferedFileUtil<T extends Number> {
	private static final Logger logger = getLogger(BufferedFileUtil.class.getName());

	private Charset charset;

	public BufferedFileUtil(String charsetName) {
		this.charset = forName(charsetName);
	}

	public List<Integer> readFile(Path path, List<Integer> list) {
		try (BufferedReader reader = newBufferedReader(path, charset)) {
			reader.lines() //
					.forEach(r -> list.add(parseInt(r)));
		} catch (IOException x) {
			logger.log(SEVERE, "Error", x);
		}
		return list;
	}
}