package channelpopularity.state;

import java.io.IOException;
import java.util.HashMap;

import channelpopularity.context.ContextI;
import channelpopularity.operation.OperationArgs;
import channelpopularity.util.Results;

public class Ultrapopular extends AbstractState{

	public Ultrapopular(Results results,ContextI c, StateName sn) {
		super(results, c, sn);
	}

	@Override
	public void addRequest(HashMap<String, ?> str) throws IOException {
		int lengthOfAdd = (int) str.get(OperationArgs.LEN.toString());
		
		if(lengthOfAdd>1 && lengthOfAdd<=40) {
			results.writeToFile(StateName.ULTRA_POPULAR.name()+OperationArgs.__AD_REQUEST+"::"+OperationArgs.APPROVED+"\n");
			results.printToConsole(StateName.ULTRA_POPULAR.name()+OperationArgs.__AD_REQUEST+"::"+OperationArgs.APPROVED);
		}
		else {
			results.writeToFile(StateName.ULTRA_POPULAR.name()+OperationArgs.__AD_REQUEST+"::"+OperationArgs.REJECTED+"\n");
			results.printToConsole(StateName.ULTRA_POPULAR.name()+OperationArgs.__AD_REQUEST+"::"+OperationArgs.REJECTED);
		}
		
	}


}
