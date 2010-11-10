package main;

import grammar.JavaLexer;
import grammar.JavaParser;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.StringTokenizer;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.tree.CommonTree;

/**
 * This class reads all java source files in the folder "javaprojectsources"
 * and generates a collection of CommonTreePackages 
 */
public class GenerateTreePackages {

	private BufferedInputStream inputStream;
	private JavaLexer lexer;
	private JavaParser parser;
	
	private static final String PATH = "javaprojectsources";
	
	public Collection<CommonTreePackage> generate(){
		File parent = new File(PATH);
		return searchInFolder(parent);
	}
	
	private Collection<CommonTreePackage> searchInFolder(File parent){
		Collection<CommonTreePackage> trees = new HashSet<CommonTreePackage>();
		File[] files = parent.listFiles();
		if(files == null){
			try {
				if(!parent.getName().endsWith(".java")){
					return trees;
				}							
				this.inputStream = new BufferedInputStream(new FileInputStream(parent));
				this.lexer = new JavaLexer(new ANTLRInputStream(this.inputStream ));
				this.parser = new JavaParser(new CommonTokenStream(this.lexer));
				CommonTree tree = (CommonTree) (this.parser.compilationUnit().getTree());
				trees.add(new CommonTreePackage(tree, getProjectName(parent), parent));
			} catch (Exception e) { e.printStackTrace(); }
		}
		else{
			for(File file : files){
				trees.addAll(searchInFolder(file));
			}
		}
		return trees;
	}

	private String getProjectName(File parent) { 
        String path = parent.getAbsolutePath(); 
        String name = ""; 
        StringTokenizer tokenizer = new StringTokenizer(path, "\\"); 
        while(tokenizer.hasMoreTokens()){ 
             if(tokenizer.nextToken().equals(PATH)){ 
                  break; 
             } 
        } 
        if(tokenizer.hasMoreElements()){ 
             name = tokenizer.nextToken(); 
        } 
        return name; 
   }
	
}
