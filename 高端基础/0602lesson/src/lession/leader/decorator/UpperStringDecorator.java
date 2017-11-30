package lession.leader.decorator;

public class UpperStringDecorator extends StringDecorator{

	private StringDecorator decorator;
	
	public UpperStringDecorator(StringDecorator decorator) {
		this.decorator = decorator;
	}

	@Override
	public String resolve() {
		return decorator.resolve().toUpperCase();
	}

}
