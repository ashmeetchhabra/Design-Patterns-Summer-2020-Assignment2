package channelpopularity.state;

public interface StateI {
	StateName changeState(int popularityScore);
	void approveAdd(int adLength);
}
