package channelpopularity.state;

import java.io.IOException;
import java.util.HashMap;

public interface StateI {
	StateName changeState();
	void addVideo(HashMap<String, ?> str) throws IOException;
	void removeVideo(HashMap<String, ?> str) throws IOException;
	void addRequest(HashMap<String, ?> str) throws IOException;
	void metrics(HashMap<String, ?> str) throws IOException;
}
