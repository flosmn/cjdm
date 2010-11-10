package main;

import java.util.LinkedList;

import database.Database;
import database.Record;

import workers.CommonTreePackage;
import workers.Worker;

public class WorkerQueue {
	private boolean workersFinished;
	private LinkedList<Worker> workers = new LinkedList<Worker>();
	private Database database = new Database();
	
	public void addWorker(Worker worker) {
		if (workersFinished) {
			System.out.println("should not add workers anymore");
			return;
		}
		
		workers.add(worker);
		database.addAttribute(worker.getAttributeName());
	}
	
	public void doWork(CommonTreePackage treePackage) {
		workersFinished = true;
		
		Record record = database.newRecord();
		
		for (Worker worker : workers) {
			record.setValueForAttribute(worker.doWork(treePackage), worker.getAttributeName());
		}

		database.add(record);
	}

	public void exportResults() {
		database.export();
	}
	
}
