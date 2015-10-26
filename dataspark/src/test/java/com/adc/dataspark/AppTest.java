package com.adc.dataspark;

import static com.adc.dataspark.util.FileUtil.getDataFromFile;
import static java.util.Comparator.naturalOrder;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

/**
 * Unit test for App.
 */
public class AppTest {

	@Test
	public void should_read_file() throws Exception {
		App app = new App();
		String filePath = "target/data.txt";
		String destinationPath = "target/result.txt";
		app.sortData(filePath, destinationPath);
		
		assertThat(getDataFromFile(destinationPath)).isSortedAccordingTo(naturalOrder());
	}
}
