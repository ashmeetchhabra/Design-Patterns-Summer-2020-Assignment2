package channelpopularity.state;

import java.io.IOException;
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
	public void addRequest(HashMap<String, ?> str) throws IOException {
		
		int lengthOfAdd = (int) str.get(OperationArgs.LEN.toString());
		
		if(lengthOfAdd>1 && lengthOfAdd<=10) {
			results.writeToFile(StateName.UNPOPULAR.name()+OperationArgs.__AD_REQUEST+"::"+OperationArgs.APPROVED+"\n");
			results.printToConsole(StateName.UNPOPULAR.name()+OperationArgs.__AD_REQUEST+"::"+OperationArgs.APPROVED);
			
		}
		else {
			results.writeToFile(StateName.UNPOPULAR.name()+OperationArgs.__AD_REQUEST+"::"+OperationArgs.REJECTED+"\n");
			results.printToConsole(StateName.UNPOPULAR.name()+OperationArgs.__AD_REQUEST+"::"+OperationArgs.REJECTED);
		}
		
	}

}
