package lession.leader.decorator;

public class ScaleStringDecorator extends StringDecorator{
	
	private StringDecorator decorator;
	
	public ScaleStringDecorator(StringDecorator decorator) {
		super();
		this.decorator = decorator;
	}

	@Override
	public String resolve() {
		String s = this.decorator.resolve();
		if (s.length() != 10) {
			return (s+"!!!!!!!!!!").substring(0, 10);
		}
		return s;
	}
	
}
