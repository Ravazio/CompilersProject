package io.compiler.core.ast;

import java.util.List;

public class DoWhileCommand extends Command{

	private String condition;
	
	private List<Command> listaBloco;
	
	public DoWhileCommand() {
		super();
	}

	public DoWhileCommand(String condition) {
		super();
		this.condition = condition;
	}

	public DoWhileCommand(List<Command> listaBloco) {
		super();
		this.listaBloco = listaBloco;
	}


	public DoWhileCommand(String condition, List<Command> listaBloco) {
		super();
		this.condition = condition;
		this.listaBloco = listaBloco;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public List<Command> getListaBloco() {
		return listaBloco;
	}

	public void setListaBloco(List<Command> listaBloco) {
		this.listaBloco = listaBloco;
	}

	@Override
	public String generateTarget() {
		
		StringBuilder str = new StringBuilder();

		str.append("do {");
        for (Command cmd: listaBloco) {
            str.append(cmd.generateTarget());
        }
        str.append("}");
        str.append("while ("+condition+");");
        return str.toString();
	}

	@Override
	public String toString() {
		return "DoWhileCommand [condition=" + condition + ", listaBloco=" + listaBloco + "]";
	}
}