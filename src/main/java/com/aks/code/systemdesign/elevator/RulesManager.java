package com.aks.code.systemdesign.elevator;

public interface RulesManager {
	void enqueue(Request request);

	int getNextDestination();
}
