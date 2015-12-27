package com.h2y.jxc.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

	public static void main(String[] args){
		//		getRandom2();
		//		duplicateMapKey();
		//		listToStr();


		//		List<String> lists = MemcachedUtil.getInstance().getAllKeys();
		//		
		//		for (String key : lists) {
		//			MemcachedUtil.getInstance().delete(key);
		//		}

		//		Date oldDate = DateUtil.toDate("2015-09-02 10:27",DateUtil.YYYY_MM_DD_HH_MM);
		//		Date newDate = DateUtil.toDate("2015-09-02 10:27:00");
		//		
		//		System.out.println(oldDate.compareTo(newDate));


		List<Person> listA = new ArrayList<Person>();
		Person p1 = new Person();
		Person p2 = new Person();
		Person p3 = new Person();

		p1.setName("name1");
		p1.setOrder(1);
		p2.setName("name2");
		p2.setOrder(2);
		p3.setName("name3");
		p3.setOrder(3);

		listA.add(p2);
		listA.add(p1);
		listA.add(p3);

		Collections.sort(listA, new Comparator<Person>() {
			public int compare(Person arg0, Person arg1) {
				return arg1.getOrder().compareTo(arg0.getOrder());
			}
		});

		for (Person p : listA) {
			System.out.println(p.getName());
		}

	}

	public static void listToStr(){
		List<String> tmpList = new ArrayList<String>();
		tmpList.add("hello");
		tmpList.add("world");

		System.out.println(tmpList.toString().replace("[", "(").replace("]", ")"));
	}

	public static void division(){
		Double d1 = 65.00;
		System.out.println(100.00/3.00);
	}

	public static void getRandom(){
		int i = (int)(Math.random()*90)+10;
		System.out.println(i);
	}

	public static void getRandom2(){
		System.out.println((Math.random()*9+1)*1000);
	}

	public static void duplicateMapKey(){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("hello", 1);
		map.put("hello", 2);

		System.out.println(map);
	}
}
