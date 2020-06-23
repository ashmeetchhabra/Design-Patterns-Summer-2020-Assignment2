package channelpopularity.state.factory;

import channelpopularity.context.ContextI;
import channelpopularity.state.Highlypopular;
import channelpopularity.state.MildlyPopular;
import channelpopularity.state.StateI;
import channelpopularity.state.StateName;
import channelpopularity.state.Ultrapopular;
import channelpopularity.state.Unpopular;
import channelpopularity.util.Results;

public class SimpleStateFactory implements SimpleStateFactoryI {

	@Override
	public StateI create(StateName stateName, Results results, ContextI context) {
		switch (stateName) {
		case UNPOPULAR:
			return new Unpopular(results, context, stateName);
		case MILDLY_POPULAR:
			return new MildlyPopular(results, context, stateName);
		case HIGHLY_POPULAR:
			return new Highlypopular(results, context, stateName);
		case ULTRA_POPULAR:
			return new Ultrapopular(results, context, stateName);
		default:
			return null;
		}
	}
}