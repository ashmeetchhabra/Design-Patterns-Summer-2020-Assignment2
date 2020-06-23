package channelpopularity.state;

import java.util.HashMap;
import java.util.Map;

import channelpopularity.context.ContextI;
import channelpopularity.operation.OperationArgs;
import channelpopularity.util.Results;

public class Unpopular extends AbstractState {

	public Unpopular(Results results, ContextI c, StateName sn) {
		super(results, c, sn);
	}

	@Override
	public void addRequest(HashMap<String, ?> str) {
		
		int lengthOfAdd = (int) str.get(OperationArgs.LEN.toString());
		
		if(lengthOfAdd>0 && lengthOfAdd<=10) {
			System.out.println(StateName.UNPOPULAR.name()+OperationArgs.__AD_REQUEST+"::"+OperationArgs.APPROVED);
			
		}
		else
			System.out.println(StateName.UNPOPULAR.name()+OperationArgs.__AD_REQUEST+"::"+OperationArgs.REJECTED);
		
	}

}
