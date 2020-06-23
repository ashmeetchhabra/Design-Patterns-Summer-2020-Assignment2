package channelpopularity.driver;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.naming.spi.StateFactory;

import channelpopularity.context.ChannelContext;
import channelpopularity.operation.Operation;
import channelpopularity.state.StateName;
import channelpopularity.state.factory.SimpleStateFactory;
import channelpopularity.util.FileProcessor;
import channelpopularity.util.LineHandler;
import channelpopularity.util.Results;

/**
 * @author John Doe
 *
 */
public class Driver {
	private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 2;

	public static void main(String[] args) {

		/*
		 * As the build.xml specifies the arguments as input,output or metrics, in case
		 * the argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		if ((args.length != 2) || (args[0].equals("${input}")) || (args[1].equals("${output}"))) {
			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.",
					REQUIRED_NUMBER_OF_CMDLINE_ARGS);
			System.exit(0);
		}

		// Initializations
		LineHandler lh = new LineHandler();
		String line;
		FileProcessor fp;
		Results res = null ;
		try {
			res = new Results(args[1]);
			fp = new FileProcessor(args[0]);
			ChannelContext cc = new ChannelContext(new SimpleStateFactory(), StateName.values(), res);
			while ((line = fp.poll()) != null) {
				HashMap<String, ?> hm = lh.lineProcessor(line);
				Operation op = lh.getOperation(line);
				cc.operationHandler(op, hm);
			}
			res.closeFile();
		} catch (InvalidPathException | SecurityException | IOException e) {
			e.printStackTrace();
		}
	}
}
