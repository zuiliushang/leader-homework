package lession.leader;


import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.Test;

public class Main {
	
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
	public void test04() throws Exception{
		File file = new File("e://salaries.txt");
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter r = new FileWriter(file);
		PrintWriter writer = new PrintWriter(r);
		writer.println();
		
	}
	
}
