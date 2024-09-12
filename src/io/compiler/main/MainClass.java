package io.compiler.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import io.compiler.core.IsiGrammarLexer;
import io.compiler.core.IsiGrammarParser;
import io.compiler.core.ast.Program;
import io.compiler.core.exceptions.*;

public class MainClass {
	public static void main(String[] args) {
		try {
			IsiGrammarLexer lexer; // Analisador Lexico
			IsiGrammarParser parser; // Analisador Sintatico
			
			// crio o analisador léxico a partir da leitura de um arquivo
			lexer = new IsiGrammarLexer(CharStreams.fromFileName("input.in"));
			
			//lexer = new IsiGrammarLexer(CharStreams.fromFileName("input1.in")); // Declara 3 Tipos de Váriaveis
            //lexer = new IsiGrammarLexer(CharStreams.fromFileName("input2.in")); // Estrutura if..else
            //lexer = new IsiGrammarLexer(CharStreams.fromFileName("input3.in")); // Estruturas while/do while
            //lexer = new IsiGrammarLexer(CharStreams.fromFileName("input4.in")); // Operações Aritméticas
            //lexer = new IsiGrammarLexer(CharStreams.fromFileName("input5.in")); // Atribuicoes corretas
            //lexer = new IsiGrammarLexer(CharStreams.fromFileName("input6.in")); // Entrada e Saida
            //lexer = new IsiGrammarLexer(CharStreams.fromFileName("input7.in")); // Aceita Decimal
            //lexer = new IsiGrammarLexer(CharStreams.fromFileName("input8.in")); // Variavel Previamente Declarada Erro
            //lexer = new IsiGrammarLexer(CharStreams.fromFileName("input9.in")); // Variavel Declarada e Nao Usada Warnnig
            //lexer = new IsiGrammarLexer(CharStreams.fromFileName("input10.in")); // Variavel Usada sem ter valor Inicial

			// agora a partir do analisador lexico, obtenho um fluxo de tokens
			CommonTokenStream tokenStream = new CommonTokenStream(lexer);

			// crio o parser a partir do tokenStream
			parser = new IsiGrammarParser(tokenStream);

			// agora eu quero chamar do meu jeito
			System.out.println("Isi Compiler");
			parser.prog();
			WarningHandler.printAllWarnings();
			System.out.println("Compilation Successfully - Good Job");

			//parser.exibirVar();
			//System.out.println("Expr Value " + parser.generateValue());
			//System.out.println("Expr Value " + parser.generateJSON()); //v4

			// vou deixar aqui a geracao do codigo do programa
			Program program = parser.getProgram();
			//System.out.println(program.generateTarget());

			File f = new File(program.getName() + ".java");
			FileWriter fr = new FileWriter(f);
			PrintWriter pr = new PrintWriter(fr);
			pr.println(program.generateTarget());
			pr.close();

			// Expr Matematicas Ordenadas
			// parser.expr();
			//System.out.println("Expr Value " + parser.generateValue());
			//System.out.println("Expr Value " + parser.generateJSON());

			//parser.exibirVar();
		} catch (IOException ex) {
			//ex.printStackTrace();
			System.err.println("Error: " + ex.getMessage());
		} catch (Exception ex) {
			//ex.printStackTrace();
			System.err.println("Error: " + ex.getMessage());
		}
	}
}
