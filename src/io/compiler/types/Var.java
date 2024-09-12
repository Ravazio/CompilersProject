package io.compiler.types;

import io.compiler.runtime.AbstractExpression;

public class Var {
	private String id;
	private Types type;
	private boolean initialized;
	private String value;
	
	
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Var(String id, Types type) {
		super();
		this.id = id;
		this.type = type;
	}
	public Var() {
		super();
	}
	public Var(String id) {
		super();
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Types getType() {
		return type;
	}
	public void setType(Types type) {
		this.type = type;
	}
	public boolean isInitialized() {
		return initialized;
	}
	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}
	
	private Double resultado(AbstractExpression expr) {
		return expr.evaluate();
	}
	
	
	private String resultado(String expr) {
		return expr;
	}
	@Override
	public String toString() {
		return "Var [id=" + id + ", type=" + type + ", initialized=" + initialized + ", value=" + value + "]";
	}
	
	
	
	
	
}

