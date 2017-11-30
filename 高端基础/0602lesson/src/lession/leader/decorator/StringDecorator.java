package lession.leader.decorator;

public class StringDecorator{

	private String row;
	
	public String resolve() {
		return row;
	}
	
	public void resolve(String row) {
		this.row = row;
	}
	
}
