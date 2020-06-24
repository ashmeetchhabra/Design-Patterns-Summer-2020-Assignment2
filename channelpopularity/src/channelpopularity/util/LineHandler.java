package channelpopularity.util;

import java.util.HashMap;

import channelpopularity.operation.Operation;
import channelpopularity.operation.OperationArgs;

public class LineHandler {
	/**
	 * Processes the line and retun a Map
	 * 
	 * @param line: line of the Input file
	 * @return HashMap<String, ?> of videoname and the parameters(Metrics and
	 *         length)
	 */
	public HashMap<String, ?> lineProcessor(String line) {
		if (!line.contains("::"))
			throw new RuntimeException("Invalid Input Line Format " + line);

		String s[] = line.split("::");
		if (s[0].startsWith(Operation.ADD_VIDEO.toString())) {
			// func1(): returns Hashmap<String,String>
			HashMap<String, String> addVideoHashMap = new HashMap<String, String>();
			addVideoHashMap.put(OperationArgs.VIDEONAME.toString(), s[1]);
			return addVideoHashMap;

		}
		if (s[0].startsWith(Operation.REMOVE_VIDEO.toString())) {
			HashMap<String, String> removeVideoHashMap = new HashMap<String, String>();
			removeVideoHashMap.put(OperationArgs.VIDEONAME.toString(), s[1]);
			return removeVideoHashMap;
		}
		if (s[0].startsWith(Operation.METRICS.toString())) {
			HashMap<String, Object> metricsHashMap = new HashMap<String, Object>();
			if (!s[0].contains("__"))
				throw new RuntimeException("Invalid Input Line Format " + line);
			String preOp[] = s[0].split("__");
			String videoName = preOp[1];

			if (!s[1].startsWith("[") && !s[1].endsWith("]") && !s[1].contains(","))
				throw new RuntimeException("Invalid Input Line Format " + line);

			String m[] = s[1].substring(1, s[1].length() - 1).split(",");
			for (int i = 0; i < m.length; i++) {
				String n[] = m[i].split("=");
				metricsHashMap.put(n[0], Integer.parseInt(n[1]));// throws NumberFormatException if views/likes/dislikes
																	// are not integers and is handled in driver.class
				metricsHashMap.put(OperationArgs.VIDEONAME.toString(), videoName);
			}
			return metricsHashMap;
		}
		if (s[0].startsWith(Operation.AD_REQUEST.toString())) {
			HashMap<String, Object> advRequestHashMap = new HashMap<String, Object>();
			String m[] = s[1].split("=");
			advRequestHashMap.put(m[0], Integer.parseInt(m[1]));// throws NumberFormatException if Len are not integers
																// and is handled in driver.class
			String preOp[] = s[0].split("__");
			String videoName = preOp[1];
			advRequestHashMap.put(OperationArgs.VIDEONAME.toString(), videoName);
			return advRequestHashMap;
		}
		throw new RuntimeException("Invalid Input Line: " + line);

	}

	/**
	 * Check for the Operation to be performed
	 * 
	 * @param line: line of the Input file
	 * @return Operation enum
	 */
	public Operation getOperation(String line) {
		if (line.startsWith(Operation.ADD_VIDEO.toString()))
			return Operation.ADD_VIDEO;
		else if (line.startsWith(Operation.REMOVE_VIDEO.toString()))
			return Operation.REMOVE_VIDEO;
		else if (line.startsWith(Operation.METRICS.toString()))
			return Operation.METRICS;
		else if (line.startsWith(Operation.AD_REQUEST.toString()))
			return Operation.AD_REQUEST;
		return null;
	}

}
