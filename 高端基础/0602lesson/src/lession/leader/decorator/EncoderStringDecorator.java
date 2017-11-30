package lession.leader.decorator;

public class EncoderStringDecorator extends StringDecorator{

	private StringDecorator decorator;
	
	public EncoderStringDecorator(StringDecorator decorator) {
		this.decorator = decorator;
	}
	
	@Override
	public String resolve() {
		String row = this.decorator.resolve();
		return "#" + row +"#";
	}

}
