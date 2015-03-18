package com.vj.test;

public class TestBreakRule {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testSubString();
		
		for(int i=0;i<=10; i++){
			System.out.println("inner for loop - " + i);
			if(i==5){
				System.out.println("inner if clause - " + i);
				break;
			}
		}
	}

	private static void testSubString() {
		// TODO Auto-generated method stub
		String s = "com.lcs.wc.product.LCSProduct|315850~MBA|315942";
		System.out.println("1=" + s.substring(s.indexOf("|")+1).indexOf("|"));
		s = s.substring(s.indexOf("|")+1);
		int i = s.indexOf("|");
		System.out.println("2="+i);
	}

}
