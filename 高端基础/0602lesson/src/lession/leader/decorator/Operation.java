package lession.leader.decorator;

import java.util.Scanner;

public class Operation {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		StringDecorator decorator = new StringDecorator();
		System.out.println("请输入一段文字:");
		Scanner scanner = new Scanner(System.in);
		String text = scanner.nextLine();
		if (text == null || text.isEmpty()) {
			System.out.println("输入为空");
			System.exit(0);
		}
		decorator.resolve(text);
		System.out.println(" 1 ：加密  2 ：反转字符串  3：转成大写  4：转成小写  5：扩展或者剪裁到10个字符，不足部分用！补充 组合用\",\"分割 比如 1,2,3,4,5");
		System.out.println();
		text = scanner.nextLine();
		if (text == null || text.isEmpty()) {
			System.out.println("输入为空");
			System.exit(0);
		}
		String[] in = text.split(",");
		for (int i = 0; i < in.length; i++) {
			switch (in[i]) { 
				case "1" : decorator = new EncoderStringDecorator(decorator); break;
				case "2" : decorator = new ReserveStringDecorator(decorator); break;
				case "3" : decorator = new UpperStringDecorator(decorator);break;
				case "4" : decorator = new LowerStringDecorator(decorator);break;
				case "5" : decorator = new ScaleStringDecorator(decorator);break;
				default: break;
			}
		}
		System.out.println(decorator.resolve());
	}
}
