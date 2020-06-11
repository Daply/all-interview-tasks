package log.analyser.tool.statistics;

import log.analyser.tool.entity.TimeUnit;

public class GroupingParameters {

	private boolean usernameSet;

	private TimeUnit timeUnit;
	
	public boolean paramsAreSet() {
		return usernameSet || timeUnit == TimeUnit.NO;
	}

	public boolean isUsernameSet() {
		return usernameSet;
	}

	public void setUsernameSet(boolean usernameSet) {
		this.usernameSet = usernameSet;
	}

	public TimeUnit getTimeUnit() {
		return timeUnit;
	}

	public void setTimeUnit(TimeUnit timeUnit) {
		this.timeUnit = timeUnit;
	}

}
