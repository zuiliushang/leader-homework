package lesson.leader;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

import org.junit.Test;

public class Salary {
	
	String name; 
	
	int baseSalary;
	
	int bonus;

	public Salary(String name, int baseSalary, int bonus) {
		super();
		this.name = name;
		this.baseSalary = baseSalary;
		this.bonus = bonus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(int baseSalary) {
		this.baseSalary = baseSalary;
	}

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
	
	
	public static String getRandomString() {
	    String KeyString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	    StringBuffer sb = new StringBuffer();
	    int len = KeyString.length();
	    for (int i = 0; i < 5; i++) {
	       sb.append(KeyString.charAt((int) Math.round(Math.random() * (len - 1))));
	    }
	    return sb.toString();
	}
	
	public static int get5_500W() {
		return (int) (5+Math.random()*4999995);
	}
	
	public static int get0_10W() {
		return (int)( Math.random()*100000);
	}
	
	public int getTotal() {
		return baseSalary * 13 + bonus;
	}
	
	public void getInfo() {
		System.out.println(String.format("%s:%d", name,baseSalary * 13 + bonus));
	}
	
	
	
}
