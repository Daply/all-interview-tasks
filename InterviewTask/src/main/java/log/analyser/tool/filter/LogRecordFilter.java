package log.analyser.tool.filter;

import log.analyser.tool.entity.LogRecord;

public class LogRecordFilter {
	
	private FilterParameters filterParameters;
	
	public LogRecordFilter(FilterParameters filterParameters) {
		this.filterParameters = filterParameters;
	}
	
	public boolean filterLogRecord(LogRecord logRecord) throws Exception {
		
		if (logRecord == null) {
			throw new Exception("Input log record is null");
		}
		
		return checkTimePeriodParameter(logRecord) ||
				checkUsernameParameter(logRecord) ||
				checkMessagePatternParameter(logRecord);
	}
	
	public boolean checkTimePeriodParameter(LogRecord logRecord) throws Exception {
		
		if (logRecord == null) {
			throw new Exception("Input log record is null");
		}
		
		if (filterParameters.getFromDate() != null && filterParameters.getToDate() != null) {
			if ((logRecord.getDate().after(filterParameters.getFromDate()) &&
					logRecord.getDate().before(filterParameters.getToDate())) ||
					logRecord.getDate().equals(filterParameters.getFromDate()) ||
					logRecord.getDate().equals(filterParameters.getToDate())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkUsernameParameter(LogRecord logRecord) throws Exception {
		
		if (logRecord == null) {
			throw new Exception("Input log record is null");
		}
		
		if (filterParameters.getUsername() != null) {
		    if (logRecord.getUsername().equals(filterParameters.getUsername())) {
		    	return true;
		    }
		}
		return false;
	}
	
	public boolean checkMessagePatternParameter(LogRecord logRecord) throws Exception {
		
		if (logRecord == null) {
			throw new Exception("Input log record is null");
		}
		
		if (filterParameters.getMessagePattern() != null) {
			if (logRecord.getMessage().matches(filterParameters.getMessagePattern())) {
				return true;
			}
		}
		return false;
	}

}
