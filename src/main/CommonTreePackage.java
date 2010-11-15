package main;

import java.io.File;
import java.util.List;

import org.antlr.runtime.tree.CommonTree;

import utils.DirtyLittleHelper;

/**
 * This class implements a data structure to store a CommonTree and more additional 
 * information like the file the tree was created from.
 */
public class CommonTreePackage extends CommonTree{
	
	// the java source file from which the AST was created
	private File generatedFromFile;
	private String projectName;
	private CommonTree tree;
			
	public CommonTreePackage(CommonTree t, String p, File f) {
		this.tree = t;
		this.projectName = p;
		this.generatedFromFile = f;
	}
	
	/**
	 * Returns the name of the java source file from which the tree was created.
	 * 
	 * @return the name of the java source file from which the tree was created.
	 */
	public String getFileName(){
		return this.generatedFromFile.getName();
	}
	
	/**
	 * Returns the tree of the package.
	 *  
	 * @return the tree of the package.
	 */
	public CommonTree getTree(){
		return this.tree;
	}
	
	/**
	 * Returns the name of the project of the java source file
	 * from which the tree was created.
	 * 
	 * @return name of the project
	 */
	public String getProjectName(){
		return this.projectName;
	}
}
