grammar IsiGrammar;

@header {
	import java.util.ArrayList;
	import java.util.Stack;
	import io.compiler.runtime.*;
	import java.util.HashMap;
	import io.compiler.types.*;
	import io.compiler.core.exceptions.*;
	import io.compiler.core.ast.*;
}

@members {
	private Stack<AbstractExpression> stack = new Stack<AbstractExpression>();
	private AbstractExpression topo = null;
	
	private HashMap<String,Var> symbolTable = new HashMap<String, Var>();
	private ArrayList<Var> currentDecl = new ArrayList<Var>();
	private Types currentType;
	private Types leftType = null, rightType=null;
	private Program program = new Program();
	
	private String strExpr = "";
	
	private Stack<ArrayList<Command>> cmdStack = new Stack<ArrayList<Command>>();
	private Stack<IfCommand> ifStack = new Stack<IfCommand>();
	private Stack<WhileCommand> whileStack = new Stack<WhileCommand>();
	private Stack<DoWhileCommand> doWhileStack = new Stack<DoWhileCommand>();
	private Stack<String> expressionStack = new Stack<String>();
	
	private ArrayList<String> condicao = new ArrayList<String>();
	
	private AtribCommand currentAtribCommand;
	
	private ArrayList<String> notUsed = new ArrayList<String>();
	private AbstractExpression topo2 = null;
	private String _ID;
	
	public void isNotUsedWarningGen(){
    	for (String element : notUsed) {
		    WarningHandler.sendWarning("Variable " + element + " declared but not used");
		}
    }
	
	public double generateValue(){
		if (topo == null){
			topo = stack.pop();
		}
		return topo.evaluate();
	}
	
	public String generateJSON(){
		if (topo == null){
			topo = stack.pop();
		}
		return topo.toJson();
	}
	
	// Compiler
	
	public void updateType(){
    	for(Var v: currentDecl){
    	   v.setType(currentType);
    	   symbolTable.put(v.getId(), v);
    	}
    }
	
	public void exibirVar(){
		for (String id: symbolTable.keySet()){
        	System.out.println(symbolTable.get(id));
        }
	}
	
	public Program getProgram(){
    	return this.program;
    	}
	
	public boolean isDeclared(String id){
    	return symbolTable.get(id) != null;
    }    
}

prog	: 	'programa' ID { program.setName(_input.LT(-1).getText());
                            cmdStack.push(new ArrayList<Command>());
                          }

			declara+ 
			'inicio' bloco 'fim' 
			'fimprog'{
                  	  program.setSymbolTable(symbolTable);
                 	  program.setCommandList(cmdStack.pop());
                  	  isNotUsedWarningGen();
               		}
		;

declara	: 	'declare' {currentDecl.clear();}
			ID { currentDecl.add(new Var(_input.LT(-1).getText()));
				 notUsed.add(_input.LT(-1).getText());
				} 
			( VIRG ID { currentDecl.add(new Var(_input.LT(-1).getText()));
						notUsed.add(_input.LT(-1).getText());
						}
			)* 
			DP 
			
			(tipo)
			{updateType();}
			PV
		;
		

bloco	: cmd+
		;

cmd		:	cmdLeitura
		|	cmdEscrita
		|	cmdEscritaSemPulo
		|	cmdExpr 
		|	cmdIf
		|	cmdRepWhile
		|	cmdRepDoWhile
		;
		
cmdLeitura	: 	'leia' AP 
				ID 	{ if (!isDeclared(_input.LT(-1).getText())) {
                       throw new IsiSemanticException("Undeclared Variable: "+_input.LT(-1).getText());
                   		}
	                   symbolTable.get(_input.LT(-1).getText()).setInitialized(true);
	                   Command cmdRead = new ReadCommand(symbolTable.get(_input.LT(-1).getText()));
	                   notUsed.remove(_input.LT(-1).getText());
	                   cmdStack.peek().add(cmdRead);
                 	} 
				FP 
				PV
			;

cmdEscrita	:	'escrevaln' AP 
				(fator {  Command cmdWrite = new WriteCommand(_input.LT(-1).getText());
                          cmdStack.peek().add(cmdWrite);
                       }
				) FP PV { rightType = null;}
			;
			
