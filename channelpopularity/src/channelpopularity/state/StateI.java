package channelpopularity.state;

import java.util.HashMap;

public interface StateI {
	StateName changeState();
	void addVideo(HashMap<String, ?> str);
	void removeVideo(HashMap<String, ?> str);
	void addRequest(HashMap<String, ?> str);
	void metrics(HashMap<String, ?> str);
}
