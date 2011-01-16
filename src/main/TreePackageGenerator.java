package main;

import grammar.JavaLexer;
import grammar.JavaParser;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Collection;
import java.util.LinkedList;
import java.util.StringTokenizer;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.tree.CommonTree;

import utils.PathAndFileNames;
import database.Scope;

/**
 * This class reads all java source files in the folder 
 * PathAndFileNames.PROJECT_SOURCES_PATH
 * and generates a collection of CommonTreePackage.
 */
public class TreePackageGenerator {

	private BufferedInputStream inputStream;
	private JavaLexer lexer;
	private JavaParser parser;
	
	/**
	 * Generate a collection of tree packages. One tree package for each java source file
	 * in the project represented by projectTreePackage.
	 * 
	 * @param projectRoot path where the project is stored
	 * @return treePackages
	 */
	public Collection<TreePackage> generateTreePackagesForProject(
			File projectRoot) {
		if(projectRoot == null) {
			System.out.println("File of project tree package must not be null");
			return null;
		}
		
		Collection<TreePackage> treePackages = new LinkedList<TreePackage>();
		Collection<File> sourceFiles = getAllSourcesInFolder(projectRoot);
		for(File file : sourceFiles) {
			try {
				treePackages.add(generateTreeFromFile(file));
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		
		return treePackages; 
	}

	private Collection<File> getAllSourcesInFolder(File parent) {
		Collection<File> allSourceFiles = new LinkedList<File>();
		File[] files = parent.listFiles();
		
		if(files == null){
			try {
				if(!parent.getName().endsWith(".java")) {
					return allSourceFiles;
				}
				
				allSourceFiles.add(parent);
			} catch (Exception e) { 
				System.err.println("Error: " + e.getMessage());
			}
		} else{
			for(File file : files){
				allSourceFiles.addAll(getAllSourcesInFolder(file));
			}
		}
		
		return allSourceFiles;
	}

	private TreePackage generateTreeFromFile(File file) throws Exception {
		this.inputStream = new BufferedInputStream(new FileInputStream(file));
		this.lexer = new JavaLexer(new ANTLRInputStream(this.inputStream ));
		this.parser = new JavaParser(new CommonTokenStream(this.lexer));
		CommonTree tree = (CommonTree) (this.parser.compilationUnit().getTree());
		return new TreePackage(tree, getProjectName(file), file, null);
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

	public Collection<TreePackage> generateProjectPackages() {
		Collection<TreePackage> projectPackages = new LinkedList<TreePackage>();
		File[] projects = (new File(PathAndFileNames.PROJECT_SOURCES_PATH)).listFiles();
		
		for(File file : projects){
			if(!(file.getName().equals("data"))){
				projectPackages.add(new TreePackage(null, file.getName(), file, Scope.PROJECT));
			}
		}
		
		return projectPackages;
	}
}

