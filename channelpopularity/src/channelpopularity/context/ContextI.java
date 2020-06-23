package channelpopularity.context;

import java.util.HashMap;
import java.util.Map;

import channelpopularity.operation.Operation;

public interface ContextI {

	void operationHandler(Operation op,HashMap<String, ?> str);
	
	Map<String, HashMap<String, Integer>> getVideos();
	
	void setPopularityScore(double score);
	double getPopularityScore();

}
