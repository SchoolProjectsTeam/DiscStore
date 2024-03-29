package logic.business.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;

import logic.business.core.Worker;
import logic.util.PositionValue;
import logic.util.PublicScholarDegree;
import logic.util.ScholarDegreeValue;

public class HRController {
	private ArrayList<Worker> workersList;
	private Worker manager;
	private Date managerStartDate;
	
	//Builders
	public HRController(String name, String lastName, String ci, ScholarDegreeValue scholarDegree, ArrayList<Worker> workerList)
	{
		this.workersList = workerList;
		this.manager = new Worker(name, lastName, ci, 0, PositionValue.manager, scholarDegree);
		managerStartDate = Calendar.getInstance().getTime();
		workersList.add(manager);
		workerList.add(new Worker("admin", "admin", "admin", 9999, PositionValue.manager, ScholarDegreeValue.superior));
	}
	public HRController(ArrayList<Worker> workersList)
	{
		this.workersList = new ArrayList<Worker>(workersList);
		this.manager = new Worker(workersList.get(0));
	}
	
	//Methods
	public void changeManager(int newManagerPosition){
		Worker actualManager = this.manager;
		Worker newManager = this.workersList.get(newManagerPosition);
		actualManager.setPosition(PositionValue.dependent);
		newManager.setPosition(PositionValue.manager);
		actualManager.setWorkerID(workersList.get(newManagerPosition).getWorkerID());
		newManager.setWorkerID(0);
		this.manager = newManager;
		managerStartDate = Calendar.getInstance().getTime();
		sortWorkersList();
	}
	
	public void hireWorker(String name, String lastName, String ci, PositionValue position, ScholarDegreeValue scholarDegree)
	{
		Worker hiredWorker = new Worker(name, lastName, ci, generateID(), position, scholarDegree);
		workersList.add(hiredWorker.getWorkerID(), hiredWorker);
	}
	
	public void hireWorker(String name, String lastName, String ci, PositionValue position, PublicScholarDegree scholarDegree)
	{
		HashMap<PublicScholarDegree, ScholarDegreeValue> hash = new HashMap<PublicScholarDegree, ScholarDegreeValue>();
		hash.put(PublicScholarDegree.B�sico, ScholarDegreeValue.basic);
		hash.put(PublicScholarDegree.Medio_Superior, ScholarDegreeValue.halfSuperior);
		hash.put(PublicScholarDegree.Superior, ScholarDegreeValue.superior);
		Worker hiredWorker = new Worker(name, lastName, ci, generateID(), position, hash.get(scholarDegree));
		workersList.add(hiredWorker.getWorkerID(), hiredWorker);
	}
	
	public void fireWorker(int ID)
	{
		for(int i = 0; i < workersList.size(); i++){
			Worker analizedWorker = workersList.get(i);
			if(analizedWorker.getWorkerID() == ID){
				workersList.remove(i);
			}
		}
	}
	
	private void sortWorkersList()
	{
		Collections.sort(workersList, new Comparator<Worker>() {
			@Override
			public int compare(Worker wrk1, Worker wrk2) {
				// TODO Auto-generated method stub
				return wrk1.getWorkerID().compareTo(wrk2.getWorkerID());
			}
		});
	}

	private Integer generateID()
	{
		int ID = 0;
		if(this.isContinous()){
			ID = workersList.size();
		}
		else{
			ID = findMissingID();
		}
		return ID;
	}
	
	private boolean isContinous()
	{
		boolean continous = true;
		
		for(int i = 0; i < workersList.size(); i++)
		{
			if(i != workersList.get(i).getWorkerID()){
				continous = false;
			}
		}
		return continous;
	}
	
	private int findMissingID(){
		int ID = 0;
		boolean finded = false;
		for(int i = 0; i < workersList.size() && !finded; i++){
			if(i != workersList.get(i).getWorkerID()){
				ID = workersList.get(i - 1).getWorkerID() + 1;
				finded = true;
			}
		}
		return ID;
	}
	
	public Worker getManager(){
		return manager;
	}
	
	public ArrayList<String> getWorkers(){
		ArrayList<String> workersNames = new ArrayList<String>();
		for(int i = 0; i < workersList.size(); i++){
			workersNames.add(workersList.get(i).getName() + " " + workersList.get(i).getLastName());
		}
		return workersNames;
	}
	
	public ArrayList<Worker> findWorkers(String critery){
		ArrayList<Worker> workers = new ArrayList<Worker>();
		for(Worker workerI : workersList){
			if(workerI.getName().contains(critery) && workerI.getPosition() != PositionValue.manager){
				workers.add(workerI);
			}
			if(workerI.getLastName().contains(critery) && workerI.getPosition() != PositionValue.manager){
				boolean exist = false;
				for(Worker workerJ : workers){
					if(workerJ.getWorkerID().equals(workerI.getWorkerID())){
						exist = true;
					}
				}
				if(!exist){
					workers.add(workerI);
				}
			}
		}
		return workers;
	}
	
	public String getDate(){
		return managerStartDate.toString();
	}
}
