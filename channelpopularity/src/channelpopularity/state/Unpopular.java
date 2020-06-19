package channelpopularity.state;

import channelpopularity.util.Results;

public class Unpopular extends AbstractState {

	public Unpopular(Results results) {
		super(results);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void approveAdd(int advLength) {
		if (advLength > 1 && advLength <= 10) {

		}
	}

}
