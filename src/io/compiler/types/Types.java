package io.compiler.types;

public enum Types {
	TEXT(1),
	NUMBER(2),
	REALNUMBER(3);
	//era number1, realnumber2 e text3
	private int value;
	
	private  Types(int valueNumber) {
		this.value = valueNumber;
	}
	public Integer getValue() {
		return this.value;
	}
}