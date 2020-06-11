package log.analyser.tool.processing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import log.analyser.tool.entity.LogRecord;
import log.analyser.tool.filter.FilterParameters;
import log.analyser.tool.filter.LogRecordFilter;
import log.analyser.tool.filter.LogRecordParser;
import log.analyser.tool.statistics.AggregateStatisticsCollector;
import log.analyser.tool.statistics.GroupingParameters;


public class LogProcessor {
	
	private File outputFile = null;
	
	private FilterParameters filterParameters;
	
	private GroupingParameters groupingParameters;
	
	private AggregateStatisticsCollector aggregateStatisticsCollector;
	
	public LogProcessor(File outputFile,
			FilterParameters filterParameters, 
			GroupingParameters groupingParameters,
			AggregateStatisticsCollector aggregateStatisticsCollector) {
		this.outputFile = outputFile;
		this.filterParameters = filterParameters;
		this.groupingParameters = groupingParameters;
		this.aggregateStatisticsCollector = aggregateStatisticsCollector;
	}
	
	public synchronized void processLogRecordsFromFile(File file) throws Exception {
		
		if (file == null || !file.exists())  
            throw new Exception("Input log file is null or does ot exist");
		
		if (outputFile == null || !outputFile.exists())
			throw new Exception("Output file is null or does ot exist");
		
		if (filterParameters.paramsAreNull())
			throw new Exception("Filter parameters are not set");
		
		if (!groupingParameters.paramsAreSet())
			throw new Exception("Grouping parameters are not set");
		
		if (aggregateStatisticsCollector == null)
			throw new Exception("Aggregate collector is null");
		
		FileReader r = new FileReader(file);
		BufferedReader br = new BufferedReader(r);
		FileWriter fw = new FileWriter(outputFile, true);
        BufferedWriter bw = new BufferedWriter(fw);
        
	    String currentRecord = null;
	    LogRecordFilter logRecordFilter = new LogRecordFilter(filterParameters);
	    while ((currentRecord = br.readLine()) != null) {
	    	LogRecordParser logParser = new LogRecordParser();
	    	LogRecord logRecord = logParser.parseLogRecord(currentRecord);
	    	if (logRecord != null) { 	
		    	aggregateStatisticsCollector.addLogRecordToAggregateStatistics(logRecord);
				if (logRecordFilter.filterLogRecord(logRecord)) {
					bw.append(currentRecord);
					bw.newLine();
				}
	    	}
	    }
		
		br.close();
		bw.close();
	}

}
