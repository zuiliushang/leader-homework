package lesson.leader;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

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
		System.out.println((a >> 1));
		System.out.println(a >>> 1);
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
		System.out.println(time2 - time1);
		for (int i = 0; i < byteArray.length; i++) {
			for (int j = 0; j < byteArray.length; j++) {
				count += byteArray[j][i];
			}
		}
		long time3 = System.currentTimeMillis();
		System.out.println(time3 - time2);
	}

	@Test
	public void test04() {
		int i = 1;
		while (true) {
			System.out.println(i);
			Byte[] bytes = new Byte[i];
			Arrays.setAll(bytes, (n)->{return (byte)1;})  ;
			Arrays.parallelSort(bytes);
			i = i << 1;
		}
	}

	@Test
	public  void test05() {
		Salary[] salaries = new Salary[10000];
		Arrays.setAll(salaries, (n)->{
			Salary salary = new Salary(Salary.getRandomString(),
					Salary.get5_500W(),Salary.get0_10W());
			return salary;
		});
		Arrays.sort(salaries,new Comparator<Salary>() {
			@Override
			public int compare(Salary o1, Salary o2) {
				int oS1 = o1.getTotal();
				int oS2 = o2.getTotal();
				if (oS1 == oS2) {
					return 0;
				}
				if (oS1 < oS2) {
					return 1;
				}
				return -1;
			}
		});
		Stream.of(Arrays.copyOf(salaries, 10)).forEach(Salary::getInfo);
	}
	
	@Test
	public  void test06() {
		Salary[] salaries = new Salary[10000];
		Arrays.setAll(salaries, (n)->{
			Salary salary = new Salary(Salary.getRandomString(), Salary.get5_500W(),Salary.get0_10W());
			return salary;
		});
		Sort.quickSort(salaries,0,salaries.length-1,new Comparator<Salary>() {
			@Override
			public int compare(Salary o1, Salary o2) {
				int oS1 = o1.getTotal();
				int oS2 = o2.getTotal();
				if (oS1 == oS2) {
					return 0;
				}
				if (oS1 > oS2) {
					return 1;
				}
				return -1;
			}
		});
		Stream.of(Arrays.copyOf(salaries, 10)).forEach(Salary::getInfo);
	}
	
	@Test
	public  void test07() {
		Salary[] salaries = new Salary[10000];
		Arrays.setAll(salaries, (n)->{
			Salary salary = new Salary(Salary.getRandomString(), Salary.get5_500W(),Salary.get0_10W());
			return salary;
		});
		Sort.bubbleSort(salaries,0,salaries.length-1, new Comparator<Salary>() {
			@Override
			public int compare(Salary o1, Salary o2) {
				int oS1 = o1.getTotal();
				int oS2 = o2.getTotal();
				if (oS1 == oS2) {
					return 0;
				}
				if (oS1 > oS2) {
					return 1;
				}
				return -1;
			}
		});
		Stream.of(Arrays.copyOf(salaries, 10)).forEach(Salary::getInfo);
	}
	
	@Test
	public void test08() {
		System.out.println(2<<10);
	}
}