cmdEscritaSemPulo	:	'escreva' AP 
						(fator {  Command cmdWriteNoJump = new WriteNoJumpCommand(_input.LT(-1).getText());
                          		cmdStack.peek().add(cmdWriteNoJump);
                       			}
						) FP PV { rightType = null;}
					;		
			
cmdExpr		: 	ID 	{ 	_ID = _input.LT(-1).getText();
						if (!isDeclared(_input.LT(-1).getText())) {
                      	 	throw new IsiSemanticException("Undeclared Variable: "+_input.LT(-1).getText());
                   		}
                   	   	notUsed.remove(_input.LT(-1).getText());
	                   	symbolTable.get(_input.LT(-1).getText()).setInitialized(true);
	                   	leftType = symbolTable.get(_input.LT(-1).getText()).getType();
	                   
	                   	// ini da AtribCommand
	                   	strExpr = "";
	                   	currentAtribCommand = new AtribCommand();
	                   	currentAtribCommand.setId(_input.LT(-1).getText());  
               	  	} 
				OP_AT  
					 
				expr 
				PV
				 {      if (leftType.getValue() < rightType.getValue()){
	                    throw new IsiSemanticException("Type Mismatching on Assignment");
	                 	}
	                 
	                
	                currentAtribCommand.setExpr(strExpr); // end of strExpr
	                cmdStack.peek().add(currentAtribCommand); // put in Stack
	               
	                
	                if (topo2 == null){
						topo2 = stack.peek();
					}
					if (ExpressionChecker.checkString(strExpr) == -1){
						symbolTable.get(_ID).setValue(Double.toString(topo2.evaluate()));
					}
					else{
						symbolTable.get(_ID).setValue(strExpr);
					}
					
					topo2 = null;
					
					
	                rightType = null; //Para nao quebrar quando troca de cmd
             	 }
			;	

cmdIf    :     'se' { 	cmdStack.push(new ArrayList<Command>());
						strExpr = "";
						ifStack.push(new IfCommand());
                     	ifStack.peek().setFalseList(new ArrayList<Command>());
                 	}  
            	AP 
	            	expr 
	            		OPREL  	{ 	strExpr += _input.LT(-1).getText(); }
	            	expr
            	FP 				{ 	expressionStack.push(strExpr); }
            	'entao' 
            			cmd+  		
            	'fimEntao' 	{   ifStack.peek().setExpression(expressionStack.pop());
        						ifStack.peek().setTrueList(cmdStack.pop());
        					}
            	('senao' 	{ cmdStack.push(new ArrayList<Command>()); }
            			'entao'
              					cmd+	
              			'fimEntao' 	{ 	ifStack.peek().setFalseList(cmdStack.pop()); }
            	)?
            	'fimse'		{   cmdStack.peek().add(ifStack.pop());
                 				rightType = null; //Para n quebra quando troca de cmd
            				} 
        ;

cmdRepWhile		:	'enquanto' {  cmdStack.push(new ArrayList<Command>());
								  strExpr = "";
								  whileStack.push(new WhileCommand());
							   }
					AP 
						expr
							OPREL   { strExpr += _input.LT(-1).getText(); }
						expr
					FP 			{ 	expressionStack.push(strExpr);
								}
					ACH 
						cmd+ 	{
									whileStack.peek().setCondition(expressionStack.pop());
        							whileStack.peek().setListaBloco(cmdStack.pop());
								}
					FCH 		{	cmdStack.peek().add(whileStack.pop());
							 		rightType = null; //Para n quebra quando troca de cmd
								}
				;

cmdRepDoWhile	: 	'faca' {  	cmdStack.push(new ArrayList<Command>());
								doWhileStack.push(new DoWhileCommand());  
							}
					ACH 
					cmd+ {doWhileStack.peek().setListaBloco(cmdStack.pop());} 
					FCH {strExpr = "";}
					'enquanto' 
					AP 
					expr 
					OPREL  { strExpr += _input.LT(-1).getText(); 
							 
							}
					expr 
					FP { 	expressionStack.push(strExpr);
							doWhileStack.peek().setCondition(expressionStack.pop()); }
					'fimDW' {cmdStack.peek().add(doWhileStack.pop());
							 rightType = null; //Para n quebra quando troca de cmd
							}
				;

expr	: termo exprl 
		;

