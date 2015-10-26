package com.adc.dataspark.util;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileUtil {

	private static Scanner reader;

	public static List<Integer> getDataFromFile(String path) throws Exception {
		reader = new Scanner(new FileInputStream(path));
		List<Integer> data = new ArrayList<Integer>();
		reader.forEachRemaining(s -> data.add(Integer.valueOf(s)));
		return data;
	}
}
