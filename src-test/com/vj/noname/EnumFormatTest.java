package com.vj.noname;

public class EnumFormatTest {

	private enum EnumExample{
		A_VALUE("long"),
		B_VALUE("boolean"),
		C_VALUE("integer");
		public String dataType;
		private EnumExample(final String dataType){
			this.dataType = dataType;
		}
	}
	
	@SuppressWarnings("unused")
	void method1(){
		EnumExample ee = EnumExample.A_VALUE;
		String a = ee.dataType;
	}
}
