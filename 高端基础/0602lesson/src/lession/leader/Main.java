package lession.leader;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import com.sun.org.apache.bcel.internal.generic.NEW;

import sun.nio.ch.FileChannelImpl;

public class Main {
	
	private final static int fileSize = 1000000;
	
	@Test
	public void test01() throws UnsupportedEncodingException {
		String s = "中国";
		System.out.println(s.getBytes("utf-8").length);
		System.out.println(s.getBytes("gbk").length);
		System.out.println(s.getBytes("iso-8859-1").length);
	}
	
	//大端
	@Test
	public void test02() throws IOException {
		int a = 10240;
		byte[] bs = new byte[4];
		bs[0] = (byte)(a >> 0 );
		bs[1] = (byte)(a >> 8 );
		bs[2] = (byte)(a >> 16 );
		bs[3] = (byte)(a >> 24 );
		for (int i = 0; i < bs.length; i++) {
			System.out.println(bs[i]);
		}
		System.out.println(40 << 8);
		
		File file = new File("e://test.txt");
		if (!file.exists()) {
			file.createNewFile();
		}
		OutputStream out = new FileOutputStream(file);
		out.write(bs);
		out.flush();
		out.close();
	}
	
	//小端
	@Test
	public void test03() throws IOException {
		int a = 10240;
		byte[] bs = new byte[4];
		bs[3] = (byte)(a >> 0 );
		bs[2] = (byte)(a >> 8 );
		bs[1] = (byte)(a >> 16 );
		bs[0] = (byte)(a >> 24 );
		File file = new File("e://test1.txt");
		if (!file.exists()) {
			file.createNewFile();
		}
		OutputStream out = new FileOutputStream(file);
		out.write(bs);
		out.flush();
		out.close();
	}
	
	//写文件
	@Test
	public void test04(){
		long start = System.currentTimeMillis();
		File file = new File("e://salaries.txt");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				throw new RuntimeException();
			}
		}
		try (
			 PrintWriter writer = new PrintWriter(new FileWriter(file));
			){
			Salary[] salaries = new Salary[fileSize];
			Arrays.setAll(salaries, (n)->{
				return new Salary(Salary.getRandomString(), Salary.get5_500W(), Salary.get0_10W());
			});
			
			Arrays
				.stream(salaries)
				.parallel()
				.forEach(s->writer.println(String.format("%s,%d,%d", s.getName(),s.getBaseSalary(),s.getBonus())));
			writer.flush();
			List<Statistics> statistics = new ArrayList<>();
			List<Salary> s = new ArrayList<>();
			Files.readAllLines(file.toPath(), Charset.forName("utf-8")).forEach(row->{
				String[] tmps = row.split(",");
				s.add(new Salary(tmps[0], Integer.parseInt(tmps[1]), Integer.parseInt(tmps[2])));
			});;
			//s.stream().collect(Collectors.groupingBy());
			System.out.println(s.size());
			s.stream()
				.collect(Collectors.groupingBy(rs -> rs.getName().substring(0, 2)))
				.entrySet()
				.stream()
				.forEach(entry->{
				int salary = entry.getValue().stream().reduce(0,((t1,t2)->(t1+t2.getTotal())),(total1,total2)->total1+total2);
				statistics.add(new Statistics(entry.getKey(), salary, entry.getValue().size()));
			});
			statistics
				.stream()
				.sorted((s1,s2)->{
					if(s1.getSalary() > s2.getSalary())
						return -1;
					if(s1.getSalary() < s2.getSalary())
						return 1;
					return 0;
				})
				.limit(10)
				.forEach(row->{
					System.out.println(String.format("%s,%d万,%d人", row.getName(),row.getSalary(),row.getSum()));
				});;
			System.out.println(System.currentTimeMillis()-start);
		} catch (IOException e) {
			
		} 
	}
	
	@Test
	public void test07() throws FileNotFoundException {
		
		Salary[] salaries = new Salary[fileSize];
		try (RandomAccessFile file = new RandomAccessFile("e://salaries_bs.txt","rw");
				FileChannel fileChannel = file.getChannel()){
			Arrays.setAll(salaries, (n)->{
				return new Salary(Salary.getRandomString(), Salary.get5_500W(), Salary.get0_10W());
			});
			byte[] bs = Arrays
				.stream(salaries)
				.reduce((new StringBuffer()),((sb,total)->(sb.append(String.format("%s,%d,%d\n", total.getName(),total.getBaseSalary(),total.getBonus())))),(total1,total2)->total1=total2)
				.toString()
				.getBytes("utf-8");
			ByteBuffer dst = ByteBuffer.wrap(bs);
			while (dst.hasRemaining()) {
				fileChannel.write(dst);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void test08() throws FileNotFoundException, IOException {
		long start = System.currentTimeMillis();
		test07();
		try (RandomAccessFile file = new RandomAccessFile("e://salaries_bs.txt","rw");
				FileChannel fileChannel = file.getChannel()){
			ByteBuffer dsf = ByteBuffer.allocate(1024);
			List<Statistics> statistics = new ArrayList<>();
			List<Salary> s = new ArrayList<>();
			StringBuffer stringBuffer = new StringBuffer();
			int bytesRead = fileChannel.read(dsf);  
	        while (bytesRead != -1) {  
	            dsf.flip();  
	            stringBuffer.append(Charset.forName("utf-8").decode(dsf));
	            dsf.clear();  
	            bytesRead = fileChannel.read(dsf);  
	        }
	        Stream.of(stringBuffer.toString().split("\n")).forEach(n->{
	        	String[] tmps = n.split(",");
	        	if (tmps.length==3) {
	        		s.add(new Salary(tmps[0], Integer.parseInt(tmps[1]), Integer.parseInt(tmps[2])));
				}
	        	});
	        s.stream()
			.collect(Collectors.groupingBy(rs -> rs.getName().substring(0, 2)))
			.entrySet()
			.stream()
			.forEach(entry->{
			int salary = entry.getValue().stream().reduce(0,((t1,t2)->(t1+t2.getTotal())),(total1,total2)->total1+total2);
			statistics.add(new Statistics(entry.getKey(), salary, entry.getValue().size()));
		});
	    System.out.println(s.size());
		statistics
			.stream()
			.sorted((s1,s2)->{
				if(s1.getSalary() > s2.getSalary())
					return -1;
				if(s1.getSalary() < s2.getSalary())
					return 1;
				return 0;
			})
			.limit(10)
			.forEach(row->{
				System.out.println(String.format("%s,%d万,%d人", row.getName(),row.getSalary(),row.getSum()));
			});;
		}
		System.out.println(System.currentTimeMillis() - start);
	}
	
	
	class Statistics {
		
		private String name;

		private int salary;
		
		private int sum;

		public Statistics(String name, int salary, int sum) {
			super();
			this.name = name;
			this.salary = salary;
			this.sum = sum;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getSalary() {
			return salary;
		}

		public void setSalary(int salary) {
			this.salary = salary;
		}

		public int getSum() {
			return sum;
		}

		public void setSum(int sum) {
			this.sum = sum;
		}
		
	}
}
