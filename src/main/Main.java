package main;

import grammar.JavaLexer;
import grammar.JavaParser;
import grammar.JavaParser.compilationUnit_return;

import java.util.HashMap;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;

/**
 * Parse a java file or directory of java files using the generated parser
 * ANTclassOrInterfaceDeclarationLR builds from java.g
 */
class Main {
	
	public static void main(String[] args) throws RecognitionException {
		CharStream charStream = new ANTLRStringStream(
				"public class Hallo{" +
				"	private synchronized int doNothing() {" +
				"	}" + "	public static void main(String[] args) {" +
				"		System.out.println(\"HalloWelt\");" +
				"	}" +
				"}");
		
		JavaLexer lexer = new JavaLexer(charStream);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		JavaParser parser = new JavaParser(tokens);
		compilationUnit_return compilationUnit = parser.compilationUnit();
		CommonTree tree = (CommonTree) (compilationUnit.getTree());
		
		Database database = new Database();
		database.addAttribute("method_modifier_public");
		database.addAttribute("method_modifier_private");
		database.addAttribute("method_modifier_synchronized");
		
		Record record = database.newRecord();
		
		String[] modifiers = { "public", "private", "synchronized" };
		for (String modifier : modifiers) {
			TreeVisitor visitor = new MethodModifierCounter(modifier);
			int numberOfModifiers = visitor.visit(tree);
			record.setValueForAttribute(numberOfModifiers, "method_modifier_" + modifier);
		}
		
		database.add(record);
		
		System.out.println(database);
	}
}
