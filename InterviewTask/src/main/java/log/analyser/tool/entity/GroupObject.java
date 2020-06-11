package log.analyser.tool.entity;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class GroupObject implements Comparable<GroupObject> {
	
	private String username;
	
	private Date time;
	
	private TimeUnit timeUnit;
	
	private SimpleDateFormat timeUnitFormat;
	
	public int getYear() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		return Integer.parseInt(sdf.format(time));
	}
	
	public int getMonth() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM");
		return Integer.parseInt(sdf.format(time));
	}
	
	public int getDay() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		return Integer.parseInt(sdf.format(time));
	}
	
	public int getHour() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH");
		return Integer.parseInt(sdf.format(time));
	}
	
	@Override
	public String toString() {
		if (username == null && time == null) {
			return null; // error
		}
		else if (username == null && time != null) {
			return timeUnitFormat.format(time);
		}
		else if (username != null && time == null) {
			return username;
		}
		return timeUnitFormat.format(time) + " " + username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	public TimeUnit getTimeUnit() {
		return timeUnit;
	}

	public void setTimeUnit(TimeUnit timeUnit) {
		this.timeUnit = timeUnit;
		timeUnitFormat = new SimpleDateFormat(timeUnit.getDateTemplate());
	}

	public SimpleDateFormat getTimeUnitFormat() {
		return timeUnitFormat;
	}

	public void setTimeUnitFormat(SimpleDateFormat timeUnitFormat) {
		this.timeUnitFormat = timeUnitFormat;
	}
	
	public Comparator<GroupObject> compareByTime() {	
		if (time != null) {
			switch (timeUnit) {
			    case HOUR:
			    	return Comparator.comparing(GroupObject::getYear)
			    			.thenComparing(GroupObject::getMonth)
			    			.thenComparing(GroupObject::getDay)
			    			.thenComparing(GroupObject::getHour);
			    case DAY:
			    	return Comparator.comparing(GroupObject::getYear)
			    			.thenComparing(GroupObject::getMonth)
			    			.thenComparing(GroupObject::getDay);
			    case MONTH:
			    	return Comparator.comparing(GroupObject::getYear)
			    			.thenComparing(GroupObject::getMonth);
				default:
					break;
			}
		}
		return null;
	}

	@Override
	public int compareTo(GroupObject o) {	
		if (time != null && username != null) {
			return compareByTime()
					.thenComparing(GroupObject::getUsername)
					.compare(this, o);
		}
		else if (time != null && username == null) {
			return compareByTime()
					.compare(this, o);	
		}
		else if (time == null && username != null) {
			return Comparator.comparing(GroupObject::getUsername)
					.compare(this, o);	
		}
		return -1;
	}
	
}
