package channelpopularity.context;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import channelpopularity.operation.Operation;
import channelpopularity.state.StateName;

public interface ContextI {

	void operationHandler(Operation op,HashMap<String, ?> str) throws IOException;
	
	Map<String, HashMap<String, Integer>> getVideos();
	
	void setPopularityScore(double score);
	double getPopularityScore();
	void setCurrentState(StateName name);

}
