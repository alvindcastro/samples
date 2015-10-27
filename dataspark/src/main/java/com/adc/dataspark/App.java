package com.adc.dataspark;

import static com.adc.dataspark.util.FileUtil.getDataFromFile;
import static com.google.common.base.Stopwatch.createStarted;
import static java.nio.file.Paths.get;
import static java.util.logging.Logger.getLogger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import com.adc.dataspark.util.BufferedFileUtil;
import com.adc.dataspark.util.MergeSortUtil;
import com.google.common.base.Stopwatch;

public class App {
	private static final Logger logger = getLogger(App.class.getName());

	public void sortDataByAPI(String filePath, String destinationPath) throws Exception {
		Stopwatch stopwatch = createStarted();
		List<Integer> data = getDataFromFile(filePath);

		Collections.sort(data, (a, b) -> a.compareTo(b));

		try (PrintWriter pw = new PrintWriter(new FileOutputStream(destinationPath))) {
			data.forEach(integer -> pw.println(integer));
		}

		stopwatch.stop();
		logger.info("Time took to process file with Collections API: [" + stopwatch.elapsed(TimeUnit.MILLISECONDS)
				+ "] ms");
	}

	public void sortDataByMerge(String filePath, String destinationPath) throws Exception {
		Stopwatch stopwatch = createStarted();
		MergeSortUtil sortUtil = new MergeSortUtil();
		List<Integer> data = new ArrayList<Integer>();
		BufferedFileUtil<Integer> bufferedFile = new BufferedFileUtil<Integer>("UTF-8");

		data = bufferedFile.readFile((get(filePath)).toFile().toPath(), data);

		try (PrintWriter pw = new PrintWriter(new FileOutputStream(destinationPath))) {
			sortUtil.mergeSort(data) //
					.forEach(integer -> pw.println(integer));
		}

		stopwatch.stop();
		logger.info("Time took to process file with merge sort: [" + stopwatch.elapsed(TimeUnit.MILLISECONDS) + "] ms");
	}

}
