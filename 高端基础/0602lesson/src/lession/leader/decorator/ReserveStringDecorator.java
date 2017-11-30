package lession.leader.decorator;

public class ReserveStringDecorator extends StringDecorator{
	
	private StringDecorator decorator;
	
	public ReserveStringDecorator(StringDecorator decorator) {
		this.decorator = decorator;
	}

	@Override
	public String resolve() {
		return new StringBuffer(this.decorator.resolve()).reverse().toString();
	}
	
}
