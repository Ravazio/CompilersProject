package io.compiler.core.ast;

public class AtribCommand extends Command{
	
	private String id;
	private String expr;
	
	
	


	public AtribCommand() {
		super();
	}



	public AtribCommand(String id, String expr) {
		super();
		this.id = id;
		this.expr = expr;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getExpr() {
		return expr;
	}



	public void setExpr(String expr) {
		this.expr = expr;
	}



	@Override
	public String generateTarget() {
		return id + " = " + expr + ";";
	}



	@Override
	public String toString() {
		return "AtribCommand [id=" + id + ", expr=" + expr + "]";
	}
	
	

}
