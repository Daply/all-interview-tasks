package log.analyser.tool;

import java.io.File;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import log.analyser.tool.entity.TimeUnit;
import log.analyser.tool.filter.FilterParameters;
import log.analyser.tool.processing.LogProcessor;
import log.analyser.tool.statistics.AggregateStatisticsCollector;
import log.analyser.tool.statistics.GroupingParameters;

public class Application {

	public static void main(String [] args) throws Exception { 
		
		Scanner sc = new Scanner(System.in);
		
		FilterParameters filterParameters = new FilterParameters();
		while (filterParameters.paramsAreNull()) {	
			System.out.println("Input filter parameters please");
			System.out.println("Input username (if not set, please type '-')");
			String username = sc.next();
			if (!username.matches("\\s*\\-\\s*")) {
				filterParameters.setUsername(username);
			}
			System.out.println("Input two dates of time period in format yyyy-MM-dd HH:mm:ss"
					+ " (if not set, please type '-')");
			String fromDate = sc.next();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (!fromDate.matches("\\s*\\-\\s*")) {
				Date dateFrom = sdf.parse(fromDate);
				filterParameters.setFromDate(dateFrom);
				String toDate = sc.next();
				if (!toDate.matches("\\s*\\-\\s*")) {
					Date dateTo = sdf.parse(toDate);
					filterParameters.setToDate(dateTo);
				}
			}
			System.out.println("Input pattern for log message (regexp used) "
					+ "(if not set, please type '-')");
			String messagePattern = sc.next();
			if (!messagePattern.matches("\\s*\\-\\s*")) {
				filterParameters.setMessagePattern(messagePattern);
			}
			if (filterParameters.paramsAreNull()) {
				System.out.println("At least one parameter must be set!");
			}
		}

		GroupingParameters groupingParameters = new GroupingParameters();
		while (!groupingParameters.paramsAreSet()) {
			System.out.println("Input grouping parameters please");
			System.out.println("Is grouping by username needed? (y/n)");
			String answer = sc.next().toLowerCase();
			if (answer.equals("y")) {
				groupingParameters.setUsernameSet(true);
			}
			System.out.println("Input time unit please (no, hour, day, month)");
			String timeUnit = sc.next().toUpperCase();
			if (!timeUnit.equals("NO") && !timeUnit.equals("HOUR") &&
					!timeUnit.equals("DAY") && !timeUnit.equals("MONTH")) {
				groupingParameters.setTimeUnit(TimeUnit.NO);
			}
			else {
				groupingParameters.setTimeUnit(TimeUnit.valueOf(timeUnit));
			}
			if (!groupingParameters.paramsAreSet()) {
				System.out.println("At least one parameter must be set!");
			}
		}
		
		AggregateStatisticsCollector aggregateStatisticsCollector = new AggregateStatisticsCollector(groupingParameters);
		
		System.out.println("Input other parameters please");
		System.out.println("Input number of threads to process files");
		int numberOfThreads = sc.nextInt();
		if (numberOfThreads == 0) {
			numberOfThreads = 1;
		}
		System.out.println("Input output file path");
		File output = null;
		while (output == null) {
			String outputPath = sc.next();
			output = new File(outputPath);
			if (!output.exists()) {
				System.out.println("Such output file does not exists, it will be created");
				output.createNewFile();
			}
		}
		sc.close();
		
		// getting current path of where jar is
		String path = Application.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		String decodedPath = URLDecoder.decode(path, "UTF-8");
		File directory = new File(decodedPath);
		if (!directory.isDirectory()) {
			directory = directory.getParentFile();
		}

		LogProcessor logProcessor = new LogProcessor(output,
				filterParameters, groupingParameters, aggregateStatisticsCollector);
		ExecutorService service = Executors.newFixedThreadPool(numberOfThreads);
		List<Callable<Void>> tasks = new ArrayList<Callable<Void>>();
		// for spent time check
	    // Instant start = Instant.now();
		if (directory.isDirectory()) {
			for (File file: directory.listFiles()) {
				if (file.isFile()) {
					Callable<Void> task = () -> {
						    logProcessor.processLogRecordsFromFile(file);
							return null;
					};
					tasks.add(task);
				}
			}
		}
		else {
			throw new Exception("not a directory");
		}
		service.invokeAll(tasks);
	    service.shutdown();
		
        // for spent time check
        // Instant end = Instant.now();
		// Duration timeElapsed = Duration.between(start, end);
	    // System.out.println("Time taken: " + timeElapsed.toMillis() + " milliseconds");
     
		System.out.println("Aggregate statistics:");
		aggregateStatisticsCollector.printAggregateStatistics();
	}
	
}
