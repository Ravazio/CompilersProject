// Generated from IsiGrammar.g4 by ANTLR 4.13.2
package io.compiler.core;

	import java.util.ArrayList;
	import java.util.Stack;
	import io.compiler.runtime.*;
	import java.util.HashMap;
	import io.compiler.types.*;
	import io.compiler.core.exceptions.*;
	import io.compiler.core.ast.*;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link IsiGrammarParser}.
 */
public interface IsiGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link IsiGrammarParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(IsiGrammarParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiGrammarParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(IsiGrammarParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiGrammarParser#declara}.
	 * @param ctx the parse tree
	 */
	void enterDeclara(IsiGrammarParser.DeclaraContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiGrammarParser#declara}.
	 * @param ctx the parse tree
	 */
	void exitDeclara(IsiGrammarParser.DeclaraContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiGrammarParser#bloco}.
	 * @param ctx the parse tree
	 */
	void enterBloco(IsiGrammarParser.BlocoContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiGrammarParser#bloco}.
	 * @param ctx the parse tree
	 */
	void exitBloco(IsiGrammarParser.BlocoContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiGrammarParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterCmd(IsiGrammarParser.CmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiGrammarParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitCmd(IsiGrammarParser.CmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiGrammarParser#cmdLeitura}.
	 * @param ctx the parse tree
	 */
	void enterCmdLeitura(IsiGrammarParser.CmdLeituraContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiGrammarParser#cmdLeitura}.
	 * @param ctx the parse tree
	 */
	void exitCmdLeitura(IsiGrammarParser.CmdLeituraContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiGrammarParser#cmdEscrita}.
	 * @param ctx the parse tree
	 */
	void enterCmdEscrita(IsiGrammarParser.CmdEscritaContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiGrammarParser#cmdEscrita}.
	 * @param ctx the parse tree
	 */
	void exitCmdEscrita(IsiGrammarParser.CmdEscritaContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiGrammarParser#cmdEscritaSemPulo}.
	 * @param ctx the parse tree
	 */
	void enterCmdEscritaSemPulo(IsiGrammarParser.CmdEscritaSemPuloContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiGrammarParser#cmdEscritaSemPulo}.
	 * @param ctx the parse tree
	 */
	void exitCmdEscritaSemPulo(IsiGrammarParser.CmdEscritaSemPuloContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiGrammarParser#cmdExpr}.
	 * @param ctx the parse tree
	 */
	void enterCmdExpr(IsiGrammarParser.CmdExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiGrammarParser#cmdExpr}.
	 * @param ctx the parse tree
	 */
	void exitCmdExpr(IsiGrammarParser.CmdExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiGrammarParser#cmdIf}.
	 * @param ctx the parse tree
	 */
	void enterCmdIf(IsiGrammarParser.CmdIfContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiGrammarParser#cmdIf}.
	 * @param ctx the parse tree
	 */
	void exitCmdIf(IsiGrammarParser.CmdIfContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiGrammarParser#cmdRepWhile}.
	 * @param ctx the parse tree
	 */
	void enterCmdRepWhile(IsiGrammarParser.CmdRepWhileContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiGrammarParser#cmdRepWhile}.
	 * @param ctx the parse tree
	 */
	void exitCmdRepWhile(IsiGrammarParser.CmdRepWhileContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiGrammarParser#cmdRepDoWhile}.
	 * @param ctx the parse tree
	 */
	void enterCmdRepDoWhile(IsiGrammarParser.CmdRepDoWhileContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiGrammarParser#cmdRepDoWhile}.
	 * @param ctx the parse tree
	 */
	void exitCmdRepDoWhile(IsiGrammarParser.CmdRepDoWhileContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(IsiGrammarParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(IsiGrammarParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiGrammarParser#exprl}.
	 * @param ctx the parse tree
	 */
	void enterExprl(IsiGrammarParser.ExprlContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiGrammarParser#exprl}.
	 * @param ctx the parse tree
	 */
	void exitExprl(IsiGrammarParser.ExprlContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiGrammarParser#termo}.
	 * @param ctx the parse tree
	 */
	void enterTermo(IsiGrammarParser.TermoContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiGrammarParser#termo}.
	 * @param ctx the parse tree
	 */
	void exitTermo(IsiGrammarParser.TermoContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiGrammarParser#termol}.
	 * @param ctx the parse tree
	 */
	void enterTermol(IsiGrammarParser.TermolContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiGrammarParser#termol}.
	 * @param ctx the parse tree
	 */
	void exitTermol(IsiGrammarParser.TermolContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiGrammarParser#fator}.
	 * @param ctx the parse tree
	 */
	void enterFator(IsiGrammarParser.FatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiGrammarParser#fator}.
	 * @param ctx the parse tree
	 */
	void exitFator(IsiGrammarParser.FatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiGrammarParser#tipo}.
	 * @param ctx the parse tree
	 */
	void enterTipo(IsiGrammarParser.TipoContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiGrammarParser#tipo}.
	 * @param ctx the parse tree
	 */
	void exitTipo(IsiGrammarParser.TipoContext ctx);
}