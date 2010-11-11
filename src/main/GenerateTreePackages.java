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

import utils.PathAndFileNames;
import workers.CommonTreePackage;

/**
 * This class reads all java source files in the folder 
 * PathAndFileNames.PROJECT_SOURCES_PATH
 * and generates a collection of CommonTreePackages 
 */
public class GenerateTreePackages {

	private BufferedInputStream inputStream;
	private JavaLexer lexer;
	private JavaParser parser;
	
	public Collection<CommonTreePackage> generate() {
		File parent = new File(PathAndFileNames.PROJECT_SOURCES_PATH);
		
		return searchInFolder(parent);
	}
	
	private Collection<CommonTreePackage> searchInFolder(File parent) {
		Collection<CommonTreePackage> trees = new HashSet<CommonTreePackage>();
		File[] files = parent.listFiles();
		
		if(files == null){
			try {
				if(!parent.getName().endsWith(".java")) {
					return trees;
				}
				
				trees.add(generateTreeFromFile(parent));
			} catch (Exception e) { System.err.println("Error: " + e.getMessage());}
		} else{
			for(File file : files){
				trees.addAll(searchInFolder(file));
			}
		}
		
		return trees;
	}

	private CommonTreePackage generateTreeFromFile(File parent) throws Exception {
		this.inputStream = new BufferedInputStream(new FileInputStream(parent));
		this.lexer = new JavaLexer(new ANTLRInputStream(this.inputStream ));
		this.parser = new JavaParser(new CommonTokenStream(this.lexer));
		CommonTree tree = (CommonTree) (this.parser.compilationUnit().getTree());
		return new CommonTreePackage(tree, getProjectName(parent), parent);
	}

	private String getProjectName(File parent) { 
        String path = parent.getAbsolutePath(); 
        String name = ""; 
        StringTokenizer tokenizer = new StringTokenizer(path, "/"); 
        
        while(tokenizer.hasMoreTokens()) { 
             if(tokenizer.nextToken().equals(PathAndFileNames.PROJECT_SOURCES_PATH)) { 
                  break; 
             } 
        } 
        
        if(tokenizer.hasMoreElements()) { 
             name = tokenizer.nextToken(); 
        } 
        
        return name; 
   }
	
}
