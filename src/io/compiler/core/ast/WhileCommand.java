package io.compiler.core.ast;

import java.util.ArrayList;
import java.util.List;

public class WhileCommand extends Command{
	
	private String condition;
	
	private List<Command> listaBloco;
	
	
	public WhileCommand() {
		super();
	}
	
	

	public WhileCommand(String condition) {
		super();
		this.condition = condition;
	}

	

	public WhileCommand(List<Command> listaBloco) {
		super();
		this.listaBloco = listaBloco;
	}



	public WhileCommand(String condition, List<Command> listaBloco) {
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

        str.append("while ("+condition+") {");
        for (Command cmd: listaBloco) {
            str.append(cmd.generateTarget());
        }
        str.append("}");
        return str.toString();
	}
	
	@Override
    public String toString() {
        return "WhileCommand [condition=" + condition + ", listaBloco= " + listaBloco + "]";
    }

}
