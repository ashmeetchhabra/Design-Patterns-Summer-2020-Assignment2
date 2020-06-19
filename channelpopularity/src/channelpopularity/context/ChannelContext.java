package channelpopularity.context;

import java.util.HashMap;
import java.util.Map;

import channelpopularity.state.StateI;
import channelpopularity.state.StateName;
import channelpopularity.state.factory.SimpleStateFactoryI;

public class ChannelContext implements ContextI {
	
	protected StateI curState;
    protected final Map<StateName, StateI> availableStates = new HashMap<>();
    
    public StateI getCurState() {
		return curState;
	}
	public void setCurState(StateI curState) {
		this.curState = curState;
	}
	public Map<StateName, StateI> getAvailableStates() {
		return availableStates;
	}
	
    public ChannelContext(SimpleStateFactoryI stateFactoryIn, StateName[] stateNames) {
//    	StateName.values();
    	for (StateName state : stateNames)
    		availableStates.put(state, stateFactoryIn.create(state));
		setCurState(availableStates.get(StateName.UNPOPULAR));
    }
	@Override
	public void handleInput(String token) {
		
	}

}
