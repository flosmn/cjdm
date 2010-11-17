package main;

import java.io.File;
import org.antlr.runtime.tree.CommonTree;

/**
 * This class implements a data structure to store a CommonTree and more additional 
 * information like the file the tree was created from.
 */
public class TreePackage extends CommonTree{
	
	private File file;
	private String projectName;
	private CommonTree tree;
			
	public TreePackage(CommonTree t, String p, File f) {
		this.tree = t;
		this.projectName = p;
		this.file = f;
	}
	
	public TreePackage(CommonTree t, TreePackage p) {
		this.tree = t;
		this.projectName = p.projectName;
		this.file = p.file;
	}
	
	/**
	 * Returns the name of the java construct from which the package was created.
	 * 
	 * @return name
	 */
	public String getName(){
		if(tree == null){
			return projectName;
		}
		
		if(tree.getText().matches("VOID_METHOD_DECL")){
			if(tree.getChild(1) != null){
				return tree.getChild(1).getText();
			}
		}
		
		if(tree.getText().matches(".*METHOD_DECL")){
			if(tree.getChild(2) != null){
				return tree.getChild(2).getText();
			}
		}
		
		if(tree.getText().matches("class")){
			if(tree.getChild(1) != null){
				return tree.getChild(1).getText();
			}
		}
		
		return file.getName();
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

	public File getFile() {
		return file;
	}
}
