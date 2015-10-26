package com.adc.dataspark;

import static com.adc.dataspark.util.FileUtil.getDataFromFile;
import static com.google.common.base.Stopwatch.createStarted;
import static java.util.logging.Logger.getLogger;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import com.google.common.base.Stopwatch;

public class App {
	private static final Logger logger = getLogger(App.class.getName());

	public void sortData(String filePath, String destinationPath) throws Exception  {
		Stopwatch stopwatch = createStarted();
		List<Integer> data = getDataFromFile(filePath);
		Collections.sort(data, (a, b) -> a.compareTo(b));

		try (PrintWriter pw = new PrintWriter(new FileOutputStream(destinationPath))) {
			data.forEach(integer -> pw.println(integer));
		}

		stopwatch.stop();
		logger.info("Time took to process file: [" + stopwatch.elapsed(TimeUnit.MILLISECONDS) + "] ms");
	}

}
