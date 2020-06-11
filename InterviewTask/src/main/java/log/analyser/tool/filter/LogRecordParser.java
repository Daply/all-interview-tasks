package log.analyser.tool.filter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import log.analyser.tool.entity.LogRecord;


public class LogRecordParser {
	
	private String datePattern = "yyyy-MM-dd HH:mm:ss";
	private String dateRegexpPattern = "[0-9]{4}-[0-9]{2}-[0-9]{2}\\s[0-9]{2}:[0-9]{2}:[0-9]{2}";
	
	public LogRecord parseLogRecord(String record) throws Exception {
		
		if (record == null) {
			throw new Exception("Input record is null");
		}
		
		Pattern p = Pattern.compile(dateRegexpPattern);
		Matcher matcher = p.matcher(record);
		if (matcher.find()) {
			int lastIndex = matcher.end();
	        String dateString = record.substring(0, lastIndex);
			SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
			Date date = sdf.parse(dateString);
			String[] userMes = record.split("\\s");
			String username = userMes[2];
			String message = userMes[3];
			LogRecord logRecord = new LogRecord(date, username, message);
			return logRecord;
		}
		else {
			return null;
		}
	}

}
