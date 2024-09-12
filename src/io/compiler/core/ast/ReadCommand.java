package io.compiler.core.ast;

import io.compiler.types.Types;
import io.compiler.types.Var;

public class ReadCommand extends Command{
	
private Var var;
	
	public ReadCommand() {
		super();
	}

	public ReadCommand(Var var) {
		super();
		this.var = var;
	}

	public Var getVar() {
		return var;
	}

	public void setVar(Var var) {
		this.var = var;
	}

	@Override
	public String generateTarget() { 
		String comando;
		if(var.getType() == Types.NUMBER) {
			comando = "_scTrx.nextInt();";
		} else if(var.getType() == Types.REALNUMBER) {
			comando = "_scTrx.nextDouble();";
		} else {
			comando = "_scTrx.nextLine();";
		}
		return var.getId() + " = " + comando +"\n";
	}

}
