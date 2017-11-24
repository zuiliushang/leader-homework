package lesson.leader;

import java.util.Arrays;

import org.junit.Test;

public class lesson1 {
	
	
	@Test
	public void test01() {
		byte ba = 127;
		int bb = ba << 2;
		System.out.println(bb);
	}
	
	@Test
	public void test02() {
		int a = -1024;
		System.out.println(a>>1);
		System.out.println(a>>>1);
	}
	
	@Test
	public void test03() {
		byte[][] byteArray = new byte[10240][10240];
		int count = 0;
		long time1 = System.currentTimeMillis();
		for (int i = 0; i < byteArray.length; i++) {
			for (int j = 0; j < byteArray.length; j++) {
				count += byteArray[i][j];
			}
		}
		long time2 = System.currentTimeMillis();
		System.out.println(time2-time1);
		for (int i = 0; i < byteArray.length; i++) {
			for (int j = 0; j < byteArray.length; j++) {
				count += byteArray[j][i];
			}
		}
		long time3 = System.currentTimeMillis();
		System.out.println(time3-time2);
	}
	
}
