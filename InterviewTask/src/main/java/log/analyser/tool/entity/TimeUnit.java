package log.analyser.tool.entity;

public enum TimeUnit {
	NO, HOUR, DAY, MONTH;
	
	public String getDateTemplate() {
		switch (this) {
		    case HOUR:
		    	return "yyyy/MM/dd HH";
		    case DAY:
		    	return "yyyy/MM/dd";
		    case MONTH:
		    	return "yyyy/MM";
			default:
				return "yyyy/MM/dd HH:mm:ss";
	    }
	}
}
