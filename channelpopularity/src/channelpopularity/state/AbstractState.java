package channelpopularity.state;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import channelpopularity.context.ContextI;
import channelpopularity.operation.OperationArgs;
import channelpopularity.util.Results;

public abstract class AbstractState implements StateI {

	protected Results results;
	protected ContextI con;
	protected StateName st;

	public AbstractState(Results results, ContextI con, StateName st) {
		this.results = results;
		this.con = con;
		this.st = st;
	}

	protected void calculatePopularityScore() {
		Map<String, HashMap<String, Integer>> allVideos = con.getVideos();
		int views = 0, likes = 0, dislikes = 0, noOfVideos = 0;

		for (HashMap<String, Integer> it : allVideos.values()) {
			noOfVideos++;
			views = views + it.getOrDefault(OperationArgs.VIEWS.name(), 0);
			likes = likes + it.getOrDefault(OperationArgs.LIKES.name(), 0);
			dislikes = dislikes + it.getOrDefault(OperationArgs.DISLIKES.name(), 0);
		}
		if (noOfVideos != 0)
			con.setPopularityScore((views + 2 * (likes - dislikes)) / noOfVideos);
		else
			con.setPopularityScore(views + 2 * (likes - dislikes));

	}

	public StateName changeState() {
		double newPopularityScore = con.getPopularityScore();
		if (newPopularityScore <= 1000)
			return StateName.UNPOPULAR;
		if (newPopularityScore <= 10000 && newPopularityScore > 1000)
			return StateName.MILDLY_POPULAR;
		if (newPopularityScore <= 100000 && newPopularityScore > 10000)
			return StateName.HIGHLY_POPULAR;
		if (newPopularityScore <= Integer.MAX_VALUE && newPopularityScore > 100000)
			return StateName.ULTRA_POPULAR;
		throw new RuntimeException("Invalid State");
	}

	/**
	 * Add a Video in a channel
	 * 
	 * @param str: HashMap of videonames and parameters(metrics and adv length)
	 *
	 */
	public void addVideo(HashMap<String, ?> str) throws IOException {
		if (con.getVideos().containsKey((String) str.get(OperationArgs.VIDEONAME.toString())))
			throw new RuntimeException(
					"Invalid Input File: " + (String) str.get(OperationArgs.VIDEONAME.toString()) + " Already Added");
		con.getVideos().put((String) str.get(OperationArgs.VIDEONAME.toString()), new HashMap<String, Integer>());
		calculatePopularityScore();
		con.setCurrentState(this.changeState());
		results.writeToFile(
				st + OperationArgs.__VIDEO_ADDED.name() + "::" + str.get(OperationArgs.VIDEONAME.name()) + "\n");
		results.printToConsole(
				st + OperationArgs.__VIDEO_ADDED.name() + "::" + str.get(OperationArgs.VIDEONAME.name()));
	}

	/**
	 * Remove the video from the Channel
	 * 
	 * @param str: HashMap of videonames and parameters(metrics and adv length)
	 *
	 */
	public void removeVideo(HashMap<String, ?> str) throws IOException {
		if (!con.getVideos().containsKey((String) str.get(OperationArgs.VIDEONAME.toString())))
			throw new RuntimeException("Invalid Input File: " + (String) str.get(OperationArgs.VIDEONAME.toString())
					+ " does not present");
		con.getVideos().remove((String) str.get(OperationArgs.VIDEONAME.toString()));
		calculatePopularityScore();
		con.setCurrentState(this.changeState());
		results.writeToFile(
				st + OperationArgs.__VIDEO_REMOVED.name() + "::" + str.get(OperationArgs.VIDEONAME.name()) + "\n");
		results.printToConsole(
				st + OperationArgs.__VIDEO_REMOVED.name() + "::" + str.get(OperationArgs.VIDEONAME.name()));
	}

	/**
	 * Calculates the metrics
	 * 
	 * @param str: HashMap of videonames and parameters(metrics and adv length)
	 *
	 */
	public void metrics(HashMap<String, ?> str) throws IOException {
		int views = (int) str.get(OperationArgs.VIEWS.toString());
		int likes = (int) str.get(OperationArgs.LIKES.toString());
		int dislikes = (int) str.get(OperationArgs.DISLIKES.toString());
		int totalLikes, totalDislikes;

		if (views < 0)
			throw new RuntimeException(
					"Invalid Input File: Negative Views for " + str.get(OperationArgs.VIDEONAME.name()));

		Map<String, Integer> metrics = con.getVideos().get(str.get(OperationArgs.VIDEONAME.name()));

		totalLikes = likes + metrics.getOrDefault(OperationArgs.LIKES.name(), 0);
		totalDislikes = dislikes + metrics.getOrDefault(OperationArgs.DISLIKES.name(), 0);

		if (totalLikes < 0)
			throw new RuntimeException(
					"Invalid Input File: Negative Total Likes for " + str.get(OperationArgs.VIDEONAME.name()));

		if (totalDislikes < 0)
			throw new RuntimeException(
					"Invalid Input File: Negative Total Dislikes for " + str.get(OperationArgs.VIDEONAME.name()));

		if (!con.getVideos().containsKey((String) str.get(OperationArgs.VIDEONAME.toString())))
			throw new RuntimeException("Invalid Input File: " + (String) str.get(OperationArgs.VIDEONAME.toString())
					+ " does not present");
		metrics.put(OperationArgs.VIEWS.name(), views + metrics.getOrDefault(OperationArgs.VIEWS.name(), 0));
		metrics.put(OperationArgs.LIKES.name(), totalLikes);
		metrics.put(OperationArgs.DISLIKES.name(), dislikes);

		calculatePopularityScore();
		con.setCurrentState(this.changeState());
		results.writeToFile(
				st + OperationArgs.__POPULARITY_SCORE_UPDATE.name() + "::" + (int) con.getPopularityScore() + "\n");
		results.printToConsole(
				st + OperationArgs.__POPULARITY_SCORE_UPDATE.name() + "::" + (int) con.getPopularityScore());
	}

}
