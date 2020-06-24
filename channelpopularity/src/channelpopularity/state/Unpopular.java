package channelpopularity.state;

import java.io.IOException;
import java.util.HashMap;

import channelpopularity.context.ContextI;
import channelpopularity.operation.OperationArgs;
import channelpopularity.util.Results;

public class Unpopular extends AbstractState {

	public Unpopular(Results results, ContextI c, StateName sn) {
		super(results, c, sn);
	}

	/**
	 * Categorize the advertisement according to the length and the states
	 * 
	 * @param str: HashMap of videonames and parameters(metrics and adv length)
	 *
	 */
	@Override
	public void addRequest(HashMap<String, ?> str) throws IOException {
		if (!con.getVideos().containsKey((String) str.get(OperationArgs.VIDEONAME.toString())))
			throw new RuntimeException("Invalid Input File: " + (String) str.get(OperationArgs.VIDEONAME.toString())
					+ " does not present");

		int lengthOfAdd = (int) str.get(OperationArgs.LEN.toString());
		if (lengthOfAdd < 0)
			throw new RuntimeException("Invalid Input File: Negative length of advertisement is not accepted");

		if (lengthOfAdd > 1 && lengthOfAdd <= 10) {
			results.writeToFile(
					StateName.UNPOPULAR.name() + OperationArgs.__AD_REQUEST + "::" + OperationArgs.APPROVED + "\n");
			results.printToConsole(
					StateName.UNPOPULAR.name() + OperationArgs.__AD_REQUEST + "::" + OperationArgs.APPROVED);

		} else {
			results.writeToFile(
					StateName.UNPOPULAR.name() + OperationArgs.__AD_REQUEST + "::" + OperationArgs.REJECTED + "\n");
			results.printToConsole(
					StateName.UNPOPULAR.name() + OperationArgs.__AD_REQUEST + "::" + OperationArgs.REJECTED);
		}

	}

}
