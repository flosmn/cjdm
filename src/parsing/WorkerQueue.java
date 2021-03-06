/**
 * @author Christian Wellenbrock
 * @author Florian Simon
 * @author J�rgen Walter
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

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.antlr.runtime.tree.CommonTree;

import utils.Utils;
import workers.Worker;
import database.Database;
import database.Record;
import database.Relation;
import database.Scope;

public class WorkerQueue {
	private boolean workerInitializationFinished;
	private HashMap<Scope, LinkedList<Worker>> queues = new HashMap<Scope, LinkedList<Worker>>();
	private HashMap<Scope, Relation> relations = new HashMap<Scope, Relation>();
	private HashMap<Scope, Integer> currentIDs = new HashMap<Scope, Integer>();
	private Database database;
	
	public WorkerQueue(Database database) {
		this.database = database;
		
		for (Scope scope : Scope.getInstances()) {
			queues.put(scope, new LinkedList<Worker>());
			relations.put(scope, new Relation(database, scope));
			currentIDs.put(scope, new Integer(0));
		}
	}
	
	public void addWorker(Worker worker) {
		if (workerInitializationFinished) {
			System.out.println("should not add workers anymore");
			return;
		}
		
		Scope scope = worker.getScope();
		LinkedList<Worker> queue = queues.get(scope);
		queue.add(worker);
		relations.get(worker.getScope()).addAttribute(worker.getAttributeName());
	}
	
	public void doWork(TreePackage projectPackage) {
		workerInitializationFinished = true;

		if (relations.get(Scope.PROJECT).hasRowNamed(projectPackage.getName())) {
			System.out.println("Skipping project: " + projectPackage.getName());
			return;
		}
		
		System.out.println("Processing project: "+projectPackage.getName());
		
		process(projectPackage);
		
		Collection<TreePackage> treePackagesOfProject = 
			(new TreePackageGenerator()).generateTreePackagesForProject(projectPackage.getFile());
		Collections.sort((List<TreePackage>) treePackagesOfProject);
		for (TreePackage treePackage : treePackagesOfProject){
			traverse(treePackage);
		}
	}

	private void traverse(TreePackage treePackage) {
		List<CommonTree> children = Utils.castList(CommonTree.class, treePackage.getTree().getChildren());
		
		for (CommonTree child : children) {
			if (child.getText().equals("class") && child.getChildCount() > 0) {
				process(new TreePackage(child, treePackage, Scope.CLASS));
			}
			
			if (child.getText().equals("interface")) {
				continue;
			}
			
			if (child.getText().matches(".*METHOD_DECL")) {
				process(new TreePackage(child, treePackage, Scope.METHOD));

				continue;
			}

			traverse(new TreePackage(child, treePackage, null));
		}
	}
	
	private void process(TreePackage treePackage) {
		Scope scope = treePackage.getScope();
		Record record = relations.get(scope).newRecord();
		int ID = newID(scope);
		int parentID = currentIDs.get(scope.getParent());
		
		record.setID(ID);
		record.setParentID(parentID);
		
		// TODO: remove if when project has own treePackage
		if (treePackage != null) {
			record.setName(treePackage.getName());
			
			for (Worker worker : queues.get(scope)) {
				record.setValueForAttribute(worker.doWork(treePackage), worker.getAttributeName());
			}
		}
			
		relations.get(scope).add(record);
	}

	private int newID(Scope scope) {
		int currentID = currentIDs.get(scope);
		++currentID;
		currentIDs.put(scope, currentID);
		return currentID;
	}

	public void dropViews() {
		for (Relation relation : relations.values()) {
			relation.dropView();
		}
	}

	public void dropTables() {
		dropTable(Scope.METHOD);
		dropTable(Scope.CLASS);
		dropTable(Scope.PROJECT);
	}

	private void dropTable(Scope scope) {
		relations.get(scope).dropTable();
	}

	public void createTables() {
		createTable(Scope.PROJECT);
		createTable(Scope.CLASS);
		createTable(Scope.METHOD);
	}

	private void createTable(Scope scope) {
		currentIDs.put(scope, relations.get(scope).createTable());
	}

	public void createViews() {
		HashMap<Scope, String> columns = new HashMap<Scope, String>();
		HashMap<Scope, String> groupedColumns = new HashMap<Scope, String>();
		HashMap<Scope, String> viewQueries = new HashMap<Scope, String>();
		
		for (Scope scope : Scope.getInstances()) {
			columns.put(scope, "");
			groupedColumns.put(scope, "");
			
			LinkedList<Worker> methodWorkers = queues.get(scope);
			for (Worker methodWorker : methodWorkers) {
				String attributeName = methodWorker.getAttributeName();
				String aggregator = methodWorker.getAggregator().toString();
				
				columns.put(scope, combineColumns(columns.get(scope), attributeName));
				groupedColumns.put(scope, combineColumns(groupedColumns.get(scope), aggregator + "(" + attributeName + ") AS " + attributeName));
			}
		}
		
		// FIXME: hard coded scope names
		
		viewQueries.put(Scope.METHOD,
				" CREATE VIEW method_view AS" +
				" SELECT " + combineColumns(
						"project.name AS project_name",
						"class.name AS class_name",
						"method.name AS method_name",
						columns.get(Scope.METHOD)) +
				" FROM project INNER JOIN class" +
				" ON project.ID = class.parentID JOIN method" +
				" ON class.ID = method.parentID");
		
		viewQueries.put(Scope.CLASS,
				" CREATE VIEW class_view AS" +
				" SELECT " + combineColumns(
						"MIN(project.name) AS project_name",
						"class.name AS class_name",
						columns.get(Scope.CLASS),
						groupedColumns.get(Scope.METHOD)) +
				" FROM project INNER JOIN class" +
				" ON project.ID = class.parentID JOIN method" +
				" ON class.ID = method.parentID" +
				" GROUP BY class.ID");
		
		String queryProjectMethod =
			" SELECT " + combineColumns(
					"project.name AS project_name",
					groupedColumns.get(Scope.METHOD)) +
			" FROM project INNER JOIN class" +
			" ON project.ID = class.parentID JOIN method" +
			" ON class.ID = method.parentID" +
			" GROUP BY project.ID";
		
		String queryProjectClass = 
			" SELECT " + combineColumns(
					"project.name AS pname",
					columns.get(Scope.PROJECT),
					groupedColumns.get(Scope.CLASS)) +
			" FROM project INNER JOIN class" +
			" ON project.ID = class.parentID" +
			" GROUP BY project.ID";
		
		
		viewQueries.put(Scope.PROJECT,
				" CREATE VIEW project_view AS (" +
				" SELECT " + combineColumns(
						"project_name",
						columns.get(Scope.PROJECT),
						groupedColumns.get(Scope.CLASS),
						groupedColumns.get(Scope.METHOD)) +
				" FROM ( " + queryProjectMethod + " )" +
				" INNER JOIN (" + queryProjectClass + ")" +
				" ON project_name = pname" +
				" GROUP BY project_name )");
		
		for (Scope scope : Scope.getInstances()) {
			try {
				database.update(viewQueries.get(scope));
			} catch (Exception exception) {
				System.out.println(exception.getMessage());
			}
		}
	}

	private String combineColumns(String leftColumns, String ... rightColumns) {
		for (String columns : rightColumns) {
			if (!leftColumns.equals("") && !columns.equals("")) {
				leftColumns += ", ";
			}
			
			leftColumns += columns;
		}
		
		return leftColumns;
	}
}