exprl	: 	( ( OP_SUM | OP_SUB) 
			{	// Create a BinaryExpression for '+' or '-' operation
                BinaryExpression bin = new BinaryExpression(_input.LT(-1).getText().charAt(0));
                bin.setLeft(stack.pop());
                stack.push(bin);
                strExpr += _input.LT(-1).getText();
			}
			termo {	
				// Set the right operand of the BinaryExpression and push back to the stack
                AbstractExpression right = stack.pop();
                bin = (BinaryExpression) stack.pop();
                bin.setRight(right);
                stack.push(bin);
			}
			)*
		  
		;

termo 	: 	fator { strExpr += _input.LT(-1).getText(); }
			termol
		;

termol	: 	( (OP_MUL | OP_DIV)
				{			
					// Create a BinaryExpression for '*' or '/' operation
               		BinaryExpression bin = new BinaryExpression(_input.LT(-1).getText().charAt(0));
                	bin.setLeft(stack.pop());
                	stack.push(bin);
                	strExpr += _input.LT(-1).getText();
				} 
			
			fator {
					strExpr += _input.LT(-1).getText(); 
					// Set the right operand of the BinaryExpression and push back to the stack
	                AbstractExpression right = stack.pop();
	                bin = (BinaryExpression) stack.pop();
	                bin.setRight(right);
	                stack.push(bin);
				  }
			)*
		;



		
fator	: ID	{	if (!isDeclared(_input.LT(-1).getText())) {
                         throw new IsiSemanticException("Undeclared Variable: "+_input.LT(-1).getText());
                   	}
                  	if (!symbolTable.get(_input.LT(-1).getText()).isInitialized()){
                   	   throw new IsiSemanticException("Variable "+_input.LT(-1).getText()+" has no value assigned");
                    }
                  	if (rightType == null){
                         rightType = symbolTable.get(_input.LT(-1).getText()).getType();
                    }
                    else{
                       if (symbolTable.get(_input.LT(-1).getText()).getType().getValue() > rightType.getValue()){
                          rightType = symbolTable.get(_input.LT(-1).getText()).getType();
                          }
                       	}   
                  
                  	UnaryExpression element = new UnaryExpression((_input.LT(-1).getText()));
			  		stack.push(element);
                 }

		| NUM	
			{ 	UnaryExpression element = new UnaryExpression(Double.parseDouble(_input.LT(-1).getText()));
			  	stack.push(element);
			  	
			  	//Compiler
			  	if (rightType == null) {
			 		rightType = Types.NUMBER;
			    }
			    else{
			    	 if (rightType.getValue() < Types.NUMBER.getValue()){			                    			                   
			         		rightType = Types.NUMBER;
			         }
			        }
			}
		| TEXTO { rightType = Types.TEXT; }
			
		| RNUM 
			{ 	UnaryExpression element = new UnaryExpression(Double.parseDouble(_input.LT(-1).getText()));
			  	stack.push(element);
			  	
			  	//Compiler
			  	if (rightType == null) {
			 		rightType = Types.REALNUMBER;
			    }
			    else{
			    	 if (rightType.getValue() < Types.REALNUMBER.getValue()){			                    			                   
			         		rightType = Types.REALNUMBER;
			         }
			    }
			}
		
		;
		

		
tipo	: 'number' {currentType = Types.NUMBER;}
		| 'text' {currentType = Types.TEXT;}
		| 'real' {currentType = Types.REALNUMBER;}
		;


OP_SUM	: '+' 
		;

OP_SUB	: '-' 
		;

OP_MUL	: '*'
		;

OP_DIV	: '/' 
		;
			
OP_AT	: ':='
		;
		
OPREL	:	'>' | '<' | '>=' | '<= ' | '<>' | '=='
		;

ID	: [a-z] ([a-z] | [A-Z] | [0-9])*
	;

VIRG	: ','
		;

DP		: ':'
		;

PV		: ';'
		;
		
AP		: '('
		;            
						
FP		: ')'
		;
		
ACH		: '{'
		;

FCH		: '}'
		;

WS		: (' ' | '\n' | '\r' | '\t' ) -> skip
		;

NUM		: [0-9]+ 
		;

RNUM	: [0-9]+ ('.' [0-9]+ )?
		;

TEXTO	: '"' ( [a-z] | [A-Z] | [0-9] | ',' | '.' | ' ' | '-' )* '"'
		;
		