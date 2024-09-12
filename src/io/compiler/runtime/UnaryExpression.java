package io.compiler.runtime;

public class UnaryExpression extends AbstractExpression{

	private double value;
	
	// mud ini
	private String id; 
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	// mud fim
	
	public UnaryExpression() {
		super();
	}

	public UnaryExpression(double value) {
		super();
		this.value = value;
	}
	
	
	public UnaryExpression(String id) {
		super();
		this.id = id;
	}
	 
	
	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
	
	
	@Override
	public double evaluate() {
		// TODO Auto-generated method stub
		return value;
	}
	
	

	@Override
	public String toJson() {
		// TODO Auto-generated method stub
		if (value == -1) {
			return "{ \"value\": " + this.id + "}";
		} else {
			return "{ \"value\": " + this.value + "}";
			
		}
		
	}
	 
	
	


}
