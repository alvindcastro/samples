package com.adc.dataspark.util;

import static java.lang.Integer.parseInt;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Logger;

public class BufferedFileUtil<T extends Number> {
	private static final Logger logger = getLogger(BufferedFileUtil.class.getName());

	private Charset charset;

	public BufferedFileUtil(String charsetName) {
		this.charset = Charset.forName(charsetName);
	}

	public List<Integer> readFile(Path path, List<Integer> list) {
		try (BufferedReader reader = Files.newBufferedReader(path, charset)) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				list.add(parseInt(line));
			}
		} catch (IOException x) {
			logger.log(SEVERE, "Error", x);
		}
		return list;
	}

	public void writeFile(Path path, List<T> list) {
		try (BufferedWriter writer = Files.newBufferedWriter(path, charset)) {
			for (T d : list) {
				String s = String.valueOf(d);
				writer.write(s, 0, s.length());
				writer.newLine();
			}
		} catch (IOException x) {
			logger.log(SEVERE, "Error", x);
		}
	}
}