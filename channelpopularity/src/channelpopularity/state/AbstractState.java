package channelpopularity.state;

import channelpopularity.util.Results;

public abstract class AbstractState implements StateI {
	
	protected Results results;
	
	public AbstractState(Results results) {
		this.results = results;
	}
	
	int calcPopularityScore(int views,int likes,int dislikes) {
		return views+2*(likes-dislikes);
	}
	
	public StateName changeState(int newPopularityScore) {
		if (newPopularityScore <= 1000)
			return StateName.UNPOPULAR;
		if (newPopularityScore <= 10000 && newPopularityScore >1000)
			return StateName.MILDLY_POPULAR;
		if (newPopularityScore <= 100000 && newPopularityScore >10000)
			return StateName.HIGHLY_POPULAR;
		if (newPopularityScore <= Integer.MAX_VALUE && newPopularityScore >100000)
			return StateName.ULTRA_POPULAR;
		return null;
	}

}
