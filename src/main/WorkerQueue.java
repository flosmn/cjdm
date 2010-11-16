package main;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.antlr.runtime.tree.CommonTree;

import database.Relation;
import database.Record;
import database.Scope;

import utils.DirtyLittleHelper;
import workers.Worker;

// TODO: replace all LinkedLists by "LinkedHashLists"

public class WorkerQueue {
	private boolean workerInitializationFinished;
	private HashMap<Scope, LinkedList<Worker>> queues = new HashMap<Scope, LinkedList<Worker>>();
	private HashMap<Scope, Relation> relations = new HashMap<Scope, Relation>();
	private CommonTreePackage currentTreePackage;
	
	public WorkerQueue() {
		for (Scope scope : Scope.getInstances()) {
			queues.put(scope, new LinkedList<Worker>());
			relations.put(scope, new Relation(scope));
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
	
	public void doWork(CommonTreePackage treePackage) {
		workerInitializationFinished = true;
		
		currentTreePackage = treePackage;
		
		traverse(treePackage.getTree());
	}

	private void traverse(CommonTree parent) {
		List<CommonTree> children = DirtyLittleHelper.castList(CommonTree.class, parent.getChildren());
		
		for (CommonTree child : children) {
			if (child.getText().equals("class")) {
				process(new CommonTreePackage(child, currentTreePackage), Scope.CLASS);
			}
			
			if (child.getText().matches(".*METHOD_DECL")) {
				process(new CommonTreePackage(child, currentTreePackage), Scope.METHOD);

				continue;
			}

			traverse(child);
		}
	}

	public void exportResults() {
	//	System.out.println(relations.get(Scope.CLASS));
	//	relations.export();
	}
	
	private void process(CommonTreePackage treePackage, Scope scope) {
		Record record = relations.get(scope).newRecord();
		
		for (Worker worker : queues.get(scope)) {
			record.setValueForAttribute(worker.doWork(treePackage), worker.getAttributeName());
		}

		relations.get(scope).add(record);
	}
}
