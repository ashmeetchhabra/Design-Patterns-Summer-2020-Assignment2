package channelpopularity.state.factory;

import channelpopularity.state.Highlypopular;
import channelpopularity.state.MildlyPopular;
import channelpopularity.state.StateI;
import channelpopularity.state.StateName;
import channelpopularity.state.Ultrapopular;
import channelpopularity.state.Unpopular;
import channelpopularity.util.Results;

public class SimpleStateFactory implements SimpleStateFactoryI {

	private final Results results;

	public SimpleStateFactory(Results results) {
		this.results = results;
	}

	@Override
	public StateI create(StateName stateName) {
		switch (stateName) {
		case UNPOPULAR:
			return new Unpopular(results);
		case MILDLY_POPULAR:
			return new MildlyPopular(results);
		case HIGHLY_POPULAR:
			return new Highlypopular(results);
		case ULTRA_POPULAR:
			return new Ultrapopular(results);
		default:
			return null;
		}
	}
}