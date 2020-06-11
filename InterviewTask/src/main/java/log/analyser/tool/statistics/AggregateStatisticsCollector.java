package log.analyser.tool.statistics;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import log.analyser.tool.entity.GroupObject;
import log.analyser.tool.entity.LogRecord;
import log.analyser.tool.entity.TimeUnit;

public class AggregateStatisticsCollector {
	
	private GroupingParameters groupingParameters = null;
	
	private Map<GroupObject, Integer> statistics = null;

	public AggregateStatisticsCollector(GroupingParameters groupingParameters) {
		this.groupingParameters = groupingParameters;
		this.statistics = new TreeMap<GroupObject, Integer>();
	}
	
	public synchronized void addLogRecordToAggregateStatistics(LogRecord record) throws Exception {
		
		if (record == null)
			throw new Exception("Input record is null");
		
		GroupObject groupObject = new GroupObject();
		if (groupingParameters.isUsernameSet()) {
			groupObject.setUsername(record.getUsername());
		}
		if (!groupingParameters.getTimeUnit().equals(TimeUnit.NO)) {
			groupObject.setTimeUnit(groupingParameters.getTimeUnit());
			groupObject.setTime(record.getDate());
		}
		
		if (groupObject.getUsername() == null &&
				groupObject.getTime() == null) {
			throw new Exception("All grouping parameters are null");
		}
		
		if (statistics.containsKey(groupObject)) {
			int numberOfRecords = statistics.get(groupObject);
			statistics.replace(groupObject, numberOfRecords + 1);
		}
		else {
			statistics.put(groupObject, 1);
		}
	}
	
	public void printAggregateStatistics() {
		for (Entry<GroupObject, Integer> entry: statistics.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
	}

	public Map<GroupObject, Integer> getStatistics() {
		return statistics;
	}

	public void setStatistics(Map<GroupObject, Integer> statistics) {
		this.statistics = statistics;
	}

	public GroupingParameters getGroupingParameters() {
		return groupingParameters;
	}

	public void setGroupingParameters(GroupingParameters groupingParameters) {
		this.groupingParameters = groupingParameters;
	}
	
}
