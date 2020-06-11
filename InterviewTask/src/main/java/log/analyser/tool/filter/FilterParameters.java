package log.analyser.tool.filter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FilterParameters {
	
	private String dateFormatTemplate;

    private Date fromDate;
	
	private Date toDate;
	
	private String username;
	
	private String messagePattern;

	public boolean paramsAreNull() {
		return fromDate == null && toDate == null &&
				username == null && messagePattern == null;
	}
	
	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	
	public void setFromDateAsText(String fromDate) throws ParseException {
		this.fromDate = parseDate(fromDate);
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	
	public void setToDateAsText(String toDate) throws ParseException {
		this.toDate = parseDate(toDate);
	}
	
	public Date parseDate(String date) throws ParseException {
		return new SimpleDateFormat(dateFormatTemplate).parse(date);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMessagePattern() {
		return messagePattern;
	}

	public void setMessagePattern(String messagePattern) {
		this.messagePattern = messagePattern;
	}
	
}
