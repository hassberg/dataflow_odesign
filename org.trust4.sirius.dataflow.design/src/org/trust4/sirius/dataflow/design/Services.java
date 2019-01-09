package org.trust4.sirius.dataflow.design;

import java.util.*;

import org.eclipse.emf.ecore.EObject;


import edu.kit.ipd.sdq.dataflow.systemmodel.*;
import edu.kit.ipd.sdq.dataflow.systemmodel.System;

/**
 * The services class used by VSM.
 */
public class Services {
	public String operationNumber(OperationCall call) {
		Operation callee = call.getCallee();
		Caller caller = call.getCaller();
		int callSize = caller.getCalls().size();
		
		for(int i=0; i<callSize; i++) {
			if(caller.getCalls().get(i).getCallee().equals(callee)) {
				return Integer.toString(i+1);
			}
		}
		return "0";
	}    
	public String getPreCallAssignmen(OperationCall call) {
		String returnValue ="";
		for(VariableAssignment preCallStateVar : call.getPreCallStateDefinitions()) {
			returnValue += preCallStateVar.getVariable().getName() + "\n";
		}
		
		return returnValue;
	}
	
	public String getReturnValueAssignment(OperationCall call) {
		String returnValue="";
		for (VariableAssignment var : call.getCallee().getReturnValueAssignments()) {
			returnValue += var.toString() + "\n";
		}
		
		return returnValue;
	}
	
	public Collection<OperationCall> hasReturnValueAssignment(System system) {
		Collection<OperationCall> available = new Vector<OperationCall>();
		for (Operation operation : system.getOperations()) {
			for (OperationCall calls : operation.getCalls()) {
				if(getReturnValueAssignment(calls) != "") {
					available.add(calls);
				}
			}
		}
		return available;	
	}
}
