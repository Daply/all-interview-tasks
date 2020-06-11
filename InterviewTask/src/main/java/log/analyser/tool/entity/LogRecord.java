package log.analyser.tool.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogRecord {
	
	private Date date;
	
	private SimpleDateFormat sdf;
	
	private String username;
	
	private String message;

	public LogRecord(Date date, String username, String message) {
		this.date = date;
		this.sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.username = username;
		this.message = message;
	}

	@Override
	public String toString() {
		String dte = sdf.format(date);
		return dte + " " + username + " " + message;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public SimpleDateFormat getSdf() {
		return sdf;
	}

	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
