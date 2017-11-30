package lession.leader.decorator;

public class LowerStringDecorator extends StringDecorator{

	private StringDecorator decorator;
	
	public LowerStringDecorator(StringDecorator decorator) {
		super();
		this.decorator = decorator;
	}

	@Override
	public String resolve() {
		return this.decorator.resolve().toLowerCase();
	}
	
}
