package main;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.antlr.runtime.tree.CommonTree;

import utils.DirtyLittleHelper;
import workers.Worker;
import database.Database;
import database.Record;
import database.Relation;
import database.Scope;

// TODO: replace all LinkedLists by "LinkedHashLists"

public class WorkerQueue {
	private boolean workerInitializationFinished;
	private HashMap<Scope, LinkedList<Worker>> queues = new HashMap<Scope, LinkedList<Worker>>();
	private HashMap<Scope, Relation> relations = new HashMap<Scope, Relation>();
	
	public WorkerQueue(Database database) {
		for (Scope scope : Scope.getInstances()) {
			queues.put(scope, new LinkedList<Worker>());
			relations.put(scope, new Relation(database, scope));
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
	
	public void doWork(File project) {
		workerInitializationFinished = true;
		
		// TODO: process project scope
		// process(new CommonTreePackage(null, path, null), Scope.PROJECT);
		
		Collection<TreePackage> treePackagesOfProject = 
			(new TreePackageGenerator()).generateTreePackagesForProject(project);
		
		for (TreePackage treePackage : treePackagesOfProject){
			traverse(treePackage);
		}
	}

	private void traverse(TreePackage treePackage) {
		List<CommonTree> children = DirtyLittleHelper.castList(CommonTree.class, treePackage.getTree().getChildren());
		
		for (CommonTree child : children) {
			if (child.getText().equals("class")) {
				process(new TreePackage(child, treePackage), Scope.CLASS);
			}
			
			if (child.getText().matches(".*METHOD_DECL")) {
				process(new TreePackage(child, treePackage), Scope.METHOD);

				continue;
			}

			traverse(new TreePackage(child, treePackage));
		}
	}
	
	private void process(TreePackage treePackage, Scope scope) {
		Record record = relations.get(scope).newRecord();
		
		for (Worker worker : queues.get(scope)) {
			record.setValueForAttribute(worker.doWork(treePackage), worker.getAttributeName());
		}

		relations.get(scope).add(record);
	}
}
