// Generated from IsiGrammar.g4 by ANTLR 4.13.2
package io.compiler.core;

	import java.util.ArrayList;
	import java.util.Stack;
	import io.compiler.runtime.*;
	import java.util.HashMap;
	import io.compiler.types.*;
	import io.compiler.core.exceptions.*;
	import io.compiler.core.ast.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class IsiGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, OP_SUM=20, OP_SUB=21, OP_MUL=22, OP_DIV=23, OP_AT=24, 
		OPREL=25, ID=26, VIRG=27, DP=28, PV=29, AP=30, FP=31, ACH=32, FCH=33, 
		WS=34, NUM=35, RNUM=36, TEXTO=37;
	public static final int
		RULE_prog = 0, RULE_declara = 1, RULE_bloco = 2, RULE_cmd = 3, RULE_cmdLeitura = 4, 
		RULE_cmdEscrita = 5, RULE_cmdEscritaSemPulo = 6, RULE_cmdExpr = 7, RULE_cmdIf = 8, 
		RULE_cmdRepWhile = 9, RULE_cmdRepDoWhile = 10, RULE_expr = 11, RULE_exprl = 12, 
		RULE_termo = 13, RULE_termol = 14, RULE_fator = 15, RULE_tipo = 16;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "declara", "bloco", "cmd", "cmdLeitura", "cmdEscrita", "cmdEscritaSemPulo", 
			"cmdExpr", "cmdIf", "cmdRepWhile", "cmdRepDoWhile", "expr", "exprl", 
			"termo", "termol", "fator", "tipo"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'programa'", "'inicio'", "'fim'", "'fimprog'", "'declare'", "'leia'", 
			"'escrevaln'", "'escreva'", "'se'", "'entao'", "'fimEntao'", "'senao'", 
			"'fimse'", "'enquanto'", "'faca'", "'fimDW'", "'number'", "'text'", "'real'", 
			"'+'", "'-'", "'*'", "'/'", "':='", null, null, "','", "':'", "';'", 
			"'('", "')'", "'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, "OP_SUM", "OP_SUB", "OP_MUL", 
			"OP_DIV", "OP_AT", "OPREL", "ID", "VIRG", "DP", "PV", "AP", "FP", "ACH", 
			"FCH", "WS", "NUM", "RNUM", "TEXTO"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "IsiGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


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

	public IsiGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IsiGrammarParser.ID, 0); }
		public BlocoContext bloco() {
			return getRuleContext(BlocoContext.class,0);
		}
		public List<DeclaraContext> declara() {
			return getRuleContexts(DeclaraContext.class);
		}
		public DeclaraContext declara(int i) {
			return getRuleContext(DeclaraContext.class,i);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiGrammarListener ) ((IsiGrammarListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiGrammarListener ) ((IsiGrammarListener)listener).exitProg(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			match(T__0);
			setState(35);
			match(ID);
			 program.setName(_input.LT(-1).getText());
			                            cmdStack.push(new ArrayList<Command>());
			                          
			setState(38); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(37);
				declara();
				}
				}
				setState(40); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__4 );
			setState(42);
			match(T__1);
			setState(43);
			bloco();
			setState(44);
			match(T__2);
			setState(45);
			match(T__3);

			                  	  program.setSymbolTable(symbolTable);
			                 	  program.setCommandList(cmdStack.pop());
			                  	  isNotUsedWarningGen();
			               		
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclaraContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(IsiGrammarParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IsiGrammarParser.ID, i);
		}
		public TerminalNode DP() { return getToken(IsiGrammarParser.DP, 0); }
		public TerminalNode PV() { return getToken(IsiGrammarParser.PV, 0); }
		public TipoContext tipo() {
			return getRuleContext(TipoContext.class,0);
		}
		public List<TerminalNode> VIRG() { return getTokens(IsiGrammarParser.VIRG); }
		public TerminalNode VIRG(int i) {
			return getToken(IsiGrammarParser.VIRG, i);
		}
		public DeclaraContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declara; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiGrammarListener ) ((IsiGrammarListener)listener).enterDeclara(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiGrammarListener ) ((IsiGrammarListener)listener).exitDeclara(this);
		}
	}

	public final DeclaraContext declara() throws RecognitionException {
		DeclaraContext _localctx = new DeclaraContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_declara);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			match(T__4);
			currentDecl.clear();
			setState(50);
			match(ID);
			 currentDecl.add(new Var(_input.LT(-1).getText()));
							 notUsed.add(_input.LT(-1).getText());
							
			setState(57);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIRG) {
				{
				{
				setState(52);
				match(VIRG);
				setState(53);
				match(ID);
				 currentDecl.add(new Var(_input.LT(-1).getText()));
										notUsed.add(_input.LT(-1).getText());
										
				}
				}
				setState(59);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(60);
			match(DP);
			{
			setState(61);
			tipo();
			}
			updateType();
			setState(63);
			match(PV);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BlocoContext extends ParserRuleContext {
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public BlocoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bloco; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiGrammarListener ) ((IsiGrammarListener)listener).enterBloco(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiGrammarListener ) ((IsiGrammarListener)listener).exitBloco(this);
		}
	}

	public final BlocoContext bloco() throws RecognitionException {
		BlocoContext _localctx = new BlocoContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_bloco);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(65);
				cmd();
				}
				}
				setState(68); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 67158976L) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdContext extends ParserRuleContext {
		public CmdLeituraContext cmdLeitura() {
			return getRuleContext(CmdLeituraContext.class,0);
		}
		public CmdEscritaContext cmdEscrita() {
			return getRuleContext(CmdEscritaContext.class,0);
		}
		public CmdEscritaSemPuloContext cmdEscritaSemPulo() {
			return getRuleContext(CmdEscritaSemPuloContext.class,0);
		}
		public CmdExprContext cmdExpr() {
			return getRuleContext(CmdExprContext.class,0);
		}
		public CmdIfContext cmdIf() {
			return getRuleContext(CmdIfContext.class,0);
		}
		public CmdRepWhileContext cmdRepWhile() {
			return getRuleContext(CmdRepWhileContext.class,0);
		}
		public CmdRepDoWhileContext cmdRepDoWhile() {
			return getRuleContext(CmdRepDoWhileContext.class,0);
		}
		public CmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiGrammarListener ) ((IsiGrammarListener)listener).enterCmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiGrammarListener ) ((IsiGrammarListener)listener).exitCmd(this);
		}
	}

	public final CmdContext cmd() throws RecognitionException {
		CmdContext _localctx = new CmdContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_cmd);
		try {
			setState(77);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
				enterOuterAlt(_localctx, 1);
				{
				setState(70);
				cmdLeitura();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 2);
				{
				setState(71);
				cmdEscrita();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 3);
				{
				setState(72);
				cmdEscritaSemPulo();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 4);
				{
				setState(73);
				cmdExpr();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 5);
				{
				setState(74);
				cmdIf();
				}
				break;
			case T__13:
				enterOuterAlt(_localctx, 6);
				{
				setState(75);
				cmdRepWhile();
				}
				break;
			case T__14:
				enterOuterAlt(_localctx, 7);
				{
				setState(76);
				cmdRepDoWhile();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdLeituraContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiGrammarParser.AP, 0); }
		public TerminalNode ID() { return getToken(IsiGrammarParser.ID, 0); }
		public TerminalNode FP() { return getToken(IsiGrammarParser.FP, 0); }
		public TerminalNode PV() { return getToken(IsiGrammarParser.PV, 0); }
		public CmdLeituraContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdLeitura; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiGrammarListener ) ((IsiGrammarListener)listener).enterCmdLeitura(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiGrammarListener ) ((IsiGrammarListener)listener).exitCmdLeitura(this);
		}
	}

	public final CmdLeituraContext cmdLeitura() throws RecognitionException {
		CmdLeituraContext _localctx = new CmdLeituraContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_cmdLeitura);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			match(T__5);
			setState(80);
			match(AP);
			setState(81);
			match(ID);
			 if (!isDeclared(_input.LT(-1).getText())) {
			                       throw new IsiSemanticException("Undeclared Variable: "+_input.LT(-1).getText());
			                   		}
				                   symbolTable.get(_input.LT(-1).getText()).setInitialized(true);
				                   Command cmdRead = new ReadCommand(symbolTable.get(_input.LT(-1).getText()));
				                   notUsed.remove(_input.LT(-1).getText());
				                   cmdStack.peek().add(cmdRead);
			                 	
			setState(83);
			match(FP);
			setState(84);
			match(PV);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdEscritaContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiGrammarParser.AP, 0); }
		public TerminalNode FP() { return getToken(IsiGrammarParser.FP, 0); }
		public TerminalNode PV() { return getToken(IsiGrammarParser.PV, 0); }
		public FatorContext fator() {
			return getRuleContext(FatorContext.class,0);
		}
		public CmdEscritaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdEscrita; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiGrammarListener ) ((IsiGrammarListener)listener).enterCmdEscrita(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiGrammarListener ) ((IsiGrammarListener)listener).exitCmdEscrita(this);
		}
	}

	public final CmdEscritaContext cmdEscrita() throws RecognitionException {
		CmdEscritaContext _localctx = new CmdEscritaContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_cmdEscrita);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			match(T__6);
			setState(87);
			match(AP);
			{
			setState(88);
			fator();
			  Command cmdWrite = new WriteCommand(_input.LT(-1).getText());
			                          cmdStack.peek().add(cmdWrite);
			                       
			}
			setState(91);
			match(FP);
			setState(92);
			match(PV);
			 rightType = null;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdEscritaSemPuloContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiGrammarParser.AP, 0); }
		public TerminalNode FP() { return getToken(IsiGrammarParser.FP, 0); }
		public TerminalNode PV() { return getToken(IsiGrammarParser.PV, 0); }
		public FatorContext fator() {
			return getRuleContext(FatorContext.class,0);
		}
		public CmdEscritaSemPuloContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdEscritaSemPulo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiGrammarListener ) ((IsiGrammarListener)listener).enterCmdEscritaSemPulo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiGrammarListener ) ((IsiGrammarListener)listener).exitCmdEscritaSemPulo(this);
		}
	}

	public final CmdEscritaSemPuloContext cmdEscritaSemPulo() throws RecognitionException {
		CmdEscritaSemPuloContext _localctx = new CmdEscritaSemPuloContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_cmdEscritaSemPulo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			match(T__7);
			setState(96);
			match(AP);
			{
			setState(97);
			fator();
			  Command cmdWriteNoJump = new WriteNoJumpCommand(_input.LT(-1).getText());
			                          		cmdStack.peek().add(cmdWriteNoJump);
			                       			
			}
			setState(100);
			match(FP);
			setState(101);
			match(PV);
			 rightType = null;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdExprContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IsiGrammarParser.ID, 0); }
		public TerminalNode OP_AT() { return getToken(IsiGrammarParser.OP_AT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode PV() { return getToken(IsiGrammarParser.PV, 0); }
		public CmdExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiGrammarListener ) ((IsiGrammarListener)listener).enterCmdExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiGrammarListener ) ((IsiGrammarListener)listener).exitCmdExpr(this);
		}
	}

	public final CmdExprContext cmdExpr() throws RecognitionException {
		CmdExprContext _localctx = new CmdExprContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_cmdExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			match(ID);
			 	_ID = _input.LT(-1).getText();
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
			               	  	
			setState(106);
			match(OP_AT);
			setState(107);
			expr();
			setState(108);
			match(PV);
			      if (leftType.getValue() < rightType.getValue()){
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
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdIfContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiGrammarParser.AP, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OPREL() { return getToken(IsiGrammarParser.OPREL, 0); }
		public TerminalNode FP() { return getToken(IsiGrammarParser.FP, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdIfContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdIf; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiGrammarListener ) ((IsiGrammarListener)listener).enterCmdIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiGrammarListener ) ((IsiGrammarListener)listener).exitCmdIf(this);
		}
	}

	public final CmdIfContext cmdIf() throws RecognitionException {
		CmdIfContext _localctx = new CmdIfContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_cmdIf);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			match(T__8);
			 	cmdStack.push(new ArrayList<Command>());
									strExpr = "";
									ifStack.push(new IfCommand());
			                     	ifStack.peek().setFalseList(new ArrayList<Command>());
			                 	
			setState(113);
			match(AP);
			setState(114);
			expr();
			setState(115);
			match(OPREL);
			 	strExpr += _input.LT(-1).getText(); 
			setState(117);
			expr();
			setState(118);
			match(FP);
			 	expressionStack.push(strExpr); 
			setState(120);
			match(T__9);
			setState(122); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(121);
				cmd();
				}
				}
				setState(124); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 67158976L) != 0) );
			setState(126);
			match(T__10);
			   ifStack.peek().setExpression(expressionStack.pop());
			        						ifStack.peek().setTrueList(cmdStack.pop());
			        					
			setState(139);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__11) {
				{
				setState(128);
				match(T__11);
				 cmdStack.push(new ArrayList<Command>()); 
				setState(130);
				match(T__9);
				setState(132); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(131);
					cmd();
					}
					}
					setState(134); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 67158976L) != 0) );
				setState(136);
				match(T__10);
				 	ifStack.peek().setFalseList(cmdStack.pop()); 
				}
			}

			setState(141);
			match(T__12);
			   cmdStack.peek().add(ifStack.pop());
			                 				rightType = null; //Para n quebra quando troca de cmd
			            				
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdRepWhileContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiGrammarParser.AP, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OPREL() { return getToken(IsiGrammarParser.OPREL, 0); }
		public TerminalNode FP() { return getToken(IsiGrammarParser.FP, 0); }
		public TerminalNode ACH() { return getToken(IsiGrammarParser.ACH, 0); }
		public TerminalNode FCH() { return getToken(IsiGrammarParser.FCH, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdRepWhileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdRepWhile; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiGrammarListener ) ((IsiGrammarListener)listener).enterCmdRepWhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiGrammarListener ) ((IsiGrammarListener)listener).exitCmdRepWhile(this);
		}
	}

	public final CmdRepWhileContext cmdRepWhile() throws RecognitionException {
		CmdRepWhileContext _localctx = new CmdRepWhileContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_cmdRepWhile);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144);
			match(T__13);
			  cmdStack.push(new ArrayList<Command>());
											  strExpr = "";
											  whileStack.push(new WhileCommand());
										   
			setState(146);
			match(AP);
			setState(147);
			expr();
			setState(148);
			match(OPREL);
			 strExpr += _input.LT(-1).getText(); 
			setState(150);
			expr();
			setState(151);
			match(FP);
			 	expressionStack.push(strExpr);
											
			setState(153);
			match(ACH);
			setState(155); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(154);
				cmd();
				}
				}
				setState(157); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 67158976L) != 0) );

												whileStack.peek().setCondition(expressionStack.pop());
			        							whileStack.peek().setListaBloco(cmdStack.pop());
											
			setState(160);
			match(FCH);
				cmdStack.peek().add(whileStack.pop());
										 		rightType = null; //Para n quebra quando troca de cmd
											
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdRepDoWhileContext extends ParserRuleContext {
		public TerminalNode ACH() { return getToken(IsiGrammarParser.ACH, 0); }
		public TerminalNode FCH() { return getToken(IsiGrammarParser.FCH, 0); }
		public TerminalNode AP() { return getToken(IsiGrammarParser.AP, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OPREL() { return getToken(IsiGrammarParser.OPREL, 0); }
		public TerminalNode FP() { return getToken(IsiGrammarParser.FP, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdRepDoWhileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdRepDoWhile; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiGrammarListener ) ((IsiGrammarListener)listener).enterCmdRepDoWhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiGrammarListener ) ((IsiGrammarListener)listener).exitCmdRepDoWhile(this);
		}
	}

	public final CmdRepDoWhileContext cmdRepDoWhile() throws RecognitionException {
		CmdRepDoWhileContext _localctx = new CmdRepDoWhileContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_cmdRepDoWhile);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(163);
			match(T__14);
			  	cmdStack.push(new ArrayList<Command>());
											doWhileStack.push(new DoWhileCommand());  
										
			setState(165);
			match(ACH);
			setState(167); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(166);
				cmd();
				}
				}
				setState(169); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 67158976L) != 0) );
			doWhileStack.peek().setListaBloco(cmdStack.pop());
			setState(172);
			match(FCH);
			strExpr = "";
			setState(174);
			match(T__13);
			setState(175);
			match(AP);
			setState(176);
			expr();
			setState(177);
			match(OPREL);
			 strExpr += _input.LT(-1).getText(); 
										 
										
			setState(179);
			expr();
			setState(180);
			match(FP);
			 	expressionStack.push(strExpr);
										doWhileStack.peek().setCondition(expressionStack.pop()); 
			setState(182);
			match(T__15);
			cmdStack.peek().add(doWhileStack.pop());
										 rightType = null; //Para n quebra quando troca de cmd
										
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public TermoContext termo() {
			return getRuleContext(TermoContext.class,0);
		}
		public ExprlContext exprl() {
			return getRuleContext(ExprlContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiGrammarListener ) ((IsiGrammarListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiGrammarListener ) ((IsiGrammarListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
			termo();
			setState(186);
			exprl();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprlContext extends ParserRuleContext {
		public List<TermoContext> termo() {
			return getRuleContexts(TermoContext.class);
		}
		public TermoContext termo(int i) {
			return getRuleContext(TermoContext.class,i);
		}
		public List<TerminalNode> OP_SUM() { return getTokens(IsiGrammarParser.OP_SUM); }
		public TerminalNode OP_SUM(int i) {
			return getToken(IsiGrammarParser.OP_SUM, i);
		}
		public List<TerminalNode> OP_SUB() { return getTokens(IsiGrammarParser.OP_SUB); }
		public TerminalNode OP_SUB(int i) {
			return getToken(IsiGrammarParser.OP_SUB, i);
		}
		public ExprlContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiGrammarListener ) ((IsiGrammarListener)listener).enterExprl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiGrammarListener ) ((IsiGrammarListener)listener).exitExprl(this);
		}
	}

	public final ExprlContext exprl() throws RecognitionException {
		ExprlContext _localctx = new ExprlContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_exprl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(195);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP_SUM || _la==OP_SUB) {
				{
				{
				setState(188);
				_la = _input.LA(1);
				if ( !(_la==OP_SUM || _la==OP_SUB) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
					// Create a BinaryExpression for '+' or '-' operation
				                BinaryExpression bin = new BinaryExpression(_input.LT(-1).getText().charAt(0));
				                bin.setLeft(stack.pop());
				                stack.push(bin);
				                strExpr += _input.LT(-1).getText();
							
				setState(190);
				termo();
					
								// Set the right operand of the BinaryExpression and push back to the stack
				                AbstractExpression right = stack.pop();
				                bin = (BinaryExpression) stack.pop();
				                bin.setRight(right);
				                stack.push(bin);
							
				}
				}
				setState(197);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TermoContext extends ParserRuleContext {
		public FatorContext fator() {
			return getRuleContext(FatorContext.class,0);
		}
		public TermolContext termol() {
			return getRuleContext(TermolContext.class,0);
		}
		public TermoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiGrammarListener ) ((IsiGrammarListener)listener).enterTermo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiGrammarListener ) ((IsiGrammarListener)listener).exitTermo(this);
		}
	}

	public final TermoContext termo() throws RecognitionException {
		TermoContext _localctx = new TermoContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_termo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(198);
			fator();
			 strExpr += _input.LT(-1).getText(); 
			setState(200);
			termol();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TermolContext extends ParserRuleContext {
		public List<FatorContext> fator() {
			return getRuleContexts(FatorContext.class);
		}
		public FatorContext fator(int i) {
			return getRuleContext(FatorContext.class,i);
		}
		public List<TerminalNode> OP_MUL() { return getTokens(IsiGrammarParser.OP_MUL); }
		public TerminalNode OP_MUL(int i) {
			return getToken(IsiGrammarParser.OP_MUL, i);
		}
		public List<TerminalNode> OP_DIV() { return getTokens(IsiGrammarParser.OP_DIV); }
		public TerminalNode OP_DIV(int i) {
			return getToken(IsiGrammarParser.OP_DIV, i);
		}
		public TermolContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termol; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiGrammarListener ) ((IsiGrammarListener)listener).enterTermol(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiGrammarListener ) ((IsiGrammarListener)listener).exitTermol(this);
		}
	}

	public final TermolContext termol() throws RecognitionException {
		TermolContext _localctx = new TermolContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_termol);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(209);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP_MUL || _la==OP_DIV) {
				{
				{
				setState(202);
				_la = _input.LA(1);
				if ( !(_la==OP_MUL || _la==OP_DIV) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
							
									// Create a BinaryExpression for '*' or '/' operation
				               		BinaryExpression bin = new BinaryExpression(_input.LT(-1).getText().charAt(0));
				                	bin.setLeft(stack.pop());
				                	stack.push(bin);
				                	strExpr += _input.LT(-1).getText();
								
				setState(204);
				fator();

									strExpr += _input.LT(-1).getText(); 
									// Set the right operand of the BinaryExpression and push back to the stack
					                AbstractExpression right = stack.pop();
					                bin = (BinaryExpression) stack.pop();
					                bin.setRight(right);
					                stack.push(bin);
								  
				}
				}
				setState(211);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FatorContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IsiGrammarParser.ID, 0); }
		public TerminalNode NUM() { return getToken(IsiGrammarParser.NUM, 0); }
		public TerminalNode TEXTO() { return getToken(IsiGrammarParser.TEXTO, 0); }
		public TerminalNode RNUM() { return getToken(IsiGrammarParser.RNUM, 0); }
		public FatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiGrammarListener ) ((IsiGrammarListener)listener).enterFator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiGrammarListener ) ((IsiGrammarListener)listener).exitFator(this);
		}
	}

	public final FatorContext fator() throws RecognitionException {
		FatorContext _localctx = new FatorContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_fator);
		try {
			setState(220);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(212);
				match(ID);
					if (!isDeclared(_input.LT(-1).getText())) {
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
				break;
			case NUM:
				enterOuterAlt(_localctx, 2);
				{
				setState(214);
				match(NUM);
				 	UnaryExpression element = new UnaryExpression(Double.parseDouble(_input.LT(-1).getText()));
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
				break;
			case TEXTO:
				enterOuterAlt(_localctx, 3);
				{
				setState(216);
				match(TEXTO);
				 rightType = Types.TEXT; 
				}
				break;
			case RNUM:
				enterOuterAlt(_localctx, 4);
				{
				setState(218);
				match(RNUM);
				 	UnaryExpression element = new UnaryExpression(Double.parseDouble(_input.LT(-1).getText()));
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
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TipoContext extends ParserRuleContext {
		public TipoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiGrammarListener ) ((IsiGrammarListener)listener).enterTipo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiGrammarListener ) ((IsiGrammarListener)listener).exitTipo(this);
		}
	}

	public final TipoContext tipo() throws RecognitionException {
		TipoContext _localctx = new TipoContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_tipo);
		try {
			setState(228);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__16:
				enterOuterAlt(_localctx, 1);
				{
				setState(222);
				match(T__16);
				currentType = Types.NUMBER;
				}
				break;
			case T__17:
				enterOuterAlt(_localctx, 2);
				{
				setState(224);
				match(T__17);
				currentType = Types.TEXT;
				}
				break;
			case T__18:
				enterOuterAlt(_localctx, 3);
				{
				setState(226);
				match(T__18);
				currentType = Types.REALNUMBER;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001%\u00e7\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0004\u0000\'\b\u0000\u000b\u0000\f\u0000(\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u00018\b"+
		"\u0001\n\u0001\f\u0001;\t\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0002\u0004\u0002C\b\u0002\u000b\u0002\f\u0002"+
		"D\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0003\u0003N\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0004\b{\b\b\u000b\b\f\b|\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0004\b\u0085\b\b\u000b\b\f\b\u0086\u0001\b\u0001\b\u0001\b\u0003\b"+
		"\u008c\b\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0004\t\u009c\b\t\u000b"+
		"\t\f\t\u009d\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0004\n\u00a8\b\n\u000b\n\f\n\u00a9\u0001\n\u0001\n\u0001\n\u0001\n"+
		"\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f"+
		"\u0001\f\u0001\f\u0005\f\u00c2\b\f\n\f\f\f\u00c5\t\f\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0005\u000e\u00d0\b\u000e\n\u000e\f\u000e\u00d3\t\u000e\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0003\u000f\u00dd\b\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u00e5\b\u0010\u0001\u0010\u0000"+
		"\u0000\u0011\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016"+
		"\u0018\u001a\u001c\u001e \u0000\u0002\u0001\u0000\u0014\u0015\u0001\u0000"+
		"\u0016\u0017\u00ea\u0000\"\u0001\u0000\u0000\u0000\u00020\u0001\u0000"+
		"\u0000\u0000\u0004B\u0001\u0000\u0000\u0000\u0006M\u0001\u0000\u0000\u0000"+
		"\bO\u0001\u0000\u0000\u0000\nV\u0001\u0000\u0000\u0000\f_\u0001\u0000"+
		"\u0000\u0000\u000eh\u0001\u0000\u0000\u0000\u0010o\u0001\u0000\u0000\u0000"+
		"\u0012\u0090\u0001\u0000\u0000\u0000\u0014\u00a3\u0001\u0000\u0000\u0000"+
		"\u0016\u00b9\u0001\u0000\u0000\u0000\u0018\u00c3\u0001\u0000\u0000\u0000"+
		"\u001a\u00c6\u0001\u0000\u0000\u0000\u001c\u00d1\u0001\u0000\u0000\u0000"+
		"\u001e\u00dc\u0001\u0000\u0000\u0000 \u00e4\u0001\u0000\u0000\u0000\""+
		"#\u0005\u0001\u0000\u0000#$\u0005\u001a\u0000\u0000$&\u0006\u0000\uffff"+
		"\uffff\u0000%\'\u0003\u0002\u0001\u0000&%\u0001\u0000\u0000\u0000\'(\u0001"+
		"\u0000\u0000\u0000(&\u0001\u0000\u0000\u0000()\u0001\u0000\u0000\u0000"+
		")*\u0001\u0000\u0000\u0000*+\u0005\u0002\u0000\u0000+,\u0003\u0004\u0002"+
		"\u0000,-\u0005\u0003\u0000\u0000-.\u0005\u0004\u0000\u0000./\u0006\u0000"+
		"\uffff\uffff\u0000/\u0001\u0001\u0000\u0000\u000001\u0005\u0005\u0000"+
		"\u000012\u0006\u0001\uffff\uffff\u000023\u0005\u001a\u0000\u000039\u0006"+
		"\u0001\uffff\uffff\u000045\u0005\u001b\u0000\u000056\u0005\u001a\u0000"+
		"\u000068\u0006\u0001\uffff\uffff\u000074\u0001\u0000\u0000\u00008;\u0001"+
		"\u0000\u0000\u000097\u0001\u0000\u0000\u00009:\u0001\u0000\u0000\u0000"+
		":<\u0001\u0000\u0000\u0000;9\u0001\u0000\u0000\u0000<=\u0005\u001c\u0000"+
		"\u0000=>\u0003 \u0010\u0000>?\u0006\u0001\uffff\uffff\u0000?@\u0005\u001d"+
		"\u0000\u0000@\u0003\u0001\u0000\u0000\u0000AC\u0003\u0006\u0003\u0000"+
		"BA\u0001\u0000\u0000\u0000CD\u0001\u0000\u0000\u0000DB\u0001\u0000\u0000"+
		"\u0000DE\u0001\u0000\u0000\u0000E\u0005\u0001\u0000\u0000\u0000FN\u0003"+
		"\b\u0004\u0000GN\u0003\n\u0005\u0000HN\u0003\f\u0006\u0000IN\u0003\u000e"+
		"\u0007\u0000JN\u0003\u0010\b\u0000KN\u0003\u0012\t\u0000LN\u0003\u0014"+
		"\n\u0000MF\u0001\u0000\u0000\u0000MG\u0001\u0000\u0000\u0000MH\u0001\u0000"+
		"\u0000\u0000MI\u0001\u0000\u0000\u0000MJ\u0001\u0000\u0000\u0000MK\u0001"+
		"\u0000\u0000\u0000ML\u0001\u0000\u0000\u0000N\u0007\u0001\u0000\u0000"+
		"\u0000OP\u0005\u0006\u0000\u0000PQ\u0005\u001e\u0000\u0000QR\u0005\u001a"+
		"\u0000\u0000RS\u0006\u0004\uffff\uffff\u0000ST\u0005\u001f\u0000\u0000"+
		"TU\u0005\u001d\u0000\u0000U\t\u0001\u0000\u0000\u0000VW\u0005\u0007\u0000"+
		"\u0000WX\u0005\u001e\u0000\u0000XY\u0003\u001e\u000f\u0000YZ\u0006\u0005"+
		"\uffff\uffff\u0000Z[\u0001\u0000\u0000\u0000[\\\u0005\u001f\u0000\u0000"+
		"\\]\u0005\u001d\u0000\u0000]^\u0006\u0005\uffff\uffff\u0000^\u000b\u0001"+
		"\u0000\u0000\u0000_`\u0005\b\u0000\u0000`a\u0005\u001e\u0000\u0000ab\u0003"+
		"\u001e\u000f\u0000bc\u0006\u0006\uffff\uffff\u0000cd\u0001\u0000\u0000"+
		"\u0000de\u0005\u001f\u0000\u0000ef\u0005\u001d\u0000\u0000fg\u0006\u0006"+
		"\uffff\uffff\u0000g\r\u0001\u0000\u0000\u0000hi\u0005\u001a\u0000\u0000"+
		"ij\u0006\u0007\uffff\uffff\u0000jk\u0005\u0018\u0000\u0000kl\u0003\u0016"+
		"\u000b\u0000lm\u0005\u001d\u0000\u0000mn\u0006\u0007\uffff\uffff\u0000"+
		"n\u000f\u0001\u0000\u0000\u0000op\u0005\t\u0000\u0000pq\u0006\b\uffff"+
		"\uffff\u0000qr\u0005\u001e\u0000\u0000rs\u0003\u0016\u000b\u0000st\u0005"+
		"\u0019\u0000\u0000tu\u0006\b\uffff\uffff\u0000uv\u0003\u0016\u000b\u0000"+
		"vw\u0005\u001f\u0000\u0000wx\u0006\b\uffff\uffff\u0000xz\u0005\n\u0000"+
		"\u0000y{\u0003\u0006\u0003\u0000zy\u0001\u0000\u0000\u0000{|\u0001\u0000"+
		"\u0000\u0000|z\u0001\u0000\u0000\u0000|}\u0001\u0000\u0000\u0000}~\u0001"+
		"\u0000\u0000\u0000~\u007f\u0005\u000b\u0000\u0000\u007f\u008b\u0006\b"+
		"\uffff\uffff\u0000\u0080\u0081\u0005\f\u0000\u0000\u0081\u0082\u0006\b"+
		"\uffff\uffff\u0000\u0082\u0084\u0005\n\u0000\u0000\u0083\u0085\u0003\u0006"+
		"\u0003\u0000\u0084\u0083\u0001\u0000\u0000\u0000\u0085\u0086\u0001\u0000"+
		"\u0000\u0000\u0086\u0084\u0001\u0000\u0000\u0000\u0086\u0087\u0001\u0000"+
		"\u0000\u0000\u0087\u0088\u0001\u0000\u0000\u0000\u0088\u0089\u0005\u000b"+
		"\u0000\u0000\u0089\u008a\u0006\b\uffff\uffff\u0000\u008a\u008c\u0001\u0000"+
		"\u0000\u0000\u008b\u0080\u0001\u0000\u0000\u0000\u008b\u008c\u0001\u0000"+
		"\u0000\u0000\u008c\u008d\u0001\u0000\u0000\u0000\u008d\u008e\u0005\r\u0000"+
		"\u0000\u008e\u008f\u0006\b\uffff\uffff\u0000\u008f\u0011\u0001\u0000\u0000"+
		"\u0000\u0090\u0091\u0005\u000e\u0000\u0000\u0091\u0092\u0006\t\uffff\uffff"+
		"\u0000\u0092\u0093\u0005\u001e\u0000\u0000\u0093\u0094\u0003\u0016\u000b"+
		"\u0000\u0094\u0095\u0005\u0019\u0000\u0000\u0095\u0096\u0006\t\uffff\uffff"+
		"\u0000\u0096\u0097\u0003\u0016\u000b\u0000\u0097\u0098\u0005\u001f\u0000"+
		"\u0000\u0098\u0099\u0006\t\uffff\uffff\u0000\u0099\u009b\u0005 \u0000"+
		"\u0000\u009a\u009c\u0003\u0006\u0003\u0000\u009b\u009a\u0001\u0000\u0000"+
		"\u0000\u009c\u009d\u0001\u0000\u0000\u0000\u009d\u009b\u0001\u0000\u0000"+
		"\u0000\u009d\u009e\u0001\u0000\u0000\u0000\u009e\u009f\u0001\u0000\u0000"+
		"\u0000\u009f\u00a0\u0006\t\uffff\uffff\u0000\u00a0\u00a1\u0005!\u0000"+
		"\u0000\u00a1\u00a2\u0006\t\uffff\uffff\u0000\u00a2\u0013\u0001\u0000\u0000"+
		"\u0000\u00a3\u00a4\u0005\u000f\u0000\u0000\u00a4\u00a5\u0006\n\uffff\uffff"+
		"\u0000\u00a5\u00a7\u0005 \u0000\u0000\u00a6\u00a8\u0003\u0006\u0003\u0000"+
		"\u00a7\u00a6\u0001\u0000\u0000\u0000\u00a8\u00a9\u0001\u0000\u0000\u0000"+
		"\u00a9\u00a7\u0001\u0000\u0000\u0000\u00a9\u00aa\u0001\u0000\u0000\u0000"+
		"\u00aa\u00ab\u0001\u0000\u0000\u0000\u00ab\u00ac\u0006\n\uffff\uffff\u0000"+
		"\u00ac\u00ad\u0005!\u0000\u0000\u00ad\u00ae\u0006\n\uffff\uffff\u0000"+
		"\u00ae\u00af\u0005\u000e\u0000\u0000\u00af\u00b0\u0005\u001e\u0000\u0000"+
		"\u00b0\u00b1\u0003\u0016\u000b\u0000\u00b1\u00b2\u0005\u0019\u0000\u0000"+
		"\u00b2\u00b3\u0006\n\uffff\uffff\u0000\u00b3\u00b4\u0003\u0016\u000b\u0000"+
		"\u00b4\u00b5\u0005\u001f\u0000\u0000\u00b5\u00b6\u0006\n\uffff\uffff\u0000"+
		"\u00b6\u00b7\u0005\u0010\u0000\u0000\u00b7\u00b8\u0006\n\uffff\uffff\u0000"+
		"\u00b8\u0015\u0001\u0000\u0000\u0000\u00b9\u00ba\u0003\u001a\r\u0000\u00ba"+
		"\u00bb\u0003\u0018\f\u0000\u00bb\u0017\u0001\u0000\u0000\u0000\u00bc\u00bd"+
		"\u0007\u0000\u0000\u0000\u00bd\u00be\u0006\f\uffff\uffff\u0000\u00be\u00bf"+
		"\u0003\u001a\r\u0000\u00bf\u00c0\u0006\f\uffff\uffff\u0000\u00c0\u00c2"+
		"\u0001\u0000\u0000\u0000\u00c1\u00bc\u0001\u0000\u0000\u0000\u00c2\u00c5"+
		"\u0001\u0000\u0000\u0000\u00c3\u00c1\u0001\u0000\u0000\u0000\u00c3\u00c4"+
		"\u0001\u0000\u0000\u0000\u00c4\u0019\u0001\u0000\u0000\u0000\u00c5\u00c3"+
		"\u0001\u0000\u0000\u0000\u00c6\u00c7\u0003\u001e\u000f\u0000\u00c7\u00c8"+
		"\u0006\r\uffff\uffff\u0000\u00c8\u00c9\u0003\u001c\u000e\u0000\u00c9\u001b"+
		"\u0001\u0000\u0000\u0000\u00ca\u00cb\u0007\u0001\u0000\u0000\u00cb\u00cc"+
		"\u0006\u000e\uffff\uffff\u0000\u00cc\u00cd\u0003\u001e\u000f\u0000\u00cd"+
		"\u00ce\u0006\u000e\uffff\uffff\u0000\u00ce\u00d0\u0001\u0000\u0000\u0000"+
		"\u00cf\u00ca\u0001\u0000\u0000\u0000\u00d0\u00d3\u0001\u0000\u0000\u0000"+
		"\u00d1\u00cf\u0001\u0000\u0000\u0000\u00d1\u00d2\u0001\u0000\u0000\u0000"+
		"\u00d2\u001d\u0001\u0000\u0000\u0000\u00d3\u00d1\u0001\u0000\u0000\u0000"+
		"\u00d4\u00d5\u0005\u001a\u0000\u0000\u00d5\u00dd\u0006\u000f\uffff\uffff"+
		"\u0000\u00d6\u00d7\u0005#\u0000\u0000\u00d7\u00dd\u0006\u000f\uffff\uffff"+
		"\u0000\u00d8\u00d9\u0005%\u0000\u0000\u00d9\u00dd\u0006\u000f\uffff\uffff"+
		"\u0000\u00da\u00db\u0005$\u0000\u0000\u00db\u00dd\u0006\u000f\uffff\uffff"+
		"\u0000\u00dc\u00d4\u0001\u0000\u0000\u0000\u00dc\u00d6\u0001\u0000\u0000"+
		"\u0000\u00dc\u00d8\u0001\u0000\u0000\u0000\u00dc\u00da\u0001\u0000\u0000"+
		"\u0000\u00dd\u001f\u0001\u0000\u0000\u0000\u00de\u00df\u0005\u0011\u0000"+
		"\u0000\u00df\u00e5\u0006\u0010\uffff\uffff\u0000\u00e0\u00e1\u0005\u0012"+
		"\u0000\u0000\u00e1\u00e5\u0006\u0010\uffff\uffff\u0000\u00e2\u00e3\u0005"+
		"\u0013\u0000\u0000\u00e3\u00e5\u0006\u0010\uffff\uffff\u0000\u00e4\u00de"+
		"\u0001\u0000\u0000\u0000\u00e4\u00e0\u0001\u0000\u0000\u0000\u00e4\u00e2"+
		"\u0001\u0000\u0000\u0000\u00e5!\u0001\u0000\u0000\u0000\r(9DM|\u0086\u008b"+
		"\u009d\u00a9\u00c3\u00d1\u00dc\u00e4";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}