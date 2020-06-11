package log.analyser.tool.processing;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import log.analyser.tool.entity.GroupObject;
import log.analyser.tool.entity.TimeUnit;
import log.analyser.tool.filter.FilterParameters;
import log.analyser.tool.processing.LogProcessor;
import log.analyser.tool.statistics.AggregateStatisticsCollector;
import log.analyser.tool.statistics.GroupingParameters;

public class LogProcessorTest {
	
	public File getCurrentDirectory() throws UnsupportedEncodingException {
		String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		String decodedPath = URLDecoder.decode(path, "UTF-8");
		return new File(decodedPath);
	}
	
	public File createTestFile(String filename) throws IOException {
		String path = getCurrentDirectory() + "//" + filename;
		File file = new File(path);
		file.createNewFile();
		return file;
	}
	
	public boolean compareFileContentAndArrayOfStrings(File file, String[] content) throws IOException {
		FileReader r = new FileReader(file);
		BufferedReader br = new BufferedReader(r);
		String line = null;
		int contentIndex = 0;
		while ((line = br.readLine()) != null) {
			if (!line.equals(content[contentIndex])) {
				br.close();
				return false;
			}
			contentIndex++;
		}
		br.close();
		return true;
	}
	
	public boolean compareAggregateStatistics(Map<GroupObject, Integer> statistics, String[] content) throws IOException {
		String stat = null;
		int contentIndex = 0;
		for (Entry<GroupObject, Integer> entry: statistics.entrySet()) {
			stat = entry.getKey() + " " + entry.getValue();
			if (!stat.equals(content[contentIndex])) {
				return false;
			}
			contentIndex++;
		}
		return true;
	}
	
	@Test
    public void logProcessorTest() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date1 = sdf.parse("2010-01-23 21:29:19");
		Date date2 = sdf.parse("2018-03-05 11:38:09");
		
		FilterParameters filterParameters = new FilterParameters();
		filterParameters.setFromDate(date1);
		filterParameters.setToDate(date2);
		GroupingParameters groupingParameters = new GroupingParameters();
		groupingParameters.setTimeUnit(TimeUnit.DAY);
		AggregateStatisticsCollector aggregateStatisticsCollector = 
				new AggregateStatisticsCollector(groupingParameters);
		
		File output = createTestFile("output.txt");
		File input = createTestFile("log.txt");
		
		String [] rightFileOutput = { 
				"2011-10-11 05:11:34 adminus DEBUG", 
				"2011-11-30 17:32:37 SpongeBob CONFIG", 
				"2011-11-30 19:45:23 user101 CONFIG",
				"2012-03-21 21:28:30 JustinDoe WARNING",
				"2014-08-02 16:01:45 adminus CONFIG",
				"2015-03-26 07:03:58 SpongeBob DEBUG",
				"2015-04-26 03:18:31 SpongeBob ERROR",
				"2016-03-10 15:44:57 user101 DEBUG"
		};
		
		String [] rightStatisticsOutput = {
				"2001/09/05 2",
				"2001/09/06 1",
				"2005/10/13 3",
				"2007/01/04 1",
				"2007/03/26 1",
				"2008/03/13 1",
				"2008/05/01 1",
				"2008/05/02 1",
				"2009/04/25 1",
				"2009/08/03 1",
				"2009/09/19 1",
				"2009/11/27 1",
				"2011/10/11 1",
				"2011/11/30 2",
				"2012/03/21 1",
				"2014/08/02 1",
				"2015/03/26 1",
				"2015/04/26 1",
				"2016/03/10 1",
				"2018/09/13 1",
				"2019/06/11 1",
		};
		
		LogProcessor logProcessor = new LogProcessor(output,
				filterParameters, groupingParameters, aggregateStatisticsCollector);
		logProcessor.processLogRecordsFromFile(input);
		assertTrue(compareFileContentAndArrayOfStrings(output, rightFileOutput));

		aggregateStatisticsCollector.printAggregateStatistics();
		assertTrue(compareAggregateStatistics(aggregateStatisticsCollector.getStatistics(), rightStatisticsOutput));
    }

}
