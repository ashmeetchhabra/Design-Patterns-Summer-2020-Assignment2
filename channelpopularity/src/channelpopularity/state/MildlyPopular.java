package channelpopularity.state;

import java.io.IOException;
import java.util.HashMap;

import channelpopularity.context.ContextI;
import channelpopularity.operation.OperationArgs;
import channelpopularity.util.Results;

public class MildlyPopular extends AbstractState {

	public MildlyPopular(Results results,ContextI c, StateName sn) {
		super(results, c, sn);
	}

	@Override
	public void addRequest(HashMap<String, ?> str) throws IOException {
		int lengthOfAdd = (int) str.get(OperationArgs.LEN.toString());
		
		if(lengthOfAdd>1 && lengthOfAdd<=20) {
			results.writeToFile(StateName.MILDLY_POPULAR.name()+OperationArgs.__AD_REQUEST+"::"+OperationArgs.APPROVED+"\n");
			results.printToConsole(StateName.MILDLY_POPULAR.name()+OperationArgs.__AD_REQUEST+"::"+OperationArgs.APPROVED);
		}
		else {
			results.writeToFile(StateName.MILDLY_POPULAR.name()+OperationArgs.__AD_REQUEST+"::"+OperationArgs.REJECTED+"\n");
			results.printToConsole(StateName.MILDLY_POPULAR.name()+OperationArgs.__AD_REQUEST+"::"+OperationArgs.REJECTED);
		}
		
	}


}
