package logic.business.abstractions;

import java.util.ArrayList;

public interface IContainerManager {
	public boolean addItem(IProduct item);
	public void removeItem(IProduct item);
	public void removeItem(int index);
	public double calculateCost();
	/*public ArrayList<IProduct> search(String critery);*/
	public void sell();
}
