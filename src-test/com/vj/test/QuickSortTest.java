package com.vj.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class QuickSortTest {

	public static void main(String[] args) {
		test1();
		long begin = System.currentTimeMillis();
		test2();
		long end = System.currentTimeMillis() - begin;
		System.out.println("time cost: " + (end));
	}
	private static void test2() {
		// TODO Auto-generated method stub
		int count = 10000;
		count = count * 10;
		count = count * 10;//482418ms
		List<Integer> list = new ArrayList<Integer> ();
		Random r = new Random(100000L);
		for(int i=0; i<count;i++){
			list.add(r.nextInt());
		}
		List<Integer> rs = sortCollection(list);		
		System.out.println("rown- "+rs);		
	}
	/**
	 * @param args
	 */
	public static void test1() {
		// TODO Auto-generated method stub

		List<Integer> ints = Arrays.asList(12, 34, 1, 0, 40, 99, 66);
		
		List<Integer> rs = sortCollection(ints);
		
		System.out.println("1- "+rs);
		
	}

	// asc order, no duplicates
	private static List<Integer> sortCollection(List<Integer> ints) {
		// TODO Auto-generated method stub
		
		if(ints.size() == 0) {
			return ints;
		}
		
		if (ints.size()==1){
			return (ints);
		}
		
		int seed = ints.get(0);
		List<Integer> leftList = new ArrayList<Integer> ();
		List<Integer> rightList = new ArrayList<Integer> ();
		for(int i=1; i<ints.size();i++){
			// ASC order
			int tmp = ints.get(i);
			if(tmp <= seed){
				leftList.add(tmp);
			} else if(tmp > seed){
				rightList.add(tmp);
			}
		}
		
		List<Integer> a = sortCollection(leftList);
		List<Integer> b = sortCollection(rightList);
		
		List<Integer> overall = new ArrayList<Integer> ();
		overall.addAll(a);
		overall.add(seed);
		overall.addAll(b);
		
		return overall;		
	}
	
	

}
