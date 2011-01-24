/**
 * @author Christian Wellenbrock
 * @author Florian Simon
 * @author JŸrgen Walter
 * @author Stefan Kober
 * Teams 09, 10
 *
 * This code has been developed during the winter term 2010-2011 at the
 * Karlsruhe Institute of Technology (KIT), Germany.
 * It is part of a project assignment in the course
 * "Multicore Programming in Practice: Tools, Models, and Languages".
 * Project director/instructor:
 * Dr. Victor Pankratius (pankratius@ipd.uka.de)
**/
package parsing;

import java.io.File;
import java.util.List;

import org.antlr.runtime.tree.CommonTree;

import utils.Utils;
import database.Scope;

/**
 * This class implements a data structure to store a CommonTree and more additional 
 * information like the file the tree was created from.
 */
public class TreePackage extends CommonTree implements Comparable<TreePackage> {
	
	private File file;
	private String name;
	private CommonTree tree;
	private Scope scope;
			
	public TreePackage(CommonTree t, String p, File f, Scope scope) {
		this.tree = t;
		this.name = p;
		this.file = f;
		this.scope = scope;
	}
	
	public TreePackage(CommonTree t, TreePackage p, Scope scope) {
		this.tree = t;
		this.name = p.name;
		this.file = p.file;
		this.scope = scope;
	}
	
	public Scope getScope(){
		return this.scope;
	}
	
	/**
	 * Returns the name of the java construct from which the package was created.
	 * 
	 * @return name
	 */
	public String getName(){
		if(tree == null){
			return name;
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
		return this.name;
	}

	public File getFile() {
		return file;
	}
	
	public void printTree(){
		printTree(this.tree, "");
	}

	private void printTree(CommonTree tree, String string) {
		if(tree == null){
			return;
		}
		
		System.out.println(string + tree.getText());

		List<CommonTree> children = Utils.castList(CommonTree.class, tree.getChildren());
		for(CommonTree child : children) {
			printTree(child, string + "  ");
		}
	}

	@Override
	public int compareTo(TreePackage treePackage) {
		return name.compareTo(treePackage.name);
	}
}
