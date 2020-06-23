package channelpopularity.state.factory;

import channelpopularity.context.ContextI;
import channelpopularity.state.StateI;
import channelpopularity.state.StateName;
import channelpopularity.util.Results;

public interface SimpleStateFactoryI {
	StateI create(StateName stateName, Results results, ContextI context);

}
