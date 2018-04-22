package com.gochinatv.cdn.api.jdk.jdk8.enums;


/**
 * 测试enum中使用枚举方法
 * @author jacktomcat
 * https://www.cnblogs.com/jingmoxukong/p/6098351.html
 */

public enum EnumMethod {

	RED() {
		@Override
		public String getName() {
			return "red";
		}

		@Override
		public String getName(String prefix) {
			return prefix+"red";
		}
	},
	BLUE() {
		@Override
		public String getName() {
			return "blue";
		}

		@Override
		public String getName(String prefix) {
			return prefix+"blue";
		}
	};
	
	abstract String getName();	
	abstract String getName(String prefix);	
	
}
