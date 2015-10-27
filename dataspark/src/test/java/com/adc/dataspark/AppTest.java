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
	public void should_read_file_then_sort_by_API() throws Exception {
		App app = new App();
		String filePath = "target/data.txt";
		String destinationPath = "target/api_result.txt";
		app.sortDataByAPI(filePath, destinationPath);

		assertThat(getDataFromFile(destinationPath)).isSortedAccordingTo(naturalOrder());
	}

	@Test
	public void should_read_file_then_sort_by_merge() throws Exception {
		App app = new App();
		String filePath = "target/data.txt";
		String destinationPath = "target/merge_result.txt";
		app.sortDataByMerge(filePath, destinationPath);

		assertThat(getDataFromFile(destinationPath)).isSortedAccordingTo(naturalOrder());
	}
}
